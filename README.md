# restful-booker-API

This project is used to automate API testing for restful-booker website. It runs test suite for testing happy scenario using CURD and bad scenarios using OOP design for testing AUTH and BOOKING services. Bug reports are generated with Extent Reports
## The main Frameworks included in the project:

 - Rest Assured
 - TestNG
 - Maven
 - Extent Reports
 - Java

## Project Design:
•	CURD for happy scenarios with dependent tests.
•	Service Object Model for bad scenarios. All tests are independent.
•	Behavior Driven Development
•	Extent Reports


    

## How to run the project main test cases locally:

- Please check configuration is set to "suiteconfig".
- Can find the test cases in the src/test/java folder apiTest package. 
- Can find the test suite for all the main practice test cases under root in "testing.xml" file.
- To start the execution, please right click on testing.xml file and click Run.
- After executing, you can find Extent Report under extent-reports right click "extent-report.html" open in browser.
## Future work
- •	Run Tests in parallel.
- Add Load testing.
- Add Fuzz testing.