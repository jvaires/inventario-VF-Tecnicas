async function carregarCommits() {
    let htmlItemGroup;
    await fetch(commitItemGroupEndpoint)
        .then((response) => response.text())
        .then((html) => {
            htmlItemGroup = html;
        });

    let htmlItem;
    await fetch(commitItemEndpoint)
        .then((response) => response.text())
        .then((html) =>{
            htmlItem = html;
        });

    await fetch(commitsEndpoint)
        .then((response) => response.json())
        .then((data) => {
            if(data) {

                for(const [key, value] of Object.entries(data))
                {
                    let timeline = document.getElementById('timeline-content-commits');
                    let parser = new DOMParser();

                    const docCommitGroup = parser.parseFromString(htmlItemGroup, "text/html");

                    docCommitGroup.getElementById('data-commit').textContent = 'Commits on ' + formatDate(Date.parse(key));

                    const commitItemDom = docCommitGroup.getElementById('commit-item');

                    for (let commitItem of value) {

                        let docCommitItem = parser.parseFromString(htmlItem, "text/html");

                        docCommitItem.getElementById('commit-li').setAttribute('item-id', commitItem.codigo);
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

function formatDate(data)
{
    let ye = new Intl.DateTimeFormat('en', { year: 'numeric' }).format(data);
    let mo = new Intl.DateTimeFormat('en', { month: 'short' }).format(data);
    let da = new Intl.DateTimeFormat('en', { day: '2-digit' }).format(data);

    return `${mo}. ${da}, ${ye}`;
}

function refUpdateCommit(updateButton)
{
    let itemId = updateButton.closest('.commit').getAttribute('item-id');

    let url = new URL(commitUpdateEndpoint);

    url.searchParams.append('commit-id', itemId);

    location.href = url.href;
}

async function removeCommit(removeButton)
{
    if (confirm('Deseja remover o commit?')) {
        let itemId = removeButton.closest('.commit').getAttribute('item-id');

        let commit = new Commit(itemId, null, null, null);

        await deleteData(commitsEndpoint, commit, () => { window.location.href = baseUrl; });
    } else {
        console.log('Deletar commit cancelado!');
    }
}

window.addEventListener("load", carregarCommits);
