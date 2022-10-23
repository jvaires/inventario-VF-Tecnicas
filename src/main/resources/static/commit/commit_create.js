window.onload = async function getBody() {
    await loadListeners();
}

async function loadListeners(){
    const form = document.querySelector("#form-create-commit");

    if(form)
    {
        form.addEventListener("submit", function(e){
            e.preventDefault();

            postData(commitsEndpoint, buildJsonFormData(form), () => { window.location.href = baseUrl; });
        })
    }
}