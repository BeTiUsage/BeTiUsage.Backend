# BeTiUsage
## Better Time Usage

A tool designed to help you manage and optimize your time usage.

## Table of Contents
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Common Maven Commands](#common-maven-commands)
- [Running the Entire Project](#running-the-entire-project)

## Installation

1. Clone the repository
   ```bash
   git clone https://github.com/BeTiUsage/BeTiUsage.Backend
   ```

2. Enter the directory
   ```bash
   cd BeTiUsage
   ```

## Running the Application

1. Set up environment variables
   ```bash
    # Example for Unix-based systems
    export JDBC_DATABASE_URL=jdbc:mysql://localhost:8080/db
    export JDBC_USERNAME=user
    export JDBC_PASSWORD=password

    # Example for Windows
    set JDBC_DATABASE_URL=jdbc:mysql://localhost:8080/db
    set JDBC_USERNAME=user
    set JDBC_PASSWORD=password

   ```

2. Run the application using Maven
   ```bash
   mvn spring-boot:run
   ```

The backend should now be running on `http://localhost:8080/`

## Running the Entire Project

To build and run the entire project from terminal with a single command sequence:

```bash
# Clean previous builds, compile, run tests, and start the application
mvn clean install spring-boot:run

# Skip tests and start the application
mvn clean install -DskipTests spring-boot:run

# For production deployment, create an executable JAR
mvn clean package
java -jar target/betiusage-0.0.1-SNAPSHOT.jar
```

## Common Maven Commands

### Basic Build Lifecycle

```bash
# Install a package
mvn install

# Clean the project (remove target directory)
mvn clean

# Compile the source code
mvn compile

# Package the project (create JAR/WAR file)
mvn package

# Run tests
mvn home

# Clean and package in one command
mvn clean package

# Install without running tests
mvn install -DskipTests
```

### Additional Commands

```bash
# Run a specific home class
mvn home -Dtest=TestClassName

# Generate project site documentation
mvn site

# Verify the project (run integration tests)
mvn verify

# Display project dependencies
mvn dependency:tree

# Debug mode (useful for troubleshooting)
mvnDebug spring-boot:run
```
### Live Development

```bash
# Run with automatic restart on code changes (requires spring-boot-devtools)
mvn spring-boot:run -Dspring-boot.run.addResources=true
```
