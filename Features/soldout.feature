Feature: Let Me Know Form

  Scenario: Fill Let Me Know fields if the product is out of stock
    Given Search "Office Desk" in the Homepage
    When item is Out of Stock
    Then Fill the Let Me Know Form with below detail.
      | email  | harini@gmail.com |
      | number |       9876543210 |
