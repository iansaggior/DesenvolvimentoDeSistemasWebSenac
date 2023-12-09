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
    };
    xhr.send();
  });
  return promise;
}

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

  document.querySelectorAll('input[name]')
    .forEach(el => el.classList.remove('is-invalid'));
  document.querySelectorAll('input[name] ~ p')
    .forEach(el => el.innerHTML = '');

  try {
    await postJson('/carros', dados);
    alert('Sucesso');
    window.location.href = "./index.html";
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

document.getElementById('frmCarros').onsubmit = montarEnviarDados;

// Setar data de nascimento máxima
document.getElementById('anoLancamento').max = new Date().getFullYear()
// console.log(new Date().getFullYear());


// // Setar data de nascimento máxima
// document.getElementById('txtDataNascimento').max = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60 * 1000)
//   .toISOString().substring(0, 10);
