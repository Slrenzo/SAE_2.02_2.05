/*
 * Projet.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */
package sae.pert;

import java.util.ArrayList;

/** 
 * Un projet est un ensemble de taches que l'on doit ordonné pour arriver à
 * un objectif.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Projet {

    /* Nom du projet */
    private String nom;
    
    /* Listes des taches à effectuer dans pour ce projet */
    private ArrayList<Tache> taches;
    
    /* Unité de temps utilisé pour parler des durées des taches */
    private String uniteTemps;
    
    /* Dates au plus tôt des différentes taches du projet */
    private ArrayList<Double> datesAuPlusTot;
    
    /* Dates au plus tard des différentes taches du projet */
    private ArrayList<Double> datesAuPlusTard;
    
    /* Date au plus tôt du projet */
    private double dateAuPlusTotFinDeProjet;
    
    /* Date au plus tard du projet */
    private double dateAuPlusTardFinDeProjet;    
    
    
    /** TODO commenter le rôle de ce champ (attribut, rôle associatif) */
    public final static String[] UNITE_TEMPS = { 
        "Minute(s)",
        "Heure(s)",
        "Jour(s)",
        "Semaine(s)",
        "Mois",
        "Année(s)"
    };
    
    
    /**
     * Définition d'un projet qui possède un nom 
     * et qui possède une unité de temps
     * @param nom le nom du projet
     * @param uniteTemps l'unite de temps défini pour ce projet
     */
    public Projet(String nom, String uniteTemps) {
        this.nom = nom;
        this.uniteTemps = uniteTemps;
        this.taches = new ArrayList<Tache>();
        this.datesAuPlusTard = new ArrayList<Double>();
        this.datesAuPlusTot = new ArrayList<Double>();
        this.dateAuPlusTardFinDeProjet = 0.0;
        this.dateAuPlusTotFinDeProjet = 0.0;
    }

    /**
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom modifie le nom du projet
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return uniteTemps du projet 
     */
    public String getUniteTemps() {
        return uniteTemps;
    }

    /**
     * @param uniteTemps modifie uniteTemps du projet
     */
    public void setUniteTemps(String uniteTemps) {
        this.uniteTemps = uniteTemps;
    }
    
    /**
     * @return taches du projet
     */
    public ArrayList<Tache> getTaches() {
        return taches;
    }

    /**
     * @return datesAuPlusTot de chaque tache du projet
     */
    public ArrayList<Double> getDatesAuPlusTot() {
        return datesAuPlusTot;
    }

    /**
     * @return datesAuPlusTard de chaque tache du projet
     */
    public ArrayList<Double> getDatesAuPlusTard() {
        return datesAuPlusTard;
    }

    /**
     * @return dateAuPlusTotFinDeProjet
     */
    public double getDateAuPlusTotFinDeProjet() {
        return dateAuPlusTotFinDeProjet;
    }

    /**
     * @return dateAuPlusTardFinDeProjet
     */
    public double getDateAuPlusTardFinDeProjet() {
        return dateAuPlusTardFinDeProjet;
    }

    /** 
     * Ajoute une tache à ce projet
     * @param tacheAAjouter la tache à ajouter au projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        this.taches.add(tacheAAjouter);
    }
    
    /** 
     * Enlève une tache à ce projet
     * @param tacheAEnlever la tache à enlever au projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        this.taches.remove(tacheAEnlever);
    }
    
    @Override
    public String toString() {
        String taches = "";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        return "Ce projet est nommé : " + this.nom + "\n" 
               + "Son unité de temps est : " + this.uniteTemps + "\n" 
               + "Ses tâches sont : \n" + taches;
    }
    
}
