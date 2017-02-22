/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.service;

import java.util.List;
import javax.ejb.Local;
import org.videoclub.domain.Categoria;

/**
 *
 * @author lodiade
 */
@Local
public interface CategoriaServiceLocal {

    List listCategorias();

    void addCategoria(Categoria categoria);

    void deleteCategoria(Categoria categoria);

    void updateCategoria(Categoria categoria);

    Categoria findCategoriaById(Categoria categoria);
    
}
