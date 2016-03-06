# OSTDLabsTask
Create example web application using Java, Maven, Spring, Spring MVC, Spring Security,
ActiveMQ, CXF, JAX-RS, Hibernate, Hsqldb, Tomcat, AngularJS, JUnit.

Main objectives of this application are
1. Create multi module maven project.
2. Create jsp page that displays list of bank accounts, using jstl, provided by backend.
 A bank account consists of two fields: iban (String) and Business Identifier Code (String).
3. Create rest endpoint to store changes to the bank account list from user.
4. Implement logic to store user data.
5. Create js based submission of bank account list edited by user towards backend.
6. Implement storage of user data in embedded hsql database using jpa.
7. Add wadl description to the rest endpoint.
8. Add profile to generate client based on wadl.
9. Add jms producer to flush persistent bank account lists to the consumers.
10. Add module with jms consumer that prints messages from queue to the log file.


Before start you need to install maven, jdk, Apache ActiveMQ, Tomcat web server.
Start Apache ActiveMQ message broker and create queue.
Install and start Tomcat web server.
Build OSTDLabsTask application using maven.
Deploy to Tomcat web server.

There are two properties files for this application:
ostd-application.properties
log4j.properties.
You should setup JMS settings in ostd-application.properties file (queue.name and broker.uri)
And define path to the log file with messages from JMS in log4j.properties.
For authentication use admin/admin, also you can define login/password in spring-security.xml file.
