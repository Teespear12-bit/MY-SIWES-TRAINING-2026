let checkForLargestNum = document.getElementById('button').onclick = function checkLargest(){

    let list = []

    let listOfNumbers1 = Number(document.getElementById('listOfNums1').value);
    let listOfNumbers2 = Number(document.getElementById('listOfNums2').value);
    let listOfNumbers3 = Number(document.getElementById('listOfNums3').value);
    let listOfNumbers4 = Number(document.getElementById('listOfNums4').value);
    let listOfNumbers5 = Number(document.getElementById('listOfNums5').value); 
    list.push(listOfNumbers1);
    list.push(listOfNumbers2);
    list.push(listOfNumbers3);
    list.push(listOfNumbers4);
    list.push(listOfNumbers5);
    console.log(list)
    let larNumber = Math.max(...list)
    console.log(larNumber)


    showanswer(list,larNumber)
   

}  

function showanswer(list, larNumber){
     let largestnum = document.getElementById('largestnum')
    largestnum.textContent = `The largest number from the list [${list}] is: ${larNumber} `

}




// let arr = [1,2,3,4,44,66,22,44,82];
// let max = Math.max(...arr)
// console.log (arr)
// console.log (max)