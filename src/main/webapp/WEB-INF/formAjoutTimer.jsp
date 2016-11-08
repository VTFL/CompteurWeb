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

