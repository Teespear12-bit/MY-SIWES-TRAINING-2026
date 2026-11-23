// 1. Highlight current page in navbar
const navLinks = document.querySelectorAll(".navlinks a");
for (let i = 0; i < navLinks.length; i++) {
    if (navLinks[i].getAttribute("href") === window.location.pathname) {
        navLinks[i].classList.add("active");
    }
}

// 2. Confirm before deleting a student
const deleteLinks = document.querySelectorAll(".delete-link");
for (let i = 0; i < deleteLinks.length; i++) {
    deleteLinks[i].addEventListener("click", function (event) {
        const sure = confirm("Are you sure you want to remove this student?");
        if (!sure) {
            event.preventDefault();
        }
    });
}

const removeForm = document.getElementById("removeForm");
if (removeForm) {
    removeForm.addEventListener("submit", function (event) {
        const id = document.getElementById("deleteID").value;
        const sure = confirm("Are you sure you want to delete student with ID " + id + "?");
        if (!sure) {
            event.preventDefault();
        }
    });
}

// 3. Filter students table on viewstudents.html
const searchInput = document.getElementById("searchInput");
const levelFilter = document.getElementById("levelFilter");
const studentsTable = document.getElementById("studentsTable");

function filterStudentsTable() {
    const text = searchInput.value.toLowerCase();
    const level = levelFilter.value.toUpperCase();
    const rows = studentsTable.querySelectorAll("tbody tr");

    for (let i = 0; i < rows.length; i++) {
        const rowText = rows[i].textContent.toLowerCase();
        const rowLevel = rows[i].cells[4].textContent.toUpperCase();

        const matchesText = rowText.includes(text);
        const matchesLevel = level === "" || rowLevel === level;

        if (matchesText && matchesLevel) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}

if (searchInput && levelFilter && studentsTable) {
    searchInput.addEventListener("input", filterStudentsTable);
    levelFilter.addEventListener("change", filterStudentsTable);
}

// 4. Check empty fields before submitting Add / Edit Student form
const studentForm = document.getElementById("studentForm");
if (studentForm) {
    studentForm.addEventListener("submit", function (event) {
        const inputs = studentForm.querySelectorAll(".enter");
        let emptyFound = false;

        for (let i = 0; i < inputs.length; i++) {
            if (inputs[i].value.trim() === "") {
                inputs[i].classList.add("invalid");
                emptyFound = true;
            } else {
                inputs[i].classList.remove("invalid");
            }
        }

        if (emptyFound) {
            event.preventDefault();
            alert("Please fill in all fields before submitting.");
        }
    });
}