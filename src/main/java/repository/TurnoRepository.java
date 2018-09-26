/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import model.Professor;
import model.Turno;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Muriel
 */
public interface TurnoRepository extends MongoRepository<Turno, String>{
    
}
