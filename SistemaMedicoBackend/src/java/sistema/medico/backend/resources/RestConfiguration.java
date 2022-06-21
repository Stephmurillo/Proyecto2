package sistema.medico.backend.resources;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("api")
public class RestConfiguration extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        //classes.add(MultiPartFeature.class);
        // Add each Class in RestFull
        classes.add(Pacientes.class);
        classes.add(Enfermedades.class);
        classes.add(Alergias.class);
        classes.add(Cirugias.class);
        classes.add(RestMedico.class);
        classes.add(login.class);
        classes.add(user.class);
        classes.add(horarios.class);
        return classes;
    }    
}
