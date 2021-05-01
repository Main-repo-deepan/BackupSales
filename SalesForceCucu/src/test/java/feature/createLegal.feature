Feature: Create Legal Functionality 


Scenario Outline: CreateLegal
And Navigate to Legal tab 
And Create new Legal with Company as <enterCom> Description<Des> and title as <title> 
And Click status as Active
When Click save
Then Verify alert message 

Examples:
|entercom||Des||enterStatus|
|Testleaf||Salesforce||Active|
|Testleaf||Salesforce||Active|