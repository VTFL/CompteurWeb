<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="beans.Compteur" %>
<%@ page import="java.util.TreeMap" %><%--
  Created by IntelliJ IDEA.
  User: Florian
  Date: 14/11/2016
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><meta charset="UTF-8"/></head>
<body>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/compteurWeb.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<div class="container">
<div class="row">
    <div class="col-md-12">
        <div class="divModif">
            <div style="text-align: center;font-size: 25px;">
                Modifier un compteur
                <%
                    Compteur c = (Compteur) request.getAttribute("compteur");
                %>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th colspan="2">
                        <div id="error-message"> </div>
                    </th>
                </tr>
                </thead>
                <tbody>

                <form method="post" action="/CompteurWeb/index">

                    <tr>
                        <td>
                            <b>Titre</b>
                        </td>
                        <td>
                            <input id="titre" class="timerForm"  name="titre" type="text" <%out.println("value=\""+ c.getTitre() + "\"");%>/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Langue</b>
                        </td>
                        <td>
                            <select id="langue" class="timerForm" name="langue" <%out.println("value=\""+ c.getPays() + "\"");%>>


                                <%
                                    HashMap<String,Integer> locales = (HashMap<String,Integer>) request.getAttribute("locales");
                                    Map<String, Integer> treeMap = new TreeMap<String, Integer>(locales);

                                    for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {

                                        if(entry.getKey().equals(c.getPays())) {

                                            if(entry.getValue()>=0)
                                                out.println("<option selected=\"selected\" value=\"" + entry.getKey() + "\">" + entry.getKey() + " (GMT+" + entry.getValue()+")</option>");
                                            else
                                                out.println("<option selected=\"selected\" value=\"" + entry.getKey() + "\">" + entry.getKey() + " (GMT" + entry.getValue()+")</option>");
                                        }
                                        else {
                                            if(entry.getValue()>=0)
                                                out.println("<option value=\"" + entry.getKey() + "\">" + entry.getKey() + " (GMT+" + entry.getValue()+")</option>");
                                            else
                                                out.println("<option value=\"" + entry.getKey() + "\">" + entry.getKey() + " (GMT" + entry.getValue()+")</option>");
                                        }
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
                            <input id="echeance" class="timerForm" name="echeance" type="datetime-local" <%out.println("value=\""+ c.getDate() + "\"");%> />
                        </td>
                    </tr>
                    <tr class="submitButton">
                        <td colspan="2">
                            <input id="id" type="hidden" name="id" <%out.println("value=\""+ c.getId() + "\"");%>/>
                            <input id="idSession" type="hidden" name="idSession" <%out.println("value=\""+ c.getIdSession() + "\"");%>/>
                            <input id="modif" type="hidden" value="modif" name="modif"/>
                            <input id="submit" type="submit" value="Modifier" class="btn btn-primary"/>
                            <input type="button" id="annuler" value="Annuler" class="btn btn-danger" onclick="location.href='/CompteurWeb/index'"/>
                        </td>
                    </tr>
                </form>
                </tbody>

            </table>

        </div>
    </div>
</div>
</div>

<div class="container">
    <div class="row">
        <hr>
        <div class="col-lg-12">
            <div class="col-md-8">
                <a href="http://pigne.org/teaching/javawebapptools/lab/WebAppLab">TP CAW</a> | <a href="https://github.com/VTFL/CompteurWeb">GitHub/CompteurWeb</a>
            </div>
            <div class="col-md-4">
                <p class="muted pull-right" style="font-size:11px">Alline Florian - Defortescu Thibault - Patard Lucas - Pitel Valentin</p>
            </div>
        </div>
    </div>
</div>
</body>

<script>$(document).ready(function() {
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

        }

        return valid
    })
});
</script>
</html>


