<#import "/spring.ftl" as spring/>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/main.css"/>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" >

function printOnConsole(obj){
    console.log(obj.value);
}

function liveSearch(name){


    const url = "http://localhost:8082/names/"+name;
    console.log("calling " + url);
    $.ajax({
        async: false,
        type: "GET",
        url: url,
        success: function(result){

            console.log(result);
            document.getElementById("name").value = result ;
            //$("#name").value(result);

          //$(document).html(result);
          //window.location.href = result.data;
          //location.reload(true);
        }});
      /*
      if ( "davidone".match(name) ){
        document.getElementById("name").value = "davidone" ;
      }
      */
}

</script>
</body>
</html>