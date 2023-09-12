function getJson(url) {
    // const promise = new Promise(function(resolve, reject) {
    const promise = new Promise((resolve, reject) => {
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
        }
        xhr.send();
    });
    return promise;
}

async function carregarDadosJson() {
    try {
        const dadosIan = await getJson('http://127.0.0.1:8080/dadosIan');
        document.getElementById('nome').innerHTML = dadosIan.nome;
        document.getElementById('email').innerHTML = dadosIan.email;
        document.getElementById('telefone').innerHTML = dadosIan.telefone;
        document.getElementById('dataNascimento').innerHTML = dadosIan.dataNascimento;
        document.getElementById('linkedInUrl').href = dadosIan.linkedInUrl;
        document.getElementById('gitHubUrl').href = dadosIan.gitHubUrl;
    } catch (erro) {
        alert("Erro dentro da função carregarDadosJson \n" + erro.mensagem + ' - ' + erro.codigo);
    }
}

// function carregarDadosSeguidoresJson() {
//     getJson('dados-array.json').then((dadosArray) => {
//         for (let i = 0; i < dadosArray.length; i++) {
//             const dados = dadosArray[i];
//             document.getElementById('seguidores')
//                 .insertAdjacentHTML('beforeend',
//                     '<p>' + dados.nome + '<br/>' + dados.email + '</p>');
//         }
//     }).catch((erro) => {
//         alert(erro.mensagem + ' - ' + erro.codigo);
//     })
// }

async function carregarDadosFormacaoAcademicaJson() {
    //getJson('dados-array-fm-ac.json').then((dadosArray) => {
    try {
        const dadosArray = await getJson('dados-array-fm-ac.json');

        const ulElement = document.createElement('ul');
        ulElement.id = 'formacad';
        var tituloFormAcd = document.createElement('div');
        tituloFormAcd.innerHTML = '<h3>Formação Academica</h3>';

        for (let i = 0; i < dadosArray.length; i++) {
            const dados = dadosArray[i];
            ulElement.insertAdjacentHTML('beforeend', '<li><h4>' + dados.nomeLugar + '</h4><p>' + dados.nomeCurso + '</p></li>');
        }
        const containerElement = document.getElementById('container-formacad');
        containerElement.appendChild(tituloFormAcd);
        containerElement.appendChild(ulElement);
    } catch (erro) {
        alert("Erro dentro da função carregarDadosFormacaoAcademicaJson \n" + erro.mensagem + ' - ' + erro.codigo);
    }
}

async function carregarDadosExperienciaProfissionalJson() {
    //getJson('dados-array-exp-prof.json').then((dadosArray) => {
    try {
        const dadosArray = await getJson('dados-array-exp-prof.json');
        const ulElement = document.createElement('ul');
        ulElement.id = 'expProf';
        var tituloExpProf = document.createElement('div');
        tituloExpProf.innerHTML = '<h3>Experiencia Profissional</h3>';

        for (let i = 0; i < dadosArray.length; i++) {
            const dados = dadosArray[i];
            ulElement.insertAdjacentHTML('beforeend', '<li><h4>' + dados.empresa + '</h4><p>' + dados.cargo + '</p><p>' + dados.tempoServ + '</p></li>');
        }
        const containerElement = document.getElementById('container-expProf');
        containerElement.appendChild(tituloExpProf);
        containerElement.appendChild(ulElement);
    } catch (erro) {
        alert("Erro dentro da função carregarDadosExperienciaProfissionalJson \n" + erro.mensagem + ' - ' + erro.codigo);
    }
    // }).catch((erro) => {
    //     alert(erro.mensagem + ' - ' + erro.codigo);
    // })
}

async function carregarDadosConhecimentosJson() {
    //getJson('dados-array-conhec.json').then((dadosArray) => {
    try {
        const dadosArray = await getJson('dados-array-conhec.json');
        const ulElement = document.createElement('ul');
        ulElement.id = 'conhecimentos';
        var tituloConhecimentos = document.createElement('div');
        tituloConhecimentos.innerHTML = '<h3>Conhecimentos</h3>';

        for (let i = 0; i < dadosArray.length; i++) {
            const dados = dadosArray[i];
            ulElement.insertAdjacentHTML('beforeend', '<li>' + dados.lingProg + '</li>');
        }
        const containerElement = document.getElementById('container-conhecimentos');
        containerElement.appendChild(tituloConhecimentos);
        containerElement.appendChild(ulElement);
    } catch (erro) {
        alert("Erro dentro da função carregarDadosConhecimentosJson \n" + erro.mensagem + ' - ' + erro.codigo);
    }
    // }).catch((erro) => {
    //     alert(erro.mensagem + ' - ' + erro.codigo);
    // })
}

function carregarDadosIdiomaJson() {
    getJson('dados-array-idioma.json')
        .then(function (dados) {
            criarTabelaComJson(dados);
        })
        .catch(function (error) {
            console.error("Erro dentro da função carregarDadosIdiomaJson: " + error);
        });
}


function criarTabelaComJson(idiomasData) {
    try {
        var tabelaContainer = document.getElementById('tabela-idioma');
        var tabela = document.createElement('table');
        tabela.setAttribute('border', '1');

        //criando o h3 do container
        var tituloIdioma = document.createElement('div');
        tituloIdioma.innerHTML = '<h3>Idiomas</h3>';

        // criando o cabeçalho
        var cabecalhoRow = document.createElement('tr');
        cabecalhoRow.innerHTML = '<td>Idioma</td><td>Leitura</td><td>Escrita</td><td>Conversação</td>';
        tabela.appendChild(cabecalhoRow);

        // criando as linhas da tabela
        for (var i = 0; i < idiomasData.length; i++) {
            var idiomaRow = document.createElement('tr');
            var idioma = idiomasData[i];

            idiomaRow.innerHTML = '<td>' + idioma.idioma + '</td>' +
                '<td>' + idioma.leitura + '</td>' +
                '<td>' + idioma.escrita + '</td>' +
                '<td>' + idioma.conversao + '</td>';

            tabela.appendChild(idiomaRow);
        }
        tabelaContainer.appendChild(tituloIdioma);
        tabelaContainer.appendChild(tabela);
    } catch (error) {
        alert.error("Erro dentro da função criarTabelaComJson: " + error);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    carregarDadosJson();
    //carregarDadosSeguidoresJson();
    carregarDadosExperienciaProfissionalJson();
    carregarDadosFormacaoAcademicaJson();
    carregarDadosConhecimentosJson();
    carregarDadosIdiomaJson();
})