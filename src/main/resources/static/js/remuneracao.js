let r = document.getElementById("remun").innerText
console.log(r)


function mudarSalario() {
    const ch = document.getElementById("cargaHoraria").value;
    const tt = document.getElementById("titulacao").value;
    let novo = document.getElementById("remun").value;
    if (ch === "20") {
        const valor = document.getElementById("rem" + tt).dataset.value;
        document.getElementById("remun").innerText = valor;
        console.log("salario mudou");
    } else if (ch === "40") {
        const valor = document.getElementById("rem" + tt).dataset.value;
        document.getElementById("remun").innerText = valor;
        console.log("salario mudou");
    } else if (ch === "99") {
        const valor = document.getElementById("rem" + tt).dataset.value;
        document.getElementById("remun").innerText = valor;
        console.log("salario mudou");
    }
}