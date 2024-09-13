/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

/**
 *
 * @author hcadavid
 */
@Service("inMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{
    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    /*
    Metodo que busca en el mapa blueprints y devuelve todos los Blueprints que pertenecen a un autor específico
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> Author=new HashSet<Blueprint>();
        for(Tuple<String,String> tupla : blueprints.keySet()){
            if(tupla.getElem1().equals(author)){
                Author.add(blueprints.get(tupla));
            }
        }
        return Author;
    }
    @Override
    /*
    metodo que recorre todas las keys y objetos del mapa blueprints,para almacenarlos en un conjunto llamado Author para devolverlos.
     */
    public Set<Blueprint> getBlueprintByAll() throws BlueprintNotFoundException {
        Set<Blueprint> Author=new HashSet<Blueprint>(); //almacena los objetos Blueprint.
        for(Tuple<String,String> tupla : blueprints.keySet()){
            Author.add(blueprints.get(tupla));
        }
        return Author;
    }
}
