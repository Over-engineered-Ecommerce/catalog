# Overengineered E-commerce Integration Tests

This project contains integration tests for the Overengineered E-commerce application. The tests are written using Cucumber and Spring Boot.

## Prerequisites

- Java 21 or higher
- Maven
- Docker (for running the database)

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/your-repo/overengineered-ecommerce.git
    cd overengineered-ecommerce
    ```

2. Start the required services using Docker:
    ```sh
    make up
    ```

## Project Structure

- `src/test/java/com/overengineeredecommerce/integrationtest`: Contains the step definitions and test context.
- `src/test/resources/features`: Contains the Cucumber feature files.
- `Makefile`: Contains commands to manage Docker services.

## Running Tests

To run the integration tests, use the following command:
```sh
mvn clean install
```

## Configuration

The `CucumberIT` class is used to run the Cucumber tests and configure the Spring context. 
The `TestConfig` class scans for Spring components.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.