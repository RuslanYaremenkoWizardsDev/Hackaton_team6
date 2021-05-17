Feature: Link check
  Scenario: Redirect to auth
    Given I open reg page
    And I click Sign in link
    And I check that auth page opened