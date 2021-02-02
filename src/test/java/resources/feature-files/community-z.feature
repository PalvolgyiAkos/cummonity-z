Feature: Community-Z search page

  Background:
    Given the home page is open
    And the COMMUNITIES header is clicked

  Scenario: Check search without results
    Given the Cookie disclaimer is closed
    When the search field is filled in with 'question'
    Then the No Results Found msg should be shown

  Scenario: Check search with results
    When the search field is filled in with 'Top'
    Then wait the search and the '.evnt-card-cell.evnt-info-cell' is should contains the 'top'

  Scenario Outline: Check search with filter and results
    When the search field is filled in with '<input>'
    And click on the filters menu
    And click on the '<options>' drop down
    And the text field is filled in with '<parameter>' at '<options>'
    And the first check-box is clicked on '<parameter>' at '<options>'
    And the Show results button is clicked
    Then the 'evnt-info-cell' field is should contains the '<input>'
    Examples:
      | input | parameter | options  |
      | epam  | Back end  | Category |
      | epam  | Hungary   | Location |

    Scenario: Check side list should be shown
      Given click on the filters menu
      And click on the 'Location' drop down
      And the text field is filled in with 'Belarus' at 'Location'
      And click on the drop down menu
      Then the side drop down menu should be shown
