package edu.eci.arsw.blueprints.UI;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String []args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        //Definición del archivo de contexto para Spring.
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Creación de la instancia de la clase de servicios.
        BlueprintsServices cs = ac.getBean(BlueprintsServices.class);
        //Registro de planos.
        //Consulta de planos.
        //Consulta de un plano específico.
        //Consulta de un plano por un autor.
    }
}
