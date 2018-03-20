# Zopa Technical Test  

## Problem Statement
* There is a need for a rate calculation system allowing prospective borrowers to obtain a quote from zopa's pool of lenders
* for 36 month loans.
* This new system should strive to provide as low a rate to the borrower as is possible to ensure that Zopa's quotes are as competitive as they can be against our competitors'.
* And also to provide the borrower with the details of the monthly repayment amount
* and the total repayment amount.

The application should take arguments in the form:  
```
    cmd> [application] [market_file] [loan_amount]  
```

Example: 
```
    cmd> quote.exe market.csv 1500  
```

The application should produce output in the form: 
```
    cmd> [application] [market_file] [loan_amount] 
    Requested amount: £XXXX 
    Rate: X.X% 
    Monthly repayment: £XXXX.XX 
    Total repayment: £XXXX.XX  
```

Example:  
```
    cmd> quote.exe market.csv 1000 
    Requested amount: £1000 
    Rate: 7.0% 
    Monthly repayment: £30.78 
    Total repayment: £1108.10  
```

## Remarks   
- We do not mind what language you chose for your implementation 
- The monthly and total repayment should use monthly compounding interest 
- We will review your code and run it against some other test cases to see how  it handles them 
- If you have any questions then don't hesitate to contact us

## Installation pre-requisites

For running this project we need Java and Maven installed on our machine.

*Its important to install Java 8+ and Maven (3.5.2+)*

- [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org/)

# Installing the code

The code can be installed with the following command (needs to be run in the folder where root pom.xml is):

```
$ git clone https://github.com/gajaharan/qengine
$ cd qengine
$ mvn clean install
```

## Running the App
* Sample test file is located at /src/test/resources/MarketData.csv
From the root path, run the following commands:

`java -jar target/qengine-1.0-SNAPSHOT.jar [path_to_csv_file] [requested_loan_amount]`

e.g. `java -jar target/qengine-1.0-SNAPSHOT.jar src/main/resources/MarketData.csv 1000`

## Running the Tests
```
mvn test
```

## Viewing Jacoco test coverage reports
In the root project after running maven build or maven test then in root folder navigate to
`/target/site/jacoco/index.html`

##Assumptions
- The console application can be adapted to Spring/Spring boot implementation with RESTful interface.
- The models acts as both the model data and controller. This ideally should not be done.
- The lender rates does not change within the given loan term
- TDD used so that unit tests created fails and then fix/implemented the code to fix the test.
- Uses monthly compounding interest.
- Used Jacoco for test coverage.


##Requirements
- [Z-001] CSV Parser                - DONE
- [Z-002] CSV Format Validator
- [Z-003] Input Validator           - DONE
- [Z-004] Lender Model              - DONE
- [Z-005] Loan Quote Model          - DONE
- [Z-006] Lender Service (get all available lenders for quote)  - DONE
- [Z-007] Loan Calculator service - Loan rate                   - DONE
- [Z-008] Loan Calculator service - Monthly repayment           - DONE
- [Z-009] Loan Calculator service - Total repayment             - DONE