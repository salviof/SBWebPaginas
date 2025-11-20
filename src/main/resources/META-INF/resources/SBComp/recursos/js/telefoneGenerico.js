/**
 * Máscara de telefone brasileira inteligente
 * Basta adicionar a classe: mask-telefone-br
 * Totalmente protegido contra dupla inicialização
 */
function inicializarMascarasTelefone() {
    alert("iniciou!");
    // ==== MÁSCARA DE TELEFONE BRASILEIRA + INTERNACIONAL ====
// Basta adicionar a classe "mask-telefone-br" no seu p:inputMask
    $(document)
            .on('keydown.masktel', '.mask-telefone-br', function (e) {
                $(this).trigger('keyup');
                if (e.key === '+' || (e.shiftKey && e.key === '=')) {
                    e.preventDefault(); // impede duplicar o +
                    const $input = $(this);
                    // Força modo internacional
                    $input.val('+');

                    // Remove máscara antiga e aplica internacional
                    if ($input.data('inputmask'))
                        $input.inputmask('remove');
                    $input.inputmask({
                        mask: '+{1,4} 99999999999999999',
                        placeholder: '_',
                        greedy: false,
                        clearMaskOnLostFocus: false
                    });

                    // Coloca cursor depois do +
                    setTimeout(() => $input[0].setSelectionRange(1, 1), 0);
                    return;
                }

                // Permite apagar tudo (mesmo com máscara ativa)
                if (e.key === 'Backspace' && (valor === '+55 ' || valor === '+55' || valor === '+')) {
                    e.preventDefault();
                    $input.val('+'); // deixa só o + (ou vazio, se preferir)
                    setTimeout(() => $input[0].setSelectionRange(1, 1), 0);
                }


            })
            .on('keyup.masktel input.masktel paste.masktel', '.mask-telefone-br', function () {
                const $input = $(this);

                let mask = $input[0].inputmask.maskset.mask;
                let valor = $input.val().trim();
                let apenasDigitos = valor.replace(/\D/g, '');
                // Inicialização única
                if (!$input.data('mask-init')) {
                    if (!$input.val() || $input.val().trim() === '' || $input.val() === '+55 ') {
                        $input.val('+55 ');
                        apenasDigitos = '55';// começa só com +
                    }
                    $input.data('mask-init', true);
                    if (!valor) {
                        // vazio, null, undefined, ou só espaços
                        mask = '+55 (99) 99999-9999';
                    }
                }




                // =============================================
                // DECIDE A MÁSCARA CORRETA (a mágica acontece aqui)
                // =============================================



                if (apenasDigitos.startsWith('55')) {
                    // Só ativa máscara brasileira quando tiver 55 + DDD completo (55XX)
                    const numeroLocal = apenasDigitos.substring(4); // tudo depois do DDD
                    console.log("contemApenasdigitos iniciando com 55");
                    console.log(apenasDigitos);
                    if (apenasDigitos.startsWith('5555')) {

                    }
                    if (numeroLocal.length > 0) {
                        // Já digitou pelo menos 1 dígito do número → sabemos se é 9 ou não
                        mask = numeroLocal.startsWith('9')
                                ? '+55 (99) 99999-9999'
                                : '+55 (99) 9999-9999';
                    } else {
                        // Tem 55 + DDD completo, mas ainda não digitou o número → padrão celular
                        mask = '+55 (99) 99999-9999';

                    }
                }
                console.log("A mascaraDefinida foi" + mask);
                // Se não entrou no if acima → continua com máscara internacional
                // GENIAL: Remove o código do Brasil antes de aplicar a máscara brasileira
                // Assim o Inputmask NUNCA vê dois "55" → nunca duplica
                if (mask.includes('+55') && apenasDigitos.length > 2) {
                    // Remove os primeiros 55 dos dígitos e deixa só DDD + número
                    console.log("Padrão brasileiro");
                    const digitosApos55 = apenasDigitos.substring(2);
                    if (digitosApos55 >= 3) {
                        // Momento crítico: usuário digitou +553 ou +5531
                        // Remove o 55 do valor para o Inputmask não ver duplicado

                        if (!digitosApos55.startsWith('55')) {
                            $input.val(digitosApos55); // Inputmask vai adicionar +55 ( automaticamente
                            console.log("removendo 55 substituindo para:" + digitosApos55);
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
                console.log("Aplicando mascara");
                $input.inputmask({
                    mask: mask,
                    placeholder: '_',
                    greedy: false,
                    clearMaskOnLostFocus: false,
                    showMaskOnHover: false

                });

                // =============================================
                // CURSOR SEMPRE NO PRÓXIMO _
                // =============================================
                setTimeout(() => {
                    console.log("posicionando cursor");
                    const texto = $input.val();
                    if (texto.length > 0) {
                        const pos = texto.indexOf('_');
                        const novaPos = pos !== -1 ? pos : texto.length;
                        if ($input[0].setSelectionRange) {
                            $input[0].setSelectionRange(novaPos, novaPos);
                        }
                    }
                }, 0);
            });
}


$(function () {
    inicializarMascarasTelefone();
});