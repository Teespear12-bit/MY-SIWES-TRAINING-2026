# Week 4: Student Management System (Java OOP Refactor)

## Overview
For this week's task, I refactored my earlier JavaScript Student Management System into a console-based Java application using Maven.

In my previous JavaScript code, I stored student records as simple objects inside a basic array (and used `JSON.stringify()` with `localStorage` for the web version). For this Java version, I changed my approach to follow Object-Oriented Programming (OOP) principles. Instead of putting everything in one place, I split the code across separate Java classes under the `com.student` package.

---

## Class Responsibilities & Algorithm

### 1. `Student.java` (Model Class)
This class defines what a student object actually holds in Java.
- **Data Encapsulation:** Instead of loose JS objects, I made all variables (`id`, `name`, `age`, `course`, and `department`) `private` so they can't be changed directly from outside.
- **Getters & Setters:** I added public getter and setter methods to read and update student information safely.
- **Composition:** I included a `Department` object inside the `Student` class so department details are grouped properly rather than stored as plain text.
- **Display Logic:** Added a `displayStudent()` method that prints out the formatted student details to the terminal.

### 2. `Department.java` (Composite Model Class)
This class handles department details separately to keep things clean.
- Stores `deptId` and `deptName`.
- Overrode the `toString()` method so that printing a department outputs it neatly like `Department Name (ID: X)`.

### 3. `StudentService.java` (Business Logic & Operations)
This class takes care of all the core logic, replacing what I previously wrote in my JavaScript `operations.js` file. It manages student data using Java's `List<Student>` and `ArrayList`.

- **Storing Data:** I set up a `private List<Student> students = new ArrayList<>();` to hold all student records in memory while the app runs.
- **Adding Students:** Just like using `students.push()` in JS, I created an `addStudent()` method that creates a new `Student` instance and adds it with `students.add()`.
- **Generating Unique IDs:** I used a simple counter `private int nextId = 1;`. Whenever a new student is created, they get assigned `nextId`, and then `nextId` goes up by 1 (`nextId++`). This makes sure every student gets a unique ID number.
- **Viewing Students:** It checks if the list is empty first; if not, it loops through the array list and calls `displayStudent()` on each record.
- **Searching Students:** Loops through the list and uses `.equalsIgnoreCase()` to check if the name or course matches what was entered in the terminal.
- **Updating Students:** Finds the student by their ID and updates their `name`, `age`, `course`, and `department` using the setter methods.
- **Deleting Students:** Uses a helper method `findById(id)` to locate the student and removes them using `students.remove(student)`.

### 4. `Main.java` (User Interface & Input Handler)
This acts as the entry point for the whole program.
- **Interactive Menu:** Uses a `while` loop to keep showing the option menu (1–6) until the user decides to quit.
- **Scanner Operations:** Uses `Scanner` to pick up whatever text or numbers the user types in.
- **Input Validation:** Wrapped number inputs inside a `try-catch` block for `InputMismatchException`. If someone types letters instead of a number for age or menu choices, it catches the error and asks them to try again without crashing the app.

---
