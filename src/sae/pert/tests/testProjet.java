/*
 * testProjet.java                                  10 mai 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package sae.pert.tests;

import java.util.ArrayList;

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
            ok &= assertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getNom());
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
            ok &= assertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getDescription());
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
            ok &= assertionTest.assurerEgalite(nomAttendus[noJeu],aTester[noJeu].getUniteTemps());
        }
        return ok;
    }
    
    
    /**
     * Test unitaires de ajouterTache
     * @return true si test reussis sinon false
     */
    private static boolean testAjouterTacheProjet() {
        boolean ok = true;
        
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
    
    private static boolean testEnleverTacheProjet() {
        boolean ok = true;
        
        Projet ProjetTest = new Projet("Projet A", "Projet automatique", Projet.UNITE_TEMPS[2]);
        
        Tache tacheAAjouter = new Tache("Tache A", "Répartition du travail", 30.5);
        
        Tache tacheAAjouter1 = new Tache("Tache B", "Réalisation de l'application", 3.5);
        
        Tache tacheAAjouter2 = new Tache("Tache B", "Réalisation des tests", 20.0);
        
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
    
    
    
    
}
