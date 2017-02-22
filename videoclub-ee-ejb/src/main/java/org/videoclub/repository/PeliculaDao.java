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
import org.videoclub.domain.Pelicula;

/**
 *
 * @author lodiade
 */
@Stateless
public class PeliculaDao implements PeliculaDaoLocal {
    
    @PersistenceContext(unitName="VideoclubPU")
    EntityManager em;        

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List listPeliculas() {
        List a = em.createNamedQuery("Pelicula.findAll").getResultList();
        return a;
    }
    
    @Override
    public void addPelicula(Pelicula pelicula) {
      em.persist(pelicula);          
    }    
    
    @Override
    public void deletePelicula(Pelicula pelicula) {
        pelicula = findPeliculaById(pelicula);
        em.remove(pelicula);           
    }    
    
    @Override
    public void updatePelicula(Pelicula pelicula) {
        em.merge(pelicula);        
    }

    @Override
    public Pelicula findPeliculaById(Pelicula pelicula) {
        return em.find(Pelicula.class, pelicula.getId());
    }    
}
