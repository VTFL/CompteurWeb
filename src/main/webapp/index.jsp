
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
        <div class="col-md-12"><%@include file="WEB-INF/formAjoutTimer.jsp" %></div>
    </div>
    <div class="row">
        <%@include file="WEB-INF/timer.jsp" %>
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


<script type="text/javascript">
    <%@include file="js/index.js" %>
</script>
</body>
</html>
