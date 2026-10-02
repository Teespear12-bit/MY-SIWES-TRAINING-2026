window.onload = function viewStudents() {
    let tableBody = document.getElementById("student-table");

    //Get students array from localStorage
    let students = [];
    let data = localStorage.getItem("student_list");
    if (data !== null) {
        students = JSON.parse(data);
    }

    //Clear table body
    tableBody.innerHTML = "";

    //If empty, display message
    if (students.length === 0) {
        tableBody.innerHTML = "<tr><td colspan='4'>No students available.</td></tr>";
        return;
    }

    //Loop through array and build table rows
    for (let i = 0; i < students.length; i++) {
        let row = document.createElement("tr");
        row.innerHTML = 
            "<td>" + students[i].id + "</td>" +
            "<td>" + students[i].name + "</td>" +
            "<td>" + students[i].age + "</td>" +
            "<td>" + students[i].course + "</td>";
        tableBody.appendChild(row);
    }
};