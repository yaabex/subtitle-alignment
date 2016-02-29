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
    ?>
</head>
<body>
    <header>
        <b>O cinema ao serviço do Processamento da Língua Natural</b> | Tese de Mestrado - Luís Rosado
    </header>
    <center>
        <div id='instructions'>
            Olá! Antes de mais, obrigado por aceitares ajudar-me na minha tese de mestrado.<br>
            Peço-te que me ajudes a encontrar <b>correspondências entre frases</b> tiradas de legendas de filmes.<br>
            Vou mostrar-te frases em Inglês e Português e deves seguir os passos de forma a indicar-me correspondências entre elas.
            O tempo que demoras não é importante, mas 15 minutos devem ser suficientes para terminares.<br>
            Antes de começares observa os seguintes exemplos, para melhor entenderes o que pretendo.
        </div>
    </center>
    <div id='container' onclick='refreshState()'>
        <span class='table_header'><img class='flag' src="img/uk-flag.png"> Frases em Inglês</span>
        <button id='validate_btn' style='opacity:0' disabled>Validar</button>
        <span class='table_header'><img class='flag' src="img/pt-flag.png"> Frases em Português</span>
        <div class='table_container'>
            <table id='en_subs'>
                <tr><td><b>Exemplo 1: correspondência 1 frase com 1 frase</b></td></tr>
                <tr><td class='not_clickable'>The stated goal of the Soviets is global Communism.</td></tr>
                <tr><td class='not_clickable selected_en'>Mathematicians won the war.</td></tr>
                <tr><td class='not_clickable'>Mathematicians, like you.</td></tr>
                <tr><td>&thinsp;</td></tr>
                <tr><td><b>Exemplo 2: correspondência 2 frases com 1 frase</b></td></tr>
                <tr><td class='not_clickable'>Helped rid the world of Fascism.</td></tr>
                <tr><td class='not_clickable selected_en'>The name's Bender.</td></tr>
                <tr><td class='not_clickable selected_en'>Atomic physics.</td></tr>
                <tr><td>&thinsp;</td></tr>
                <tr><td><b>Podem surgir frases sem correspondência do outro lado.</b></td></tr>
            </table>
        </div>
        
        <div class='table_container'>
            <table id='pt_subs'>
                <tr><td><b>&thinsp;</b></td></tr>
                <tr><td class='not_clickable selected_pt'>Os matemáticos venceram a guerra.</td></tr>
                <tr><td class='not_clickable'>Não, tem de ter tudo só para ele.</td></tr>
                <tr><td class='not_clickable'>Ele ficou aborrecido.</td></tr>
                <tr><td>&thinsp;</td></tr>
                <tr><td>&thinsp;</td></tr>
                <tr><td class='not_clickable'>Chamava-se Johnny Walker.</td></tr>
                <tr><td class='not_clickable selected_en'>Chamo-me Bender, da Física Atómica.</td></tr>
                <tr><td class='not_clickable'>E você?</td></tr>
                <tr><td>&thinsp;</td></tr>
                <tr><td><b>Ou seja, 1 frase com 0 frases.</b></td></tr>
            </table>
        </div>
    </div>
    <button id='next_btn' onclick='document.getElementById("results").submit()'>Avançar</button> <span id='page_number'>1 de 4</span>
    <form method="get" action="2.php" id='results' style='display:none'>
        <?="<input type='text' name='id' value=".$id.">\n"?>
    </form>
</body>