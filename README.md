# Digital Lending Platform

The goal of this project is to design and write a simple micro service to mimic a
digital lending platform. 
Through this web service one can request a loan offer and he/she is presented with one or
more loan offers based on the customer loan maximum qualification. From the offers
presented, a customer can choose and accept one of the offers. When a customer accepts
an offer then we will credit his mobile wallet.

## Features
Loan product: Use the following loan products:
1. Product A – (Max allowable limit 10000, interest percentage 10%, tenure 15 days),
2. Product B – (Max allowable limit 25000, interest percentage 12.5%, tenure 30 days)

- Loan maximum qualification: This a hard coded value stored per customer.

- Loan offer: One or more loan products that the customer can qualify to borrow. An offer
should include, loan amount, fixed percentage interest, loan tenure.

- Mobile wallet: Virtual account tied to your mobile phone number. To implement this just
create a method that returns “failed“ if the amount is 5000 and “success” for any other
amount.


#### Technologies and Frameworks used:
- Java 11
- Spring Boot 
- Spring Security
- [JSON Web Token](https://github.com/jwtk/jjwt)
- Postgresql (database)
- Maven (build tool)
- Springdoc open-api (documentation)

# Getting Started
- Clone the project from repository
- Build using maven to import dependencies used in the project.
- Run `LendingPlatformApplication` main class to start server
- After a successful build, navigate to `http://localhost:8080/swagger-ui/index.html/` 
to access the documentation for the project.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.9/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.9/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#web.security)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.9/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

