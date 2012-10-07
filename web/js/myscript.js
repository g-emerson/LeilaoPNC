// Cria um objeto de requisição AJAX
function createXMLHttpRequest() {
    var request = null;
    try {
        request = new XMLHttpRequest();
    }
    catch (trymicrosoft) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (trymicrosoft2) {
            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch (failed) {
                request = null;
            }
        }
    }

    if ( request == null ) {
        alert("Falha na criação do objeto de requisição AJAX");
    }

    return request;
}

// Função que trata da validação de uma entidade
function validarEntidade(appRoot, entId) {
    xmlhttp = createXMLHttpRequest();

    if ( xmlhttp == null ) {
        return;
    }

    // Funcão de tratamento da requisição
    xmlhttp.onreadystatechange = function () {
        if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 ) {
            data = eval( '('+xmlhttp.responseText+')' );
            if ( data.resultado ) {
                cell = document.getElementById("validacao_"+entId);
                cell.innerHTML = "Validada";
            }
            else {
                alert("Validação da entidade falhou\n" + data.erro_msg);
            }
        }
    }
    
    xmlhttp.open("POST",appRoot + "/validarEntidade",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send("entId="+entId+"&validar=true");
}
