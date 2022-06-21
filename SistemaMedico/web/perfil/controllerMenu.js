
/* global ad, listaPacinete */

var urlFront = "http://localhost:8080/SistemaMedico/";
var urlBack = "http://localhost:8080/SistemaMedicoBackend/";
//------------------------------------------------------------------------------------------//
var horaTiempo = 0;
var Medico;
//------------------------------------------------------------------------------------------//
var listaHorarios = new Array();
listaHorariosMaxMin = new Array();
listaPacinete = new Array();
var dias = ["Horario", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"];


//este metodo me genera la agenda 
function generarAgenda() {
    const Toast = Swal.mixin({
        toast: true,
        position: 'bottom-end',
        showConfirmButton: false,
        timer: 2000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer);
            toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
    });
    Toast.fire({
        icon: 'success',
        title: 'BIENVENIDO'
    });
    var body = $("#tbody");
    //let body = document.getElementById("tbody");
    let trDias = document.getElementById("dias");
    trDias.innerHTML = "";
    for (let i = 0; i < 7; i++) {
        trDias.innerHTML += ` <th id="${dias[i]}" class="text-uppercase">${dias[i]}</th>`;
    }
    let arrayH = generarHorario(calMinHorario(Medico.listahorarios), calMaxHorario(Medico.listahorarios));
    arrayH.forEach((e) => {
        //let tr = document.createElement("tr").setAttribute("id",e.toString());
        var tr = $("<tr/>");
        var tds = " <td class='align-middle'>" + e + "</td>" +
                "<td id=" + e + "Lunes> " + "</td>" +
                "<td id=" + e + "Martes>  " + "</td>" +
                "<td id=" + e + "Miercoles>" + "</td>" +
                "<td id=" + e + "Jueves> " + " </td>" +
                "<td id=" + e + "Viernes>" + "</td>" +
                "<td id=" + e + "Sabado>" + "</td>";
        tr.html(tds);
        body.append(tr);
        let arrahorariosgenerales = generarListasHorarios(Medico.listahorarios);
        arrahorariosgenerales.forEach((listas) => {
            listas.forEach((lista) => {
                if (document.getElementById(lista.hora + lista.dia)) {
                    document.getElementById(lista.hora + lista.dia).classList.add("evento");
                    document.getElementById(lista.hora + lista.dia).addEventListener("dblclick", () => {
                        cargarPopUp(lista.hora, lista.dia);
                    });
                }
            });
        });
    });
}

function cargarPopUp(hora, dia) {
    document.getElementById("hora").setAttribute("value", hora);
    document.getElementById("dia").setAttribute("value", dia);
    document.getElementById("myModal").classList.remove("active");

}

//solo quitar el popUp
function remove() {
    document.getElementById("myModal").classList.add("active");
}

// este metodo lo uso para generar los horarios de acuerdo a lo que el usuario quiere
function generarListasHorarios(lista) {
    let list = new Array();
    var ini, min;
    let rango = 0;
    let list2 = new Array();

    lista.forEach((e) => {
        list2 = new Array();
        ini = parseInt(e.apertura);
        min = 0;
        horario = {dia: e.dia, hora: ini.toString() + ":00"};
        list2.push(horario);
        switch (parseInt(e.cierre)) {
            case 1:
                rango = 13;
            case 2:
                rango = 14;
            case 3:
                rango = 15;
            case 4:
                rango = 16;
            case 5:
                rango = 17;
            case 6:
                rango = 18;
            case 7:
                rango = 19;
            case 8:
                rango = 20;
        }
        for (var i = 0; i < rango; i++) {
            if (ini === parseInt(e.cierre)) {
                break;
            }
            min += horaTiempo;
            if (min < 60) {
                var h = ini.toString() + ":" + min.toString();
                horario = {dia: e.dia, hora: h};
                list2.push(horario);
            } else {
                if (min >= 60) {
                    var x = min - 60;
                    if (x === 0) {
                        ini += 1;
                        if (ini === 13) {
                            ini = 1;
                            min = 0;
                            var h = ini.toString() + ":00";
                            horario = {dia: e.dia, hora: h};
                            list2.push(horario);
                        } else {
                            min = 0;
                            var h = ini.toString() + ":00";
                            horario = {dia: e.dia, hora: h};
                            list2.push(horario);
                        }
                    } else {
                        ini += 1;
                        if (ini === 13) {
                            ini = 1;
                            var y = Math.abs(horaTiempo - x);
                            min = y;
                            var h = ini.toString() + ":" + (y).toString();
                            horario = {dia: e.dia, hora: h};
                            list2.push(horario);
                        } else {
                            var y = Math.abs(horaTiempo - x);
                            min = y;
                            var h = ini.toString() + ":" + (y).toString();
                            horario = {dia: e.dia, hora: h};
                            list2.push(horario);
                        }
                    }
                }
            }
        }
        list.push(list2);
    });
    return list;
}

