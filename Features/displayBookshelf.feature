Feature: Display Bookshelf

  Scenario: Search BookShelves and Display the Results
    Given Bookshelf is selected from the submenu
    When Price Range is set below Rs15000
    And Select Storage Type (open)
    And Exclude out of stock
    And Sort By Recommended
    Then Print all details in console output
