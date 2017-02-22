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
import org.videoclub.domain.Pelicula;
import org.videoclub.repository.PeliculaDaoLocal;

/**
 *
 * @author lodiade
 */
@Stateless
public class PeliculaService implements PeliculaServiceLocal {

    @EJB
    private PeliculaDaoLocal peliculaDao;
    
    @Resource
    private SessionContext contexto;      

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listPeliculas() {
        try{
            return peliculaDao.listPeliculas();
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        } 
    }

    @Override
    public void addPelicula(Pelicula pelicula) {
        try{
            peliculaDao.addPelicula(pelicula);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }         
    }

    @Override
    public void deletePelicula(Pelicula pelicula) {
        try{
            peliculaDao.deletePelicula(pelicula);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }          
    }

    @Override
    public void updatePelicula(Pelicula pelicula) {
        try{
            peliculaDao.updatePelicula(pelicula);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }          
    }

    @Override
    public Pelicula findPeliculaById(Pelicula pelicula) {
        try{
            return peliculaDao.findPeliculaById(pelicula);
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        } 
    }
}
