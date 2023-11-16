function postJson(url, dados) {
    const promise = new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);
        xhr.setRequestHeader('content-type', 'application/json');
        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 400) {
                // SUCESSO
                if (xhr.responseText) {
                    resolve(JSON.parse(xhr.responseText));
                } else {
                    resolve();
                }
            } else {
                // ERROS
                if (xhr.status === 400) {
                    // Montar dados com os erros de validação
                    const objErroResp = JSON.parse(xhr.responseText);
                    const objErros = [];
                    if (objErroResp.errors.length > 0) {
                        for (const erro of objErroResp.errors) {
                            objErros.push({
                                campoName: erro.field,
                                mensagem: erro.defaultMessage
                            });
                        }
                    }
                    reject(objErros);
                } else {
                    const objErro = {
                        codigo: xhr.status,
                        mensagem: 'Erro'
                    };
                    reject(objErro);
                }
            }
        };
        xhr.send(JSON.stringify(dados));
    });
    return promise;
}

        async function montarEnviarDados(evt) {
            evt.preventDefault();
            const formulario = document.getElementById('frmCarros');

            const dados = {
                modelo: formulario.modelo.value,
                fabricante: formulario.fabricante.value,
                anoLancamento: formulario.anoLancamento.value,
            };
            console.log(JSON.stringify(dados));
            console.log("aaaaaaaaaaaaaaaaa");

            document.querySelectorAll('input[name]')
                .forEach(el => el.classList.remove('is-invalid'));
            document.querySelectorAll('input[name] ~ p')
                .forEach(el => el.innerHTML = '');

            try {
                await postJson('http://localhost:8080/carros', dados);
                alert('Sucesso');
                window.location.href = 'http://localhost:8080/index.html';
            } catch (err) {
                if (err.length > 0) {
                    for (const erro of err) {
                        const inputEl = document.querySelector('[name="' + erro.campoName + '"]');
                        inputEl.classList.add('is-invalid');
                        const messageEl = document.querySelector('[name="' + erro.campoName + '"] ~ p');
                        if (messageEl) {
                            messageEl.innerHTML = erro.mensagem;
                        }
                    }
                }
            }
        }
        document.getElementById('botaoDoIan').onsubmit = montarEnviarDados;


