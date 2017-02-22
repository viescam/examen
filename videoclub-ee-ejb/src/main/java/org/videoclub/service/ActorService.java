/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.service;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import org.videoclub.domain.Actor;
import org.videoclub.repository.ActorDaoLocal;

/**
 *
 * @author lodiade
 */
@Stateless
public class ActorService implements ActorServiceLocal {

    @EJB
    private ActorDaoLocal actorDao;
    
    @Resource
    private SessionContext contexto;       

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listActores() {
        try{
            return actorDao.listActores();
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        }  
    }

    @Override
    public void addActor(Actor actor) {
        try{
            actorDao.addActor(actor);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }          
    }

    @Override
    public void deleteActor(Actor actor) {
        try{
            actorDao.deleteActor(actor);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }         
    }

    @Override
    public void updateActor(Actor actor) {
        try{
            actorDao.updateActor(actor);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }           
    }

    @Override
    public Actor findActorById(Actor actor) {
        try{
            return actorDao.findActorById(actor);
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        }  
    }
}
