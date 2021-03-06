package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.Configuration;
import ch.heigvd.gamify.api.dto.Registration;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class ApiDefs implements En {

  public ApiDefs(Environment environment) {

    // AUTHENTICATION
    Then("I authenticate with {word} api key", (String named) -> {
      var token = environment.getClient().getPayload(named);
      Configuration.getDefaultApiClient().setApiKey((String) token);
    });

    Then("I authenticate with the {word} credentials payload", (String named) -> {
      var token = (Registration) environment.getClient().getPayload(named);
      Configuration.getDefaultApiClient().setUsername(token.getUsername());
      Configuration.getDefaultApiClient().setPassword(token.getPassword());
    });

    Then("I don't authenticate", () -> {
      Configuration.getDefaultApiClient().setApiKey("");
      Configuration.getDefaultApiClient().setUsername("");
      Configuration.getDefaultApiClient().setPassword("");
    });
  }
}
