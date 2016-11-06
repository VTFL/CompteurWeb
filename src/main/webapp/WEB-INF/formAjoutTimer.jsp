<%--
  Created by IntelliJ IDEA.
  User: Lucas-PC
  Date: 05/11/2016
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th colspan="2">
                    <div id="error-message"> </div>
                </th>
            </tr>
            </thead>
            <tbody>

                <form method="post" action="Jesus.jsp">

                    <tr>
                        <td>
                            <b>Titre</b>
                        </td>
                        <td>
                            <input id="titre" class="timerForm"  name="titre" type="text" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Langue</b>
                        </td>
                        <td>
                            <select id="langue" class="timerForm" name="langue">
                                <%
                                    String[] listpays = (String[]) request.getAttribute("listpays");
                                    for(String pays : listpays){
                                        out.println("<option value="+pays+">"+pays+"</option>");
                                    }
                                %>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <b>Echeance</b>
                        </td>
                        <td>
                            <input id="echeance" class="timerForm" name="echeance" type="datetime-local" />
                        </td>
                    </tr>
                    <tr class="submitButton">
                        <td colspan="2">
                        <input id="submit" type="Button" value="Go !" class="btn btn-primary"/>
                        </td>
                    </tr>
                </form>
            </tbody>

        </table>
    </div>

<script type="text/javascript">
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
            alert('Received: ' + event.data);
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
            if(valid){
                var data = {langue : $("#langue").val() ,titre : $("#titre").val(),echeance : $("#echeance").val()};
                ws.send(JSON.stringify(data))
                alert(JSON.stringify(data))
            }

            return valid
        })
    });

</script>