  $(document).ready(function() {
    $('select').material_select();
    $('.tooltipped').tooltip({delay: 50});

    var added = '	<div class="row">\
  <div class="input-field col s3 offset-s2">\
    	<select name="parametros">\
        <optgroup label="DC">\
        <option value="dc|title">DC title</option>\
         <option value="dc|creator">DC creator</option>\
         <option value="dc|subject">DC subject</option>\
         <option value="dc|description">DC description</option>\
         <option value="dc|publisher">DC publisher</option>\
         <option value="dc|contributor">DC contributor</option>\
         <option value="dc|date">DC date</option>\
         <option value="dc|type">DC type</option>\
         <option value="dc|format">DC format</option>\
         <option value="dc|identifier">DC identifier</option>\
         <option value="dc|source">DC source</option>\
         <option value="dc|language">DC language</option>\
         <option value="dc|relation">DC relation</option>\
         <option value="dc|coverage">DC coverage</option>\
         <option value="dc|rights">DC rights</option>\
         </optgroup>\
       <optgroup label="MODS">\
         <option value="mods|namePart">MODS namePart</option>\
         <option value="mods|dateAccessioned">MODS dateAccessioned</option>\
         <option value="mods|dateIssued">MODS dateIssued</option>\
         <option value="mods|identifier">MODS identifier</option>\
         <option value="mods|abstract">MODS abstract</option>\
         <option value="mods|language">MODS language</option>\
         <option value="mods|accessCondition">MODS accessCondition</option>\
         <option value="mods|topic">MODS topic</option>\
         <option value="mods|subject">MODS subject</option>\
         <option value="mods|title">MODS title</option>\
         <option value="mods|namePart">MODS namePart</option>\
         <option value="mods|namePart">MODS namePart</option>\
         <option value="mods|namePart">MODS namePart</option>\
         <option value="4">MODS abstract</option>\
       </optgroup>\
      </select>\
    <label>Campo</label>\
  </div>\
  <div class="input-field col s4">\
    <input placeholder="" name="campo" id="busqcampo" type="text" class="validate">\
    <label for="busqcampo">Contenido</label>\
  </div>\
    <div class="input-field col s1 offset-s1">\
    <a class="btn btn-floating btn-flat remove_field"><i class="small material-icons red darken-4">delete</i></a>\
  </div>\
</div> '
    


    var porcampos = ' <form action="resultados" id="my_form2" method="post">\
    	<div class="cadacampo">\
<div class="filas">'+added+'\
	<div class="center-align">\
        <a href="javascript:{}" onclick="document.getElementById(\'my_form2\').submit();" class="waves-effect waves-light btn red darken-4"><i class="material-icons left">search</i>Buscar</a>\
        <a class="waves-effect waves-light btn red darken-4 add_field_button"><i class="material-icons left">add</i>Agregar campos</a>\
      </div>\
</div>\
      </form>'


    $(".botoncampos").click(function(e){
    	e.preventDefault();
    	
    	$(this).parent('div').parent('form').parent('div').remove();
    	
    	$(".buscador").append(porcampos)
    	$('select').material_select(); 


    	$(".add_field_button").click(function(e){ //on add input button click
    		e.preventDefault();        
    		$(".filas").prepend(added);
    		$('select').material_select(); //add input box        
    	});
    
    	$(".filas").on("click",".remove_field", function(e){ //user click on remove text
    		e.preventDefault(); $(this).parent('div').parent('div').remove();
    	})
    })




  });




