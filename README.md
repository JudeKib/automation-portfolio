# automation-portfolio

Thank you for taking the time to review my portfolio!

The purpose of this project was to automate the steps to reproduce defects found in my freelancing work. The motivation for doing so was to demonstrate my automation and coding abilities, as well as to get more practice for myself.

### Architecture
All reproductions are located in the class BugReproductions. There are reproductions of bugs found on several websites, and for each website, there is a class that exposes methods for interacting with that site. For example, the WeatherChannel class exposes methods for opening the website, searching a location, and navigating to the Almanac. These site classes inherit reusable utility methods from the class BaseSite.

Each test case consists of a series of these methods, each method representing a step to reproduce the defect. It ends with an assertion implemented in the BugReproductions level. BugReproductions inherits from BaseTest, which encapsulates the setup for executing the reproductions.

### Technologies Used
All automation was done using Selenium Webdriver in Java. The test framework used was TestNG.
