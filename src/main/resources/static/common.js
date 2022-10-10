const baseUrl = 'http://localhost:8080';

const commitsEndpoint = baseUrl + '/api/commits';
const commitItemEndpoint = baseUrl + '/commit_item.html';
const commitItemGroupEndpoint = baseUrl + '/commit_item_group.html';
const commitUpdateEndpoint = baseUrl + '/commit_update.html';
const commitCreateEndpoint = baseUrl + '/commit_create.html';
const createCommitEndpoint = baseUrl + '/api/commits';

function postData(url = '', data = {}, callback) {
    fetch(url, {
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

function buildJsonFormData(form) {
    const jsonFormData = {};

    for(const pair of new FormData(form)){
        jsonFormData[pair[0]] = pair[1];
    }

    return jsonFormData;
}

function redirectWithParameter(url, parameter, value)
{

}