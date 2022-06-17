var urlFrontEnd = "http://localhost:8080/SistemaMedico/";
var urlBackend = "http://localhost:8080/SistemaMedicoBackend/api";

async function fetchPacientesByMedico() {
    let idMedico = 22; // TO DO (para hacer) Obtener el Id del medico logeado
    const response = await fetch(`${urlBackend}/pacientes/medico/${idMedico}`);
    if (response.status !== 200) {
        alert("Error al obtener los pacientes");
        return;
    }
    actualizarTable(await response.json());
}

function actualizarTable(pacientes) {
    tablePacientes = $("#content-table-pacientes");
    tablePacientes.html("");
    if (pacientes.length === 0) {
        newTable = `<tr>
                    <td colspan="4">No tiene pacientes registrados</td>
            </tr>`;
    } else {
        newTable = pacientes.reduce((table, paciente) => {
            return table + `
                <tr>
                    <td>${paciente.idPaciente}</td>
                    <td>${paciente.nombre}</td>
                    <td>${paciente.edad}</td>
                    <td><button type="button" class="btn buttom-costum-color" onClick="visualizarPaciente(${paciente.idPaciente})">Visualizar</button></td>
                </tr>
            `;
        }, "");
    }
    tablePacientes.append(newTable);
}

function visualizarPaciente(idPaciente) {;
    document.location = url + "mantenimiento/paciente.html?idPaciente=" + idPaciente;
}

async function registrarPaciente() {
    let idMedico = 1234; // TO DO (para hacer) Obtener el Id del medico logeado        
    paciente = {
        idPaciente: $("#idPaciente").val(),
        medico: {idUser: idMedico},
        nombre: $("#nombrePaciente").val(),
        edad: parseInt($("#edadPaciente").val()),
        infoContacto: $("#infoContactoPaciente").val()
    };
    const response = await fetch(`${urlBackend}/pacientes`, {method: "POST", headers: {'Content-Type': 'application/json'}, body: JSON.stringify(paciente)});
    if (response.status !== 200) {
        alert("Error al registrar el paciente");
        return;
    } else {
        $("#modalAgregarPaciente").modal('hide');
        $("#idPaciente").val("");
        $("#nombrePaciente").val("");
        $("#edadPaciente").val("");
        $("#infoContactoPaciente").val("");
        fetchPacientesByMedico();
    }
}

$(document).ready(function(){
    fetchPacientesByMedico();
});
