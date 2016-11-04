<html>
<head><meta charset="UTF-8"/></head>
<body>
<link href="css/bootstrap.min.css" rel="stylesheet">




<div class="container">
    <div class="row">
        <div class="col-md-10"></div>
        <div class="col-md-2">
            <table>
                <form method="post" action="traitement.php">
                    <tr>
                        <td>
                            Titre
                        </td>
                        <td>
                            <input name="titre" type="text" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Langue
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
                            Echeance
                        </td>
                        <td>
                            <input name="echeance" type="datetime-local" />
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
