var url = "http://localhost:8080/SistemaMedicoBackend/";
var horarios = new Array();
var horario = {medicoId: "", dia: "", apertura: "", cierre: ""};
var medico = {costo: "", nombre: "", estado: "", descripcion: "", direccion: "", especialidad: "", localidad: "", tiempoCitas: "", idUser: "", horarios};
var user = {idUser: "", password: "", roll: ""};
var dias = ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes"];
var localidades = new Array();
var especialidades = new Array();
let isCreate = false;
var i = 0;

//================================= HORARIO =================================
function horarioPopUp() {
    let containerHorario = document.getElementById("HLaboral");
    if (isCreate === false) {
        containerHorario.innerHTML = "";
        for (let i = 0; i < 5; i++) {
            containerHorario.innerHTML += `<div class ="col-md-2">${dias[i]}<br>
                                <input type="checkbox" value="${dias[i]}" id="checkboxHorario">
                                <div class="horarioLaboral ocultar" id="${dias[i]}">
                                    <select id="HI-${dias[i]}">
                                        <option value="0" disabled selected>Hora inicio</option>
                                        <option value="8">8 am</option>
                                        <option value="9">9 am</option>
                                        <option value="10">10 am</option>
                                        <option value="11">11 am</option>
                                        <option value="12">12 pm</option>
                                    </select>
                                    <select id="HF-${dias[i]}">
                                        <option value="0" disabled selected>Hora cierre</option>
                                        <option value="1">1 pm</option>
                                        <option value="2">2 pm</option>
                                        <option value="3">3 pm</option>
                                        <option value="4">4 pm</option>
                                        <option value="5">5 pm</option>
                                    </select>
                                </div>
                            </div>`;
        }
        document.querySelectorAll("#checkboxHorario").forEach(item => {
            item.addEventListener('click', e => {
                if (e.target.checked) {
                    e.target.parentNode.querySelector(".horarioLaboral").classList.remove("ocultar");
                } else {
                    e.target.parentNode.querySelector(".horarioLaboral").classList.add("ocultar");
                }
            });
        });
        isCreate = true;
    }
    document.getElementById('guardar').addEventListener('click', guardarHorario);
    document.getElementById('cerrar').addEventListener('click', limpiarHorario);
}

//--------------------------------------------------------------------------
function guardarHorario() {
    document.querySelectorAll("#checkboxHorario:checked").forEach((e) => {
        var diaCita = e.value;
        console.log(diaCita);
        idMed = document.getElementById("id").value;
        entrada = document.getElementById("HI-" + diaCita).value;
        salida = document.getElementById("HF-" + diaCita).value;
        tConsul = document.getElementById("tiempoConsulta").value;
        if (!(entrada.length === 0 || salida.length === 0 || diaCita.length === 0)) {
            horario = {medicoId: idMed, dia: diaCita, apertura: entrada, cierre: salida};
            horarios.push(horario);
            console.log(horarios);
        }
    });
}

//--------------------------------------------------------------------------
function limpiarHorario() {
    document.querySelectorAll("#checkboxHorario:checked").forEach((e) => {
        e.checked = false;
        document.getElementById("HI-" + e.value).value = "0";
        document.getElementById("HF-" + e.value).value = "0";
        e.parentNode.querySelector(".horarioLaboral").classList.add("ocultar");
    });
    document.getElementById("tiempoConsulta").value = "0";
}

//============================= CARGAR DATOS =============================
function CargarLocalidades() {
    localidades = [];
    let request = new Request(url + 'api/medicos/localidades', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            localidades = [];
            Swal.fire({toast: true, position: 'center', icon: 'error', titleText: '¡Error con el servidor!',
                background: '#0489B1', showConfirmButton: false, timer: 1600})
        } else {
            localidades = await response.json();
            HTMLLocalidades();
        }
    })();
}

//---------------------------------------
function HTMLLocalidades() {
    let containerLocalidades = document.getElementById("ubicacion");
    containerLocalidades.innerHTML = "";
    for (let i = 0; i < localidades.length; i++) {
        containerLocalidades.innerHTML += `<option value="${localidades[i]}" selected>${localidades[i]}</option>`;
    }
    containerLocalidades.innerHTML += `<option value= "0" disabled selected>Elija una opción</option>`;

}

//--------------------------------------------------------------------------
function CargarEspecialidades() {
    especialidades = [];
    let request = new Request(url + 'api/medicos/especialidades', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            especialidades = [];
            Swal.fire({toast: true, position: 'center', icon: 'error', titleText: '¡Error con el servidor!',
                background: '#0489B1', showConfirmButton: false, timer: 1600})
        } else {
            especialidades = await response.json();
            HTMLEspecialidades();
        }
    })();
}

//---------------------------------------
function HTMLEspecialidades() {
    let containerEspecialidades = document.getElementById("especialidad");
    containerEspecialidades.innerHTML = "";
    for (let i = 0; i < especialidades.length; i++) {
        containerEspecialidades.innerHTML += `<option value="${especialidades[i]}" selected>${especialidades[i]}</option>`;
    }
    containerEspecialidades.innerHTML += `<option value= "0" disabled selected>Elija una opción</option>`;
}

