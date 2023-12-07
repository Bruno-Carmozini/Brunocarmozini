var valores = [];
var idmedico = 0;

function novo() {
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    form.style.display = "block";
    lista.style.display = "none";

    idmedico = 0;
    var nome = document.getElementById("nome");
    var especialidade = document.getElementById("especialidade");
    var crm = document.getElementById("crm");
    var idade = document.getElementById("idade");
    var formacao = document.getElementById("formacao");
    nome.value = "";
    especialidade.value = "";
    crm.value = "";
    idade.value = "";
    formacao.value = "";

    nome.focus();

}

function alterar(i) {
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    form.style.display = "block";
    lista.style.display = "none";

    idmedico = valores[i].idmedico;
    var nome = document.getElementById("nome");
    var especialidade = document.getElementById("especialidade");
    var crm = document.getElementById("crm");
    var idade = document.getElementById("idade");
    var formacao = document.getElementById("formacao");

    nome.value = valores[i].nome;
    especialidade.value = valores[i].especialidade;
    crm.value = valores[i].crm;
    idade.value = valores[i].idade;
    formacao.value = valores[i].formacao;

    nome.focus();

}

function salvar() {
    var m = {
        idmedico: idmedico,
        nome: document.getElementById("nome").value,
        especialidade: document.getElementById("especialidade").value,
        crm: document.getElementById("crm").value,
        idade: document.getElementById("idade").value,
        formacao: document.getElementById("formacao").value
    };

    if (idmedico == 0) {
        metodo = "POST";
    } else {
        metodo = "PUT";
    }

    mostraLoading("aguarde, salvando dados...")
    fetch("http://localhost:8080/JavaRest1/Medico",
        {
            method: metodo,
            body: JSON.stringify(m)
        }
    ).then(resp => resp.json())
        .then(function (retorno) {
            escondeLoading();
            alert(retorno.mensagem);

            var form = document.getElementById("formulario");
            var lista = document.getElementById("lista");

            form.style.display = "none";
            lista.style.display = "block";

            listar();
        });
}

function excluir(i) {
    idmedico = valores[i].idmedico;
	console.log(idmedico);
    mostraLoading("aguarde, estamos excluindo...")
    fetch("http://localhost:8080/JavaRest1/Medico/" + idmedico,
        {
            method: "DELETE"

        })

        .then(resp => resp.json())
        .then(function (retorno) {
            escondeLoading();
            alert(retorno.mensagem);

            var form = document.getElementById("formulario");
            var lista = document.getElementById("lista");

            form.style.display = "none";
            lista.style.display = "block";

            listar();
        });
}


function cancelar() {
    var form = document.getElementById("formulario");
    var lista = document.getElementById("lista");

    form.style.display = "none";
    lista.style.display = "block";

}

function listar() {
    var lista = document.getElementById("dados");
    lista.innerHTML = "<tr><td colspan=4> aguarde, carregando... </td></tr>";

    fetch("http://localhost:8080/JavaRest1/Medico")
        .then(resp => resp.json())
        .then(dados => mostrar(dados));
}

function mostraLoading(msg) {
    var loa = document.getElementById("loading");
    var con = document.getElementById("conteudo");

    loa.style.display = "block";
    con.style.display = "none";
    loa.innerHTML = msg;
}

function escondeLoading() {
    var loa = document.getElementById("loading");
    var con = document.getElementById("conteudo");

    loa.style.display = "none";
    con.style.display = "block";
}

function mostrar(dados) {
    valores = dados;
    var lista = document.getElementById("dados");
    lista.innerHTML = "";

    for (var i in dados) {
        lista.innerHTML += "<tr>"
            + "<td>" + dados[i].idmedico + "</td>"
            + "<td>" + dados[i].nome + "</td>"
            + "<td>" + dados[i].especialidade + "</td>"
            + "<td>" + dados[i].crm + "</td>"
            + "<td>" + dados[i].idade + "</td>"
            + "<td>" + dados[i].formacao + "</td>"
            + "<td>"
            + "<input type='button' value='Edit' onclick='alterar(" + i + ")'/>"
            + "<input type='button' value='Delete' onclick='excluir(" + i + ")'/>"
            + "</td>"
            + "</tr>";
    }
}

listar();
