### How the program stores students:
I created a Student class with four fields (id, name, age, and department) to act as the blueprint for each student. Instead of a database, the program stores these Student objects inside an ArrayList<Student> called students while the app is running. Whenever I add a student, it creates a new Student(...) with an automatic ID (nextId) and pushes it into the list using students.add().

### How the program retrieves students:
To get the students back out, I used a for loop that runs through the ArrayList from 0 up to students.size() and grabs each student using students.get(i):

View: It just loops through the whole list and prints every student's details.

Search: It checks if what I typed matches s.name or s.department (converting both to lowercase first so case doesn't matter).

Update & Delete: It loops through until s.id matches the ID I entered, then either overwrites the fields directly (s.name = newName, etc.) or removes that index from the list with students.remove(i).