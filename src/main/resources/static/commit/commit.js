const baseUrl = 'http://localhost:8080';

const commitsEndpoint = baseUrl + '/api/commits';
const commitItemEndpoint = baseUrl + '/commit_item.html';
const commitItemGroupEndpoint = baseUrl + '/commit_item_group.html';

window.onload = function getBody() {
    carregarCommits();
}
    // const update = {
    //     autor: document.getElementById("username").value,
    //     mensagem: document.getElementById("msg").value
    // }
    // const options ={
    //     method: "POST",
    //     headers: {
    //         "Content-type": "application/json"
    //     },
    //     body: JSON.stringify(update)
    // }
    //
    // fetch(commitsEndpoint, options)
    //     .then(data =>{
    //         if (!data.ok){
    //             throw Error(data.status)
    //         }
    //         return data.json()
    //     }).then(update => {
    //         console.log(update)
    // }).catch(e => {
    //     console.log(e)
    // })


function fazPost(url, body){
    console.log("Body= ", body)
    let request = new XMLHttpRequest()
    request.open("POST", url, true)
    request.setRequestHeader("Content-type", "application/json")
    request.send(JSON.stringify(body))

    request.onload = function (){
        console.log(this.responseText)
    }
    return request.responseText
}
function cadastraCommit(){
    event.preventDefault()
    let url = "http://localhost:8080/api/commits"
    let autor = document.getElementById("username").value
    let mensagem = document.getElementById("msg").value
    console.log(autor)
    console.log(mensagem)

    let body = {
        "name": autor,
        "mensagem": mensagem
    }

    fazPost(url, body)
}

const submit = document.querySelector( '.myForm').submit()


function carregarCommits() {
    let htmlItemGroup;
    fetch(commitItemGroupEndpoint)
        .then((response) => response.text())
        .then((html) => {
            htmlItemGroup = html;
        });

    let htmlItem;
    fetch(commitItemEndpoint)
        .then((response) => response.text())
        .then((html) =>{
            htmlItem = html;
        });

    fetch(commitsEndpoint)
        .then((response) => response.json())
        .then((data) => {
            if(data) {

                for(const [key, value] of Object.entries(data))
                {
                    let timeline = document.getElementById('timeline-content-commits');
                    let parser = new DOMParser();

                    const docCommitGroup = parser.parseFromString(htmlItemGroup, "text/html");

                    docCommitGroup.getElementById('data-commit').textContent = 'Commits on ' + formatarData(Date.parse(key));

                    const commitItemDom = docCommitGroup.getElementById('commit-item');

                    for(let commitItem of value)
                    {
                        let docCommitItem = parser.parseFromString(htmlItem, "text/html");

                        docCommitItem.getElementById('nome-commit').textContent = commitItem.autor.nome;
                        docCommitItem.getElementById('periodo-commit').textContent = commitItem.intervaloCommit;
                        docCommitItem.getElementById('mensagem-commit').textContent = commitItem.mensagem;
                        docCommitItem.getElementById('sha-commit').textContent = commitItem.codigoAbreviado;

                        commitItemDom.appendChild(docCommitItem.body);
                    }

                    timeline.appendChild(docCommitGroup.body);
                }
            }
        });
}

function formatarData(data)
{
    let ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(data);
    let mo = new Intl.DateTimeFormat('en', { month: 'short' }).format(data);
    let da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(data);

    return `${mo}. ${da}, ${ye}`;
}

