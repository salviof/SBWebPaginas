



function scrollToTop() {
    const scrollToTop = window.setInterval(function () {
        const pos = window.scrollY;
        if (pos > 0) {
            window.scrollTo(0, pos - 10); // Ajuste o valor 20 para controlar a velocidade
        } else {
            window.clearInterval(scrollToTop);
        }
    }, 5);
}

function scrollToBottom() {
    const scrollToBottom = window.setInterval(function () {
        const pos = window.scrollY;
        if (pos < document.body.scrollHeight - window.innerHeight) {
            window.scrollTo(0, pos + 10); // Ajuste o valor 20 para controlar a velocidade
        } else {
            window.clearInterval(scrollToBottom);
        }
    }, 5);
}
function scrolltoprodapeAction() {
    tipoScrool = verificarPosicaoScroll();
    if (tipoScrool === 1) {
        btnScrollToBottom.style.display = 'block';
        btnScrollToTop.style.display = 'none';
    }
    if (tipoScrool === 2) {
        btnScrollToBottom.style.display = 'none';
        btnScrollToTop.style.display = 'block';
    }
    if (tipoScrool === 0) {
        btnScrollToBottom.style.display = 'none';
        btnScrollToTop.style.display = 'none';
    }
}


function verificarPosicaoScroll() {
    var alturaPagina = document.body.scrollHeight;
    var alturaJanela = window.innerHeight;
    var posicaoScroll = window.scrollY;

    if (alturaPagina <= alturaJanela + 20) {
        return 0;
    }
    if (posicaoScroll === 0) {
        return 1; // Está no topo da página
    } else if (posicaoScroll + alturaJanela >= alturaPagina) {
        return 2; // Está no rodapé da página
    } else {
        return 0; // A página não requer rolagem
    }
}

function ativarBotaoTopoRodape() {
    window.scrollTo({top: 0, behavior: 'smooth'});
    const btnScrollToTop = document.getElementById('btnScrollToTop');
    const btnScrollToBottom = document.getElementById('btnScrollToBottom');
    btnScrollToTop.addEventListener('click', function () {
        scrollToTop();
    });

    btnScrollToBottom.addEventListener('click', function () {
        scrollToBottom();
    });

    window.onscroll = function () {
        scrolltoprodapeAction();
    };
    scrolltoprodapeAction();
}