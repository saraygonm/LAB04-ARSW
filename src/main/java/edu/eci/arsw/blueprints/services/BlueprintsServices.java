/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BluePrintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.LinkedHashMap;
import java.util.Map;

import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("BlueprintsServices")
public class BlueprintsServices {


    @Autowired
    @Qualifier("inMemoryBlueprintPersistence")
    BlueprintsPersistence bpp=null;

    @Autowired
    @Qualifier("Redundancia")
    BluePrintFilter filter;
    
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException {
        return bpp.getBlueprintByAll();
    }

    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
        return bpp.getBlueprint(author,name);
    }

    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
        return bpp.getBlueprintsByAuthor(author);
    }

    /**
     * Aplica el filtro que esté definido al blueprint que se envía.
     * @param bluePrint Plano al que se quiere aplicar el filtro.
     * @return Un nuevo blueprint con el filtro aplicado.
     * @throws BlueprintPersistenceException
     */
    public Blueprint getFilteredBlueprints(Blueprint bluePrint) throws BlueprintPersistenceException {
        return filter.filter(bluePrint);
    }
}
