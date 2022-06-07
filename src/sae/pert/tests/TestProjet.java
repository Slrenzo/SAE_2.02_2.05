/*
 * testProjet.java                                  10 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.pert.tests;

import java.util.ArrayList;
import java.util.List;

import sae.pert.Projet;
import sae.pert.Tache;
import test.outillage.AssertionTest;

/** 
 * Série de test de la classe Projet permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class TestProjet {
    /**
     * Jeux de tests unitaires qui servira a tester des méthodes
     */
    private static Projet[] aTester = {
        new Projet("Projet A", "Projet réalisant une expertise", Projet.UNITE_TEMPS[0]),
        new Projet("Projet B", "Projet calculant un cout", Projet.UNITE_TEMPS[1]),
        new Projet("Projet C", "Projet automatique", Projet.UNITE_TEMPS[2])
    };
    
    /**
     * Lance les différents jeux de tests
     * @param args
     */
    public static void main(String[] args) {
        
        boolean ok;
        
        ok = testConstructeurStringStringString();
        ok &= testGetNom();
        ok &= testGetDescription();
        ok &= testSetDescription();
        ok &= testGetUniteTemps();
        ok &= testAjouterTacheProjet();
        ok &= testEnleverTacheProjet();
        ok &= testToString();
        ok &= testGetTaches();
        ok &= testCalculDateAuPlusTot();
        ok &= testCalculDateAuPlusTard();
        ok &= testCalculDateAuPlusTotDeFinDeProjet();
        ok &= testGetDateAuPlusTotetTardProjet();
        ok &= testAUnCircuit();
        ok &= testDeterminerLesSuccesseurs();
        ok &= testDeterminerTachesCritiques();
        ok &= testDeterminerMarges();
        
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
    private static boolean testConstructeurStringStringString() {
        
        boolean testOK;
        
        try {
            new Projet(null, "Projet réalisant une expertise", Projet.UNITE_TEMPS[0]);
            testOK = false;
        } catch (NullPointerException nomNul) {
            testOK = true;
        }
        
        try {
            new Projet("  ", "Projet réalisant une expertise", Projet.UNITE_TEMPS[0]);
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Projet("Projet A", null, Projet.UNITE_TEMPS[0]);
            testOK = false;
        } catch (NullPointerException nomNul) {
            testOK = true;
        }
        
        try {
            new Projet("Projet A", "  ", Projet.UNITE_TEMPS[0]);
            testOK = false;
        } catch (IllegalArgumentException nomVide) {
            testOK = true;
        }
        
        try {
            new Projet("Projet A", "Projet réalisant une expertise", Projet.UNITE_TEMPS[0]);
            testOK = true;
        } catch (IllegalArgumentException ProjetIncorrect) {
            testOK = false;
        }
        return testOK;
    }
    
    
    /**
     * Test unitaires de getNom
     * @return true si test réussis sinon false
     */
    private static boolean testGetNom() {
        
        String[] nomAttendus = {"Projet A", "Projet B", "Projet C"};
        
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
        
        String[] nomAttendus = {"Projet réalisant une expertise",
                        "Projet calculant un cout",
                        "Projet automatique"};
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= AssertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getDescription());
        }
        return ok;
    }
    
    /** Test unitaires de setDescription
     * @return true si test réussis sinon false 
     */
    private static boolean testSetDescription() {
        
        boolean ok;
        
        /** Tache de test */
        Projet test = new Projet("Projet C", "Projet automatique", Projet.UNITE_TEMPS[2]);
        String[] testDescription = {"Projet réalisant une expertise",
                        "Réalisation-du_projet", "Fin_de_projet", "    "};
        
        String[] descriptionAttendus = {"Projet réalisant une expertise", 
                        "Réalisation-du_projet", "Fin_de_projet", "Projet automatique"};
        
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
    
    
    /**
     * Test unitaires de getUniteTemps
     * @return true si test réussis sinon false
     */
    private static boolean testGetUniteTemps() {
        
        String[] nomAttendus = {"Minute(s)", "Heure(s)", "Jour(s)"};
        
        boolean ok;
        
        ok = true;
        for (int noJeu = 0; ok && noJeu < aTester.length; noJeu++) {
            ok &= AssertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getUniteTemps());
        }
        return ok;
    }
    
    
    /**
     * Test unitaires de ajouterTache
     * @return true si test réussis sinon false
     */
    private static boolean testAjouterTacheProjet() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        ArrayList<Tache> tacheProjetAttendues = new ArrayList<Tache>();
        tacheProjetAttendues.add(tacheAAjouter);
        
        ProjetTest.ajouterTache(tacheAAjouter);
        ok &= ProjetTest.getTaches().equals(tacheProjetAttendues);
        
          try { 
              ProjetTest.ajouterTache(tacheAAjouter); 
              ok = false; 
          } catch (IllegalArgumentException tacheDejaPrésente) { 
              ok = true; 
          }
         
        
            
        return ok;
    }
    
    /**
     * Test unitaires de enleverTache
     * @return true si test réussis sinon false
     */
    private static boolean testEnleverTacheProjet() {
        boolean ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 3.5);
        
        Tache tacheAAjouter2 = new Tache("Tache C", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheAAjouter);
        ProjetTest.ajouterTache(tacheAAjouter1);
        ProjetTest.ajouterTache(tacheAAjouter2);
        
        ArrayList<Tache> tacheProjetAttendues = new ArrayList<Tache>();
        tacheProjetAttendues.add(tacheAAjouter);
        tacheProjetAttendues.add(tacheAAjouter1);
        
        ProjetTest.enleverTache(tacheAAjouter2);
        ok &= ProjetTest.getTaches().equals(tacheProjetAttendues);
        
        
        try {
            ProjetTest.enleverTache(tacheAAjouter2);
            ok = false;
        } catch (IllegalArgumentException tacheNonPrésente) {
            ok = true;
        } 
        return ok;
    }
    
    /**
     * test unitaires de la méthode getTaches
     * @return
     */
    private static boolean testGetTaches() {
        
        boolean ok;
        ok = true;
        
        List<Tache> cloneTaches = new ArrayList<Tache>();
        
        Projet ProjetA = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        ProjetA.ajouterTache(tacheAAjouter);
        
        cloneTaches = ProjetA.getTaches();
        
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
     * test unitaires de calcul de la date au plus tôt
     * @return true si test réussis, sinon false
     */
    private static boolean testCalculDateAuPlusTot() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheD = new Tache("Tache D", "Réalisation de l'application", 30.0);
        Tache tacheE = new Tache("Tache E", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheE.ajouterTachePrealable(tacheC);
        tacheD.ajouterTachePrealable(tacheE);
        tacheD.ajouterTachePrealable(tacheB);
        
        ProjetTest.ordonnancement();
        
        ok = AssertionTest.assurerEgaliteDouble(0.0, tacheA.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(2.0, tacheB.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(2.0, tacheC.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(5.5, tacheE.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(25.5, tacheD.getDateAuPlusTot());
        
        return ok;
    }
    
    /**
     * test unitaires de calcul de la date au plus tôt de fin de projet
     * @return true si test réussis, sinon false
     */
    private static boolean testCalculDateAuPlusTotDeFinDeProjet() {
        boolean ok;
        ok = true;
        
Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheE.ajouterTachePrealable(tacheD);
        
        
        ProjetTest.ordonnancement();
        
        ok = AssertionTest.assurerEgaliteDouble(55.5, ProjetTest.getDateAuPlusTardProjet());
        
        return ok;
    }
    
    
    /**
     * test unitaires de calcul de la date au plus tard
     * @return true si test réussis, sinon false
     */
    private static boolean testCalculDateAuPlusTard() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheE.ajouterTachePrealable(tacheD);
        
        
        ProjetTest.ordonnancement();
        
        ok = AssertionTest.assurerEgaliteDouble(0.0, tacheA.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(2.0, tacheB.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(2.0, tacheC.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(5.5, tacheD.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(25.5, tacheE.getDateAuPlusTard());
        
        return ok;
    }
    
    /**
     * test unitaires de la méthode getDateAuPlusTotProjet
     * @return true si test réussis, sinon false
     */
    private static boolean testGetDateAuPlusTotetTardProjet() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheE.ajouterTachePrealable(tacheD);
        
        ProjetTest.ordonnancement();
    
        ok &= AssertionTest.assurerEgaliteDouble(55.5, ProjetTest.getDateAuPlusTotProjet());
        ok &= AssertionTest.assurerEgaliteDouble(55.5, ProjetTest.getDateAuPlusTardProjet());
        return ok;
    }
    
    /**
     * test unitaire de la méthode aUnCircuit
     * @return true si test réussis ,sinon false
     */
    private static boolean testAUnCircuit() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheA.ajouterTachePrealable(tacheE);
        
        ok = ProjetTest.aUnCircuit(tacheA);
        
        
        Projet ProjetTest1 = new Projet("Projet B", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheF = new Tache("Tache F", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheG = new Tache("Tache G", "Mise en place des méthodes de travail", 5.0);
        Tache tacheH = new Tache("Tache H", "Répartition du travail", 3.5);
        Tache tacheI = new Tache("Tache I", "Réalisation de l'application", 30.0);
        Tache tacheJ = new Tache("Tache J", "Réalisation des tests", 20.0);
        
        ProjetTest1.ajouterTache(tacheF);
        ProjetTest1.ajouterTache(tacheG);
        ProjetTest1.ajouterTache(tacheH);
        ProjetTest1.ajouterTache(tacheI);
        ProjetTest1.ajouterTache(tacheJ);
        
        tacheG.ajouterTachePrealable(tacheF);
        tacheH.ajouterTachePrealable(tacheF);
        tacheD.ajouterTachePrealable(tacheH);
        tacheJ.ajouterTachePrealable(tacheG);
        tacheJ.ajouterTachePrealable(tacheI);
        
        ok &= !ProjetTest1.aUnCircuit(tacheJ);
        
        return ok;
    }
    
    
    /**
     * test unitaires de la méthode toString
     * @return true si test réussis, sinon false
     */
    private static boolean testToString() {
       
        
        boolean ok;
        ok = true;
        
        String attenduA = "Ce projet est nommé : Projet A\n"
                        + "Sa description est : Projet automatique\n"
                        + "Son unité de temps est : Jour(s)\n"
                        + "Ses tâches sont : \n"
                        + "Cette tâche est défini par :\n"
                        + "  Nom : Tache A\n"
                        + "  Description : Répartition du travail\n"
                        + "  Durée : 30.5\n"
                        + "  Date au plus tôt : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Cette tâche n'a pas de tâches préalables\n"
                        + "  Marge libre : 0.0\n"
                        + "  Marge totale : 0.0\n"
                        + "Cette tâche est défini par :\n"
                        + "  Nom : Tache B\n"
                        + "  Description : Réalisation de l'application\n"
                        + "  Durée : 3.5\n"
                        + "  Date au plus tôt : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Tâches préalables : Tache A | Tache C | \n"
                        + "  Marge libre : 0.0\n"
                        + "  Marge totale : 0.0\n"
                        + "\n"
                        + "Sa date au plus tôt est : 0.0\n"
                        + "Sa date au plus tard est : 0.0\n"
                        + "Ce projet ne contient pas encore de tâche critique";
        
        String attenduB = "Ce projet est nommé : Projet B\n"
                        + "Sa description est : Projet calculant un cout\n"
                        + "Son unité de temps est : Heure(s)\n"
                        + "Ses tâches sont : \n"
                        + "Cette tâche est défini par :\n"
                        + "  Nom : Tache C\n"
                        + "  Description : Réalisation des tests\n"
                        + "  Durée : 20.0\n"
                        + "  Date au plus tôt : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Tâches préalables : Tache B | \n"
                        + "  Marge libre : 0.0\n"
                        + "  Marge totale : 0.0\n"
                        + "Cette tâche est défini par :\n"
                        + "  Nom : Tache A\n"
                        + "  Description : Répartition du travail\n"
                        + "  Durée : 30.5\n"
                        + "  Date au plus tôt : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Cette tâche n'a pas de tâches préalables\n"
                        + "  Marge libre : 0.0\n"
                        + "  Marge totale : 0.0\n"
                        + "\n"
                        + "Sa date au plus tôt est : 0.0\n"

                        + "Sa date au plus tard est : 0.0\n"
                        + "Ce projet ne contient pas encore de tâche critique";
        
        String attenduC = "Ce projet est nommé : Projet C\n"
                        + "Sa description est : Projet automatique\n"
                        + "Son unité de temps est : Jour(s)\n"
                        + "Ses tâches sont : \n"
                        + "Cette tâche est défini par :\n"
                        + "  Nom : Tache B\n"
                        + "  Description : Réalisation de l'application\n"
                        + "  Durée : 3.5\n"
                        + "  Date au plus tôt : 0.0\n"

                        + "  Date au plus tard : 0.0\n"
                        + "  Tâches préalables : Tache A | Tache C | \n"
                        + "  Marge libre : 0.0\n"
                        + "  Marge totale : 0.0\n"
                        + "Cette tâche est défini par :\n"
                        + "  Nom : Tache C\n"
                        + "  Description : Réalisation des tests\n"
                        + "  Durée : 20.0\n"
                        + "  Date au plus tôt : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Tâches préalables : Tache B | \n"
                        + "  Marge libre : 0.0\n"
                        + "  Marge totale : 0.0\n"
                        + "\n"
                        + "Sa date au plus tôt est : 0.0\n"

                        + "Sa date au plus tard est : 0.0\n"
                        + "Ce projet ne contient pas encore de tâche critique";
        
        
        
        Projet ProjetA = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        Projet ProjetB = new Projet("Projet B", "Projet calculant un cout", Projet.UNITE_TEMPS[1]);
        Projet ProjetC = new Projet("Projet C", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 3.5);
        Tache tacheAAjouter2 = new Tache("Tache C", "Réalisation des tests", 20.0);
        
        ProjetA.ajouterTache(tacheAAjouter);
        ProjetA.ajouterTache(tacheAAjouter1);
        ProjetB.ajouterTache(tacheAAjouter2);
        ProjetB.ajouterTache(tacheAAjouter);
        ProjetC.ajouterTache(tacheAAjouter1);
        ProjetC.ajouterTache(tacheAAjouter2);
        
        tacheAAjouter1.ajouterTachePrealable(tacheAAjouter);
        tacheAAjouter1.ajouterTachePrealable(tacheAAjouter2);
        tacheAAjouter2.ajouterTachePrealable(tacheAAjouter1);
        
        ok = AssertionTest.assurerEgalite(attenduA, ProjetA.toString());
        ok &= AssertionTest.assurerEgalite(attenduB, ProjetB.toString());
        ok &= AssertionTest.assurerEgalite(attenduC, ProjetC.toString());
        return ok;
    }
    
    /**
     * tests unitaires de la méthode determinerLesSuccesseurs
     * @return true si test réussis, sinon false
     */
    private static boolean testDeterminerLesSuccesseurs() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tâche consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheE.ajouterTachePrealable(tacheD);
        
        ProjetTest.determinerLesSuccesseurs();
        ok = tacheA.aLaTacheSuccesseur(tacheB);
        ok &= tacheA.aLaTacheSuccesseur(tacheC);
        ok &= tacheC.aLaTacheSuccesseur(tacheD);
        ok &= tacheD.aLaTacheSuccesseur(tacheE);
        ok &= tacheB.aLaTacheSuccesseur(tacheE);
        
        return ok;
    }
    
    /**
     * tests unitaires de la méthode determinerTachesCritiques
     * @return true si test réussis, sinon false
     */
    private static boolean testDeterminerTachesCritiques() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tâche consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheE.ajouterTachePrealable(tacheD);
        
        ProjetTest.ordonnancement();
        
        ok = tacheA.estTacheCritique();
        ok &= tacheB.estTacheCritique();
        ok &= tacheC.estTacheCritique();
        ok &= tacheD.estTacheCritique();
        ok &= tacheE.estTacheCritique();
        
        return ok;
    }
    
    /**
     * tests unitaires de la méthode determinerMarges
     * @return true si test réussis, sinon false
     */
    private static boolean testDeterminerMarges() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tâche consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheE = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheD = new Tache("Tache D", "Réalisation des tests", 20.0);
        
        ProjetTest.ajouterTache(tacheA);
        ProjetTest.ajouterTache(tacheB);
        ProjetTest.ajouterTache(tacheC);
        ProjetTest.ajouterTache(tacheD);
        ProjetTest.ajouterTache(tacheE);
        
        tacheB.ajouterTachePrealable(tacheA);
        tacheC.ajouterTachePrealable(tacheA);
        tacheD.ajouterTachePrealable(tacheC);
        tacheE.ajouterTachePrealable(tacheB);
        tacheE.ajouterTachePrealable(tacheD);
        
        ProjetTest.ordonnancement();
        
        ok = AssertionTest.assurerEgaliteDouble(0.0, tacheA.getMargeLibre());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheB.getMargeLibre());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheC.getMargeLibre());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheD.getMargeLibre());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheE.getMargeLibre());
        
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheA.getMargeTotale());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheB.getMargeTotale());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheC.getMargeTotale());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheD.getMargeTotale());
        ok &= AssertionTest.assurerEgaliteDouble(0.0, tacheE.getMargeTotale());
        
        return ok;
    }
    
}
