/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.videoclub.domain.Cinta;

/**
 *
 * @author alumno
 */
@Stateless
public class CintaDao implements CintaDaoLocal {
    
    @PersistenceContext(unitName="VideoclubPU")
    EntityManager em;  
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listCintas() {
        List a = em.createNamedQuery("Cinta.findAll").getResultList();
        return a;
    }

    @Override
    public void addCina(Cinta cinta) {
        em.persist(cinta);
    }

    @Override
    public Cinta findCintaById(Cinta cinta) {
        return em.find(Cinta.class, cinta.getId());
    }

    @Override
    public void deleteCinta(Cinta cinta) {
        cinta = findCintaById(cinta);
        em.remove(cinta);
    }

    @Override
    public void updateCinta(Cinta cinta) {
        em.merge(cinta);
    }
    
    
    
    
    
}
