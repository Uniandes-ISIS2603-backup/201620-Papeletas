/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.papeletas.hospital.persistence;

import java.util.logging.Logger;

/**
 *
 * @author jc.useche10
 */
@Stateless
public class CitaPersistence {
     private static final Logger LOGGER = Logger.getLogger(CitaPersistence.class.getName());

    @PersistenceContext(unitName = "PapeletasPU")
     protected EntityManager em;
}
