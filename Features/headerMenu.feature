Feature: Retrive all Sub-Menu items.
  Scenario: Retrive all Sub-Menu items under Living and Store in a List.
  Given Navigate to Homepage of the Urban Ladder Application
  When Living Menu is hovered
  And all submenu items are collected in a list
  Then display the items

