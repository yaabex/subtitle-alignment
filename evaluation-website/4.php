<head>
    <!-------------------------------------------------
        Look at you, reading my source code! :D

        I hope you find this readable...    
    -------------------------------------------------->
    <meta charset="utf-8">
    <title>O cinema ao serviço do Processamento da Língua Natural</title>
    <link href='css/styles.css' rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/scripts.js"></script>
    <?
        $id = $_GET["id"] or die("Invalid Subtitle ID<br>Please contact admin");
        $nameEN = "data/" . $id . "/" . $id . ".3.en.txt";
        $namePT = "data/" . $id . "/" . $id . ".3.pt.txt";
        
        $dir = "results/".$id;
        $filename = $id.".2.".time().".txt";

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
        <div id='instructions'>
            Agora, seleciona mais <b>15 correspondências</b>, clicando nas frases em Inglês e nas suas correspondentes em Português.<br>
            <b>Valida</b> as correspondências, <b>uma a uma</b>, clicando no <b style='color:green'>botão verde</b>. Evita escolher muitas frases consecutivas.<br>
            &#8226; Caso uma frase não tenha correspondente na outra língua, podes validar sem selecionar nenhuma do outro lado.<br>
            &#8226; Caso uma frase necessite de mais de uma frase na outra lingua para ser traduzida, seleciona-as antes de validar.
        </div>
        <?
            for ($i = 1; $i < 16; $i++) {
                echo "<div class='dot' id='dot".$i."'> </div>\n";
            }
        ?>
    </center>
    <div id='container' onclick='refreshState()'>
        <span class='table_header'><img class='flag' src="img/uk-flag.png"> Frases em Inglês</span>
        <button id='validate_btn' onclick='validate()' disabled>Validar</button>
        <span class='table_header'><img class='flag' src="img/pt-flag.png"> Frases em Português</span>
        <div class='table_container'>
            <table id='en_subs'>
                <?
                    $enSub = fopen($nameEN, "r") or die("Unable to open file \"$nameEN\"<br>Please contact admin");
                    while(!feof($enSub)) {
                        echo "<tr><td class='clickable' onclick='toggleSelectEN(this)'>" . fgets($enSub) . '</tr></td>';
                    }
                    fclose($enSub);
                ?>
            </table>
        </div>
        
        <div class='table_container'>
            <table id='pt_subs'>
                <?
                    $ptSub = fopen($namePT, "r") or die("Unable to open file \"$namePT\"<br>Please contact admin");
                    while(!feof($ptSub)) {
                        echo "<tr><td class='clickable' onclick='toggleSelectPT(this)'>" . fgets($ptSub) . '</tr></td>';
                    }
                    fclose($enSub);
                ?>
            </table>
        </div>
    </div>
    <p id='concluded_text'>Concluído! Avança para o próximo passo.</p>
    <button id='next_btn' onclick='document.getElementById("results").submit()' disabled>Avançar</button> <span id='page_number'>3 de 4</span>
    <form method="post" action="5.php?id=<?=$id?>" id='results' style='display:none'></form>
</body>