#Feature: GemBook Login Page Features
#
#  Scenario Outline: Validate GemBook url,login page logo and sign in button
#    When User is on correct url <Url>
#    And Title is "Gembook"
#    Then Logo is displayed
#    And Sign in button is enabled
#    Examples:
#      | Url                                    |
#      | https://gembook.geminisolutions.com/#/ |
#
#  Scenario: Login failed pop-up test
#    When User double click on Sign-in button
#    Then Login failed pop up will be displayed
#
#  Scenario Outline: Sign In Test
#    When User clicks on sign-in button
#    And User enters gmail <gmail> and password <password> and click on next button
#    And User clicks on No on stay signed in button
#    Then Login will be successful and homepage will be displayed.
#    Examples:
#      | gmail                               | password   |
#      |  |  |
#
#
#
#
#
#
