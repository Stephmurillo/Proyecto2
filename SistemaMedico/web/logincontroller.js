/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var urlFront = "http://localhost:8080/SistemaMedico/";
var urlBack = "http://localhost:8080/SistemaMedicoBackend/";
userFront = "";
//-----------------------------------------------------------------
function validar() {
    let bandera = false;
    let id = document.getElementById("id").value;
    let contra = document.getElementById("contrasena").value;
    if (!(id.length === 0 || contra.length === 0)) {
        bandera = true;
    } else {
        bandera = false;
    }
    return bandera;
}
//-----------------------------------------------------------------------------------
function login() {
    if (!validar()) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Los campos son requeridos',
            footer: '<a href="">Debe completar todos los campos</a>'
        });
    } else {
        usuario = {
            idUser: document.getElementById("id").value,
            password: document.getElementById("contrasena").value
        };
        //ingresar
        let request = new Request(urlBack + 'api/login', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(usuario)});
        (async () => {
            const response = await fetch(request);
            if (!response.ok) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Ha ocurrido un error'
                });
            } else {
                userFront = await response.json();
                console.log(userFront);
                if (!(usuario === null)) {
                    sessionStorage.setItem('user', JSON.stringify(userFront));
                    switch (userFront.roll) {
                        case 1:
                            {document.location = urlFront + "perfil/agenda.html";}
                            break;
                        case 2:
                            {document.location = urlFront + "Admin/perfil/view.html";}break;
                    }
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Usuario no registrado'
                    });
                }
            }
        })();
    }
}
//------------------------------------------------------------------------------------------------
function loaded() {
    document.getElementById("login").addEventListener("click", login);
}

document.addEventListener("DOMContentLoaded", loaded);