//este es para genera la columna de horarios principal
function generarHorario(apertura, cierre) {
    var ini, min;
    var iniMili = 0;
    let rango = 0;
    let list2 = new Array();
    ini = parseInt(apertura);
    min = 0;
    switch (parseInt(cierre)) {
        case 1:
            rango = 13;
            break;
        case 2:
            rango = 14;
            break;
        case 3:
            rango = 15;
            break;
        case 4:
            rango = 16;
            break;
        case 5:
            rango = 17;
            break;
        case 6:
            rango = 18;
            break;
        case 7:
            rango = 19;
            break;
        case 8:
            rango = 20;
            break;
    }
    list2.push(ini.toString() + ":00");

    for (var i = 0; iniMili <= rango; i++) {
        if (ini === parseInt(cierre)) {
            break;
        }
        min += horaTiempo;
        if (min < 60) {
            var h = ini.toString() + ":" + min.toString();
            list2.push(h);
        } else {
            if (min >= 60) {
                var x = min - 60;
                if (x === 0) {
                    ini += 1;
                    iniMili += 1;
                    if (ini === 13) {
                        ini = 1;
                        min = 0;
                        var h = ini.toString() + ":00";
                        list2.push(h);
                    } else {
                        min = 0;
                        var h = ini.toString() + ":00";
                        list2.push(h);
                    }
                } else {
                    iniMili += 1;
                    ini += 1;
                    if (ini === 13) {
                        ini = 1;
                        var y = Math.abs(horaTiempo - x);
                        min = y;
                        var h = ini.toString() + ":" + (y).toString();
                        list2.push(h);
                    } else {
                        var y = Math.abs(horaTiempo - x);
                        min = y;
                        var h = ini.toString() + ":" + (y).toString();
                        list2.push(h);
                    }
                }
            }
        }
    }
    return list2;
}

//generar el horario menor
function calMinHorario(lista) {
    let minHorario = Math.min(...lista.map(x => parseInt(x.apertura)));
    return minHorario;
}

//generar el horario mayor
function calMaxHorario(lista) {
    let maxHorario = Math.max(...lista.map(x => parseInt(x.cierre)));
    return maxHorario;
}

//funcion para atender cita, HAY QUE MANDAR TODOS LOS DATOS PARA EL FORM 
function atenderCita() {
    document.location = urlFront + "atendercita/atendercita.html";
}

//guardar cita y cambia el evento del td
//guardoo la cita, la mando a la bd le ejecuto el evento al atender cita
function guardarCita() {
    let frag = document.createDocumentFragment();
    let hora = document.getElementById("hora").value;
    let dia = document.getElementById("dia").value;
    let tipoCita = document.getElementById("tipo").value;
    let mobtivoCita = document.getElementById("motivo").value;
    //let paciente =  document.getElementById("paciente").value;
    //valido todos los campos
    let divIdPaciente = document.createElement('div');
    divIdPaciente.setAttribute("class", "font-size13");
    divIdPaciente.innerHTML = `Alexa`;
    frag.appendChild(divIdPaciente);
    document.getElementById(hora + dia).appendChild(frag);
    document.getElementById(hora + dia).addEventListener("click", atenderCita);
    document.getElementById("myModal").classList.add("active");
}

//carga todos los eventos al dom
function cargarEventos() {
    listarHorariosMedico();
    document.getElementById("closed").addEventListener("click", remove);
    document.getElementById("button").addEventListener("click", guardarCita);
    document.getElementById("listapaciente").addEventListener("click", listarPacientes);
}
function generarPacientesSelect() {
    let selects = document.getElementById("listapaciente");
    listaPacinete.forEach((e) => {
        selects.innerHTML += `<option value="${e.idPaciente}">${e.nombre}</option>`;
    });
    document.getElementById("listapaciente").removeEventListener("click", listarPacientes);
}
function listarPacientes() {
    let usuarioJson = sessionStorage.getItem("user");
    let usuario = JSON.parse(usuarioJson);
    let x = usuario.idUser;
    const request = new Request(urlBack + 'api/pacientes/medicos/'+x, {method: 'GET', headers: {}});
    (async () => {
        try {
            const response = await fetch(request);
            if (!response.ok) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Ha ocurrido un error!',
                    footer: '<a href="">No se encontraron los pacientes</a>'
                });
            } else {
                listaPacinete = await response.json();
                console.log(listaPacinete);
                generarPacientesSelect();
            }
        } catch (e) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: `${e}`,
                footer: '<a href="">No se encontraron los pacientes</a>'
            });
        }
    })();
}

//solo hay que esperar el user que se loguea y usar el id
function listarHorariosMedico() {
    let usuarioJson = sessionStorage.getItem("user");
    let usuario = JSON.parse(usuarioJson);
    Medico = "";
    let request = new Request(urlBack + 'api/medicos/'+usuario.idUser, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Ha ocurrido un error!',
                footer: '<a href="">No se encontraron los horarios del medico</a>'
            });
        }
        Medico = await response.json();
        horaTiempo = parseInt(Medico.tiempoCitas);
        generarAgenda();
    })();
}
document.addEventListener("DOMContentLoaded", cargarEventos);

