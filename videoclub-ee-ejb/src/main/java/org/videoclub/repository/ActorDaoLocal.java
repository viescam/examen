/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoclub.repository;

import java.util.List;
import javax.ejb.Local;
import org.videoclub.domain.Actor;

/**
 *
 * @author lodiade
 */
@Local
public interface ActorDaoLocal {

    List listActores();

    void addActor(Actor actor);

    void deleteActor(Actor actor);

    void updateActor(Actor actor);

    Actor findActorById(Actor actor);
    
}
