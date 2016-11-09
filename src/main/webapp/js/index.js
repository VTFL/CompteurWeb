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
        alert('Info: WebSocket connection opened.');
    };
    ws.onmessage = function (event) {
        var test = document.getElementById("testTimer1");
        var testJson = JSON.parse(event.data);
        var string='';
        for(var i =0;i< testJson.length;i++) {
            string += "<br/>";
            string += "ID          : "+testJson[i].id + "<br/>";
            string += "Titre       : "+testJson[i].titre + "<br/>";
            string += "GMT         : "+testJson[i].gmt + "<br/>";
            string += "Echéance    : "+testJson[i].date + "<br/>";
            string += "TPS restant : "+testJson[i].majCompteur + "<br/><br/>";
            string += "<button type=\"button\" class=\"btn btn-info\">Modifier</button> ";
            string += "<button type=\"button\" class=\"btn btn-danger\">Supprimer</button><br/><br/>"
            string += "------------------------------------------------------------";
        }
        test.innerHTML= string;
    };
    ws.onclose = function (event) {
    };
}


$(function() {
    connect()
    /*pour le test*/
    $("#langue").val("France")
    $("#titre").val("aaaa")
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
        if(valid){
            var iduser = getCookie("userID")
            var data = {idSession : iduser,gmt : $("#langue").val() ,titre : $("#titre").val(),echeance : $("#echeance").val()};
            ws.send(JSON.stringify(data))
        }

        return valid
    })
});
