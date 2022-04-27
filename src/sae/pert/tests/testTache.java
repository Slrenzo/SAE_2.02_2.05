/*
 * testTache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4, pas de copyright 
 */
package sae.pert.tests;

import sae.pert.Tache;
import testOutillage.assertionTest;

/** TODO commenter la responsabilit� de cette classe
 * @author emilien.restoueix
 *
 */
public class testTache {
    
    /**
     * Jeux de tests unitaires qui servira a tester des m�thodes
     */
    private static Tache[] aTester = {
        new Tache("Tache A", "Cette tache consiste � analyser le besoin", 2.0),
        new Tache("Tache B", "Mise en place des m�thodes de travail", 5.0),
        new Tache("Tache C", "R�partition du travail", 2.0),
        new Tache("Tache D", "R�alisation de l'application", 30.0)
    };
    
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringStringDouble();
        //ok &= testSetNom();
        ok &= testGetNom();
        
        
        if(ok) {
            System.out.println("Test r�ussis");
        } else {
            System.out.println("Test �chou�");
        }
    }
    
    /**
     * Test du constructeur de la class Tache
     * @return testOK
     */
    private static boolean testConstructeurStringStringDouble() {
        
        boolean testOK;
        
        try {
            new Tache(null, "testDescription", 30.5);
            testOK = false;
        } catch (NullPointerException nomNul) {
            testOK = true;
        }
        
        try {
            new Tache("  ", "testDescription", 30.5);
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", null, 30.5);
            testOK = false;
        } catch (NullPointerException descriptionNul) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "", 30.5);
            testOK = false;
        } catch (IllegalArgumentException descriptionVide) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "testDescription", 0.0);
            testOK = false;
        } catch (IllegalArgumentException dureeNul) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "testDescription", -5.0);
            testOK = false;
        } catch (IllegalArgumentException dureeN�gative) {
            testOK = true;
        }
        
        try {
            new Tache("testNoM", "testDescription", 35.5);
            testOK = true;
        } catch (IllegalArgumentException TacheIncorrect) {
            testOK = false;
        }
        
        return testOK;
    }
    
    private static boolean testGetNom() {
        
        String[] nomAttendus = {
            "Tache A", "Tache B", "Tache C", "Tache D"
        };
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getNom());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static boolean testSetNom() {
        
        String[] nomModifi� = {
            "TacheA", "", null, "Tache D"
        };
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            Tache noJeu = aTester[noJeu];
            noJeu.setNom(nomModifi�[noJeu]);
            
            ok &= estValide(noJeu.setNom());
        }
    }

}
