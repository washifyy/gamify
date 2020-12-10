Feature: badges test

  Background:
    Given there is a Gamify server
    Then I create the credentials payload registration
    Then I POST the registration payload to the api.register endpoint
    Then I receive a 201 status code
    And I read the registrationResponse payload
    And I read the token payload as the token property of the registrationResponse payload
    And I authenticate with token api key
    Then I create the category payload category with name cat1
    Then I PUT the category payload to the api.categories.cat1 endpoint
    Then I receive a 204 status code

  Scenario: I can create a badge, update it and delete it
    When I create the badge badge1 linked to category cat1
    Then I PUT the badge1 badge to the api.badges endpoint
    Then I receive a 204 status code
    When I just GET from the badges endpoint
    Then I receive a 200 status code
    When I GET badge1 from the badges endpoint
    Then I receive a 200 status code
    When I DELETE badge1 with api.badges.delete endpoint
    Then I receive a 204 status code
