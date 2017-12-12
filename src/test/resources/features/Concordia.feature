Feature: Concordia website Menu Test
  #the step under Background will run at the beginning of each scenario
  Background:
    Given I open concordia website

  @ConcordiaAbout
  Scenario: click the about  menu go to the about page
    When I click the about menu
    Then the about page come out

  @ConcordiaStudyHere
  Scenario: Concordia website the Study Here link under the About Menu
    When I click the about menu
    And I click the study here link
    Then the admission page is showed

  @GraduateUnderAdmission
  Scenario: move over the Admission menu and click the graduate link
    When I move over the admission link
    And I click the graduate link
    Then the graduate page is showed