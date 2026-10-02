
let button = document.getElementById('button').onclick = function evenorodd() {
    
const number = document.getElementById('number').value.trim();
let check = number % 2;
console.log(check)
if(number < 1){
    invalid(check)
}
else if(check == 0 ){
even (check)
 }
 else {
odd (check)
}

}

function invalid(number){
    
    const result = document.getElementById('result')
    result.textContent = "Sorry,number must greater than 0"

}
function even(check){
    const result = document.getElementById('result')
    result.textContent = "Your number is even"
}


function odd(check){
    const result = document.getElementById('result')
    result.textContent = "Your number is odd"

}
