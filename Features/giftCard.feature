#
#@tag2
#Scenario Outline: Title of your scenario outline
#Given I want to write a step with <name>
#When I check for the <value> in step
#Then I verify the <status> in step
#
#Examples:
#| name  | value | status  |
#| name1 |     5 | success |
#| name2 |     7 | Fail    |
Feature: Fill customize Gift Card

  Scenario Outline: Fill customize Gift Card with one invalid input
    Given Select Gift Card Label
    When Select Birthday/Anniversary
    And Choose Amount, Date and Time
    And Fill the details with excel sheet "<SheetName>" and excel row <RowNumber>
    Then Validate the Displayed Details "<Message>"

    Examples: 
      | SheetName | RowNumber | Message |
      | GiftCard  |         0 | Invalid |
      | GiftCard  |         1 | Valid   |
      #| GiftCard  |         2 | Invalid |
