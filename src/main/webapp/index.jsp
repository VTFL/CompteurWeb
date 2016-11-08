<html>
<head><meta charset="UTF-8"/></head>
<body>
<link href="css/bootstrap.min.css" rel="stylesheet">
<h2>Ouiii</h2>

<blockquote>
    &laquo; Il reste
        <%
            String diff = (String) request.getAttribute("diff");
            out.println( diff );
	%>
</blockquote>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
