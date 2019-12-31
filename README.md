# BankStatementProcessor
Bank Statement Processor

This application[bank-statement-processor] supports only two file formats i.e. CSV and XML files as input.
This is a REST application which produces response as JSON and also generates a report XML file in the project root path

# Tech Stack:
SpringBoot -- Rest Application

JAXB - XML processing
  
Apache Commons - CSV processing
  
JUnit/Mockito - Unit testing

# To Run:
This is Maven based project . The pom.xml is available in project root directory.
Import pom.xml in your IDE once you cloned the repo or directly clone the repository into the IDE

All statement files(xml/csv) to be processed are kept in "statements" directory in path "bank-statement-processor\src\main\resources\statements" 
This path can be configured using application.properties file.
Run the BankStatementProcessorApplication.java as java application.
Open the following URL in postman or chrome browser.
http://localhost:8080/bank/statement/processor/transactionreport?view=ALL

Applcation will generate reports under root directory with name [dd-MM-yyyy-HH-mm-ss]_report.xml format.

*The request parameter view can accept three values
1:>view = ALL :- This will provide a report of all the transactions
2:>view = VALID  :- This will provide a report of only the Valid transactions
3:>view = INVALID :- This will provide a report of only the Invalid transactions


