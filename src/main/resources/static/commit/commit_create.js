window.onload = function getBody() {
    loadListeners();
}

function loadListeners(){
    const form = document.querySelector("#form-create-commit");

    if(form)
    {
        form.addEventListener("submit", function(e){
            e.preventDefault();

            postData(createCommitEndpoint, buildJsonFormData(form), () => { window.location.href = baseUrl; });
        })
    }
}