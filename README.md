# job-interview-task-product-sales-company
Job Interview Task Product Sales Company

## Task Details:
You are a new Developer in an online product sales company. The company sells three types of products: high-end phones, mid-range phones, and laptops.

Your Product Manager asks you to develop a program that calculates a consumer’s shopping cart total.
There are two types of clients:
1. Individual clients, identified by a client ID, first name, and last name
2. Professional clients, identified by a client ID, company name, intra-community VAT number (optional), business registration number, and annual revenue
   
For individuals, the high-end phone model costs €1500, the mid-range model costs €800, and the laptop costs €1200.


For professional clients with annual revenue greater than €10 million, the high-end phone costs €1000, the mid-range phone costs €550, and the laptop costs €900.

For professional clients with annual revenue below €10 million, the high-end phone costs €1150, the mid-range phone costs €600, and the laptop costs €1000.

Calculate, for a given client, the total cost of their shopping cart, knowing that it may contain multiple quantities of each of the three products.


# Shopping Cart - Technical README

A small Java 25 Maven example demonstrating:
- modern Strategy pattern (sealed interface + records + pattern matching),
- separation of concerns and SOLID-friendly design,
- Lombok for boilerplate reduction,
- SLF4J + Log4j2 logging,
- JUnit 6 tests,

## Contents
- `src/main/java` - application code
- `src/test/java` - tests (JUnit 6)
- `pom.xml` - Maven build
- `README.md` - this file

## Prerequisites
Before you begin, ensure you have the following installed:
- Java 25
- Maven 3.8+ (3.9+ recommended)
- Git (optional, for cloning*)*
- IDE Lombok plugin (IntelliJ/Eclipse) and annotation processing enabled

## Setup

1. **Clone the repository (if applicable):**

   ```sh
   git clone git@github.com:rmaduzia/job-interview-task-product-sales-company.git
   cd job-interview-task-product-sales-company


## Build the project:
```
mvn clean install
```

## Running Tests
```
mvn test
```
## Running the Application
```
java -jar target/product-sales-company-0.0.1-SNAPSHOT.jar
```