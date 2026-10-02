let button = document.getElementById('button').onclick = function reverseString(){

        reverseIt();
       

}


function reverseIt(){
    let word = document.getElementById('word').value.trim();
    let newWord = ""
    for(let i = word.length - 1; i >= 0; i--){
         newWord += word[i] ;   
    }
    console.log (newWord)
    let reversed = document.getElementById('reversed')
    reversed.textContent = newWord;
}
