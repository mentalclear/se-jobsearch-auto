Feature: As I user
  I want to be able to open indeed.com home page
  So I can start using it

  Scenario: User opens home page
    Given User has started Chrome browser
    When User navigates to "https://indeed.com"
    Then Page title should be "Job Search | Indeed"
    And "What" input field should be present
    And "Where" input field should be present
    And Find jobs button should be present