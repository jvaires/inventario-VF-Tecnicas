var baseUrl = 'http://localhost:8080';

var commitsEndpoint = baseUrl + '/api/commits';
var commitItemEndpoint = baseUrl + '/commititem';
var commitItemGroupEndpoint = baseUrl + '/commititemgroup';

window.onload = function getBody() {
    carregarCommits();
}

function carregarCommits() {

    fetch(commitsEndpoint)
        .then((response) => response.json())
        .then((data) => {
            if(data) {

                for(const [key, value] of Object.entries(data))
                {
                    let timeline = document.getElementById('timeline-content-commits');

                    fetch(commitItemGroupEndpoint)
                        .then((response) => response.text())
                        .then((html) => {

                            let parser = new DOMParser();

                            const docCommitGroup = parser.parseFromString(html, "text/html");

                            docCommitGroup.getElementById('data-commit').textContent = 'Commits on ' + formatarData(Date.parse(key));

                            const commitItemDom = docCommitGroup.getElementById('commit-item');

                            for(let commitItem of value)
                            {
                                fetch(commitItemEndpoint)
                                    .then((response) => response.text())
                                    .then((html) => {

                                        let docCommitItem = parser.parseFromString(html, "text/html");

                                        docCommitItem.getElementById('nome-commit').textContent = commitItem.autor.nome;
                                        docCommitItem.getElementById('periodo-commit').textContent = commitItem.intervaloCommit;
                                        docCommitItem.getElementById('mensagem-commit').textContent = commitItem.mensagem;
                                        docCommitItem.getElementById('sha-commit').textContent = commitItem.codigoAbreviado;

                                        commitItemDom.appendChild(docCommitItem.body);
                                    });
                            }

                            timeline.appendChild(docCommitGroup.body);
                        });
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