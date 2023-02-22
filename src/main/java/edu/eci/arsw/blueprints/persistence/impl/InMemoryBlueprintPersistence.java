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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
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
        if((blueprints.get(new Tuple<>(author, bprintname)) != null)){
            return blueprints.get(new Tuple<>(author, bprintname));
        }
        throw new BlueprintNotFoundException("Blueprint no se pudo encontrar");
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Tuple<String, String>> keys = blueprints.keySet();
        HashSet bps = new HashSet();
        for (Tuple t : keys){
            if (author.equals(t.o1)){
                bps.add(blueprints.get(new Tuple<>(author,(String)t.o2)));
            }
        }
        if(bps.isEmpty()){
            throw new BlueprintNotFoundException("El autor: " + author + " No existe");
        }
        return bps; 
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> bluePrinthash = new HashSet<Blueprint>();
        for (Tuple<String, String> blueprint : blueprints.keySet()) {
            if (!blueprint.getElem1().equals("_authorname_")) {
                bluePrinthash.add(blueprints.get(blueprint));
            }
        }
        return bluePrinthash;
    }

    
    
}
