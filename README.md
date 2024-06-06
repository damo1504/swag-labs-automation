# Swag Labs Automation

This project contains automated tests for the Swag Labs e-commerce website using Selenium WebDriver and TestNG. The tests are written in Java and managed using Maven.

## Project Structure

swag-labs-automation/
├── .github/ # GitHub Actions workflows
│ └── workflows/
│ └── maven.yml # GitHub Actions workflow for CI
├── src/
│ ├── main/
│ │ └── java/
│ │ └── com/
│ │ └── saucedemo/
│ │ └── swaglabs/
│ │ ├── listeners/ # Test listeners for reporting
│ │ ├── models/ # Data models
│ │ ├── pages/ # Page Object Model classes
│ │ └── reporter/ # Extent reports setup
│ └── test/
│ └── java/
│ └── com/
│ └── saucedemo/
│ └── swaglabs/
│ └── tests/ # Test classes
├── target/ # Compiled classes and test reports
│ └── surefire-reports/ # TestNG reports
├── pom.xml # Maven configuration file
└── README.md # Project documentation


## Prerequisites

- Java 11 or higher
- Maven
- A web browser (Chrome, Firefox, Edge)

## Running the Tests

To run the tests, use the following Maven command:

```sh
mvn clean test
