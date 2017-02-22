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
import org.videoclub.domain.Actor;

/**
 *
 * @author lodiade
 */
@Stateless
public class ActorDao implements ActorDaoLocal {
    
    @PersistenceContext(unitName="VideoclubPU")
    EntityManager em;      

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listActores() {
        List a = em.createNamedQuery("Actor.findAll").getResultList();
        return a;
    }

    @Override
    public void addActor(Actor actor) {
      em.persist(actor);         
    }

    @Override
    public void deleteActor(Actor actor) {
        actor = findActorById(actor);
        em.remove(actor);           
    }

    @Override
    public void updateActor(Actor actor) {
        em.merge(actor);        
    }

    @Override
    public Actor findActorById(Actor actor) {
        return em.find(Actor.class, actor.getId());
    }
}
