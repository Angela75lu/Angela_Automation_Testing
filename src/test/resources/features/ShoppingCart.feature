Feature: Shopping cart on an e-commerce website
  @sign_in
  Scenario: Sign in and sign out
    When I open automationpractice website
    And I sign in
    Then sign out failed due to the wrong username passwords
