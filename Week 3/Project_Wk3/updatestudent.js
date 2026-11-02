let updateForm = document.getElementById("update-form");

updateForm.onsubmit = function updateAStudent(e) {
    // Stops the form from refreshing the page on submit
    e.preventDefault();

    let idInput = document.getElementById("update-id").value;
    let nameInput = document.getElementById("update-name").value;
    let ageInput = document.getElementById("update-age").value;
    let courseInput = document.getElementById("update-course").value;
    let msg = document.getElementById("message");

    if (idInput === "" || nameInput === "" || ageInput === "" || courseInput === "") {
        msg.textContent = "Please fill in all fields.";
        return;
    }

    //Get students array from localStorage
    let students = [];
    let data = localStorage.getItem("student_list");
    if (data !== null) {
        students = JSON.parse(data);
    }

    let isUpdated = false;

    //Find matching ID and update details
    for (let i = 0; i < students.length; i++) {
        if (students[i].id == idInput) {
            students[i].name = nameInput;
            students[i].age = ageInput;
            students[i].course = courseInput;
            isUpdated = true;
            break;
        }
    }

    //Save back to localStorage if updated
    if (isUpdated === true) {
        localStorage.setItem("student_list", JSON.stringify(students));
        msg.textContent = "Student ID #" + idInput + " updated successfully!";

        // Reset inputs
        document.getElementById("update-id").value = "";
        document.getElementById("update-name").value = "";
        document.getElementById("update-age").value = "";
        document.getElementById("update-course").value = "";
    } else {
        msg.textContent = "Student ID #" + idInput + " was not found.";
    }
};