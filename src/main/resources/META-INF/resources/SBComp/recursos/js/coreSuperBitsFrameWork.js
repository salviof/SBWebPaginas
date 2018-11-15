

/**
 * Limpa todos os Tooltips do Primefaces
 */
function esconderTooltips() {
    $(".ui-tooltip").hide();

}

function liberarBloqueios() {
    for (i in PrimeFaces.widgets) {
        if (PrimeFaces.widgets[i].show && PrimeFaces.widgets[i].blocker) {
            PrimeFaces.widgets[i].hide();
        }
    }
}

function scrollEmCampoNaoValidado() {

    try {
        var elementoErro = $("input.ui-state-error:first");
        if (elementoErro.length > 0) {

            $('html, body').animate({
                scrollTop: elementoErro.offset().top - 200
            });
            return true;
        }

        return false;
    } catch (err) {

    }
    return false;
}


/**
 * Vai para o Topo
 */
function irParaTopo() {
    $("html, body").animate({scrollTop: 0}, "slow");
}
function irParTopo() {
    irParaTopo();
}

function acoesPosAjax() {
    try {
        esconderTooltips();
        liberarBloqueios();
        if (!scrollEmCampoNaoValidado()) {
            irParTopo();
        }
    } catch (err) {

    }

}

/**
 * ->
 * O enviarInf
 */
function atualizarResolucaoDaTela() {
    getClientInfo();
    enviarInfoTela();
}
/**
 *
 *
 * Atualiza o campo infotelafrm:clientinfo com a resolução atual da tela
 */
function getClientInfo()
{
    var clientinfo = "";
    clientinfo += "aplicativo:" + navigator.appName + "||";
    clientinfo += "versaoAplicativo:" + navigator.appVersion + "||";
    clientinfo += "tamanhoX:" + window.innerWidth + "||";
    clientinfo += "tamanhoY:" + window.innerHeight;
    (document.getElementById('contactfrm:clientinfo')).value = clientinfo;
}

function baixarArquivo(fileURL, fileName) {
    // for non-IE
    if (!window.ActiveXObject) {
        var save = document.createElement('a');
        save.href = fileURL;
        save.target = '_blank';
        save.download = fileName || 'unknown';

        var event = document.createEvent('Event');
        event.initEvent('click', true, true);
        save.dispatchEvent(event);
        (window.URL || window.webkitURL).revokeObjectURL(save.href);
    }

    // for IE
    else if (!!window.ActiveXObject && document.execCommand) {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}

function modificarIntputEclicar(idBotao, idInputEnvio, valor) {
    try {
        elementos = idInputEnvio.split(" ");

        elementoCodigo = document.getElementById(elementos[0]);
        if (elementoCodigo === null) {
            elementoCodigo = document.getElementById(elementos[1]);
        }


        elementoCodigo.value = valor;
        botoes = idBotao.split(" ");
        botao = document.getElementById(botoes[0]);
        if (botao === null) {
            botao = document.getElementById(botoes[1]);
        }
        botao.click();
    } catch (t) {
        console.log(t);
    }
}

function copiarValoresCKEditor(idOrigem, idDestino) {
    var conteudo = CKEDITOR.instances[idOrigem].getData();
    document.getElementById(idDestino).value = conteudo;
}

function adicionarChamadaComDelay(idElemento, metodo) {

}

function mesclarOnChangeComDelay(idElementoDigitacao) {
    elemento = document.getElementById(idElementoDigitacao);
    var timeout = null;


    if (elemento.onchange) {
        elemento.metodoOnchangeComDelay = elemento.onchange;
        elemento.ultimapesquisa = elemento.value;
        //elemento.onchange = null;

        elemento.onkeyup = function (e) {

            try {
                if (elemento.value === elemento.ultimapesquisa) {

                } else {
                    // Clear the timeout if it has already been set.
                    // This will prevent the previous task from executing
                    // if it has been less than <MILLISECONDS>
                    clearTimeout(timeout);
                    // Make a new timeout set to go off in 800ms
                    elemento.ultimapesquisa = elemento.value;
                    timeout = setTimeout(function () {


                        elemento.metodoOnchangeComDelay();
                    }, 800);
                }
            } catch (t) {

            }
        };
    }





    // Listen for keystroke events


}


function pesquisaDataSetComDelay(idElementoDigitacao, idDataSetPrime) {
    //Contribuição : https://schier.co/blog/2014/12/08/wait-for-user-to-stop-typing-using-javascript.html
    elemento = document.getElementById(idElementoDigitacao);
    // Init a timeout variable to be used below
    var timeout = null;


    // Listen for keystroke events
    elemento.onkeyup = function (e) {



        // Clear the timeout if it has already been set.
        // This will prevent the previous task from executing
        // if it has been less than <MILLISECONDS>
        clearTimeout(timeout);
        // Make a new timeout set to go off in 800ms
        timeout = setTimeout(function () {

            PF(idDataSetPrime).filter();
        }, 800);
    };
}