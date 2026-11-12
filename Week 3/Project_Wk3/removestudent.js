let removeButton = document.getElementById("delete-btn");

removeButton.onclick = function removeAStudent() {
    let idInput = document.getElementById("id").value;
    let msg = document.getElementById("msg");

    if (idInput === "") {
        msg.textContent = "Please enter a valid Student ID.";
        return;
    }

    //Get students array from localStorage
    let students = [];
    let data = localStorage.getItem("student_list");
    if (data !== null) {
        students = JSON.parse(data);
    }

    let newStudents = [];
    let found = false;

    //Keep everyone except the student to delete
    for (let i = 0; i < students.length; i++) {
        if (students[i].id == idInput) {
            found = true; // Skip this student
        } else {
            newStudents.push(students[i]); // Keep this student
        }
    }

    //Save new list back to localStorage
    if (found === true) {
        localStorage.setItem("student_list", JSON.stringify(newStudents));
        msg.textContent = "Student ID #" + idInput + " removed successfully!";
        document.getElementById("id").value = "";
    } else {
        msg.textContent = "Error: Student ID #" + idInput + " does not exist.";
    }
};