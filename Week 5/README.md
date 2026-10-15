### Student Management System
I organized this project into three separate SQL files to keep the setup clean, structured, and easy to run step-by-step.

### How the Project is Organized
1. **schema.sq:**
This file holds the code used to build the database tables (departments, students, courses, and students_course).It sets up all primary keys, auto-incrementing IDs, and foreign keys that link the tables together.

2. **values.sql:**
This file contains all the sample data added to the system.It populates the departments, students, courses, and the student-course pairings.

3. **queries.sql:**
This file contains all the queries written to test and pull information out of the database.It includes basic list queries, table joins, department/level filters, total counts, and checks for courses with no enrolled students.

### Order to run the files
To avoid errors with table references and foreign keys, the files should be run in this exact order:

1. **schema.sql (Creates the empty database tables first)**

2. **values.sql (Fills the tables with sample data)**

3. **queries.sql (Runs the searches and data checks)**