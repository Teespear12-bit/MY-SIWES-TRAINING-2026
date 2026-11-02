let addButton = document.getElementById("add");

addButton.onclick = function addAStudent() {
    let nameInput = document.getElementById("name").value;
    let ageInput = document.getElementById("age").value;
    let courseInput = document.getElementById("course").value;
    let msg = document.getElementById("msg");

    if (nameInput === "" || ageInput === "" || courseInput === "") {
        msg.textContent = "Please fill in all fields.";
        return;
    }

    // To get the current students array from localStorage
    let students = [];
    let data = localStorage.getItem("student_list");
    if (data !== null) {
        students = JSON.parse(data);
    }

    // To find the highest ID currently in the array
    let highestId = 0;
    for (let i = 0; i < students.length; i++) {
        if (students[i].id > highestId) {
            highestId = students[i].id;
        }
    }

    // Generate ID for the new guy,The new student gets highestId + 1
    let newId = highestId + 1;

    // Create student object
    let newStudent = {
        id: newId,
        name: nameInput,
        age: ageInput,
        course: courseInput
    };

    //Add to array and save to localStorage
    students.push(newStudent);
    localStorage.setItem("student_list", JSON.stringify(students));

    msg.textContent = "Student " + nameInput + " added successfully with ID: " + newId;

    // Reset input boxes
    document.getElementById("name").value = "";
    document.getElementById("age").value = "";
    document.getElementById("course").value = "";
};