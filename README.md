The test cases are implemented using Page Object Pattern including logging (Using Log4J2) 
and reporting (using Allure)

Tests have ability:
to be run in 2 threads
to have browser resolution passed
to have thread count passed

To run tests use these commands:

1) To run default suite (which is "allTests.xml"): 
   **`mvn clean test`** 
or 
   **`mvn clean test -Dsuite=allTests.xml`**

2) Tests can be run in Chrome, Safari and FireFox. 
Default browser is Chrome. To run tests in another browser, use commands:
   **mvn clean test -Dbrowser= ...(your params)**

3) Default browser resolution parameters are browserHeight=1440, browserWidth=900.
To run tests with your browser resolution parameters:
   **mvn clean test -DbrowserHeight=... (your params) -DbrowserWidth=... (your params)**

4) Default thread count quantity is 2. To run with your parameters:
   **mvn test -DthreadCount=... (your params)**