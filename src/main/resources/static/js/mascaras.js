function mascaraNumProc(input) {
    let valor = input.value.replace(/\D/g, "");
    if (valor.length > 2) {
        let posicaoTraco = valor.length - 2;
        valor = valor.substring(0, posicaoTraco) + "-" + valor.substring(posicaoTraco);
    }
    if (valor.length > 7) {
        let posicaoBarra = valor.length - 7;
        valor = valor.substring(0, posicaoBarra) + "/" + valor.substring(posicaoBarra);
    }
    input.value = valor;
}

function mascaraEdital(input) {
    let valor = input.value.replace(/\D/g, "");
    if (valor.length > 4) {
        let posicaoBarra = valor.length - 4;
        valor = valor.substring(0, posicaoBarra) + "/" + valor.substring(posicaoBarra);
    }
    input.value = valor;
}

function mascaraCpf(input) {
    let valor = input.value.replace(/\D/g, "").slice(0, 11);

    valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
    valor = valor.replace(/(\d{3})(\d)/, "$1.$2");
    valor = valor.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

    input.value = valor;
}