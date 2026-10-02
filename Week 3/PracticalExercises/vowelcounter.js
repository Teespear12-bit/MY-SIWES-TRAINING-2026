let word2 = "abebiolu"
console.log (word2)
let vcounter = 0
for(let i = 0; i <= word2.length - 1 ; i++){
    if(word2.charAt(i).includes("a") || word2.charAt(i).includes("e") || word2.charAt(i).includes("i") || word2.charAt(i).includes('o') || word2.charAt(i).includes('u'))
        vcounter++
}
console.log (vcounter)

let button = document.getElementById('button').onclick = function countVowels(){
        let total = checkForVowels();
        showAnswers(total)
        
}

function checkForVowels(){
    let initword = document.getElementById('word').value.trim();
    console.log(initword)
    let vowelCounter = 0;
    let word = initword.toLowerCase()
    for(let i = 0; i <= word.length - 1 ; i++){
        if(word.charAt(i).includes('a') || word.charAt(i).includes('e') || word.charAt(i).includes('i') || word.charAt(i).includes('o') || word.charAt(i).includes('u')  ){
            vowelCounter++;
        }
    
    }
        
        return vowelCounter
}

function showAnswers(vowelCounter){
    let result = document.getElementById('result')
    console.log(vowelCounter)
    result.textContent = `There are ${vowelCounter} vowels in your word`
}