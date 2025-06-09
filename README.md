# BeTiUsage
## Better Time Usage

A tool designed to help you manage and optimize your time usage.

## Table of Contents
- [Installation](#installation)
- [Running the Application](#running-the-application)

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
