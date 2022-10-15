const baseUrl = 'http://localhost:8080';

const commitsEndpoint = baseUrl + '/api/commits';
const commitItemEndpoint = baseUrl + '/commit_item.html';
const commitItemGroupEndpoint = baseUrl + '/commit_item_group.html';

window.onload = function getBody() {
    carregarCommits();
}

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
function sendJSON(){

    let username = document.querySelector('#username');
    let message = document.querySelector('#msg');

    let xhttp = new XMLHttpRequest();

    xhttp.open("POST", "api/commits", true);

//    xhttp.setRequestHeader("Content-Type", "application/json");
//
//    xhttp.onreadystatechange = function () {
//        if (xhttp.readyState === 4 && xhttp.status === 200) {
//
//            result.innerHTML = this.responseText;
//
//        }
//    };

    var data = JSON.stringify({ "username": username.value, "msg": msg.value });
    console.log(data);

    xhttp.send(data);
}