`I initially misread the assignment and did a web version intead of just a simple console based project,but i later added the code to the console based version to it(operations.js)`

### For the cosole based 

To make sure student data can be created, viewed, searched, updated, and deleted while the program is running, I used an in-memory JavaScript array.

How I stored the data:

* Every student is created as a simple JavaScript object with properties like id, name, age, and course.
* It collects all student objects into a main array called students (e.g., let students = []).
* Whenever a new student is created, I used students.push(newStudent) to add the new student object to the end of the array.

How I retrieved the data:

* Whenever the program needs to display, search, update, or delete records, it accesses the students array directly.
* I used a basic for loop (e.g., for (let i = 0; i < students.length; i++)) to cycle through the array step-by-step:
* Viewing: It reads each student's properties and combines them into text shown using alert().
* Searching:It uses .toLowerCase().includes() on the student's name or course to check if it matches what was typed into the prompt().
* Updating: It finds the matching student ID in the loop and reassigns their properties directly.
* Deleting: It finds the matching student ID and removes them from the array using students.splice(i, 1).

How Unique IDs were Generated:

* A global tracker variable called 'nextId' starts at '1'.
* Whenever a new student is added, they automatically get assigned nextId, and then nextId is increased by 1 (nextId = nextId + 1).
* This ensures that every new student gets a unique ID number, even if older students are deleted from the list.


### For the web

To make sure student data isn't lost when moving between different pages or closing the browser, I used the browser's localStorage.

How I stored the data:

Every student is created as a simple JavaScript object with properties like id, name, age, and course.

Because localStorage can only save plain text (strings), I collected all student objects into an array and then used JSON.stringify() to convert the array into a single text string before saving it under a key student_list.

How i retrieved the data:

Whenever a page opens or runs an operation (like viewing, updating, or deleting), the code grabs that text string from localStorage using localStorage.getItem("student_list").

Then, I used JSON.parse() to convert that text back into a normal JavaScript array so I can loop through it using a basic for loop.

How Unique IDs were Generated
Whenever a new student is added, the code loops through the existing list to find the highest ID number currently present, and assigns (highestId + 1) to the new student. This ensures that no two students ever get the same ID, even after deleting older records.