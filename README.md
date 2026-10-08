# SuggestionBox 💡

A robust web application built with **Spring Boot** for collecting, managing, and rating internal suggestions and improvements, focused on software engineering best practices, clean code, and prevention of spam/duplicate votes.

---

## 🚀 Technologies Used

* **Backend:** Java 17, Spring Boot, Spring Data JPA / Hibernate
* **Database:** MariaDB / MySQL
* **Testing:** JUnit 5, Mockito
* **Frontend:** HTML5, CSS3, JavaScript (Vanilla)
* **Tools:** Maven, Git, IntelliJ IDEA (Google Java Style Guide)

---

## ⚙️ Prerequisites

Make sure you have the following software installed on your system before getting started:
* **JDK 17** or higher
* **Maven** (or you can use the bundled `mvnw` script)
* A running **MariaDB** server instance

---

## 📥 Installation and Setup
```bash
#### 1 -> Clone the repository:
   
   git clone [https://github.com/your-username/suggestionsbx.git](https://github.com/your-username/suggestionsbx.git)
   cd suggestionsbx
   

#### 2 -> Configure the Database:

Create a database on your MariaDB server (e.g., suggestions_db).
Edit the src/main/resources/application.properties file with your credentials:

Properties:
spring.datasource.url=jdbc:mariadb://localhost:3306/suggestions_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


#### 3 -> Build and Run the Application:

`mvn spring-boot:run`

The application will be available at http://localhost:8080.
```
## 📡 API Documentation (Endpoints)

The application exposes the following main REST endpoints:

| Method | Endpoint                    | Description                                                   |
| :----- | :-------------------------- | :------------------------------------------------------------ |
| `GET`  | `/api/suggestions`          | Retrieves all suggestions sorted from newest to oldest.       |
| `POST` | `/api/suggestions`          | Creates a new suggestion (requires `SuggestionRequestDTO`).   |
| `POST` | `/api/suggestions/{id}/rate`| Submits a rating/vote for a suggestion with fingerprint protection. |

## 🧪 _Running Unit Tests_

To run the isolated unit tests with JUnit and Mockito:

`mvn test`

## _📄 License_

This project was developed for educational and professional software development purposes.