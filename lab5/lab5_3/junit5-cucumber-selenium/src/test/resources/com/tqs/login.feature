Feature: Buy a flight

  Scenario: Buy a flight
    When I navigate to "https://blazedemo.com/"
      And I choose a departure city with name "Boston"
      And I choose a destination city with name "Buenos Aires"
      And I click Find Flights
    Then I should see the message "Flights from Boston to Buenos Aires:"
      And I click Choose This Flight
      And my name is "Dani", my address is "McDonalds" and my city is "Aveiro"
      And I click Purchase This Flight
    Then I should see the successful message "Thank you for your purchase today!"