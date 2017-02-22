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
import org.videoclub.domain.Categoria;
import org.videoclub.repository.CategoriaDaoLocal;

/**
 *
 * @author lodiade
 */
@Stateless
public class CategoriaService implements CategoriaServiceLocal {

    @EJB
    private CategoriaDaoLocal categoriaDao;
    
    @Resource
    private SessionContext contexto;    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listCategorias() {
        try{
            return categoriaDao.listCategorias();
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        }        
    }

    @Override
    public void addCategoria(Categoria categoria) {
        try{
            categoriaDao.addCategoria(categoria);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }             
    }

    @Override
    public void deleteCategoria(Categoria categoria) {
        try{
            categoriaDao.deleteCategoria(categoria);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }             
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        try{
            categoriaDao.updateCategoria(categoria);
        }catch (Throwable t){
            contexto.setRollbackOnly();
        }             
    }

    @Override
    public Categoria findCategoriaById(Categoria categoria) {
        try{
            return categoriaDao.findCategoriaById(categoria);
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        }     
    }

    @Override
    public List listCategoriaBySimilarName(Categoria categoria) {
        try{
            return categoriaDao.listCategoriaBySimilarName(categoria);
        }catch (Throwable t){
            contexto.setRollbackOnly();
            return null;
        }
    }
    
    
}
