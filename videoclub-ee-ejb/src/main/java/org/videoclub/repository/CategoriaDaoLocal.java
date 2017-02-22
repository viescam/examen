/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.repository;

import java.util.List;
import javax.ejb.Local;
import org.videoclub.domain.Categoria;

/**
 *
 * @author lodiade
 */
@Local
public interface CategoriaDaoLocal {

    List listCategorias();

    void addCategoria(Categoria categoria);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(Categoria categoria);

    Categoria findCategoriaById(Categoria categoria);
    
}
