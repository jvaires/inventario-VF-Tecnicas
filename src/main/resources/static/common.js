const baseUrl = 'http://localhost:8080';

const commitsEndpoint = baseUrl + '/api/commits';
const commitItem = baseUrl + '/commit_item.html';
const commitItemGroup = baseUrl + '/commit_item_group.html';
const commitUpdate = baseUrl + '/commit_update.html';
const commitCreate = baseUrl + '/commit_create.html';

class Usuario
{
    constructor(codigo, nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
}

class Commit
{
    constructor(codigo, mensagem, data, autor) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.data = data;
        this.autor = autor;
    }
}

async function postData(url = '', data = {}, callback) {
    await fetch(url, {
        method: 'POST',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    });

    if(callback)
        callback();
}

async function putData(url = '', data = {}, callback) {
    await fetch(url, {
        method: 'PUT',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    });

    if(callback)
        callback();
}

async function deleteData(url = '', data = {}, callback) {
    await fetch(url, {
        method: 'DELETE',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
        body: JSON.stringify(data)
    });

    if(callback)
        callback();
}

function buildJsonFormData(form) {
    const jsonFormData = {};

    for(const pair of new FormData(form)){
        jsonFormData[pair[0]] = pair[1];
    }

    return jsonFormData;
}