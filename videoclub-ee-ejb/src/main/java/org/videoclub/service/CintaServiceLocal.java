/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.service;

import java.util.List;
import javax.ejb.Local;
import org.videoclub.domain.Cinta;

/**
 *
 * @author alumno
 */
@Local
public interface CintaServiceLocal {

    List listCintas();

    void addCinta(Cinta cinta);

    void deleteCinta(Cinta cinta);

    void updateCinta(Cinta cinta);

    Cinta findCintaById(Cinta cinta);
    
}
