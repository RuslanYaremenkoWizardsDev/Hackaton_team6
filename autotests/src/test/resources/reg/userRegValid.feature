Feature: User registration
  Scenario: Valid reg check
    Given I open reg page
    And I fill in field Login
    And I fill in field Password
    And I fill in field Confirm password
    And I click Sign up button