$(document).ready(function() {
    var example = document.getElementById("areas"),
        ctx = example.getContext('2d');
    example.width = 300;
    example.height = 300;
    printArea(4);

    /*console.log(document.getElementById("R:0").onclick);

    console.log(document.getElementById("R:1"));*/
    example.addEventListener('click', function (event) {
        var elemLeft = example.offsetLeft,
            elemTop = example.offsetTop;
        var x = event.pageX - elemLeft,
            y = event.pageY - elemTop;
        paintArc((x-150)/20, ((y-150)*-1)/20);
        /*console.log((x-150)/20, ((y-150)*-1)/20);
        console.log(gR());*/

        $('#formHiddenR').val(gR());
        $('#formHiddenX').val((x-150)/20);
        $('#formHiddenY').val(((y-150)*-1)/20);

        canvasAction();
        refresh();
        drawPoints();

    });
});

function refresh() {
    $('#formHiddenX').val('');
    $('#formHiddenY').val('');
    $('#formHiddenR').val('');
    $('#Y').val('');
}

function gR() {
    if (document.getElementById ("R:0").checked)
        return 1;
    else if (document.getElementById ("R:1").checked)
        return 2;
    else if (document.getElementById ("R:2").checked)
        return 3;
    else if (document.getElementById ("R:3").checked)
        return 4;
    else if (document.getElementById ("R:4").checked)
        return 5;
}


/*let isDefinedX = false;
let R = 0;
function validateForm(){
    $("#messageX").html("<br/>");
    let flag = true;
    if(!isDefinedX){
        $("#messageX").html("Выберите одно из значений X");
        flag = false;
    }
    if(!validateY()){
        flag = false;
    }
    if(!validateR()){
        return false;
    }
    return flag;
}*/

function validateY(){
    let y = $('#Y').val().trim();
    console.log(y);
    $("#messageY").html("<br/>");
    if(y==""){
        $("#messageY").html("значение Y должно быть числом в диапозоне (-5;5)");
    } else {
        if(!/^(-?\d+)([.,]\d+)?$/.test(y)) {
            $("#messageY").html("значение Y должно быть числом в диапозоне (-5;5)");
        } else {
            y = y.replace(',','.');
            y = Number(y);
            if (!(y > -5 && y < 5)) {
                $("#messageY").html("значение Y должно быть числом в диапозоне (-5;5)");
            } else {
                return true;
            }
        }
    }
    return false;
}

/*function validateR(){
    let radius = $("#R").val().trim();
    $("#messageR").html("<br/>");
    if(radius==""){
        $("#messageR").html("значение R должно быть числом в диапозоне (1;4)");
    } else {
        if(!/^(-?\d+)([.,]\d+)?$/.test(radius)) {
            $("#messageR").html("значение R должно быть числом в диапозоне (1;4)");
        } else {
            radius = radius.replace(',','.');
            radius = Number(radius);
            if (radius > 1 && radius < 4) {
                return true;
            } else {
                $("#messageR").html("значение R должно быть числом в диапозоне (1;4)");
            }
        }
    }
    return false;
}*/






function printArea(R) {

    var example = document.getElementById("areas"),
        ctx = example.getContext('2d');
    ctx.clearRect(0, 0, 300, 300);
    ctx.strokeRect(0, 0, 300, 300);
    ctx.beginPath();
    ctx.font = "15px Arial";
    ctx.fillText("y", 160, 20);
    ctx.fillText("x", 280, 170);

    ctx.fillStyle = "blue";
    let r = R*20;
    ctx.translate(150, 150);
    ctx.arc(0, 0, r, 0, (Math.PI/180)*90);
    ctx.moveTo(0, r);
    ctx.lineTo(0, 0);
    ctx.lineTo(r, 0);
    ctx.fillRect(-r, 0, r, r);
    ctx.moveTo(0, 0);
    ctx.lineTo(r, 0);
    ctx.lineTo(0, -r/2);
    ctx.lineTo(0, 0);
    ctx.fill();
    ctx.translate(-150, -150);

    ctx.moveTo(10, 150);        //ось х
    ctx.lineTo(290,150);        //
    ctx.lineTo(275, 145);       //стрелочка
    ctx.moveTo(290, 150);       //
    ctx.lineTo(275, 155);       //
    ctx.moveTo(150, 290);       //ось у
    ctx.lineTo(150, 10);        //
    ctx.lineTo(145, 25);        // стрелочка
    ctx.moveTo(150, 10);        //
    ctx.lineTo(155, 25);
    for (let i = 30; i <= 270; i+=20) {
        ctx.moveTo(i, 145);
        ctx.lineTo(i, 155);
        ctx.moveTo(145, i);//ось y
        ctx.lineTo(155, i);
    }
    for (let i = 40; i <= 260; i+=20) {
        ctx.moveTo(i, 147);
        ctx.lineTo(i, 153);
        ctx.moveTo(147, i);
        ctx.lineTo(153, i);
    }

    ctx.fillStyle = 'black';
    ctx.stroke();

    //функция отрисовки
}


function drawPoints(){
    let R = gR();
    let canvas = document.getElementById('areas');
    let ctx = canvas.getContext('2d');
    let rows = $('#result_table').find('tr');
    let number = rows.length;
    for(let i = 1; i < number; i++){
        let tdSet = $(rows[i]).find('td');
        let r = Number($(tdSet[2]).text().trim());
        console.log(r);
        if(r == R) {
            if ($(tdSet[3]).text().trim() == 'true') {
                ctx.fillStyle = 'green';
            } else {
                ctx.fillStyle = 'red';
            }
        } else {
            ctx.fillStyle = 'grey';
        }
        if(String($(tdSet[0]).text().trim())!=""){

        }
        paintArc($(tdSet[0]).text(), $(tdSet[1]).text());
        console.log($(tdSet[0]).text(), $(tdSet[1]).text());
        ctx.fillStyle = 'black';
        /*console.log($(tdSet[0]).text(), $(tdSet[1]).text(),$(tdSet[3]).text());*/
    }
}

function paintArc(x,y) {
    let canvas = document.getElementById('areas');
    let ctx = canvas.getContext('2d');
    let xArc= x*20+150;
    let yArc= (y*20-150)*-1;
    ctx.beginPath();
    ctx.arc(xArc, yArc, 3, 0, 2 * Math.PI, false);
    ctx.closePath();
    ctx.fill();

}


