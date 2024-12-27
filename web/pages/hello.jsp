<%
    String nom = (String) request.getAttribute("moi");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
        <h1>Hello word, et bonjour a vous <% out.print(nom); %> </h1> 
    </body>
</html>