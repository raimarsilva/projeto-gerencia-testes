document.addEventListener("DOMContentLoaded", function () {

    const input = document.getElementById("dataVigencia");
    if (!input) return;

    const hoje = new Date();
    const ano = hoje.getFullYear();

    const julho31 = new Date(ano, 6, 31);
    const dez31 = new Date(ano, 11, 31);

    // 30 dias antes
    const limiteJulho = new Date(julho31);
    limiteJulho.setDate(julho31.getDate() - 30);

    const limiteDez = new Date(dez31);
    limiteDez.setDate(dez31.getDate() - 30);

    let dataFinal;

    if (hoje <= limiteJulho) {
        // Janela de julho
        dataFinal = julho31;

    } else if (hoje <= limiteDez) {
        // Janela de dezembro
        dataFinal = dez31;

    } else {
        // Após janela de dezembro → julho do próximo ano
        dataFinal = new Date(ano + 1, 6, 31);
    }

    // Formato ISO pro input date
    const yyyy = dataFinal.getFullYear();
    const mm = String(dataFinal.getMonth() + 1).padStart(2, '0');
    const dd = String(dataFinal.getDate()).padStart(2, '0');

    input.value = `${yyyy}-${mm}-${dd}`;
});
