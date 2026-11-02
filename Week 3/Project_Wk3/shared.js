// shared.js - Simple Student Data Storage

//Student Class
class Student {
  constructor(id, name, age, course) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.course = course;
  }
}

// 2. Student Manager Class
class StudentManager {
  constructor() {
    this.key = "student_list";
    this.idKey = "student_next_id";
    this.init();
  }

  // Load initial dummy data if empty
  init() {
    if (localStorage.getItem(this.key) === null) {
      let initialStudents = [
        new Student(1, "Philip Awotunde", 20, "Computer Engineering"),
        new Student(2, "Elijah Awotunde", 18, "Electrical Engineering")
      ];
      localStorage.setItem(this.key, JSON.stringify(initialStudents));
      localStorage.setItem(this.idKey, "3");
    }
  }

  // Get array of students
  getStudents() {
    let data = localStorage.getItem(this.key);
    if (data) {
      return JSON.parse(data);
    }
    return [];
  }

  // Save array back to storage
  saveStudents(list) {
    localStorage.setItem(this.key, JSON.stringify(list));
  }

  // 1. ADD STUDENT (using push)
  addStudent(name, age, course) {
    let students = this.getStudents();
    let nextId = parseInt(localStorage.getItem(this.idKey));

    let newStudent = new Student(nextId, name, age, course);
    students.push(newStudent);

    this.saveStudents(students);
    localStorage.setItem(this.idKey, (nextId + 1).toString());
    return newStudent;
  }

  // 2. SEARCH STUDENT (using simple loop)
  searchStudent(query) {
    let students = this.getStudents();
    let results = [];
    let searchTerm = query.toLowerCase();

    for (let i = 0; i < students.length; i++) {
      let s = students[i];
      if (s.name.toLowerCase().includes(searchTerm) || s.course.toLowerCase().includes(searchTerm)) {
        results.push(s);
      }
    }
    return results;
  }

  // 3. UPDATE STUDENT
  updateStudent(id, name, age, course) {
    let students = this.getStudents();
    let found = false;

    for (let i = 0; i < students.length; i++) {
      if (students[i].id == id) {
        students[i].name = name;
        students[i].age = age;
        students[i].course = course;
        found = true;
        break;
      }
    }

    if (found) {
      this.saveStudents(students);
      return true;
    }
    return false;
  }

  // 4. DELETE STUDENT (using splice)
  deleteStudent(id) {
    let students = this.getStudents();
    let index = -1;

    for (let i = 0; i < students.length; i++) {
      if (students[i].id == id) {
        index = i;
        break;
      }
    }

    if (index !== -1) {
      students.splice(index, 1);
      this.saveStudents(students);
      return true;
    }
    return false;
  }
}

// Global manager instance
let manager = new StudentManager();