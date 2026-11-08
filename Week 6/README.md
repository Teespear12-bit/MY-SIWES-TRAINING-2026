### How a Request Reaches the Controller
### Week 6 Assignment: My First Spring Boot API

### What I built

I built a Spring Boot application with two endpoints:

GET /hello returns a simple text message
GET /students returns a list of sample students

### The project has three parts:

1. Controller: receives the request and sends back the response
2. Service: does the work and holds the sample student data
3. Model: the Student class, which describes what a student looks like (id, name, age, course)

### What happens when I start the app

When I run the application, Spring Boot starts a built-in server called Tomcat on port 8080. It also looks through my packages, finds the classes marked with @RestController and @Service, and creates them. This is how Spring knows which method should answer which URL.

### How a request reaches the controller

Using GET /students as the example:

1. I send the request from Postman (or my browser) to http://localhost:8080/students.
2. Tomcat receives the request on port 8080.
3. Tomcat passes it to Spring’s DispatcherServlet. From my understanding, it works like a receptionist. It looks at the request and      decides which method should handle it.
4. It checks the method (GET) and the path (/students), and finds the method with @GetMapping("/students"). That is getStudents() in StudentController.
5. getStudents() runs. Inside it, it calls studentService.getAllStudents() to get the data.
6. StudentService returns the list of students.
7. Spring turns the list into JSON, so Postman or the browser can read it.
8. The response is sent back with status 200 OK.

GET /hello works the same way. The only difference is that it goes to HelloController, and it returns a text message directly without using the service.



