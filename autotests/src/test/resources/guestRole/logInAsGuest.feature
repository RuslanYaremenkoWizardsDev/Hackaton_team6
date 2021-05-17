Feature: Guest ability
  Scenario: Guest login from reg page
    Given I open reg page
    And I click log_as_guest_button
    And I check that tournament page opened

