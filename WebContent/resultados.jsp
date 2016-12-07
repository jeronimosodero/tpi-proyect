<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Resultados</title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <script src="js/inicio.js" type="text/javascript"></script>
  <script src="js/bootstrap.js" type="text/javascript"></script>
</head>
<body>

<div class="container">

    <form class="well form-horizontal" action=" " method="post"  id="contact_form">
<fieldset>

<!-- Form Name -->
<legend>Resultados</legend>

<ul class="ds-artifact-list list-unstyled">

 <c:forEach items="${oas.oas}" var="oa">
	<li class="ds-artifact-item">
	<div class="artifact-description">
	<h4 class="artifact-title">
	<a href="${oa.url}">${oa.titulo}</a></h4>
	<div class="artifact-info">
	<span class="author h4"><small>${oa.autor}</small></span> <span class="publisher-date h4"><small>(<span class="date">${oa.fecha}</span>)</small></span>
	</div>
	<div class="artifact-abstract">${oa.abst}</div>
	</div>
	</li>
 </c:forEach>

</ul>


</fieldset>
</form>
</div>
    </div><!-- /.container -->


</body>
</html>