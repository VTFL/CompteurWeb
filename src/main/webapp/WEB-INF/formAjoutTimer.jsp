<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Lucas-PC
  Date: 05/11/2016
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="divSpecial">
    <div style="text-align: center;font-size: 25px;">
       Ajouter un compteur
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

