let math = 60;
let english = 90;

if (math < 0 || math > 100){
    console.log ("Invalid score (0-100)")
}
else if(math >= 80){
    console.log("Your math grade is a : A" )
}
else if ( math >= 70){
     console.log("Your math grade is a : B" )
}
else if ( math >= 55){
     console.log("Your math grade is a : C" )
}
else if ( math >= 50){
     console.log("Your math grade is a : D" )
}
else if ( math >= 40){
     console.log("Your math grade is a : E" )
}
else if ( math < 40){
     console.log("Your math grade is a : F" )
}

if (english < 0 || english > 100){
    console.log ("Invalid score (0-100)")
}
else if(english >= 85){
    console.log("Your english grade is a : A" )
}
else if ( english >= 70){
     console.log("Your english grade is a : B" )
}
else if (english >= 55){
     console.log("Your english grade is a : C" )
}
else if ( english >= 50){
     console.log("Your english grade is a : D" )
}
else if ( english >= 40){
     console.log("Your english grade is a : E" )
}
else if ( english < 40){
     console.log("Your english grade is a : F" )
}



let button = document.getElementById('button').onclick = function calculateGrade(){

    gradingSystem();
}



function gradingSystem(){
        let Math = Number(document.getElementById('math').value.trim());
        let English = Number(document.getElementById('english').value.trim());
        let CRS = Number(document.getElementById('crs').value.trim());
        let Overall = (Math + English + CRS)/3
        let MATH = document.getElementById('Math')
        let ENGLISH = document.getElementById('English')
        let cRs = document.getElementById('Crs')
        let results = document.getElementById('result')
        let mathResult;
        let englishResult;
        let crsResult;
        let overallResult;
        console.log(Overall)

if (Math < 0 || Math > 100){
    mathResult = "INVALID SCORE (0-100)"
}
else if(Math >= 80){
    mathResult ="MATHEMATICS: A" 
}
else if ( Math >= 70){
     mathResult = "MATHEMATICS: B" 
}
else if ( Math >= 55){
     mathResult = "MATHEMATICS: C" 
}
else if ( Math >= 50){
     mathResult = "MATHEMATICS: D" 
}
else if ( Math >= 40){
     mathResult = "MATHEMATICS: E" 
}
else if ( Math < 40){
     mathResult = "MATHEMATICS: F" 
}

if (English < 0 || English > 100){
    englishResult = "INVALID SCORE (0-100)"
}
else if(English >= 80){
    englishResult ="ENGLISH: A" 
}
else if ( English >= 70){
     englishResult = "ENGLISH: B" 
}
else if ( English >= 55){
     englishResult = "ENGLISH: C" 
}
else if ( English >= 50){
     englishResult = "ENGLISH: D" 
}
else if ( English >= 40){
     englishResult = "ENGLISH: E" 
}
else if ( English < 40){
     englishResult = "ENGLISH: F" 
}


if (CRS < 0 || CRS > 100){
    crsResult = "INVALID SCORE (0-100)"
}
else if(CRS >= 80){
    crsResult ="CRS: A" 
}
else if ( CRS >= 70){
     crsResult = "CRS: B" 
}
else if ( CRS >= 55){
     crsResult = "CRS: C" 
}
else if ( CRS >= 50){
     crsResult = "CRS: D" 
}
else if ( CRS >= 40){
     crsResult = "CRS: E" 
}
else if ( CRS < 40){
     crsResult = "CRS: F" 
}

if ( Overall < 0 || Overall > 100){
    overallResult = "INVALID SCORE (0-100)"
}
else if(Overall >= 80){
    overallResult ="OVERALL GRADE: A" 
}
else if ( Overall >= 70){
     overallResult = "OVERALL GRADE: B" 
}
else if ( Overall >= 55){
     overallResult = "OVERALL GRADE: C" 
}
else if ( Overall >= 50){
     overallResult = "OVERALL GRADE: D" 
}
else if ( Overall >= 40){
     overallResult = "OVERALL GRADE: E" 
}
else if ( Overall < 40){
     overallResult = "OVERALL GRADE: F" 
}


MATH.textContent = mathResult
ENGLISH.textContent = englishResult
cRs.textContent = crsResult
results.textContent =  overallResult  
}