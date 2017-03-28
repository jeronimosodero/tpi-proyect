<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>Conexiones</title>

  <!-- CSS  -->
      <head>
      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
     
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
      <link rel="stylesheet" type="text/css" href="css/main2.css">

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
</head>
<body>



      
  <nav class="grey darken-3" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Gestión de Conexiones</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="#">Buscar</a></li>
        <li><a href="#">Cargar</a></li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="#">Navbar Link</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
<main>

  <div class="container">

    


   <div class="section">
     <form action="conexiones" method="post"  id="conexion_load">
      <h5 class="enca">Carga de Conexiones</h5>
      <form class="col s12">
      <div class="row">

        <div class="input-field col s3">
          <input name="nombre" id="name" type="text" class="validate">
          <label for="name">Nombre</label>
        </div>
        <div class="input-field col s5">
          <input name="url" id="url" type="url" class="validate">
          <label for="url" data-error="wrong" data-success="right">OAI-PMH URL</label>
        </div>
        <div class="input-field col s3">
          <select name="estandar">
            <option value="" disabled selected>Elija el estándar</option>
            <option value="oai_dc">DC</option>
            <option value="mods">MODS</option>
           
          </select>
          <label>Estándar de Metadatos</label>   
        </div><button name="boton" type="submit" value="guardar">
        <a href="javascript:{}" onclick="document.getElementById('conexion_load').submit();"  class="btn btn-floating btn-flat" style="border-top-width: 15px;margin-top: 18px;margin-left: 5px;">
          <i class="small material-icons red darken-4">send</i>
        </a>
        </button>
      </div>
    </form>
    </form>
  </div>
    <div class="section">
    <form action="conexiones" method="post"  id="conexion_del">

    <h5 class="enca">Listado de Conexiones</h5>


    <table>
        <thead>
          <tr>
              <th data-field="id">#</th>
              <th data-field="name">Nombre Repositorio</th>
              <th data-field="url">OAI-PMH URL</th>
              <th data-field="meta">Estándar de Metadatos</th>
              <th data-field="meta"></th>
          </tr>
        </thead>

        <tbody>
         <c:forEach items="${cxs.conexiones}" var="c">
      		<tr>
     			<td>${c.id}</th>
      			<td>${c.nombre}</td>
      			<td>${c.url}</td>
      			<td>${c.estandar}</td>
        		<td><button type="submit" value="${c.id}" name="boton"><a href="javascript:{}" onclick="document.getElementById('conexion_del').submit();"  class="btn btn-floating btn-flat"><i class="small material-icons red darken-4">delete</i></a></button></td>
    		</tr>
 		</c:forEach>
         
         
         
         
         
         
         
         
        </tbody>
      </table>

</form>
    </div>
    
   

  
<br>

	
  </div>
</main>
  <footer class="page-footer grey darken-3"> 
    <form action="conexiones" method="post" id="conexion_act">
      <c:if test="${not empty fecha}">


      
      <div class="footer-copyright red darken-4">
      <div class="container">
      La última actualización se realizó el día <span>${fecha}</span>
      <button name="boton" type="submit" value="guardar">
        <a href="javascript:{}" onclick="document.getElementById('conexion_act').submit();" style="border-top-width: 15px;margin-top: 18px;margin-left: 5px;">
        Actualizar	
        </a>
        </button>
      </div>
    </div>
    
    </c:if>
    
    <div class="footer-copyright">
      <div class="container">
      Sistema de Interoperabilidad Basado en Ontologías para Repositorios de Objetos de Aprendizaje  
       <a class="orange-text text-lighten-3" href="http://www.frre.utn.edu.ar/">UTN FRRe</a>
      </div>
    </div>
    </form>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>

  </body>
</html>
