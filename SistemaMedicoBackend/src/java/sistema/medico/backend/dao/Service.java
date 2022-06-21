package sistema.medico.backend.dao;

import java.util.ArrayList;
import sistema.medico.backend.logic.Alergia;
import sistema.medico.backend.logic.Cirugia;
import sistema.medico.backend.logic.Enfermedad;
import sistema.medico.backend.logic.Horario;
import sistema.medico.backend.logic.Medico;
import sistema.medico.backend.logic.Paciente;
import sistema.medico.backend.logic.User;

public class Service {
    private static Service _instance;
    
    // DAOS
    private final DaoPaciente daoPacientes;
    private final DaoEnfermedades daoEnfermedades;
    private final DaoAlergias daoAlergias;
    private final DaoCirugias daoCirugias;
    private final MedicoDao daomedicos;
    private final HorarioDao daohorarios;
    private final UserDao daousuarios;
    private Service(){
        daoPacientes = new DaoPaciente();
        daoEnfermedades = new DaoEnfermedades();
        daoAlergias = new DaoAlergias();
        daoCirugias = new DaoCirugias();
        daomedicos = new MedicoDao();
        daohorarios = new HorarioDao();
        daousuarios = new UserDao();
    }
    
    public static Service getInstance(){
        if (_instance == null){
            _instance = new Service();
        }
        return _instance; 
    }
    
    // ---------------------- PACIENTES ------------------------- //
    public ArrayList<Paciente> listarPacientesPorMedico(Integer idMedico) {
        return daoPacientes.listarPacientesPorMedico(idMedico);
    }
    public Paciente obtenerPacientePorId(String idPaciente) {
        return daoPacientes.obtenerPacientePorId(idPaciente);
    }
    public Boolean editarPaciente(Paciente paciente) {
        return daoPacientes.editarPaciente(paciente);
    }
    public Boolean agregarPaciente(Paciente paciente) {
        return daoPacientes.agregarPaciente(paciente);
    }
    
     public ArrayList<Paciente> listarPById(Integer usuario){
         return daoPacientes.listarpacientes(usuario);
     }
    
    // ---------------------- ENFERMEDADES ------------------------- //
    public ArrayList<Enfermedad> listarEnfermedadesPorPaciente(String idPaciente) {
        return daoEnfermedades.listarEnfermedadesPorPaciente(idPaciente);
    }
    public Boolean eliminarEnfermedad(String idPaciente, String name) {
        return daoEnfermedades.eliminarEnfermedad(idPaciente, name);
    }
    public Boolean agregarEnfermedad(Enfermedad enfermedad) {
        return daoEnfermedades.agregarEnfermedad(enfermedad);
    }
    
    // ---------------------- ALERGIAS ------------------------- //
    public ArrayList<Alergia> listarAlergiasPorPaciente(String idPaciente) {
        return daoAlergias.listarAlergiasPorPaciente(idPaciente);
    }
    public Boolean eliminarAlergia(String idPaciente, String name) {
        return daoAlergias.eliminarAlergia(idPaciente, name);
    }
    public Boolean agregarAlergia(Alergia enfermedad) {
        return daoAlergias.agregarAlergia(enfermedad);
    }
    
    // ---------------------- CIRUGIAS ------------------------- //
    public ArrayList<Cirugia> listarCirugiasPorPaciente(String idPaciente) {
        return daoCirugias.listarCirugiasPorPaciente(idPaciente);
    }
    public Boolean eliminarCirugia(String idPaciente, String name) {
        return daoCirugias.eliminarCirugia(idPaciente, name);
    }
    public Boolean agregarCirugia(Cirugia cirugia) {
        return daoCirugias.agregarCirugia(cirugia);
    }
    //------------------------MEDICO------------------------------//
    public Boolean agregarMedico(Medico medico){
        return daomedicos.registrar(medico);
    }
    public Medico buscarMedico(Integer usuario){
        return daomedicos.buscaMedico(usuario);
    }
     //------------------------HORARIOS------------------------------//
    public Boolean agregarHorarios(ArrayList<Horario> horarios){
        return daohorarios.registrar(horarios);
    }
    public ArrayList<Horario> listarHorarios(Integer usuario){
        return daohorarios.listarHorarios(usuario);
    }
    //--------------------------USER-----------------------------------//
    public Boolean registrarUser(User usuario){
        return daousuarios.registrar(usuario);
    }
    public User buscarUser(User usuario){
       return daousuarios.buscar(usuario);
    }
    public Boolean buscaExisteUser(User usuario){
        return daousuarios.buscaExiste(usuario);
    }
}
