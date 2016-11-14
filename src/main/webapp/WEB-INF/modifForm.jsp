<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="beans.Compteur" %><%--
  Created by IntelliJ IDEA.
  User: Florian
  Date: 14/11/2016
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><meta charset="UTF-8"/><h1>Ma bite mon zboub tmtc</h1></head>
<body>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/compteurWeb.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<div class="row">
    <div class="col-md-12">
        <div class="divModif">
            <div style="text-align: center;font-size: 25px;">
                Modifier un compteur
                <h1>Ma bite mon zboub tmtc</h1>
                <%
                    Compteur c = (Compteur) request.getAttribute("compteur");

                    out.println(c.toString());
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
                                    HashMap<String,Integer> locales = (HashMap<String,Integer>) request.getAttribute("locales");

                                    for (Map.Entry<String, Integer> entry : locales.entrySet()) {

                                        if(entry.getValue()>=0)
                                            out.println("<option value=\"" + entry.getKey() + "\">" + entry.getKey() + " (GMT+" + entry.getValue()+")</option>");
                                        else
                                            out.println("<option value=\"" + entry.getKey() + "\">" + entry.getKey() + " (GMT" + entry.getValue()+")</option>");
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
    </div>
</div>
</body>
</html>
