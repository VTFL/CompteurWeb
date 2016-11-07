
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
        <div class="col-md-4"></div>
        <div class="col-md-4"><%@include file="WEB-INF/formAjoutTimer.jsp" %></div>
        <div class="col-md-4"></div>
    </div>
    <div class="row">
        <%@include file="WEB-INF/timer.jsp" %>
    </div>
</div>

<!--test à supprimer par la suite
<div class="container">
    <div class="row">
        <%//@include file="WEB-INF/formTestSocket.jsp" %>
    </div>
</div> -->


</body>
</html>
