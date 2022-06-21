var urlFrontEnd = "http://localhost:8080/SistemaMedico/";
var urlBackend = "http://localhost:8080/SistemaMedicoBackend/api";

$(document).ready(function () {
    fetchDataPaciente();
});

function renderPaciente(paciente) {
    $("#idPaciente").val(paciente.idPaciente);
    $("#nombrePaciente").val(paciente.nombre);
    $("#edadPaciente").val(paciente.edad);
    $("#infoContactoPaciente").val(paciente.infoContacto);
}

function renderEnfermedades(enfermedades) {
    tableEnfermedades = $("#content-table-enfermedades");
    tableEnfermedades.html("");
    if (enfermedades.length === 0) {
        newTable = `<tr>
                    <td colspan="2">No hay enfermedades registradas</td>
            </tr>`;
    } else {
        newTable = enfermedades.reduce((table, enfermedad) => {
            return table + `
                <tr>
                    <td>${enfermedad.nombre}</td>
                    <td><button type="button" class="btn btn-danger" onClick="eliminarEnfermedad('${enfermedad.idPaciente}', '${enfermedad.nombre}')">Eliminar</button></td>
                </tr>
            `;
        }, "");
    }
    tableEnfermedades.append(newTable);
}

function renderAlergias(alergias) {
    tableAlergias = $("#content-table-alergias");
    tableAlergias.html("");
    if (alergias.length === 0) {
        newTable = `<tr>
                    <td colspan="2">No hay alergias registradas</td>
            </tr>`;
    } else {
        newTable = alergias.reduce((table, alergia) => {
            return table + `
                <tr>
                    <td>${alergia.nombre}</td>
                    <td><button type="button" class="btn btn-danger" onClick="eliminarAlergia('${alergia.idPaciente}', '${alergia.nombre}')">Eliminar</button></td>
                </tr>
            `;
        }, "");
    }
    tableAlergias.append(newTable);
}

function renderCirugias(cirugias) {
    tableCirugias = $("#content-table-cirugias");
    tableCirugias.html("");
    if (cirugias.length === 0) {
        newTable = `<tr>
                    <td colspan="2">No hay cirugias registradas</td>
            </tr>`;
    } else {
        newTable = cirugias.reduce((table, cirugia) => {
            return table + `
                <tr>
                    <td>${cirugia.nombre}</td>
                    <td><button type="button" class="btn btn-danger" onClick="eliminarCirugia('${cirugia.idPaciente}', '${cirugia.nombre}')">Eliminar</button></td>
                </tr>
            `;
        }, "");
    }
    tableCirugias.append(newTable);
}

async function obtenerPaciente(idPaciente){
    // Obtener Paciente
    const response = await fetch(`${urlBackend}/pacientes/${idPaciente}`);
    if (response.status !== 200) {
        alert("Error al obtener el paciente");
        return;
    }
    renderPaciente(await response.json());
}

async function fetchDataPaciente() {
    const urlParams = new URLSearchParams(window.location.search);
    let idPaciente = urlParams.get('idPaciente');
    obtenerPaciente(idPaciente);
    obtenerEnfermedades(idPaciente);
    obtenerAlergias(idPaciente);
    obtenerCirugias(idPaciente);
}

async function actualizarPaciente() {
    paciente = {
        idPaciente: $("#idPaciente").val(),
        nombre: $("#nombrePaciente").val(),
        edad: parseInt($("#edadPaciente").val()),
        infoContacto: $("#infoContactoPaciente").val()
    };
    const response = await fetch(`${urlBackend}/pacientes`, {method: "PUT", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(paciente)});
    if (response.status !== 200) {
        alert("Error al actualizar el paciente");
    } else {
        alert("Paciente actualizado correctamente");
        document.location=urlFrontEnd+"mantenimiento/mantenimientoc.html";
    }
}

async function obtenerEnfermedades(idPaciente){
    // Obtener Enfermedades del Paciente
    const response = await fetch(`${urlBackend}/enfermedades/${idPaciente}`);
    if (response.status !== 200) {
        alert("Error al obtener las enfermedades del paciente");
        return;
    }
    renderEnfermedades(await response.json());
}

async function obtenerAlergias(idPaciente){
    // Obtener Alergias del Paciente
    const response = await fetch(`${urlBackend}/alergias/${idPaciente}`);
    if (response.status !== 200) {
        alert("Error al obtener las alergias del paciente");
        return;
    }
    renderAlergias(await response.json());
}

async function obtenerCirugias(idPaciente){
    // Obtener Cirugias del Paciente
    const response = await fetch(`${urlBackend}/cirugias/${idPaciente}`);
    if (response.status !== 200) {
        alert("Error al obtener las cirugias del paciente");
        return;
    }
    renderCirugias(await response.json());
}

async function registrarEnfermedad() {
    const urlParams = new URLSearchParams(window.location.search);
    let idPaciente = urlParams.get('idPaciente');
    enfermedad = {
        idPaciente: idPaciente,
        nombre: $("#nombreEnfermedad").val()
    };
    const response = await fetch(`${urlBackend}/enfermedades`, {method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(enfermedad)});
    if (response.status !== 200) {
        alert("Error al ingresar la enfermedad");
    } else {
        $("#modalAgregarEnfermedad").modal('hide');
        $("#nombreEnfermedad").val("");
        obtenerEnfermedades(idPaciente);
    }
}

async function registrarAlergia() {
    const urlParams = new URLSearchParams(window.location.search);
    let idPaciente = urlParams.get('idPaciente');
    enfermedad = {
        idPaciente: idPaciente,
        nombre: $("#nombreAlergia").val()
    };
    const response = await fetch(`${urlBackend}/alergias`, {method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(enfermedad)});
    if (response.status !== 200) {
        alert("Error al ingresar la alergia");
    } else {
        $("#modalAgregarAlergia").modal('hide');
        $("#nombreAlergia").val("");
        obtenerAlergias(idPaciente);
    }
}

async function registrarCirugia() {
    const urlParams = new URLSearchParams(window.location.search);
    let idPaciente = urlParams.get('idPaciente');
    enfermedad = {
        idPaciente: idPaciente,
        nombre: $("#nombreCirugia").val()
    };
    const response = await fetch(`${urlBackend}/cirugias`, {method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(enfermedad)});
    if (response.status !== 200) {
        alert("Error al ingresar la alergia");
    } else {
        $("#modalAgregarCirugia").modal('hide');
        $("#nombreCirugia").val("");
        obtenerCirugias(idPaciente);
    }
}

async function eliminarEnfermedad(idPaciente, nombre) {
    const response = await fetch(`${urlBackend}/enfermedades/${idPaciente}/${nombre}`, {method: "DELETE"});
    if (response.status !== 200) {
        alert("Error al eliminar la enfermedad");
        return;
    }
    obtenerEnfermedades(idPaciente);
}

async function eliminarAlergia(idPaciente, nombre) {
    const response = await fetch(`${urlBackend}/alergias/${idPaciente}/${nombre}`, {method: "DELETE"});
    if (response.status !== 200) {
        alert("Error al eliminar la alergia");
        return;
    }
    obtenerAlergias(idPaciente);
}

async function eliminarCirugia(idPaciente, nombre) {
    const response = await fetch(`${urlBackend}/cirugias/${idPaciente}/${nombre}`, {method: "DELETE"});
    if (response.status !== 200) {
        alert("Error al eliminar la cirugia");
        return;
    } 
    obtenerCirugias(idPaciente);
}