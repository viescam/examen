/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.service;

import java.util.List;
import javax.ejb.Local;
import org.videoclub.domain.Pelicula;

/**
 *
 * @author lodiade
 */
@Local
public interface PeliculaServiceLocal {

    List listPeliculas();

    void addPelicula(Pelicula pelicula);

    void deletePelicula(Pelicula pelicula);

    void updatePelicula(Pelicula pelicula);

    Pelicula findPeliculaById(Pelicula pelicula);
    
}
