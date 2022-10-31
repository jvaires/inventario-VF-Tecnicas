async function loadCommitValues()
{
    const urlParams = new URLSearchParams(window.location.search);

    let commitId = urlParams.get('commit-id');

    let getEndpoint = commitsEndpoint + `/${commitId}`;

    await fetch(getEndpoint)
        .then(response => response.json())
        .then(json => {
            fillCommitForm(json);
        });
}

function fillCommitForm(json)
{
    let { elements } = document.querySelector('#form-update-commit');

    elements.namedItem('autor').value = json.autor.nome;
    elements.namedItem('codigo-autor').value = json.autor.codigo;
    elements.namedItem('mensagem').value = json.mensagem;
    elements.namedItem('codigo').value = json.codigo;
}

async function loadListeners(){
    const form = document.querySelector("#form-update-commit");
    const { elements } = document.querySelector("#form-update-commit");

    if(form)
    {
        form.addEventListener("submit", function(e){

            e.preventDefault();

            let usuario = new Usuario(elements.namedItem('codigo-autor').value, elements.namedItem('autor').value);
            let commit = new Commit(elements.namedItem('codigo').value, elements.namedItem('mensagem').value, null, usuario);

            putData(commitsEndpoint, commit, () => { window.location.href = baseUrl; });
        })
    }
}

window.onload = async function getBody() {
    await loadListeners();
    await loadCommitValues();
}