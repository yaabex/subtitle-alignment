<html>
<head>
    <!-------------------------------------------------
        Look at you, reading my source code! :D

        I hope you find this readable...    
    -------------------------------------------------->
    <meta charset="utf-8">
    <title>O Cinema ao serviço do Processamento da Língua Natural</title>
    <link href='css/styles.css' rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/scripts.js"></script>
    <?
        $id = $_GET["id"] or die("Invalid Subtitle ID<br>Please contact admin");
        
        $dir = "results/".$id;
        $filename = $id.".3.".time().".txt";

        if( is_dir($dir) === false ){
            mkdir($dir);
        }
    
        $txt = "";
        for($i = 1; $i<16; $i++){
            $txt .= $_POST["a".$i]."\n";
        }

        $myfile = fopen($dir."/".$filename, "w") or die("Unable to save results<br>Please contact the admin");
        fwrite($myfile, $txt);
        fclose($myfile);
    ?>  
</head>
<body>
    <header>
        <b>O cinema ao serviço do Processamento da Língua Natural</b> | Tese de Mestrado - Luís Rosado
    </header>
    <center>
        <div id='instructions'><br><br>
            <b>Concluído!</b><br><br>
            Muito obrigado pela tua preciosa ajuda!
        </div>
    </center>
</body>