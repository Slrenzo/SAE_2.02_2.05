/*
 * testProjet.java                                  10 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.pert.tests;

import java.util.ArrayList;

import sae.pert.Projet;
import sae.pert.Tache;
import test.outillage.AssertionTest;

/** 
 * Serie de test de la classe Projet permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class TestProjet {
    /**
     * Jeux de tests unitaires qui servira a tester des m�thodes
     */
    private static Projet[] aTester = {
        new Projet("Projet A", "Projet realisant une expertise", Projet.UNITE_TEMPS[0]),
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
        //ok &= testCalculDateAuPlusTotDeFinDeProjet();
        
        if (ok) {
            System.out.println("Test reussis");
        } else {
            System.out.println("Test echoue");
        }
        
    }
    

    /**
     * Test du constructeur de la class Tache
     * @return testOK
     */
    private static boolean testConstructeurStringStringString() {
        
        boolean testOK;
        
        try {
            new Projet(null, "Projet realisant une expertise", Projet.UNITE_TEMPS[0]);
            testOK = false;
        } catch (NullPointerException nomNul) {
            testOK = true;
        }
        
        try {
            new Projet("  ", "Projet realisant une expertise", Projet.UNITE_TEMPS[0]);
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
            new Projet("Projet A", "Projet realisant une expertise", Projet.UNITE_TEMPS[0]);
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
            ok &= AssertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getNom());
        }
        return ok;
    }
    
    /**
     * Test unitaires de getDescription
     * @return true si test reussis sinon false
     */
    private static boolean testGetDescription() {
        
        String[] nomAttendus = {"Projet realisant une expertise",
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
     * @return true si test reussis sinon false 
     */
    private static boolean testSetDescription() {
        
        boolean ok;
        
        /** Tache de test */
        Projet test = new Projet("Projet C", "Projet automatique", Projet.UNITE_TEMPS[2]);
        String[] testDescription = {"Projet realisant une expertise","Réalisation-du_projet", "Fin_de_projet", "    "};
        
        String[] descriptionAttendus = {"Projet realisant une expertise", "Réalisation-du_projet", "Fin_de_projet", "Projet automatique"};
        
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
     * @return true si test reussis sinon false
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
     * @return true si test reussis sinon false
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
     * @return true si test reussis sinon false
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
        
        ArrayList<Tache> cloneTaches = new ArrayList<Tache>();
        
        Projet ProjetA = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        ProjetA.ajouterTache(tacheAAjouter);
        
        cloneTaches = ProjetA.getTaches();
        
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
     * test unitaires de calcul de la date au plus tot
     * @return true si test reussis, sinon false
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
        
        
        ProjetTest.calculerDateAuPlusTot();
        ok = AssertionTest.assurerEgaliteDouble(0.0, tacheA.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(2.0, tacheB.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(2.0, tacheC.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(5.5, tacheE.getDateAuPlusTot());
        ok &= AssertionTest.assurerEgaliteDouble(25.5, tacheD.getDateAuPlusTot());
        
        return ok;
    }
    
    /**
     * test unitaires de calcul de la date au plus tot de fin de projet
     * @return true si test reussis, sinon false
     */
    private static boolean testCalculDateAuPlusTotDeFinDeProjet() {
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
        
        ProjetTest.calculerDateAuPlusTot();
        ProjetTest.calculerDateAuPlusTotFinDeProjet();
        ProjetTest.calculerDateAuPlusTard();
        
        ok = AssertionTest.assurerEgaliteDouble(ProjetTest.getDateAuPlusTardProjet(), tacheD.getDateAuPlusTard());
        
        
        return ok;
    }
    
    
    /**
     * test unitaires de calcul de la date au plus tard
     * @return true si test reussis, sinon false
     */
    private static boolean testCalculDateAuPlusTard() {
        boolean ok;
        ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheA = new Tache("Tache A", "Cette tache consiste à analyser le besoin", 2.0);
        Tache tacheB = new Tache("Tache B", "Mise en place des méthodes de travail", 5.0);
        Tache tacheC = new Tache("Tache C", "Répartition du travail", 3.5);
        Tache tacheD = new Tache("Tache E", "Réalisation de l'application", 30.0);
        Tache tacheE = new Tache("Tache D", "Réalisation des tests", 20.0);
        
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
        
        
        ProjetTest.calculerDateAuPlusTot();
        ProjetTest.calculerDateAuPlusTotFinDeProjet();
        ProjetTest.calculerDateAuPlusTard();
        
        System.out.println(tacheA.getDateAuPlusTard());
        
        ok = AssertionTest.assurerEgaliteDouble(2.0, tacheA.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(25.5, tacheB.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(5.5, tacheC.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(25.5, tacheE.getDateAuPlusTard());
        ok &= AssertionTest.assurerEgaliteDouble(55.5, tacheD.getDateAuPlusTard());
        
        return ok;
    }
    
    
    /**
     * test unitaires de la méthode toString
     * @return true si test réussi, sinon false
     */
    private static boolean testToString() {
       
        
        boolean ok;
        ok = true;
        
        String attenduA = "Ce projet est nomme : Projet A\n" 
                        + "Sa description est : Projet automatique\n"
                        + "Son unite de temps est : Jour(s)\n" 
                        + "Ses taches sont : \n"
                        + "Cette tache est defini par :\n"
                        + "  Nom : Tache A\n"
                        + "  Description : Répartition du travail\n"
                        + "  Duree : 30.5\n"
                        + "  Date au plus tot : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Cette tache n'a pas de taches prealables\n"
                        + "Cette tache est defini par :\n"
                        + "  Nom : Tache B\n"
                        + "  Description : Réalisation de l'application\n"
                        + "  Duree : 3.5\n"
                        + "  Date au plus tot : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Taches prealables : Tache A | Tache C | \n\n"
                        + "Sa date au plus tot est : 0.0\n" 
                        + "Sa date au plus tard est : 0.0";
        
        String attenduB = "Ce projet est nomme : Projet B\n" 
                        + "Sa description est : Projet calculant un cout\n"
                        + "Son unite de temps est : Heure(s)\n" 
                        + "Ses taches sont : \n"
                        + "Cette tache est defini par :\n"
                        + "  Nom : Tache C\n"
                        + "  Description : Réalisation des tests\n"
                        + "  Duree : 20.0\n"
                        + "  Date au plus tot : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Taches prealables : Tache B | \n"
                        + "Cette tache est defini par :\n"
                        + "  Nom : Tache A\n"
                        + "  Description : Répartition du travail\n"
                        + "  Duree : 30.5\n"
                        + "  Date au plus tot : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Cette tache n'a pas de taches prealables\n\n"
                        + "Sa date au plus tot est : 0.0\n" 
                        + "Sa date au plus tard est : 0.0";
        
        String attenduC = "Ce projet est nomme : Projet C\n"
                        + "Sa description est : Projet automatique\n"
                        + "Son unite de temps est : Jour(s)\n"
                        + "Ses taches sont : \n"
                        + "Cette tache est defini par :\n"
                        + "  Nom : Tache B\n"
                        + "  Description : Réalisation de l'application\n"
                        + "  Duree : 3.5\n"
                        + "  Date au plus tot : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Taches prealables : Tache A | Tache C | \n"
                        + "Cette tache est defini par :\n"
                        + "  Nom : Tache C\n"
                        + "  Description : Réalisation des tests\n"
                        + "  Duree : 20.0\n"
                        + "  Date au plus tot : 0.0\n"
                        + "  Date au plus tard : 0.0\n"
                        + "  Taches prealables : Tache B | \n\n"
                        + "Sa date au plus tot est : 0.0\n"
                        + "Sa date au plus tard est : 0.0";
        
        
        
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
    
}
