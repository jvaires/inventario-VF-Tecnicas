window.onload = async function getBody() {
    await loadListeners();
}

async function loadListeners(){
    const form = document.querySelector("#form-create-commit");
    const { elements } = document.querySelector("#form-create-commit");

    if(form)
    {
        form.addEventListener("submit", function(e){

            e.preventDefault();

            let usuario = new Usuario(null, elements.namedItem('autor').value);
            let commit = new Commit(null, elements.namedItem('mensagem').value, null, usuario);

            postData(commitsEndpoint, commit, () => { window.location.href = baseUrl; });
        })
    }
}