Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2013 and 2014
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'
  
  Scenario: Search books by author
    Given a book with the title 'One good book', written by 'Tim Tomson'
      And another book with the title 'Some other book', written by 'Tim Tomson'
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone'
    When the customer searches for books published by 'Tim Tomson'
    Then 2 books should have been found
      And Book 1 should have the title 'One good book'
      And Book 2 should have the title 'Some other book'
  
  Scenario: Search books by title
    Given I have the following books in the store
      | title                                 | author      |
      | The Devil in the White City           | Erik Larson |
      | The Devil, the Witch and the Wardrobe | C.S. Lewis  |
      | In the Garden of Beasts               | Erik Larson |
    When the customer searches for books with title 'The Devil' 
    Then 2 books should have been found
      And Book 1 should have the title 'The Devil in the White City'
      And Book 2 should have the title 'The Devil, the Witch and the Wardrobe'
