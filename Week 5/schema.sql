-- Active: 1791878763671@@127.0.0.1@3306@student_management
-- CREATED THE DEPARTMENTS TABLE 
CREATE TABLE departments (
  department_id INT NOT NULL AUTO_INCREMENT,
  department VARCHAR(100) NOT NULL,
  faculty VARCHAR(80) NOT NULL,
  PRIMARY KEY (department_id),
  UNIQUE KEY (department)
);

ALTER TABLE departments
AUTO_INCREMENT = 101;

-- CREATED THE STUDENTS TABLE
CREATE TABLE students(
student_id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
lvl VARCHAR(20) NOT NULL,
department_id INT,
FOREIGN KEY(department_id) REFERENCES departments(department_id)
);

ALTER TABLE students
AUTO_INCREMENT = 26023001;


-- CREATED THE COURSES TABLE
CREATE TABLE courses (
  course_code varchar(20) NOT NULL,
  course_title varchar(100) NOT NULL,
  PRIMARY KEY (course_code),
  UNIQUE KEY (course_title)
);

-- CREATED THE STUDENTS-COURSES TABLE
CREATE TABLE students_course(
student_id INT,
course_code VARCHAR(20),
FOREIGN KEY (student_id) REFERENCES students(student_id),
FOREIGN KEY (course_code) REFERENCES courses(course_code)
);

ALTER TABLE students_course
ADD PRIMARY KEY (student_id, course_code);
