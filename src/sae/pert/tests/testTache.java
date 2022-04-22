/*
 * testTache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4, pas de copyright 
 */
package sae.pert.tests;

import sae.pert.Tache;

/** TODO commenter la responsabilité de cette classe
 * @author emilien.restoueix
 *
 */
public class testTache {
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringStringDouble();
        
        if(ok) {
            System.out.println("Test réussis");
        } else {
            System.out.println("Test échoué");
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
            new Tache("", "testDescription", 30.5);
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
        } catch (IllegalArgumentException dureeNégative) {
            testOK = true;
        }
        
        try {
            new Tache("testNom", "testDescription", 35.5);
            testOK = true;
        } catch (IllegalArgumentException TacheIncorrect) {
            testOK = false;
        }
        
        return testOK;
    }

}
