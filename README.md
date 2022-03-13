# Demo project to demonstrate test automation framework development for mobile testing

**Test automation UI framework demo project with Appium.**

Appium UI test automation in Java 11, demonstrating how to implement a scalable, flexible framework for running tests on Appium.

*Note:* The test cases implemented are not extensive. Just a few tests for a small demo.

**1. Technologies used**
- Appium 7 for mobile app tests
- Cucumber 7 for Behaviour Specification and Data Driven Testing
- TestNG 7
- Maven Failsafe plugin to run the test suite
- Awaitility to wait dynamically for elements to reach a desired state
- Lombok & Logback for logging
- Spring Core for dependency injection
- Lombok to eliminate a lot of code
- Maven Checkstyle for enforcing coding conventions
- Cluecumber plugin for visualization of test reports

**2. Design patterns used:**
- Behaviour Specification
- Page Object Model
- Factory

**3. Some aspects of mobile UI testing being demonstrated:**
- Simple interaction with an android app login.
- Simple interaction with android app screens.
- Using Appium to test a web page's form in a mobile device's  web browser. 
- Scrolling in an app via Appium TouchAction.

**4. Reporting and logging**  
The framework saves reports and logs in the target folder after a test run finishes.
1. Logs are saved in target/logs
1. Cucumber reports are saved in target/cucumber-report
1. More detailed Cluecumber reports are saved in target/test-report
   
The reports create a visualized overview of the test results. Can be viewed in a browser.
In case of failed scenarios a screenshot of the mobile app is saved.
The screenshot is added to the test reporting.

**5. Pre-requirements for running the tests**
- Have Maven installed.
- Have Java installed, at least version 11.
- Have Android SDK installed and setup properly.
- Have a running instance of a device emulator running with Android SDK.  
Recommended: Galaxy Nexus with Android 9.  
- Have an Appium server installed, configured and running.

**6. Launching the tests**  
Open a terminal and type:
    ```
    mvn clean verify
    ```
    
Setting which tests should be run based on cucumber tags can be done via the ```-Dcucumber.filter.tags option```.

Example command to run the tests with default browser settings (chrome) for only the Login Screen:  
    ```
    mvn clean verify -Dcucumber.filter.tags=@LoginScreen
    ```

*Notes about the mobile device emulation:* 
The tests were tried with Galaxy Nexus and Xiaomi Redmi Note 7.
Running them with other devices may cause failures.
Different devices may need some different setup or scrolling parameter settings.

**7. BrowserStack integration**  

The project also demonstrates browserstack integration.  
To run the test cases on BrowserStack, we need to set our unique BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY on our machine as system environment variables.  

To run the tests with BrowserStack desktop or device, add the ```-DbrowserName=browserstack``` param to the mvn verify command.  
To customize the mobile device, use the arguments listed below in the table.  
To get different values for these arguments, check out the [BrowserStack capability generator](https://app-automate.browserstack.com/dashboard/v2/quick-start/get-started#introduction).

Supported arguments:  
| argument name         | supported values             | default value       | description                                                                             |
| --------------------- | -----------------------------| ------------------- | --------------------------------------------------------------------------------------- |
| useBrowserStack       | boolean values               | false               | Sets whether the tests should be run against a local Appium or BrowserStack.            |
| browserStackOSVersion | see the BrowserStack website | 9.0                 | Tells BrowserStack which device OS to use. At the moment the tests only run on Android. |
| browserStackDevice    | see the BrowserStack website | Xiaomi Redmi Note 7 | Sets the device on which the tests should run via BrowserStack                          |
    
Example command to run the tests with BrowserStack on Samsung Galaxy S6 & Android 12.0:  
    ```
    mvn clean verify -DuseBrowserStack=true -DbrowserStackOSVersion=12.0 "-DbrowserStackDevice=Samsung Galaxy S6"
    ```
 