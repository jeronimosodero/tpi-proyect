<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
  <title>Buscador </title>

  <!-- CSS  -->
      <head>
      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
<link rel="stylesheet" type="text/css" href="css/main.css">
      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
</head>
<body>



      
  <nav class="grey darken-3" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Buscador</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="#">Buscar</a></li>
        <li><a href="#">Cargar</a></li>
        <li><a href="#">Conexiones</a></li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li><a href="#">Navbar Link</a></li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>

<main>
  <div class="container">
    


   <div class="section buscador">


   <br><br><br><br>
   <div class="todoscampos">
         <form action="resultados" id="my_form" method="post">
      <div class="row">
        <div class="input-field col s8 offset-s2">
          <input placeholder="" name="busqcampo" type="text" class="validate">
          <label for="busqcampo">BÃºsqueda por todos los campos</label>
        </div>
      </div>

      <div class="center-align">
        <a href="javascript:{}" onclick="document.getElementById('my_form').submit();" class="waves-effect waves-light btn red darken-4" type="submit"><i class="material-icons left">search</i>Buscar</a>
        <a class="waves-effect waves-light btn red darken-4 botoncampos"><i class="material-icons left">add</i>Buscar por campos</a>
      </div>
      </form>
    </div>
  </div>





    <div class="section resultados">
      <hr style="border-top: 1px dashed black">
        <ul class="collection">
         <c:forEach items="${oas.oas}" var="oa">
            <li class="collection-item avatar">
              <i class="circle red tooltipped" data-position="left" data-delay="50" data-tooltip="Estandar de metadatos"></i>
              <a class="title" href="${oa.url}">${oa.titulo}</a>
              <p class="autor">${oa.autor}</p>
              <p class="abstract">${oa.abst}</p>         
            </li>
         </c:forEach>    
  </ul>
      


      <hr style="border-top: 1px dashed black">




<div class="container center-align">
  <ul class="pagination">
    <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
    <li class="active"><a href="#!">1</a></li>
    <li class="waves-effect"><a href="#!">2</a></li>
    <li class="waves-effect"><a href="#!">3</a></li>
    <li class="waves-effect"><a href="#!">4</a></li>
    <li class="waves-effect"><a href="#!">5</a></li>
    <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
  </ul>
  </div>
    </div>
    
   

  
<br>


  </div>
</main>
  <footer class="page-footer red darken-4"> 
    
     
    <div class="footer-copyright grey darken-3">
      <div class="container">
      Sistema de Interoperabilidad Basado en OntologÃ­as para Repositorios de Objetos de Aprendizaje  
       <a class="orange-text text-lighten-3" href="http://www.frre.utn.edu.ar/">UTN FRRe</a>
      </div>
    </div>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
 
  <script src="js/init.js"></script>

  </body>
</html>
