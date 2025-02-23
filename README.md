<h1 align="center" style="display: flex;">
  Technical Challenge - Java/Spring API
</h1>

<div align="center">
  <img src="https://img.shields.io/badge/Spring-60b831?style=for-the-badge&logo=spring&logoColor=ffffff">
  <img src="https://img.shields.io/badge/Java-e76f00?style=for-the-badge&logo=coffeescript&logoColor=ffffff">
  <img src="https://img.shields.io/badge/Postman-fe6c37?style=for-the-badge&logo=postman&logoColor=ffffff">
  <img src="https://img.shields.io/badge/H2_Database-346892?style=for-the-badge&logo=hexo&logoColor=ffffff">
</div></br>

<p align="center">
This API was built as a technical challenge for a junior position in backend development. It can save and search data for bank users using an in-memory database (H2) for simplicity. It also supports financial transactions and includes validations, such as balance checks, account type verification, and an external service validator.
</p>

<h2>💻 Technologies, Patterns, and More</h2>

- Java
- Spring Boot
- H2 Database
- Postman
- IntelliJ
- Organized Layers (Domain, Service, Controller, Repository, DTOs, and Infra)
- Lombok Notation
- Exception Handling with Controller Advice
- Conventional Commits (e.g., "feat:", "fix:", "chore:")
- REST API Design

## 🚀 API Endpoints

| **Route**                                | **Description**                                               |
|------------------------------------------|---------------------------------------------------------------|
| **<img height=20 src="https://img.shields.io/badge//users-151b23?style=flat&label=GET&labelColor=60b831">**        | Retrieves a list of all users.                    |
| **<img height=20 src="https://img.shields.io/badge//users-151b23?style=flat&label=POST&labelColor=007bcc">**        | Registers a new user in the system.              |
| **<img height=20 src="https://img.shields.io/badge//transfer-151b23?style=flat&label=POST&labelColor=007bcc">**        | Registers a new financial transaction.        |
