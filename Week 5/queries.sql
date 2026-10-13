-- Active: 1791878763671@@127.0.0.1@3306@student_management
-- QUERY TO LIST OUT STUDENTS
SELECT * FROM students;

-- QUERY TO LIST OUT ALL DEPARTMENTS
SELECT * FROM departments;

-- QUERY TO LIST OUT ALL COURSES
SELECT * FROM courses;

-- QUERY TO LIST OUT STUDENTS WITH THE COURSES THEY OFFER
SELECT * FROM students_course;



-- QUERY TO LIST OUT STUDENTS WITH THEIR DEPARTMENT
SELECT 
    students.student_id ,
    students.first_name,
    students.last_name,
    departments.department as department
FROM students  JOIN departments 
ON students.department_id = departments.department_id
ORDER BY student_id;

-- QUERY TO FIND STUDENTS BY THEIR DEPARTMENT(BY JUST ADJUSTING THE DEPARTMENT)
SELECT students.student_id ,
        students.first_name,
        students.last_name,
        departments.department as department
FROM students  JOIN departments 
ON students.department_id = departments.department_id
WHERE department = "Civil Engineering" 
ORDER BY student_id;

-- QUERY TO FIND STUDENTS BY THEIR LEVEL(BY JUST ADJUSTING THE LEVEL)
SELECT students.student_id ,
        students.first_name,
        students.last_name,
        students.lvl
FROM students
WHERE lvl = "200LVL" 
ORDER BY student_id;

-- QUERY TO FIND NUMBER OF STUDENTS IN A DEPARTMENT(BY ADJUSTING THE DEPARTMENT)
SELECT 
COUNT(students.student_id) AS total_students
FROM students JOIN departments
ON students.department_id = departments.department_id
WHERE departments.department = "Mechanical Engineering";


-- QUERY TO COUNT STUDENTS IN EACH DEPARTMENT
SELECT
    departments.department AS department, 
    COUNT(students.student_id) AS total_students
FROM departments
JOIN students 
ON departments.department_id = students.department_id
GROUP BY department;




--QUERY TO SEE THE NUMBER OF COURSES OFFERED BY EACH STUDENTS
SELECT
students.student_id,
students.first_name,
students.last_name,
COUNT(students_course.course_code) AS Total_courses
FROM students JOIN students_course
ON students.student_id = students_course.student_id
GROUP BY student_id;


-- QUERY TO LIST THE COURSES OFFERED BY A STUDENT(for differnet students by adjusting the students Id)
SELECT 
    students_course.course_code,
    courses.course_title 
FROM students_course JOIN courses
ON students_course.course_code = courses.course_code
WHERE students_course.student_id = 26023001;


-- QUERY TO FIND A COURSE NOT OFFERED BY ANY STUDENTS
SELECT 
courses.course_code,
courses.course_title
FROM courses LEFT JOIN students_course
ON courses.course_code = students_course.course_code
WHERE students_course.course_code IS NULL  AND students_course.student_id IS NULL ;