//============================== VALIDACIONES ==============================
function validar() {
    let error = false;
    var id = document.getElementById("id").value;
    var nombre = document.getElementById("nombre").value;
    var contrasena = document.getElementById("contrasena").value;
    var contrasena2 = document.getElementById("contrasena2").value;
    var costo = document.getElementById("costo").value;
    var resena = document.getElementById("resena").value;
    var direccion = document.getElementById("direccion").value;
    var ubicacion = document.getElementById("ubicacion").value;
    var especialidad = document.getElementById("especialidad").value;
    var tiempoCitas = document.getElementById("tiempoConsulta").value;

    if (!(id.length === 0 || nombre.length === 0 || contrasena.length === 0 || contrasena2.length === 0 || costo.length === 0 || resena.length === 0 || direccion.length === 0 || ubicacion.length === 0 || especialidad === 0 || tiempoCitas.length === 0)) {
        medico = {costo: parseInt(costo), nombre: nombre, estado: 0, descripcion: resena, direccion: direccion, especialidad: especialidad, localidad: ubicacion, tiempoCitas: parseInt(tiempoCitas), idUser: parseInt(id), horarios};
        user = {idUser: parseInt(id), password: contrasena, roll: parseInt(1)};
        error = true;
    } else {
        error = false;
    }
    return error;
}

//--------------------------------------------------------------------------
function validarContrasena() {
    let error = false;
    const c1 = document.getElementById("contrasena").value;
    const c2 = document.getElementById("contrasena2").value;
    if (c1 === c2) {
        error = true;
    } else {
        error = false;
    }
    return error;
}

//--------------------------------------------------------------------------
function validarHorario() {
    let error = false;
    if (!(horarios.length === 0)) {
        document.querySelectorAll("#checkboxHorario:checked").forEach((e) => {
            let hi = document.getElementById("HI-" + e.value).value;
            let hf = document.getElementById("HF-" + e.value).value;
            if (hi !== "0" && hf !== "0") {
                error = true;
            } else {
                error = false;
            }
        });
    } else {
        error = false;
    }
    return error;
}

//============================== LOCALSTORAGE ==============================
function limpiarLocalStorage() {
    localStorage.removeItem("medico");
}

//================================= AÑADIR =================================
function add() {
    if (!validar()) {
        Swal.fire({toast: true, position: 'center', icon: 'warning', titleText: '¡Complete todos los campos!',
            background: '#0489B1', showConfirmButton: false, timer: 1600})
    } else if (!validarHorario()) {
        Swal.fire({toast: true, position: 'center', icon: 'warning', titleText: '¡Elija un horario laboral!',
            background: '#0489B1', showConfirmButton: false, timer: 1600})
    } else if (!validarContrasena()) {
        Swal.fire({toast: true, position: 'center', icon: 'warning', titleText: '¡Las contraseñas deben coincidir!',
            background: '#0489B1', showConfirmButton: false, timer: 1600})
    } else {
        const request = new Request(url + "api/usuarios", {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(user)});
        (async () => {
            try {
                const response = await fetch(request);
                if (!response.ok) {
                    Swal.fire({toast: true, position: 'center', icon: 'error', titleText: '¡NO se inserto el usuario',
                        background: '#0489B1', showConfirmButton: false, timer: 1600});
                } else {
                    const request = new Request(url + "api/medicos", {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(medico)});
                    (async () => {
                        try {
                            const response = await fetch(request);
                            if (!response.ok) {
                                Swal.fire({toast: true, position: 'center', icon: 'error', titleText: 'No se inserto el medico',
                                    background: '#0489B1', showConfirmButton: false, timer: 1600});
                            } else {
                                let request = new Request(url + 'api/horarios', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(horarios)});
                                (async () => {
                                    const response = await fetch(request);
                                    if (!response.ok) {
                                        Swal.fire({toast: true, position: 'center', icon: 'error', titleText: 'Ocurrio un error al agregar los horarios',
                                            background: '#0489B1', showConfirmButton: false, timer: 1600});
                                    } else {
                                        Swal.fire({toast: true, position: 'center', icon: 'error', titleText: '¡Registro realizado con éxito, ya puede iniciar sesión!',
                                            background: '#0489B1', showConfirmButton: false, timer: 1600});
                                            document.location = "/SistemaMedico/index.html";
                                    }
                                })();
                            }
                        } catch (e) {
                            Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text: `${e}`
                            });
                        }
                    })();
                }
            } catch (e) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `${e}`
                });
            }
        })();
    }
}

//async function addImagen(){
//    var imagenData = new FormData();
//    imagenData.append("id", medico.id);
//    imagenData.append("imagen", $("#imagen").get(0).files[0]); 
//    let request = new Request(url + '/medicos/' + medico.id + '/imagen', {method: 'POST', body: imagenData});
//    const response = await fetch(request);     
//    if (!response.ok) {
//        Swal.fire({toast: true, position: 'center', icon: 'error', titleText: '¡La imagen no se ha podido guardar!',
//                   background: '#0489B1', showConfirmButton: false, timer: 1600})
//    }              
//}

//--------------------------------------------------------------------------
function loaded() {
    limpiarLocalStorage();
    CargarLocalidades();
    CargarEspecialidades();
    document.getElementById('horario').addEventListener('click', horarioPopUp);
    document.getElementById("registrar").addEventListener("click", add);
}

document.addEventListener("DOMContentLoaded", loaded);
