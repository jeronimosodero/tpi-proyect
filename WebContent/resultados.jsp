




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Resultados</title>
 
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="css/main2.css">

</head>
<body>

<div class="container">

    <form class="well form-horizontal" action=" " method="post"  id="contact_form">
<fieldset>

<!-- Form Name -->
<h1>Resultados</h1>

<c:if test="${empty oas.oas}">
<h2>No hay resultados...</h2>
</c:if>


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