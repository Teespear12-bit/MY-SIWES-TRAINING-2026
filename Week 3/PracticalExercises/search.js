// let studentsNames = ["john" , "james" , "jack" , "matthew", "elisha"]

// let yes = studentsNames.includes("John")
// console.log(yes)


let button = document.getElementById('button').onclick = function search(){
let studentsNames = ["john" , "james" , "jack" , "matthew", "elisa"]
let name = String(document.getElementById('name').value.trim())
let response = document.getElementById('response')
let Name = name.toLowerCase()
if(studentsNames.includes(Name)){
    response.textContent = "Student Found"
}
else{
    response.textContent = "Student NOT Found"
}
}