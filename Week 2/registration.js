

//Got this to get elements from html
const form = document.getElementById('FORM');
const firstName = document.getElementById('Firstname');
const lastName = document.getElementById('lastname');
const email = document.getElementById('email');
const number = document.getElementById('number');
const department = document.getElementById('department');
const level = document.getElementById('level');
const statuss = document.getElementById('status')



// To Get the values inputed and store them in those variables and check if specific conditions or errors are meet
function checkinputs() {
    
    const firstNameValue = firstName.value.trim();
    const lastNameValue = lastName.value.trim();
    const emailValue = email.value.trim();
    const numberValue = number.value.trim();
    const departmentValue = department.value.trim();
    const levelValue = level.value.trim();

//All my conitions for error message to show  
    if (firstNameValue.length <= 1){
        giveerror(firstName, "First name Invalid detail");
    }
    if (firstNameValue === '') {
        // alert("You did not enter this Detail" );
        giveerror(firstName, "You did not enter this Detail" );
    } 
    if (lastNameValue.length <= 1){
        giveerror(lastName, "Last name Invalid detail");
    }
    if (lastNameValue === '') {
        giveerror(lastName, "You did not enter this Detail");
    } 
    if (emailValue.length <= 2){
        giveerror(email, "Email Invalid detail");
    }
    if (emailValue === '') {
        
        giveerror(email, "You did not enter this Detail" );
    } 
    if (numberValue === '') {
        giveerror(number, "You did not enter this Detail" );
    } 
    if (numberValue.length != 11){
        giveerror(number , "Number Invalid detail");
    }
    if (departmentValue.length <= 3){
        giveerror(department, "Department Invalid detail");
    }
    if (departmentValue === '') {
        giveerror(department, "You did not enter this Detail" );
    } 
    if(levelValue === ''){
        giveerror(level, "Select something other than none");
       
    }
    if(levelValue == '600 LEVEL'){
        giveerror(level, "Level should be between 100-500");
        
    }

   //To display the success validation message
   if(firstNameValue.length >= 2 && firstNameValue != '' && lastNameValue != '' && lastNameValue.length >=2  && emailValue != '' && emailValue.length >= 4 && numberValue.length == 11  && numberValue != '' && departmentValue.length >= 3 && numberValue != '' && levelValue != '' && levelValue != '600 LEVEL'){
    statuss.style.visibility = 'visible';
   }
}

//Function used to change and make error message visiblle
function giveerror(input, message) {

    const parentTag = input.parentElement;
    const errorElement = parentTag.querySelector('.errormessage'); 
    errorElement.innerText = message;
    
    
    errorElement.style.visibility = 'visible'; 
}


form.addEventListener('submit', (e) => {
    e.preventDefault();
    checkinputs()
})

