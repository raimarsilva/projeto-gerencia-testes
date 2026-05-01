let r = document.getElementById("remun").innerText
console.log(r)


function mudarSalario() {
    const ch = document.getElementById("cargaHoraria").value;
    console.log("carga horaria: " + ch);
    const tt = document.getElementById("titulacao").value;
    console.log("titulacao: " + tt);
    let valor;
    if (ch === "20") {
        valor = document.getElementById("rem" + tt).dataset.value;
        console.log("valor: " + valor);
        document.getElementById("remun").innerText = valor;
        console.log("valor: " + valor);
    } else if (ch === "40") {
        valor = document.getElementById("rem" + (5 + parseInt(tt)).toString()).dataset.value;
        document.getElementById("remun").innerText = valor;
        console.log("valor: " + valor);
    } else if (ch === "99") {
        let i = document.getElementById("cargo").selectedIndex;
        switch (i) {
            case 4:
                valor = document.getElementById("rem" + (6 + parseInt(tt)).toString()).dataset.value;
                document.getElementById("remun").innerText = valor;
                console.log("valor: " + valor);
                break;
            case 5:
                valor = document.getElementById("rem" + (7 + parseInt(tt)).toString()).dataset.value;
                document.getElementById("remun").innerText = valor;
                console.log("valor: " + valor);
                break;
            case 6:
                valor = document.getElementById("rem" + (8 + parseInt(tt)).toString()).dataset.value;
                document.getElementById("remun").innerText = valor;
                console.log("valor: " + valor);
                break;
        }
    }
    document.getElementById("remInput").value = valor;
}

function mudarClasse() {
    let classe = document.getElementById("classe");
    switch (document.getElementById("cargo").selectedIndex) {
        case 4: classe.value = "Adjunto";
            break;
        case 5: classe.value = "Associado";
            break;
        case 6: classe.value = "Titular";
            break;
        case 2: classe.value = "A";
            break;
        default: classe.value = "Assistente";
    }
}