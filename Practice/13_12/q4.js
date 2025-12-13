// function x(){
//     for(var i=1;i<=5;i++){// var has function schope not block schope
//         setTimeout(function (){
//             console.log(i);
//         },i*1000);
//     }
// }

// x();
// function x(){
//     for(let i=1;i<=5;i++){// var has function schope not block schope
//         setTimeout(function (){
//             console.log(i);
//         },i*1000);
//     }
// }

// x();




// function x(){
//     for(var i=1;i<=5;i++){
//         (function(j){
//             setTimeout(function(){
//                 console.log(j);
//             },j*1000);
//         })(i);
//     }
// }
// x();


// callback function
function add(a,b){
    return a+b;
}
function sub(a,b){
    return a-b;
}

function solve(a,b,exp){
    return exp(a,b);
}
console.log(solve(10,5,add));
console.log(solve(10,5,sub));