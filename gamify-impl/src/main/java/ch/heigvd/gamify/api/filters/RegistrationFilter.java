package ch.heigvd.gamify.api.filters;

import ch.heigvd.gamify.repositories.RegisteredAppRepository;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An implementation of a {@link Filter} that makes sure that proper API keys are used to
 * authenticate users.
 */
public class RegistrationFilter implements Filter {

  private static final String API_KEY_HEADER = "X-API-KEY";

  /**
   * The {@link RegisteredAppRepository} that is used to look at the available registrations for the
   * current user.
   */
  private final RegisteredAppRepository repository;

  public RegistrationFilter(RegisteredAppRepository repository) {
    this.repository = repository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doFilter(
      ServletRequest servletRequest,
      ServletResponse servletResponse,
      FilterChain filterChain
  ) throws IOException, ServletException {
    var req = (HttpServletRequest) servletRequest;
    var res = (HttpServletResponse) servletResponse;

    // Enforce API keys.
    var key = req.getHeader(API_KEY_HEADER);
    if (key == null) {
      res.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    // Retrieve the app.
    var app = repository.findByToken(key);
    if (app.isEmpty()) {
      res.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    // Populate the app for the requests.
    req.getSession().setAttribute("app", app.get());
    filterChain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig filterConfig) {
    // ignored
  }

  @Override
  public void destroy() {
    // ignored
  }
}
