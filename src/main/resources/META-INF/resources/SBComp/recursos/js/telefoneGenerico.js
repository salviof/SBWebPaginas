/**
 * Máscara de telefone brasileira inteligente
 * Basta adicionar a classe: campoResponsivoTelefone
 * Totalmente protegido contra dupla inicialização
 */

function definirMascaraTelefoneGenerico(pElemento) {
    const $input = $(pElemento);

    let mask = $input[0].inputmask.maskset.mask;
    let valor = $input.val().trim();
    let apenasDigitos = valor.replace(/\D/g, '');

    // Inicialização única
    if (!$input.data('mask-init')) {
        if (!$input.val() || $input.val().trim() === '' || $input.val() === '+55 ') {
            $input.val('+55 ');
            apenasDigitos = '55';
        }
        $input.data('mask-init', true);

        if (!valor) {
            mask = '+55 (99) 99999-9999';
        }
    }

    // =============================================
    // DECIDE A MÁSCARA CORRETA
    // =============================================

    if (apenasDigitos.startsWith('55')) {

        const numeroLocal = apenasDigitos.substring(4);

        if (numeroLocal.length > 0) {

            mask = numeroLocal.startsWith('9')
                    ? '+55 (99) 99999-9999'
                    : '+55 (99) 9999-9999';

        } else {

            mask = '+55 (99) 99999-9999';
        }
    }

    console.log("A mascaraDefinida foi" + mask);

    if (mask.includes('+55') && apenasDigitos.length > 2) {

        const digitosApos55 = apenasDigitos.substring(2);

        if (digitosApos55 >= 3) {

            if (!digitosApos55.startsWith('55')) {
                $input.val(digitosApos55);
            }

            if (digitosApos55 == '55') {
                $input.val('');
            }
        }
    }

    // =============================================
    // APLICA A MÁSCARA
    // =============================================

    if ($input.data('inputmask')) {
        $input.inputmask('remove');
    }

    $input.inputmask({
        mask: mask,
        placeholder: '_',
        greedy: false,
        clearMaskOnLostFocus: false,
        showMaskOnHover: false
    });

    // =============================================
    // ATUALIZA CAMPO DE VALOR RELACIONADO
    // =============================================

    const campoInstanciado = $input.attr('campoinstanciado');

    if (campoInstanciado) {

        const $valorCampo = $('input[campoinstanciado="' + campoInstanciado + '"]')
                .not($input)
                .first();

        if ($valorCampo.length) {
            $valorCampo.val($input.val());
        }
    }

    // =============================================
    // CURSOR
    // =============================================

    setTimeout(() => {
        const texto = $input.val();

        if (texto.length > 0) {

            const pos = texto.indexOf('_');
            const novaPos = pos !== -1 ? pos : texto.length;

            if ($input[0].setSelectionRange) {
                $input[0].setSelectionRange(novaPos, novaPos);
            }
        }
    }, 0);
}


function inicializarMascarasTelefone() {

    $(document)

            .on('keydown.masktel', '.campoResponsivoTelefone', function (e) {

                definirMascaraTelefoneGenerico(this);
                $(this).trigger('keyup');

                if (e.key === '+' || (e.shiftKey && e.key === '=')) {

                    e.preventDefault();

                    const $input = $(this);

                    $input.val('+');

                    if ($input.data('inputmask'))
                        $input.inputmask('remove');

                    $input.inputmask({
                        mask: '+{1,4} 99999999999999999',
                        placeholder: '_',
                        greedy: false,
                        clearMaskOnLostFocus: false
                    });

                    setTimeout(() => $input[0].setSelectionRange(1, 1), 0);

                    return;
                }

                const valor = $(this).val();

                if (e.key === 'Backspace' && (valor === '+55 ' || valor === '+55' || valor === '+')) {

                    e.preventDefault();

                    $(this).val('+');

                    setTimeout(() => this.setSelectionRange(1, 1), 0);
                }

            })

            .on('keyup.masktel input.masktel paste.masktel', '.campoResponsivoTelefone', function () {

                definirMascaraTelefoneGenerico(this);

            });
}


$(function () {
    inicializarMascarasTelefone();
});