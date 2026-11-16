# Week 8 — Improved Student API


## Bugs Encountered & How I Fixed Them

Here are a few actual bugs and issues I ran into while building and testing the application:

### 1. Unit Test Imports Failing ("JUnit / Mockito cannot be resolved")
* **The Bug & Impact:** When I created my test class `StudentServiceTest.java`, VS Code gave red squiggly error lines everywhere saying JUnit and Mockito imports couldn't be resolved. The test suite wouldn't compile or run at all.
* **How I Fixed It:** I realized I accidentally created `StudentServiceTest.java` inside `src/main/java/` instead of `src/test/java/`. Because it wasn't sitting inside the test directory, Maven didn't give the file access to test dependencies. Once I moved the file to `src/test/java/com/student/studentapi/service/`, all the import errors vanished and `mvn test` ran cleanly.

### 2. Postman Sending Empty Data (`null` Fields Saved)
* **The Bug & Impact:** When testing `POST /students` in Postman with a full JSON payload, the API returned a `201 Created` status, but all the saved fields (`firstName`, `email`, etc.) were coming back as `null` in the database.
* **How I Fixed It:** I forgot to put the `@RequestBody` annotation before `CreateStudentRequest request` in my `@PostMapping` method in `StudentController`. Spring wasn't binding the JSON body into my DTO. Adding `@RequestBody` fixed the deserialization immediately.

### 3. App Crashing on Startup (`ECONNREFUSED` / Database Error)
* **The Bug & Impact:** When trying to launch the app using `mvn spring-boot:run`, the terminal threw a database connection exception and stopped the application right away. Sending requests from Postman threw `ECONNREFUSED 127.0.0.1:8080` because the server wasn't running.
* **How I Fixed It:** I checked `application.properties` and noticed a small typo in my database connection URL. I also forgot to make sure MySQL Server was running locally on my PC before starting Spring. Once I corrected the URL line and started MySQL, the app booted up smoothly.