package edu.eci.arsw.blueprints.UI;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

public class main {
    public static void main(String []args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml"); // archivo de contexto para Spring.
        BlueprintsServices cs = ac.getBean(BlueprintsServices.class);//instancia de la clase de servicios.
        //puntos de cada plano.
        ArrayList<Point> punto1 = new ArrayList<>(Arrays.asList(new Point(0, 0),new Point(1, 1),
                                  new Point(11, 12),new Point(50, 32),new Point(1, 1),new Point(1, 1)));
        ArrayList<Point> punto2 = new ArrayList<>(Arrays.asList(new Point(3, 5),new Point(14, 15)));
        ArrayList<Point> punto3 = new ArrayList<>(Arrays.asList(new Point(42, 50),new Point(71, 100)));

        //Registro  planos.
        System.out.println("=================================Registrar planos=================================");
        cs.addNewBlueprint(new Blueprint("Saray", "Saraygonm",punto1));
        cs.addNewBlueprint(new Blueprint("Alieth", "AliethG",punto2));
        cs.addNewBlueprint(new Blueprint("Juana", "Juanita",punto3));
        System.out.println("Los planos se han registrado en su totalidad:");
        System.out.println();
        System.out.println();

        //Consulta de planos.
        System.out.println("============================Consultar todos los planos============================");
        for (Blueprint i:cs.getAllBlueprints()){
            System.out.println(i.toString());
        }
        System.out.println();
        System.out.println();

        // plano en específico.
        System.out.println("===========================Consultar Un Planoen en específico======================");
        System.out.println(cs.getBlueprint("Saray","Saraygonm").toString());
        System.out.println();
        System.out.println();

        //plano por un autor.
        System.out.println("======================Consultar Un Plano específico por autor=====================");
        System.out.println(cs.getBlueprintsByAuthor("Alieth").toString());
        System.out.println();
        System.out.println();

        //Filtro .
        System.out.println("==============================Aplicación del filtro===============================");
        System.out.println(cs.getBlueprint("Saray","Saraygonm").toString());
        System.out.println("- Puntos originales: "+cs.getBlueprint("Saray","Saraygonm").getPoints().toString());
        System.out.println("- Puntos filtrados: "+cs.getFilteredBlueprints(cs.getBlueprint("Saray","Saraygonm")).getPoints().toString());

    }
}