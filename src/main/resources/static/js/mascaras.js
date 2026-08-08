document.addEventListener('DOMContentLoaded', function () {
    const inputEdital = document.getElementById("edital");
    console.log('elem: ' + inputEdital);
    if (inputEdital) {
        inputEdital.addEventListener('input', function () {
            // Remove tudo o que não for número
            let valor = this.value.replace(/\D/g, "");
            console.log('valor: ' + valor);
            // Aplica a máscara se tiver mais de 4 dígitos (separa os últimos 4 para o ano)
            if (valor.length > 4) {
                let posicaoBarra = valor.length - 4;
                valor = valor.substring(0, posicaoBarra) + "/" + valor.substring(posicaoBarra);
                console.log('valor alterado: ' + valor);
            }
            this.value = valor;
            console.log('this.value: ' + this.value);
        });
    }
});