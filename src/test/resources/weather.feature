Feature: Weather API
  Scenario: Weather data for city by ID
    Given city id: 524901
    When we are requesting weather data
    Then lon is: 145.77
    And lat is: -16.92


#******** дописать все тут
