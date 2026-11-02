let students = [];
let nextId = 1;
let running = true;

while (running) {
    let choice = prompt(
        "=== STUDENT MANAGEMENT SYSTEM ===\n" +
        "1. Add Student\n" +
        "2. View Students\n" +
        "3. Search Student\n" +
        "4. Update Student\n" +
        "5. Delete Student\n" +
        "6. Exit\n\n" +
        "Enter a number (1-6):"
    );

    // Stop if user clicks Cancel or chooses 6
    if (choice === null || choice === "6") {
        alert("Exiting system. Goodbye!");
        running = false;
    } 
    
    // 1. ADD STUDENT
    else if (choice === "1") {
        let name = prompt("Enter Student Name:");
        let age = prompt("Enter Student Age:");
        let course = prompt("Enter Student Course:");

        if (name === "" || age === "" || course === "") {
            alert("Please fill in all details!");
        } else {
            let newStudent = {
                id: nextId,
                name: name,
                age: age,
                course: course
            };
            students.push(newStudent);
            nextId = nextId + 1; // Increase ID for next student
            alert("Student added successfully with ID #" + newStudent.id);
        }
    } 

    // 2. VIEW STUDENTS
    else if (choice === "2") {
        if (students.length === 0) {
            alert("No students in the list yet.");
        } else {
            let listText = "ALL STUDENTS:\n\n";
            for (let i = 0; i < students.length; i++) {
                listText += "ID #" + students[i].id + " - " + students[i].name + 
                            " (Age: " + students[i].age + ", Course: " + students[i].course + ")\n";
            }
            alert(listText);
        }
    } 

    // 3. SEARCH STUDENT
    else if (choice === "3") {
        let search = prompt("Enter Name or Course to search:");
        let foundText = "SEARCH RESULTS:\n\n";
        let found = false;

        for (let i = 0; i < students.length; i++) {
            let nameMatch = students[i].name.toLowerCase().includes(search.toLowerCase());
            let courseMatch = students[i].course.toLowerCase().includes(search.toLowerCase());

            if (nameMatch || courseMatch) {
                foundText += "ID #" + students[i].id + " - " + students[i].name + " (" + students[i].course + ")\n";
                found = true;
            }
        }

        if (found) {
            alert(foundText);
        } else {
            alert("No matching student found.");
        }
    } 

    // 4. UPDATE STUDENT
    else if (choice === "4") {
        let updateId = prompt("Enter Student ID to update:");
        let updated = false;

        for (let i = 0; i < students.length; i++) {
            if (students[i].id == updateId) {
                let newName = prompt("Enter New Name:", students[i].name);
                let newAge = prompt("Enter New Age:", students[i].age);
                let newCourse = prompt("Enter New Course:", students[i].course);

                students[i].name = newName;
                students[i].age = newAge;
                students[i].course = newCourse;

                alert("Student ID #" + updateId + " updated successfully!");
                updated = true;
                break;
            }
        }

        if (!updated) {
            alert("Student ID #" + updateId + " was not found.");
        }
    } 

    // 5. DELETE STUDENT
    else if (choice === "5") {
        let deleteId = prompt("Enter Student ID to delete:");
        let deleted = false;

        for (let i = 0; i < students.length; i++) {
            if (students[i].id == deleteId) {
                students.splice(i, 1); // Remove 1 item at index i
                alert("Student ID #" + deleteId + " deleted successfully!");
                deleted = true;
                break;
            }
        }

        if (!deleted) {
            alert("Student ID #" + deleteId + " was not found.");
        }
    } 

    // INVALID INPUT
    else {
        alert("Invalid option! Please enter a number from 1 to 6.");
    }
}