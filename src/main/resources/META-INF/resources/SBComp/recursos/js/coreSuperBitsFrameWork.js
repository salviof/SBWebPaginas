

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
    try {
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


                            try {
                                elemento.metodoOnchangeComDelay();
                            } catch (t) {

                            }
                        }, 800);
                    }
                } catch (t) {

                }
            };
        }
    } catch (t) {

    }
}

function focarComSelacaoAposAjax() {
    try {
        contemClientID = false;
        for (i = 0; i < arguments.length; i++) {
            if (document.activeElement.id.includes(arguments[i])) {
                contemClientID = true;
            }
        }
        if (!contemClientID) {
            $(PrimeFaces.escapeClientId(document.activeElement.id)).select();
        }
    } catch (o) {

    }
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

//Funções de comunicação
function notificacoesPush(notificacao) {
    console.log(notificacao);
    if (notificacao.temMensagem) {
        PF("#{layoutIdsAreasConhecidas.AREA_MENSAGEM_INTERFACE}").renderMessage(JSON.parse(notificacao.mensagemJson));
    }
    if (notificacao.temCodigoConversa) {

        responderConversa(notificacao.codigoConversa);
    }
}
function responderConversa(codigoSelo) {
    (document.getElementById('formularioComunicacao:codigoComunicacao')).value = codigoSelo;
    PF('botaoAbrirModalConversa').jq.click();
}

function responderConversaRespostaRapida(codigoSelo, codigoResposta) {
    (document.getElementById('formularioComunicacaoRespostaRapida:codigoSeloCMRespostaRapida')).value = codigoSelo;
    (document.getElementById('formularioComunicacaoRespostaRapida:codigoRespostaRapida')).value = codigoResposta;
    PF('respostaRapidaAct').jq.click();
}


function initBotaoMenuHorizontal(menuhorizontalresponsivo) {

    var itemSize = $(menuhorizontalresponsivo).parent().find('.item-menu-horizontal-responsivo').outerWidth(true);
    var itensQtd = $(menuhorizontalresponsivo).parent().find('.item-menu-horizontal-responsivo').length;
    var scrollMaximo = (itensQtd * itemSize);
    var tamanhoVisivel = $(menuhorizontalresponsivo).parent().width();
    var pd = (itensQtd * 22);
    var scrollMaximo = (itensQtd * itemSize);

    var scrollMaximoVisivel = scrollMaximo - tamanhoVisivel;




    if (scrollMaximo <= tamanhoVisivel) {
        var botaoScrollEsquerda = $(menuhorizontalresponsivo).parent().find('.botao-lateral-esquerda-menu-horizontal-responsivo');
        var botaoScrollDireita = $(menuhorizontalresponsivo).parent().find('.botao-lateral-direita-menu-horizontal-responsivo');
        $(botaoScrollDireita).addClass('hidden-botao-lateral');
        $(botaoScrollEsquerda).addClass('hidden-botao-lateral');
    } else {
        $(menuhorizontalresponsivo).css({
            "padding-left": pd + "px"
        });
        $(menuhorizontalresponsivo).mousewheel(function (e, delta) {
            // this.scrollLeft -= (delta * 350);
            e.preventDefault();
            if (delta < 0) {
                acoesBotaoMenuHorizontal(menuhorizontalresponsivo, true, true);
            } else {
                acoesBotaoMenuHorizontal(menuhorizontalresponsivo, false, true);
            }

        });
    }
    var pixelScrolAtual = $(menuhorizontalresponsivo).scrollLeft();
    $(menuhorizontalresponsivo).parent().data("posicaoInicial", pixelScrolAtual);
}
function acoesBotaoMenuHorizontal(menuhorizontalresponsivo, parafrente, semefeito) {
    var etapaPosicaoScroolAtual = $(menuhorizontalresponsivo).parent().data("posicao");


    if (etapaPosicaoScroolAtual == null) {
        etapaPosicaoScroolAtual = 1;
        $(menuhorizontalresponsivo).parent().data("posicao", etapaPosicaoScroolAtual);
    } else {
        if (parafrente) {
            etapaPosicaoScroolAtual = etapaPosicaoScroolAtual + 1;
            $(menuhorizontalresponsivo).parent().data("posicao", etapaPosicaoScroolAtual);
        } else {
            etapaPosicaoScroolAtual = etapaPosicaoScroolAtual - 1;
            if (etapaPosicaoScroolAtual < 1) {
                etapaPosicaoScroolAtual = 1;
            }
            $(menuhorizontalresponsivo).parent().data("posicao", etapaPosicaoScroolAtual);
        }

    }




    var pixelScrolAtual = $(menuhorizontalresponsivo).scrollLeft();
    var tamanhoVisivel = $(menuhorizontalresponsivo).parent().width();
    var itensQtd = $(menuhorizontalresponsivo).parent().find('.item-menu-horizontal-responsivo').length;
    var itemSize = $(menuhorizontalresponsivo).parent().find('.item-menu-horizontal-responsivo').outerWidth(true);
    var pd = (itensQtd * 22);

    var scrollMaximoVisivel = $(menuhorizontalresponsivo).outerWidth(true);
    var scrollMaximo = (itensQtd * itemSize);

    var intervaloScroll = tamanhoVisivel - itemSize;
    var novoScroll = intervaloScroll + pixelScrolAtual;

    if (parafrente) {
        novoScroll = pixelScrolAtual + (intervaloScroll * 0.45);
    } else {
        novoScroll = pixelScrolAtual - (intervaloScroll * 0.6);
        if (novoScroll < pd) {
            if (novoScroll < 0) {
                novoScroll = 0;

            }
        }


    }


    var botaoScrollEsquerda = $(menuhorizontalresponsivo).parent().find('.botao-lateral-esquerda-menu-horizontal-responsivo');
    var botaoScrollDireita = $(menuhorizontalresponsivo).parent().find('.botao-lateral-direita-menu-horizontal-responsivo');
    console.log("novoScroll>" + novoScroll + "ScrollMaximo" + scrollMaximo + "itensQtd->" + itensQtd + "itemSize" + itemSize + "erapa" + etapaPosicaoScroolAtual);

    if (tamanhoVisivel >= scrollMaximo + pd) {
        $(botaoScrollDireita).addClass('hidden-botao-lateral');
        $(botaoScrollEsquerda).addClass('hidden-botao-lateral');

    } else {

        if (novoScroll >= scrollMaximoVisivel) {

            $(botaoScrollDireita).addClass('hidden-botao-lateral');
            $(botaoScrollEsquerda).removeClass('hidden-botao-lateral');


        } else {
            if (novoScroll <= 0) {

                $(botaoScrollDireita).removeClass('hidden-botao-lateral');
                $(botaoScrollEsquerda).addClass('hidden-botao-lateral');

            } else {
                $(botaoScrollDireita).removeClass('hidden-botao-lateral');
                $(botaoScrollEsquerda).removeClass('hidden-botao-lateral');

            }
        }
    }
    var segundosefeitos = 900;
    if (semefeito) {
        segundosefeitos = 0;
    }
    if (parafrente) {
        if (novoScroll >= scrollMaximoVisivel - intervaloScroll) {
            $(menuhorizontalresponsivo).css({
                "padding-left": "0px"
            });
        }
        $(menuhorizontalresponsivo).animate({scrollLeft: novoScroll}, segundosefeitos);

    } else {
        $(menuhorizontalresponsivo).css({
            "padding-left": pd + "px"
        });
        $(menuhorizontalresponsivo).animate({scrollLeft: novoScroll}, segundosefeitos);

    }


}
