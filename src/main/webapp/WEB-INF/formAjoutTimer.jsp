<%--
  Created by IntelliJ IDEA.
  User: Lucas-PC
  Date: 05/11/2016
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-4">
    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th colspan="2">
                    A vous d'ajouter un Timer
                </th>
            </tr>
            </thead>
            <tbody>

                <form class="timerForm" method="post" action="traitement.php">

                    <tr>
                        <td>
                            <b>Titre</b>
                        </td>
                        <td>
                            <input  name="titre" type="text" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Langue</b>
                        </td>
                        <td>
                            <select name="langue">
                                <option value="France">France</option>
                                <!-- etc... -->
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <b>Echeance</b>
                        </td>
                        <td>
                            <input name="echeance" type="datetime-local" />
                        </td>
                    </tr>
                </form>
            </tbody>

        </table>
    </div>
</div>