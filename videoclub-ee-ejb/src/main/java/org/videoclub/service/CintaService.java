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
import org.videoclub.domain.Cinta;
import org.videoclub.repository.CintaDaoLocal;

/**
 *
 * @author alumno
 */
@Stateless
public class CintaService implements CintaServiceLocal {

    @EJB
    private CintaDaoLocal cintaDao;

    @Resource
    private SessionContext contexto;
    
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listCintas() {
        try{
            return cintaDao.listCintas();
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        } 
    }

    @Override
    public void addCinta(Cinta cinta) {
        try{
            cintaDao.addCina(cinta);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        } 
    }

    @Override
    public void deleteCinta(Cinta cinta) {
        try{
            cintaDao.deleteCinta(cinta);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }
    }

    @Override
    public void updateCinta(Cinta cinta) {
        try{
            cintaDao.updateCinta(cinta);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }
    }

    @Override
    public Cinta findCintaById(Cinta cinta) {
        try{
            return cintaDao.findCintaById(cinta);
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        } 
    }
    
    
    
    
    
    
}
