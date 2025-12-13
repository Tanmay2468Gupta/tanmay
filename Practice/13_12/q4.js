// function x(){
//     for(var i=1;i<=5;i++){// var has function schope not block schope
//         setTimeout(function (){
//             console.log(i);
//         },i*1000);
//     }
// }

// x();
function x(){
    for(let i=1;i<=5;i++){// var has function schope not block schope
        setTimeout(function (){
            console.log(i);
        },i*1000);
    }
}

x();