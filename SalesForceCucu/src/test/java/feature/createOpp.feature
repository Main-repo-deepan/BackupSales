Feature: Create oppurtunity Functionality 


Scenario Outline: Createopp 
And Navigate to opputunity tab 
And Create new oppurtunity with title as <entertitle>
When click Save 
Then  Verify Oppurtunity name 

Examples:
|entertitle|
|'SalesForce Automation By Barathi'|
|'SalesForce Automation By Priya'|
