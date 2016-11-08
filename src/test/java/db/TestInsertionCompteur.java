package db;
import beans.Compteur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * Created by thiba on 08/11/2016.
 */
public class TestInsertionCompteur {

    @Before
    public void insertion(){
        DatabaseManager db = new DatabaseManager();
        Compteur c = new Compteur(-1,"monTitreDeTest",1,"08/11/2016 17:30:00",9999);
        db.ajouterCompteur(c);
    }
    @Test
    public void testInsertionCompteur() {
        DatabaseManager db = new DatabaseManager();
        ArrayList<Compteur> ac = db.getCompteurs(9999);
        assertTrue(true);
        assertEquals("monTitreDeTest", ac.get(0).getTitre());
    }

    @After
    public void ModifierCompteur(){
        DatabaseManager db = new DatabaseManager();
        ArrayList<Compteur> ac = db.getCompteurs(9999);
        ac = db.getCompteurs(9999);
        db.supprimerCompteur(ac.get(0).getId());
    }
}
