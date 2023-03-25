Feature: GemBook Login Page Features

  Scenario Outline: Validate GemBook url,login page logo and sign in button
    When User is on correct url <Url>
    And Title is "Gembook"
    Then Logo is displayed
    And Sign in button is enabled
    Examples:
      | Url                                    |
      | https://gembook.geminisolutions.com/#/ |

  Scenario: Login failed pop-up test
    When User double click on Sign-in button
    Then Login failed pop up will be displayed

