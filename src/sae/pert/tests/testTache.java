/*
 * testTache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4, pas de copyright 
 */
package sae.pert.tests;

import sae.pert.Tache;
import testOutillage.assertionTest;

/** 
 * Serie de test de la classe tache permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class testTache {
    
    /**
     * Jeux de tests unitaires qui servira a tester des méthodes
     */
    private static Tache[] aTester = {
        new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0),
        new Tache("Tache B", "Mise en place des méthodes de travail", 5.0),
        new Tache("Tache C", "Répartition du travail", 3.5),
        new Tache("Tache D", "Réalisation de l'application", 30.0)
    };
    
    /**
	 * Lance les différents jeux de tests
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringStringDouble();
        ok &= testGetNom();
        ok &= testGetDescription();
        ok &= testGetDuree();
        ok &= testToString();
        ok &= testSetNom();
        ok &= testSetDescription();
        ok &= testSetDuree();
        
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
        } catch (IllegalArgumentException dureeNégative) {
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
    
    /**
     * Test unitaires de getNom
     * @return true si test reussis sinon false
     */
    private static boolean testGetNom() {
        
        String[] nomAttendus = {"Tache A", "Tache B", "Tache C", "Tache D"};
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= assertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getNom());
        }
        return ok;
    }
    
    /**
     * Test unitaires de getDescription
     * @return true si test reussis sinon false
     */
    private static boolean testGetDescription() {
        
        String[] descriptionAttendus = {
            "Cette tache consiste à analyser le besoin",
            "Mise en place des méthodes de travail",
            "Répartition du travail",
            "Réalisation de l'application"
        };
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= assertionTest.assurerEgalite(descriptionAttendus[noJeu],
                                               aTester[noJeu].getDescription());
        }
        return ok;
    }
    
    
    /**
     * Tests unitaires de getDescription
     * @return true si test reussis sinon false
     */
    private static boolean testGetDuree() {
        
        double[] dureeAttendus = {2.0, 5.0, 3.5, 30.0};
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= assertionTest.assurerEgaliteDouble(dureeAttendus[noJeu],
                                               aTester[noJeu].getDuree());
        }
        return ok;
    }
    
    /** Test unitaires de setNom
     * @return true si test reussis sinon false 
     */
    private static boolean testSetNom() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("testNom", "testDescription", 30.5);
        String[] testNom = {"A", "ABC", "    "};
        
        String[] nomAttendus = {"A", "ABC", "testNom"};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testNom.length; noTest++) {
            try {
                test.setNom(testNom[noTest]);
                ok &= test.getNom().equals(nomAttendus[noTest]);
            } catch (IllegalArgumentException NomVide) {
                ok = true;
            }
        }
        return ok;
    }
    
    /** Test unitaires de setDescription
     * @return true si test reussis sinon false 
     */
    private static boolean testSetDescription() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("testNom", "testDescription", 30.5);
        String[] testDescription = {"A", "ABC", "    "};
        
        String[] descriptionAttendus = {"A", "ABC", "testDescription"};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testDescription.length; noTest++) {
            try {
                test.setDescription(testDescription[noTest]);
                ok &= test.getDescription().equals(descriptionAttendus[noTest]);
            } catch (IllegalArgumentException DescriptionIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /** Test unitaires de setDuree
     * @return true si test reussis sinon false 
     */
    private static boolean testSetDuree() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("testNom", "testDescription", 30.5);
        double[] testDuree = {78, 0.0, -5};
        
        double[] DureeAttendus = {78, 30.5, 30.5};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testDuree.length; noTest++) {
            try {
                test.setDuree(testDuree[noTest]);
                ok &= test.getDuree() == DureeAttendus[noTest];
            } catch (IllegalArgumentException DescriptionIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /**
     * Test unitaires de toString
     * @return true si test réussi, sinon false
     */
    private static boolean testToString() {
    	
    	String attenduA = "Cette tache est défini par :\n  Nom : Tache A"
				            + "\n  Description : Répartition du travail"
				            + "\n  Duree : 29.0"
				            + "\n  Cette tache n'a pas de taches préalables";
    	
    	String attenduB =  "Cette tache est défini par :\n  Nom : Tache B"
				            + "\n  Description : Création d'une base de donnée"
				            + "\n  Duree : 10.0"
				            + "\n  Taches préalables : Tache A | ";
    	
    	String attenduD =  "Cette tache est défini par :\n  Nom : Tache D"
				            + "\n  Description : Developpement"
				            + "\n  Duree : 60.0"
				            + "\n  Taches préalables : Tache A | Tache C | ";
    	boolean ok;
    	
    	Tache tacheA = new Tache("Tache A", "Répartition du travail", 29);
    	Tache tacheB = new Tache("Tache B", "Création d'une base de donnée", 10);
    	Tache tacheC = new Tache("Tache C", "Création d'un diagramme objet", 20);
    	Tache tacheD = new Tache("Tache D", "Developpement", 60);
    	
    	tacheB.ajouterTachePrealable(tacheA);
    	tacheC.ajouterTachePrealable(tacheA);
    	tacheD.ajouterTachePrealable(tacheA);
    	tacheD.ajouterTachePrealable(tacheC);
    	
        ok = assertionTest.assurerEgalite(attenduA, tacheA.toString());
        ok &= assertionTest.assurerEgalite(attenduB, tacheB.toString());
        ok &= assertionTest.assurerEgalite(attenduD, tacheD.toString());        
        
        return ok;
    }

}
