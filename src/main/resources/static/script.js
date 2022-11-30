

const inventario={
    post:[
        {
            id: 1,
            marca: 'Asus',
            modelo: 'I5'
        }
    ],
    criaPost(dados){
        inventario.post.push({
            id:inventario.post.length+1,
            marca: dados.marca,
            modelo: dados.modelo
        });
        const lista = document.querySelector('.listaDePost')
        lista.insertAdjacentHTML('afterbegin', `<li>${dados.content}</li>`)
    }
}

const meuForm = document.querySelector('.form')

meuForm.addEventListener('submit', function criarPost(e){
    e.preventDefault()
    console.log("criado!!!")
    const campo = meuForm

    inventario.criaPost({
        marca: campo.valueOf(),
        modelo:'I7'
    }, )
    console.log(inventario.post)
})
