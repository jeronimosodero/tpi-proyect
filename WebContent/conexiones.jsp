<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Website template by freewebsitetemplates.com -->
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Conexiones</title>
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
<legend>Gestión de conexiones</legend>


<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Direccion de la conexion</th>
      <th>Estandar de metadatos</th>
      <th>Repositorio</th>
      <th>Lenguaje</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${cxs.conexiones}" var="c">
      <tr>
      <th scope="row">${c.id}</th>
      <td>${c.url}</td>
      <td>${c.estandar}</td>
      <td>${c.repositorio}</td>
      <td>${c.lenguaje}</td>
      <td><button type="submit" value="${c.id}"name="action" class="btn btn-xs btn-primary">Eliminar</button></td>
    </tr>
 	</c:forEach>
   </tbody>
</table>


<!-- Text input-->
<div  class="row">
  <div class="col-lg-4">
<label for="basic-url">Dirección de la conexión</label>
<div class="input-group">
  <span class="input-group-addon" id="basic-addon3">https://</span>
  <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3">
</div>
</div>
<div class="col-lg-2">
<div class="form-group">
  <label for="sel1">Estandar de metadatos</label>
  <select class="form-control" id="sel1">
    <option>DC</option>
    <option>LOM</option>
    <option>MODS</option>
    <option>CERIF</option>
    <option>MARC</option>
  </select>
</div>
</div>
<div class="col-lg-2" style="padding-left: 30px;">
<div class="form-group">
  <label for="sel1">Repositorio</label>
  <select class="form-control" id="sel1">
    <option>DSpace</option>
    <option>Eprints</option>
  </select>
</div>
</div>

<div class="col-lg-2" style="padding-left: 	30px;">
<div class="form-group">
  <label for="sel1">Lenguaje</label>
  <select class="form-control" id="sel1">
    <option>Español (es)</option>
    <option>Inglés - Estados Unidos (en___US)</option>
    <option>Inglés (en)</option>
    <option>Alemán (de)</option>
    <option>Frances (fr)</option>
    <option>Italiano (it)</option>
    <option>Japonés (ja)</option>
    <option>Chino (zh)</option>
    <option>Otro</option>
  </select>
</div>
</div>


<div class="col-lg-2" style="padding-left: 	30px;">
<button type="submit" class="btn btn-primary" style="margin-top: 25px">Guardar</button>
</div>


</div>
<!-- Button -->


</fieldset>
</form>
</div>
    </div><!-- /.container -->


</body>
</html>