/*
 * testProjet.java                                  10 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.pert.tests;

import sae.pert.Projet;
import sae.pert.Tache;
import testOutillage.assertionTest;

/** 
 * Serie de test de la classe Projet permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class testProjet {
    /**
     * Jeux de tests unitaires qui servira a tester des méthodes
     */
    private static Projet[] aTester = {
        new Projet("Projet A", "Minute(s)"),
        new Projet("Projet B", "Heure(s)"),
        new Projet("Projet C", "Jour(s)")
    };
    
    /**
     * Lance les différents jeux de tests
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringString();
        ok = testGetNom();
        ok = testSetNom();
        ok = testGetUniteTemps();
        
        if (ok) {
            System.out.println("Test réussis");
        } else {
            System.out.println("Test échoué");
        }
        
    }
    

    /**
     * Test du constructeur de la class Tache
     * @return testOK
     */
    private static boolean testConstructeurStringString() {
        
        boolean testOK;
        
        try {
            new Projet(null, "Minute(s)");
            testOK = false;
        } catch (NullPointerException nomNul) {
            testOK = true;
        }
        
        try {
            new Projet("  ", "Minute(s)");
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Projet("Projet A", null);
            testOK = false;
        } catch (NullPointerException nomNul) {
            testOK = true;
        }
        
        try {
            new Projet("Projet A", "  ");
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Projet("Projet A", "Minute(s)");
            testOK = true;
        } catch (IllegalArgumentException ProjetIncorrect) {
            testOK = false;
        }
        return testOK;
    }
    
    
    /**
     * Test unitaires de getNom
     * @return true si test reussis sinon false
     */
    private static boolean testGetNom() {
        
        String[] nomAttendus = {"Projet A", "Projet B", "Projet C"};
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= assertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getNom());
        }
        return ok;
    }
    
    
    /** Test unitaires de setNom
     * @return true si test reussis sinon false 
     */
    private static boolean testSetNom() {
        
        boolean ok;
        
        /** Tache de test */
        Projet test = new Projet("Projet A", "Heure(s)");
        String[] testNom = {"Projet 1", "Projet_A", "Projet-1", "    "};
        
        String[] nomAttendus = {"Projet 1", "Projet_A", "Projet-1", "Projet A"};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testNom.length; noTest++) {
            try {
                test.setNom(testNom[noTest]);
                ok &= test.getNom().equals(nomAttendus[noTest]);
            } catch (IllegalArgumentException nomIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /**
     * Test unitaires de getUniteTemps
     * @return true si test reussis sinon false
     */
    private static boolean testGetUniteTemps() {
        
        String[] nomAttendus = {"Minute(s)", "Heure(s)", "Jour(s)"};
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= assertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getUniteTemps());
        }
        return ok;
    }
    
    
    
    
    
}
