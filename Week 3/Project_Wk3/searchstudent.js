let searchButton = document.getElementById("search-btn");

searchButton.onclick = function searchAStudent() {
    let query = document.getElementById("search-input").value;
    let resultsDiv = document.getElementById("results");

    if (query === "") {
        resultsDiv.textContent = "Please enter a search term.";
        return;
    }

    //Get current students array from localStorage
    let students = [];
    let data = localStorage.getItem("student_list");
    if (data !== null) {
        students = JSON.parse(data);
    }

    let searchTerm = query.toLowerCase().trim();
    let foundStudents = [];

    //Loop through and check for match in Name, Course, or ID
    for (let i = 0; i < students.length; i++) {
        let nameLower = students[i].name.toLowerCase();
        let courseLower = students[i].course.toLowerCase();
        let studentIdString = students[i].id.toString();

        if (nameLower.includes(searchTerm) || courseLower.includes(searchTerm) || studentIdString === searchTerm) {
            foundStudents.push(students[i]);
        }
    }

    //Display search results
    resultsDiv.innerHTML = "";

    if (foundStudents.length === 0) {
        resultsDiv.textContent = "No matching student found.";
    } else {
        let list = document.createElement("ul");
        list.style.listStyle = "none";
        list.style.padding = "0";

        for (let i = 0; i < foundStudents.length; i++) {
            let item = document.createElement("li");
            item.textContent = "ID #" + foundStudents[i].id + " - " + foundStudents[i].name + " (" + foundStudents[i].course + ", Age: " + foundStudents[i].age + ")";
            list.appendChild(item);
        }
        resultsDiv.appendChild(list);
    }
};