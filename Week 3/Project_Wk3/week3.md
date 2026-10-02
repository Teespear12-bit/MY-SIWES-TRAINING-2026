To make sure student data isn't lost when moving between different pages or closing the browser, I used the browser's localStorage.

How I stored the data:

Every student is created as a simple JavaScript object with properties like id, name, age, and course.

Because localStorage can only save plain text (strings), I collected all student objects into an array and then used JSON.stringify() to convert the array into a single text string before saving it under a key student_list.

How i retrieved the data:

Whenever a page opens or runs an operation (like viewing, updating, or deleting), the code grabs that text string from localStorage using localStorage.getItem("student_list").

Then, I used JSON.parse() to convert that text back into a normal JavaScript array so I can loop through it using a basic for loop.

How Unique IDs were Generated
Whenever a new student is added, the code loops through the existing list to find the highest ID number currently present, and assigns (highestId + 1) to the new student. This ensures that no two students ever get the same ID, even after deleting older records.