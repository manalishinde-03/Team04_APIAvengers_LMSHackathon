
@UserLogin
Feature: User Login Module

  @test
  Scenario: User Login Scenario
    Given Admin creates request with valid credentials  
  When Admin calls Post Https method  with valid endpoint
  Then Admin receives 200 OK with auto generated token
