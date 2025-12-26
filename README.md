
# JetAutomation

A test automation framework built with Java, TestNG, and Playwright for testing the Just Eat Takeaway careers website.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Running Tests](#running-tests)
- [Test Configuration](#test-configuration)
- [Dependencies](#dependencies)
- [Features](#features)
- [Contributing](#contributing)

## Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Apache Maven 3.6+**
- **Git** (for version control)

## Project Structure
```

JetAutomation/
├── src/
│   ├── main/
│   └── test/
│       └── java/
│           ├── base/          # Base test classes and setup
│           ├── pages/         # Page Object Model classes
│           ├── tests/         # Test classes
│           └── utilities/     # Utility classes and helpers
├── pom.xml                    # Maven configuration
├── testng.xml                 # TestNG configuration
├── crossBrowserTesting.xml    # Cross-browser testing configuration
└── README.md
```
## Setup and Installation

### 1. Clone the Repository
```
git clone <repository-url>
cd JetAutomation
```
### 2. Install Dependencies
```
mvn clean install
```
### 3. Install Playwright Browsers

After the Maven dependencies are installed, you need to install the Playwright browsers:
```
bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```
Or alternatively, you can install browsers manually:

```
npx playwright install
```


## Running Tests

### Running All Tests

```
mvn test
```


### Running Tests with TestNG XML Configuration

```
mvn test -DsuiteXmlFile=testng.xml
```


### Running Cross-Browser Tests

```
mvn test -DsuiteXmlFile=crossBrowserTesting.xml
```


### Running Specific Test Classes

```
mvn test -Dtest=JobCategorySearchTest
```


### Running Tests with Parameters

```
mvn test -Dbrowser=chrome -Dheadless=false
```


### Running Tests in IDE

1. Right-click on any test class or method in your IDE
2. Select "Run" or "Debug"
3. Alternatively, right-click on `testng.xml` and run it directly

## Test Configuration

### TestNG Configuration

The project uses TestNG for test execution. Configure your test suites in:
- `testng.xml` - Main test configuration
- `crossBrowserTesting.xml` - Cross-browser test configuration

### Browser Configuration

Tests can be run on different browsers by setting system properties:
- `browser` - Specify browser (chrome, firefox, safari, edge)


Example:
```
mvn test -Dbrowser=firefox 
```


## Dependencies

The project uses the following key dependencies:

- **Playwright (1.57.0)** - Web automation library
- **TestNG (7.11.0)** - Testing framework
- **ExtentReports (5.1.2)** - Test reporting

## Features

- **Page Object Model (POM)** - Maintainable and reusable page components
- **Cross-browser testing** - Support for Chrome, Firefox, Safari, and Edge
- **TestNG integration** - Powerful test configuration and parallel execution
- **ExtentReports** - Rich HTML test reports
- **Playwright integration** - Modern browser automation with fast execution

## Test Examples

The project includes automated tests for the Just Eat Takeaway careers website:

- **Job Category Search Test**
- Tests job category filtering functionality
- Search for specific job categories (e.g., Sales)
- Filter by country (e.g., Germany)
- Verify search results and job counts

## Reporting

Test reports are generated using ExtentReports and can be found in the `test-output` directory after test execution.

## Troubleshooting

### Common Issues

1. **Browser not found error**
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```


2. **Java version issues**
    - Ensure you're using Java 17 or higher
    - Check `JAVA_HOME` environment variable

3. **Maven dependencies issues**
```
mvn clean compile
```


### Debug Mode

To run tests in debug mode with additional logging:
```
mvn test -Ddebug=true
```


## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Support

For questions or issues, please create an issue in the project repository.
