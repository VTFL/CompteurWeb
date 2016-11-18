/**
 * Created by Lucas-PC on 09/11/2016.
 */
var ws = null;
function connect() {
    var target ='ws://' + window.location.host+"/CompteurWeb/websocket/CreateTimer";

    if ('WebSocket' in window) {
        ws = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(target);
    } else {
        alert('WebSocket is not supported by this browser.');
        return;
    }
    ws.onopen = function () {
    };
    ws.onmessage = function (event) {
        var test = document.getElementById("testTimer1");
        var testJson = JSON.parse(event.data);
        var string='';
        for(var i =0;i< testJson.length;i++) {

            string += "<div class=\"col-md-6 col-sm-6\">";
            if(testJson[i].majCompteur=="------------------- FIN -------------------------")
                string += "<div class=\"divCompteurFIN\">";
            else
                string += "<div class=\"divSpecial\">";

            string += "<div class='nomCompteur' >";
            //string += "ID          : "+testJson[i].id + "<br/>";
            string += "<b><h2>"+testJson[i].titre+"</h2></b>";
            string += "</div>";
            string += "<b>Pays</b>        : "+testJson[i].pays + "<br/>";
            string += "<b>Ech&eacute;ance</b>    : "+testJson[i].date + "<br/>";
            string += "<b>TPS restant</b> : "+testJson[i].majCompteur + "<br/><br/>";
            string += "<div style='text-align: center'>";
            string += "<form method=\"post\" action=\"/CompteurWeb/modifTimer\">";
            string += "<input type=\"hidden\" name=\"id\" value=\""+ testJson[i].id +"\">";
            string += "<button id=\"modif\" name=\"modif\" type=\"submit\" class=\"btn btn-info\">Modifier</button> ";
            string += "<button id=\"supp\" name=\"supp\" type=\"button\" class=\"btn btn-danger\" " +
                "onclick=\"supprimer("+testJson[i].id+")\">Supprimer</button><br/><br/>";
            string += "</form>";
            string += "</div>";
            string += "</div>";
            string += "</div>";
        }
        test.innerHTML= string;


    };
    ws.onclose = function (event) {
    };
}

function getCookie(cname) {

    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function refuserToucheEntree(event)
{
    // Compatibilité IE / Firefox
    if(!event && window.event) {
        event = window.event;
    }
    // IE
    if(event.keyCode == 13) {
        event.returnValue = false;
        event.cancelBubble = true;
    }
    // DOM
    if(event.which == 13) {
        event.preventDefault();
        event.stopPropagation();
    }
}

$(function() {
    connect()
    /*pour le test*/
    $("#langue").val("France")
    $("#titre").val("TitreCompteur")
    $("#echeance").val("2020-01-24T01:01")
    $("#submit").click(function(){
        var valid = true
        $("#error-message").text(" ");
        $("#titre").css("border-color","");
        $("#echeance").css("border-color","");

        if($("#titre").val() === ""){
            $("#titre").css("border-color","red");
            $("#error-message").text("Veuillez entrer un titre");
            valid = false
        }
        if($("#titre").val().match(/[^a-z A-Z 0-9]/)){
            $("#titre").css("border-color","red");
            $("#error-message").text("Le titre ne peut être composé que de lettres ou de chiffres");
            valid = false
        }
        if($("#echeance").val() === ""){
            $("#echeance").css("border-color","red");
            $("#error-message").text("Veuillez entrer une échéance");
            valid = false
        }
        if(new Date($("#echeance").val()) < new Date()){
            $("#echeance").css("border-color","red");
            $("#error-message").text("L'échéance ne peut pas être dans le passé");
            valid = false
        }
        if(valid){

            var iduser = getCookie("userID")
            var data = {
                    idSession: iduser
                    , pays: $("#langue").val()
                    , titre: $("#titre").val()
                    , echeance: $("#echeance").val()
                    , action: "add"
                };

            ws.send(JSON.stringify(data));
        }

        return valid
    })

});

function supprimer(id) {
    var data = {id : id
        ,action : "remove"
    };
    ws.send(JSON.stringify(data));
}

function alea() {
    var iduser = getCookie("userID")
    var data = {idSession: iduser
        ,pays : "France"
        ,action : "random"
    };
    ws.send(JSON.stringify(data));
}


