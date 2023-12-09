function getJson(url) {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 400) {
                resolve(JSON.parse(xhr.responseText));
            } else {
                const erro = {
                    codigo: xhr.status,
                    mensagem: 'Erro na requisição'
                }
                reject(erro);
            }
        };
        xhr.send();
    });
}

getJson('http://localhost:8080/carros/1').then((status) => {
    document.getElementById('listaVazia').style.removeProperty('display')
}).catch((error) => {
    document.getElementById('aListaVaziaa').style.removeProperty('display')
});

async function listar() {
    try {
        const dados = await getJson('http://localhost:8080/carros');
        for (let i = 0; i < dados.length; i++) {
            const dadosHtml = montarHTML(dados[i]);
            document.getElementById('resultado').insertAdjacentHTML('beforeend', dadosHtml);
        }
    } catch (error) {
        alert('ERRO: ' + error.codigo + ' ' + error.mensagem)
    }
}

function montarHTML(dados) {
    return '<tr>' +
        //        '<th scope="row">' + dados.id + '</th>' +
        '<td>' + dados.modelo + '</td>' +
        '<td>' + dados.fabricante + '</td>' +
        '<td>' + dados.anoLancamento + '</td>' +
        '</tr>'
}
document.addEventListener('DOMContentLoaded', function () {
    listar();
});


async function buscar(evt) {
    evt.preventDefault();
    //const termo = document.getElementById('termo').value;
    const termo = evt.target.termo.value;
    try {
        const resultados = await getJson('/carros/busca?termo=' + termo + '&quantidade=50');
        document.getElementById('resultado').innerHTML = '';
        for (const dados of resultados) {
            let linha = '<tr>' +
                //                '<th scope="row">' + dados.id + '</th>' +
                '<td>' + dados.modelo + '</td>' +
                '<td>' + dados.fabricante + '</td>' +
                '<td>' + dados.anoLancamento + '</td>' +
                '</tr>'
            document.getElementById('resultado').insertAdjacentHTML('beforeend', linha);
        }
    } catch (err) {
        alert(err);
    }
}
document.getElementById('buscaa').onsubmit = buscar;
