Feature: Admin registration
  Scenario: Valid reg check
    Given I open reg page
    And I switch registration form to admin
    And I fill in the admin field login
    And I fill in the admin field password
    And I fill in the admin field confirm password
    And I fill in the admin key field
    And I click Sign up button