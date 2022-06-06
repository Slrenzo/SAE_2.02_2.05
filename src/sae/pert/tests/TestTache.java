/*
 * testTache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 4, pas de copyright 
 */
package sae.pert.tests;

import java.util.ArrayList;
import java.util.List;

import sae.pert.Projet;
import sae.pert.Tache;
import test.outillage.AssertionTest;

/** 
 * Série de test de la classe tâche permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class TestTache {
    
    /**
     * Jeux de tests unitaires qui servira a tester des méthodes
     */
    private static Tache[] aTester = {
        new Tache("Tache A", "Cette tâche consiste à analyser le besoin", 2.0),
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
        ok &= testGetSetMargeLibre();
        ok &= testGetSetMargeTotale();
        ok &= testAjouterTacheSuccesseur();
        ok &= testALaTacheSuccesseur();
        ok &= testEnleverTacheSuccesseur();
        ok &= testGetTachesSuccesseurs();
        ok &= testNombreTachesSuccesseurs();
        ok &= testEstTacheCritique();
        
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
     * @return true si test réussis sinon false
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
     * @return true si test réussis sinon false
     */
    private static boolean testGetDescription() {
        
        String[] descriptionAttendus = {
            "Cette tâche consiste à analyser le besoin",
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
     * @return true si test réussis sinon false
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
    
    /** Tests unitaires de setDescription
     * @return true si test réussis sinon false 
     */
    private static boolean testSetDescription() {
        
        boolean ok;
        
        /** Tâche de test */
        Tache test = new Tache("Tache A", "Répartition du travail", 30.5);
        String[] testDescription = {"Fin de projet",
                        "Réalisation-de-l'application", "Fin_de_projet", "    "};
        
        String[] descriptionAttendus = {"Fin de projet", 
                        "Réalisation-de-l'application", "Fin_de_projet",
                        "Répartition du travail"};
        
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
    
    /** Tests unitaires de setDuree
     * @return true si test réussis sinon false 
     */
    private static boolean testSetDuree() {
        
        boolean ok;
        
        /** Tâche de test */
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
     * Tests unitaires de ajouterTachePrealable
     * @return true si test réussis, sinon false
     */
    private static boolean testAjouterTachePrealable() {
        boolean ok = true;
        
        Tache tacheAAjouter = new Tache("tâche à ajouter", "description de la "
                                        + "tâche à ajouter", 20.0);
        Tache tacheDeTest = new Tache("tâche de test", "description de test"
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
     * Tests unitaires de enleverTachePrealable
     * @return true si test réussis, sinon false
     */
    private static boolean testEnleverTachePrealable() {
        boolean ok = true;
        
        Tache tacheAAjouter1 = new Tache("tâche à ajouter 1", "description de "
                                         + "la tâche à ajouter", 20.0);
        Tache tacheAAjouter2 = new Tache("tâche à ajouter 2", "description de "
                                         + "la tâche à ajouter", 20.0);
        Tache tacheAAjouter3 = new Tache("tâche à ajouter 3", "description de "
                                         + "la tâche à ajouter", 20.0);
        Tache tacheDeTest = new Tache("tâche de test", "description de test"
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
     * Tests unitaires de toString
     * @return true si test réussis, sinon false
     */
    private static boolean testToString() {
    	
    	String attenduA = "Cette tâche est défini par :\n  Nom : Tache A"
				            + "\n  Description : Répartition du travail"
				            + "\n  Durée : 29.0"
				            + "\n  Date au plus tôt : 0.0"
				            + "\n  Date au plus tard : 0.0"
				            + "\n  Cette tâche n'a pas de taches préalables"
				            + "\n  Marge libre : 0.0"
				            + "\n  Marge totale : 0.0";
    	
    	String attenduB =  "Cette tâche est défini par :\n  Nom : Tache B"
				            + "\n  Description : Création d'une base de donnée"
				            + "\n  Durée : 10.0"
				            + "\n  Date au plus tôt : 0.0"
                                            + "\n  Date au plus tard : 0.0"
				            + "\n  Tâches préalables : Tache A | "
				            + "\n  Marge libre : 0.0"
                                            + "\n  Marge totale : 0.0";
    	
    	String attenduD =  "Cette tâche est defini par :\n  Nom : Tache D"
				            + "\n  Description : Developpement"
				            + "\n  Durée : 60.0"
				            + "\n  Date au plus tôt : 0.0"
                                            + "\n  Date au plus tard : 0.0"
				            + "\n  Tâches préalables : Tache A | Tache C | "
				            + "\n  Marge libre : 0.0"
                                            + "\n  Marge totale : 0.0";
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
        
        double[] testDateAuPlusTot = {0.0, 5.0};
        
        double[] dateAuPlusTotAttendus = {0.0, 5.0};
        
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
    
    /** Tests unitaires de setDateAuPlusTot
     * @return true si test réussis sinon false 
     */
    private static boolean testSetDateAuPlusTot() {
        
        boolean ok;
        
        /** Tâche de test */
        Tache test = new Tache("Tache D", "Réalisation de l'application", 30.0);
        double[] testDateAuPlusTot = {0.0, 78.0, -5};
        
        double[] DateAuPlusTotAttendus = {0.0, 78.0, 30.0};
        
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
    
    /** Tests unitaires de setDateAuPlusTard
     * @return true si test réussis sinon false 
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
     * tests unitaires de getTachesPrealables
     * @return true si test réussis sinon false
     */
    private static boolean testGetTachesPrealables() {
        
        boolean ok;
        ok = true;
        
        List<Tache> cloneTaches = new ArrayList<Tache>();
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        tacheDeTest.ajouterTachePrealable(tacheAAjouter);
        
        cloneTaches = tacheDeTest.getTachesPrealables();
        
        for ( int noTest = 0 ; noTest < cloneTaches.size() ; noTest++) {
            Tache tacheClone1 = cloneTaches.get(noTest);
            
            tacheClone1.setDescription("test changement de description");
            tacheClone1.setDuree(20.0);
            
            ok = AssertionTest.assurerNonEgalite(tacheClone1.getDescription(), 
                            tacheAAjouter.getDescription());
            ok = AssertionTest.assurerNonEgaliteDouble(tacheClone1.getDuree(), 
                            tacheAAjouter.getDuree());
        }
        
        return ok;
    }
    
    /**
     * tests unitaires de NombreTachesPrealables
     * @return true si test réussis sinon false
     */
    private static boolean testNombreTachesPrealables() {
        
        boolean ok;
        ok = true;
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 2.0);
        
        tacheDeTest.ajouterTachePrealable(tacheAAjouter);
        tacheDeTest.ajouterTachePrealable(tacheAAjouter1);
        
        List<Tache> tachePrealables = tacheDeTest.getTachesPrealables();
        ok = AssertionTest.assurerEgaliteDouble(tacheDeTest.nombreTachesPrealables(),
                        tachePrealables.size());
        
        return ok;
    }
    
    /**
     * tests unitaires de ALaTachePrealable
     * @return true si test réussis sinon false
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
    
    /** Tests unitaires de setMargeLibre et getMargeLibre
     * @return true si test réussis sinon false 
     */
    private static boolean testGetSetMargeLibre() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("Tache A", "Répartition du travail", 30.5);
        double[] testMargeLibre = {78.0, 0.0};
        
        double[] margeLibreAttendus = {78.0, 0.0};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testMargeLibre.length; noTest++) {
            try {
                test.setMargeLibre(testMargeLibre[noTest]);
                ok &= test.getMargeLibre() == margeLibreAttendus[noTest] ;
            } catch (IllegalArgumentException margeIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /** Tests unitaires de setMargeTotale et getMargeTotale
     * @return true si test réussis sinon false 
     */
    private static boolean testGetSetMargeTotale() {
        
        boolean ok;
        
        /** Tache de test */
        Tache test = new Tache("Tache A", "Répartition du travail", 30.5);
        double[] testMargeTotale = {78.0, 0.0};
        
        double[] margeTotaleAttendus = {78.0, 0.0};
        
        ok = true;
        for (int noTest = 0; ok && noTest < testMargeTotale.length; noTest++) {
            try {
                test.setMargeTotale(testMargeTotale[noTest]);
                ok &= test.getMargeTotale() == margeTotaleAttendus[noTest] ;
            } catch (IllegalArgumentException margeIncorrect) {
                ok = true;
            }
        }
        return ok;
    }
    
    /**
     * Test unitaires de AjouterTacheSuccesseur
     * @return true si test réussis sinon false
     */
    private static boolean testAjouterTacheSuccesseur() {
        boolean ok;
        ok = true;
        
        Tache tacheAAjouter = new Tache("tâche à ajouter", "description de la "
                                       + "tâche à ajouter", 20.0);
        Tache tacheDeTest = new Tache("tâche de test", "description de test"
                                        , 10.0);
        ArrayList<Tache> tachesSuccesseurAttendues = new ArrayList<Tache>();
        tachesSuccesseurAttendues.add(tacheAAjouter);
        
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter);
        ok &= tacheDeTest.getTachesPrealables()
                .equals(tachesSuccesseurAttendues);
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
     * tests unitaires de ALaTacheSuccesseur
     * @return true si test réussis sinon false
     */
    private static boolean testALaTacheSuccesseur() {
        boolean ok;
        ok = true;
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 2.0);
        
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter);
        
        try {
            tacheDeTest.aLaTachePrealable(tacheAAjouter);
            ok = true;
        } catch (IllegalArgumentException tachePrésente) {
            ok = false;
        }
        
        return ok;
    }
    
    /** 
     * Tests unitaires de enleverTacheSuccesseur
     * @return true si test réussis, sinon false
     */
    private static boolean testEnleverTacheSuccesseur() {
        boolean ok = true;
        
        Tache tacheAAjouter1 = new Tache("tâche à ajouter 1", "description de "
                                         + "la tâche à ajouter", 20.0);
        Tache tacheAAjouter2 = new Tache("tâche à ajouter 2", "description de "
                                         + "la tâche à ajouter", 20.0);
        Tache tacheAAjouter3 = new Tache("tâche à ajouter 3", "description de "
                                         + "la tâche à ajouter", 20.0);
        Tache tacheDeTest = new Tache("tâche de test", "description de test"
                                      , 10.0);
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter1);
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter2);
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter3);
        
        ArrayList<Tache> tachesSuccesseurAttendues = new ArrayList<Tache>();
        tachesSuccesseurAttendues.add(tacheAAjouter1);
        tachesSuccesseurAttendues.add(tacheAAjouter2);
        
        tacheDeTest.enleverTacheSuccesseur(tacheAAjouter3);
        ok &= tacheDeTest.getTachesSuccesseurs()
                        .equals(tachesSuccesseurAttendues);
        
        try {
            tacheDeTest.enleverTacheSuccesseur(tacheAAjouter3);
            ok = false;
        } catch (IllegalArgumentException doitSePropager) {
            ok = true;
        }
        
        return ok;
    }
    
    /**
     * tests unitaires de getTachesSuccesseurs
     * @return true si test réussis sinon false
     */
    private static boolean testGetTachesSuccesseurs() {
        
        boolean ok;
        ok = true;
        
        List<Tache> cloneTaches = new ArrayList<Tache>();
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter);
        
        cloneTaches = tacheDeTest.getTachesSuccesseurs();
        
        for ( int noTest = 0 ; noTest < cloneTaches.size() ; noTest++) {
            Tache tacheClone1 = cloneTaches.get(noTest);
            
            tacheClone1.setDescription("test changement de description");
            tacheClone1.setDuree(20.0);
            
            ok = AssertionTest.assurerNonEgalite(tacheClone1.getDescription(), 
                            tacheAAjouter.getDescription());
            ok = AssertionTest.assurerNonEgaliteDouble(tacheClone1.getDuree(),
                            tacheAAjouter.getDuree());
        }
        
        return ok;
    }
    
    /**
     * tests unitaires de NombreTachesSuccesseurs
     * @return true si test réussis sinon false
     */
    private static boolean testNombreTachesSuccesseurs() {
        
        boolean ok;
        ok = true;
        
        Tache tacheDeTest = new Tache("Tache D", "Réalisation de l'application", 30.0);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 2.0);
        
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter);
        tacheDeTest.ajouterTacheSuccesseur(tacheAAjouter1);
        
        List<Tache> tachesSuccesseurs = tacheDeTest.getTachesSuccesseurs();
        ok = AssertionTest.assurerEgaliteDouble(tacheDeTest.nombreTachesSuccesseurs(), 
                        tachesSuccesseurs.size());
        
        return ok;
    }
    
    /**
     * test unitaire de la méthode estTacheCritique
     * @return true si test réussis, sinon false
     */
    private static boolean testEstTacheCritique() {
        boolean ok;
        ok = true;
        
        Tache tacheDeTest = new Tache("Tache D", "Test", 30.0);
        tacheDeTest.setDateAuPlusTot(0.0);
        tacheDeTest.setDateAuPlusTard(0.0);
        ok = tacheDeTest.estTacheCritique();
        
        Tache tacheDeTest1 = new Tache("Tache A", "Test", 10.0);
        tacheDeTest1.setDateAuPlusTot(1.0);
        tacheDeTest1.setDateAuPlusTard(0.0);
        if (tacheDeTest1.estTacheCritique() != true) {
            ok &= true;
        }
        
        Tache tacheDeTest2 = new Tache("Tache B", "Test", 2.0);
        tacheDeTest2.setDateAuPlusTot(5.0);
        tacheDeTest2.setDateAuPlusTard(5.0);
        ok &= tacheDeTest2.estTacheCritique();
        
        return ok;
    }
}
