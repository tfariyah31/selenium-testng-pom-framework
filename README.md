# selenium-testng-pom-framework
A Selenium TestNG automation framework using the Page Object Model (POM) for testing SauceDemo, built with Java and Maven. Features includes data-driven testing (JSON), retry logic (IRetryAnalyzer), Log4j2 for logging and Extent Reports for detailed test reporting.
## Features

*   **Page Object Model (POM):**  Organizes the test code by representing each page of the application as a separate class. This improves code readability and maintainability.
*   **Data-Driven Testing:** Employs JSON files to store test data, allowing for easy execution with different sets of inputs without modifying the test code.  This facilitates running the same tests with varying data.
*   **Retry Logic:** Implements retry logic using `IRetryAnalyzer` to automatically re-run failed tests. This helps in handling intermittent issues and improves test reliability.  Specifically, the framework will retry failed tests a configurable number of times.
*   **Log4j 2 Logging:** Integrates Log4j 2 for comprehensive logging of test execution. This provides detailed information about the test runs, aiding in debugging and analysis.  Logs are configured to provide different levels of detail (e.g., DEBUG, INFO, ERROR) for various aspects of the test execution.
*   **Extent Reports:** Generates HTML reports using Extent Reports, providing a clear and visually appealing summary of test results, including pass/fail status, screenshots (on failure), and detailed logs.  This makes it easier to understand test outcomes and identify areas for improvement.

## Technologies Used

*   **Selenium WebDriver:** For browser automation.
*   **TestNG:** As the testing framework for running tests, managing test suites, and generating reports.
*   **JSON:** For storing and managing test data.
*   **Log4j 2:** For logging.
*   **Extent Reports:** For generating HTML reports.
*   **Java:** As the programming language.
*   **Maven:** For dependency management.
*   **AI:** Codeium, ChatGPT

## Setup and Execution

1.  **Prerequisites:**
    *   Java Development Kit (JDK) version 18.0.2.1 installed.
    *   Maven installed.
    *   A suitable IDE (e.g., IntelliJ IDEA, Eclipse).
    *   Browser drivers for the browsers you want to test (e.g., ChromeDriver for Chrome, geckodriver for Firefox).  

2.  **Project Setup:**
    *   Clone the repository: https://github.com/tfariyah31/selenium-testng-pom-framework.git
    *   Import the project into your IDE as a Maven project.

3.  **Configuration:**
    *   **Test Data:** Modify the JSON files in the `src/test/java/Tfar/data/` directory to provide your test data.  Ensure the JSON structure matches the data expected by the tests.
    *   **Log4j 2 Configuration:**  Review and adjust the `log4j2.xml` file in the `src/test/resources/` directory to customize logging levels and output. 
    *   **Extent Reports Configuration:** The Extent Reports configuration in `src/main/java/Tfar/utilities/ExtentReportManager.java'
    *   **Browser Configuration:** Configure the browser to be used in the test execution in the `src/test/resources/GlobalParameters.properties` configuration files.

4.  **Execution:**
    *   Run the tests from your IDE using TestNG or Maven: `mvn clean`  `mvn install` `mvn test`

5.  **Reporting:**
    *   Extent Reports will be generated in the `/reports` directory after test execution.  
    *   Log files will be generated in generated in `logs/` directory, can be configured as per your `log4j2.xml` configuration.

6.  **Running Tests via Maven(Profile-Smoke):**
    *   mvn test -PSmoke





