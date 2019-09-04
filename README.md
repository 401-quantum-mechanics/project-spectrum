# Project Spectrum
Project Spectrum is a repository for coding project suggestions. It allows users to post ideas for projects, create
 and join teams for a project, comment on a project, and rate projects. The goal of the site is to show off the
  developers java/spring boot abilities.
  
  ## Getting Started
  
  * Clone this repo https://github.com/401-quantum-mechanics/project-spectrum
  
  * Start Postgresql
  
  * Create an application.properties file inside of the resources directory and add the following:
      * spring.datasource.platform=postgres
      * spring.datasource.url=jdbc:postgresql://localhost:<DATABASE>/spectrum
      * spring.datasource.username=<USERNAME>
      * spring.datasource.password=<PASSWORD>
      * spring.jpq.hibernate.ddl-auto=create
      
  * Gradle command to run:  ./gradlew run
  
  ## Running the tests
  
  * After Getting Started navigate to src\test\java\com\projectspectrum\project
  * Models holds tests for the models used.
  * spectrum holds tests for the routes used.
  
  ## Deployment
  
  This application was deployed using AWS.
  
  ## Built with:
  
  * java
  * springboot
  * gradle
  
  ## Authors
  * Fabian "Here's Johnny" Brooks  
  ![Fabian Brooks](./src/main/resources/static/images/fabian.jpg?raw=true 'Fabian Brooks')
  
  * Matt "Come at me Brah!" Stuhring  
    ![Matt Stuhring](./src/main/resources/static/images/matt.png?raw=true 'Matt Stuhring')
    
  * Travis "Cookie Monster" Cox  
    ![Travis Cox](./src/main/resources/static/images/travis.jpg?raw=true 'Fabian Brooks')
  
  ## Resources
  * The interwebs.
  
  