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
import org.videoclub.domain.Categoria;

/**
 *
 * @author lodiade
 */
@Stateless
public class CategoriaDao implements CategoriaDaoLocal {
    
    @PersistenceContext(unitName="VideoclubPU")
    EntityManager em;    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public List listCategorias() {
        List a = em.createNamedQuery("Categoria.findAll").getResultList();
        return a;
    }
    
    
    
    @Override
    public void addCategoria(Categoria categoria) {
      em.persist(categoria);  
    }   
    
    @Override
    public void updateCategoria(Categoria categoria) {
        em.merge(categoria);
    }

    @Override
    public void deleteCategoria(Categoria categoria) {
        categoria = findCategoriaById(categoria);
        em.remove(categoria);          
    }

    @Override
    public Categoria findCategoriaById(Categoria categoria) {
        return em.find(Categoria.class, categoria.getId());
    }

    @Override
    public List listCategoriaBySimilarName(Categoria categoria) {
        return em.createNamedQuery("Categoria.findByName",Categoria.class).setParameter("nombre","%"+categoria.getNombre()+"%").getResultList();
    }
    
    

}
