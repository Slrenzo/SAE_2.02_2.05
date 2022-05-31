/*
 * testTache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4, pas de copyright 
 */
package sae.pert.tests;

import java.util.ArrayList;

import sae.pert.Projet;
import sae.pert.Tache;
import test.outillage.AssertionTest;

/** 
 * Serie de test de la classe tache permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class TestTache {
    
    /**
     * Jeux de tests unitaires qui servira a tester des m�thodes
     */
    private static Tache[] aTester = {
        new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0),
        new Tache("Tache B", "Mise en place des méthodes de travail", 5.0),
        new Tache("Tache C", "Répartition du travail", 3.5),
        new Tache("Tache D", "Réalisation de l'application", 30.0)
    };
    
    /**
     * Lance les diff�rents jeux de tests
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringStringDouble();
        ok &= testGetNom();
        ok &= testGetDescription();
        ok &= testGetDuree();
        ok &= testToString();
        ok &= testSetDescription();
        ok &= testSetDuree();
        ok &= testAjouterTachePrealable();
        ok &= testEnleverTachePrealable();
        ok &= testGetDateAuPlusTot();
        ok &= testGetDateAuPlusTard();
        ok &= testSetDateAuPlusTot();
        ok &= testSetDateAuPlusTard();
        ok &= testGetTachesPrealables();
        ok &= testNombreTachesPrealables();
        ok &= testALaTachePrealable();
        
        
        if(ok) {
            System.out.println("Test reussis");
        } else {
            System.out.println("Test echoue");
        }
    }

    /**
     * Test du constructeur de la class Tache
     * @return testOK
     */
    private static boolean testConstructeurStringStringDouble() {
        
        boolean testOK;
        
        try {
            new Tache(null, "Répartition du travail", 30.5);
            testOK = false;
        } catch (NullPointerException nomNull) {
            testOK = true;
        }
        
        try {
            new Tache("  ", "Répartition du travail", 30.5);
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", null, 30.5);
            testOK = false;
        } catch (NullPointerException descriptionNull) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "", 30.5);
            testOK = false;
        } catch (IllegalArgumentException descriptionVide) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", 0.0);
            testOK = false;
        } catch (IllegalArgumentException dureeNull) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", -5.0);
            testOK = false;
        } catch (IllegalArgumentException dureeNegative) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", Double.NaN);
            testOK = false;
        } catch (IllegalArgumentException dureeNaN) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", 30.5);
            testOK = false;
        } catch (IllegalArgumentException dateAuPlusTotNull) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", 30.5);
            testOK = false;
        } catch (IllegalArgumentException dateAuPlusTotNegative) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", 30.5);
            testOK = false;
        } catch (IllegalArgumentException dateAuPlusTardNull) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", 30.5);
            testOK = false;
        } catch (IllegalArgumentException dateAuPlusTardNegative) {
            testOK = true;
        }
        
        try {
            new Tache("Tache A", "Répartition du travail", 30.5);
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
            ok &= AssertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getNom());
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
            ok &= AssertionTest.assurerEgalite(descriptionAttendus[noJeu],
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
            ok &= AssertionTest.assurerEgaliteDouble(dureeAttendus[noJeu],
                                               aTester[noJeu].getDuree());
        }
        return ok;
    }
    
    /** Test unitaires de setDescription
     * @return true si test reussis sinon false 
     */
    private static boolean testSetDescription() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("Tache A", "Répartition du travail", 30.5);
        String[] testDescription = {"Fin de projet","Réalisation-de-l'application", "Fin_de_projet", "    "};
        
        String[] descriptionAttendus = {"Fin de projet", "Réalisation-de-l'application", "Fin_de_projet", "Répartition du travail"};
        
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
     * Test unitaires de ajouterTachePrealable
     * @return true si test réussi, sinon false
     */
    private static boolean testAjouterTachePrealable() {
        boolean ok = true;
        
        Tache tacheAAjouter = new Tache("tache à ajouter", "description de la "
                                        + "tache à ajouter", 20.0);
        Tache tacheDeTest = new Tache("tache de test", "description de test"
                                      , 10.0);
        ArrayList<Tache> tachesPrealablesAttendues = new ArrayList<Tache>();
        tachesPrealablesAttendues.add(tacheAAjouter);
        
        tacheDeTest.ajouterTachePrealable(tacheAAjouter);
        ok &= tacheDeTest.getTachesPrealables()
                        .equals(tachesPrealablesAttendues);
        try {
            tacheDeTest.ajouterTachePrealable(tacheAAjouter);
            ok = false;
        } catch (IllegalArgumentException doitSePropager) {
            ok = true;
        }
        
        try {
            tacheDeTest.ajouterTachePrealable(tacheDeTest);
            ok = false;
        } catch (IllegalArgumentException doitSePropager) {
            ok = true;
        }
        return ok;
    }
    
    /** 
     * Test unitaires de enleverTachePrealable
     * @return true si test réussi, sinon false
     */
    private static boolean testEnleverTachePrealable() {
        boolean ok = true;
        
        Tache tacheAAjouter1 = new Tache("tache à ajouter 1", "description de "
                                         + "la tache à ajouter", 20.0);
        Tache tacheAAjouter2 = new Tache("tache à ajouter 2", "description de "
                                         + "la tache à ajouter", 20.0);
        Tache tacheAAjouter3 = new Tache("tache à ajouter 3", "description de "
                                         + "la tache à ajouter", 20.0);
        Tache tacheDeTest = new Tache("tache de test", "description de test"
                                      , 10.0);
        tacheDeTest.ajouterTachePrealable(tacheAAjouter1);
        tacheDeTest.ajouterTachePrealable(tacheAAjouter2);
        tacheDeTest.ajouterTachePrealable(tacheAAjouter3);
        
        ArrayList<Tache> tachesPrealablesAttendues = new ArrayList<Tache>();
        tachesPrealablesAttendues.add(tacheAAjouter1);
        tachesPrealablesAttendues.add(tacheAAjouter2);
        
        tacheDeTest.enleverTachePrealable(tacheAAjouter3);
        ok &= tacheDeTest.getTachesPrealables()
                        .equals(tachesPrealablesAttendues);
        
        try {
            tacheDeTest.enleverTachePrealable(tacheAAjouter3);
            ok = false;
        } catch (IllegalArgumentException doitSePropager) {
            ok = true;
        }
        
        return ok;
    }
    
    /**
     * Test unitaires de toString
     * @return true si test r�ussi, sinon false
     */
    private static boolean testToString() {
    	
    	String attenduA = "Cette tache est defini par :\n  Nom : Tache A"
				            + "\n  Description : Répartition du travail"
				            + "\n  Duree : 29.0"
				            + "\n  Date au plus tot : 0.0"
				            + "\n  Date au plus tard : 0.0"
				            + "\n  Cette tache n'a pas de taches prealables";
    	
    	String attenduB =  "Cette tache est defini par :\n  Nom : Tache B"
				            + "\n  Description : Création d'une base de donnée"
				            + "\n  Duree : 10.0"
				            + "\n  Date au plus tot : 0.0"
                                            + "\n  Date au plus tard : 0.0"
				            + "\n  Taches prealables : Tache A | ";
    	
    	String attenduD =  "Cette tache est defini par :\n  Nom : Tache D"
				            + "\n  Description : Developpement"
				            + "\n  Duree : 60.0"
				            + "\n  Date au plus tot : 0.0"
                                            + "\n  Date au plus tard : 0.0"
				            + "\n  Taches prealables : Tache A | Tache C | ";
    	boolean ok;
    	
    	Tache tacheA = new Tache("Tache A", "Répartition du travail", 29);
    	Tache tacheB = new Tache("Tache B", "Création d'une base de donnée", 10);
    	Tache tacheC = new Tache("Tache C", "Création d'un diagramme objet", 20);
    	Tache tacheD = new Tache("Tache D", "Developpement", 60);
    	
    	tacheB.ajouterTachePrealable(tacheA);
    	tacheC.ajouterTachePrealable(tacheA);
    	tacheD.ajouterTachePrealable(tacheA);
    	tacheD.ajouterTachePrealable(tacheC);
    	
        ok = AssertionTest.assurerEgalite(attenduA, tacheA.toString());
        ok &= AssertionTest.assurerEgalite(attenduB, tacheB.toString());
        ok &= AssertionTest.assurerEgalite(attenduD, tacheD.toString());        
        
        return ok;
    }
    
    /**
     * Tests unitaires de getDateAuPlusTot
     * @return true si test validé sinon false
     */
    private static boolean testGetDateAuPlusTot() {
        
        Tache test = new Tache("Tache A", "Répartition du travail", 30.0);
        
        double[] testDateAuPlusTot = {5.0, 0.0};
        
        double[] dateAuPlusTotAttendus = {5.0, 0.0};
        
        boolean ok;
        ok = true;
        
        for (int noJeu = 0; ok && noJeu < testDateAuPlusTot.length; noJeu++) {
            test.setDateAuPlusTot(testDateAuPlusTot[noJeu]);
            ok &= AssertionTest.assurerEgaliteDouble(dateAuPlusTotAttendus[noJeu],
                                               test.getDateAuPlusTot());
        }
        return ok;
    }
    
    /**
     * Tests unitaires de getDateAuPlusTard
     * @return true si test validé sinon false
     */
    private static boolean testGetDateAuPlusTard() {
        
Tache test = new Tache("Tache A", "Répartition du travail", 30.0);
        
        double[] testDateAuPlusTard = {5.0, 0.0};
        
        double[] dateAuPlusTardAttendus = {5.0, 0.0};
        
        boolean ok;
        ok = true;
        
        for (int noJeu = 0; ok && noJeu < testDateAuPlusTard.length; noJeu++) {
            test.setDateAuPlusTard(testDateAuPlusTard[noJeu]);
            ok &= AssertionTest.assurerEgaliteDouble(dateAuPlusTardAttendus[noJeu],
                                               test.getDateAuPlusTard());
        }
        return ok;
    }
    
    /** Test unitaires de setDateAuPlusTot
     * @return true si test reussis sinon false 
     */
    private static boolean testSetDateAuPlusTot() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("Tache D", "Réalisation de l'application", 30.0);
        double[] testDateAuPlusTot = {78, 0.0, -5};
        
        double[] DateAuPlusTotAttendus = {78, 0.0, 30.0};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testDateAuPlusTot.length; noTest++) {
            try {
                test.setDateAuPlusTot(testDateAuPlusTot[noTest]);
                ok &= test.getDateAuPlusTot() == DateAuPlusTotAttendus[noTest];
            } catch (IllegalArgumentException DateAuPlusTotIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /** Test unitaires de setDateAuPlusTard
     * @return true si test reussis sinon false 
     */
    private static boolean testSetDateAuPlusTard() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("Tache D", "Réalisation de l'application", 30.0);
        double[] testDateAuPlusTard = {78, 0.0, -5};
        
        double[] DateAuPlusTardAttendus = {78, 0.0, 30.0};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testDateAuPlusTard.length; noTest++) {
            try {
                test.setDateAuPlusTard(testDateAuPlusTard[noTest]);
                ok &= test.getDateAuPlusTard() == DateAuPlusTardAttendus[noTest];
            } catch (IllegalArgumentException DateAuPlusTardIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /**
     * test unitaires de la méthode getTachesPrealables
     * @return true si test reussis sinon false
     */
    private static boolean testGetTachesPrealables() {
        
        boolean ok;
        ok = true;
        
        ArrayList<Tache> cloneTaches = new ArrayList<Tache>();
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        tacheDeTest.ajouterTachePrealable(tacheAAjouter);
        
        cloneTaches = tacheDeTest.getTachesPrealables();
        
        for ( int noTest = 0 ; noTest < cloneTaches.size() ; noTest++) {
            Tache tacheClone1 = cloneTaches.get(noTest);
            
            tacheClone1.setDescription("test changement de description");
            tacheClone1.setDuree(20.0);
            
            ok = AssertionTest.assurerNonEgalite(tacheClone1.getDescription(), tacheAAjouter.getDescription());
            ok = AssertionTest.assurerNonEgaliteDouble(tacheClone1.getDuree(), tacheAAjouter.getDuree());
        }
        
        return ok;
    }
    
    /**
     * test unitaires de la méthode testNombreTachesPrealables
     * @return true si test reussis sinon false
     */
    private static boolean testNombreTachesPrealables() {
        
        boolean ok;
        ok = true;
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 2.0);
        
        tacheDeTest.ajouterTachePrealable(tacheAAjouter);
        tacheDeTest.ajouterTachePrealable(tacheAAjouter1);
        
        ArrayList<Tache> tachePrealables = tacheDeTest.getTachesPrealables();
        ok = AssertionTest.assurerEgaliteDouble(tacheDeTest.nombreTachesPrealables(), tachePrealables.size());
        
        return ok;
    }
    
    /**
     * test unitaires de la méthode testALaTachePrealable
     * @return true si test reussis sinon false
     */
    private static boolean testALaTachePrealable() {
        boolean ok;
        ok = true;
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 2.0);
        
        tacheDeTest.ajouterTachePrealable(tacheAAjouter);
        
        try {
            tacheDeTest.aLaTachePrealable(tacheAAjouter);
            ok = true;
        } catch (IllegalArgumentException tachePrésente) {
            ok = false;
        }
        
        return ok;
    }

}
