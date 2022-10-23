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
    elements.namedItem('mensagem').value = json.mensagem;
    elements.namedItem('codigo').value = json.codigo;
}

async function loadListeners(){
    const form = document.querySelector("#form-update-commit");

    if(form)
    {
        form.addEventListener("submit", function(e){
            e.preventDefault();

            putData(commitsEndpoint, buildJsonFormData(form), () => { window.location.href = baseUrl; });
        })
    }
}

window.onload = async function getBody() {
    await loadListeners();
    await loadCommitValues();
}