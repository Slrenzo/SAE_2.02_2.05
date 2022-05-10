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
    
    /* Matrice d'adjacence du graphe d'ordonnancement */
    private ArrayList<ArrayList<Double>> ordonnancement;
    
    
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
        this.ordonnancement = new ArrayList<ArrayList<Double>>();
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
        String ordonnancement = "";
        for (int i = 0; i < this.ordonnancement.size(); i++) {
            for (int j = 0; i < this.ordonnancement.get(i).size(); i++) {
                ordonnancement += this.ordonnancement.get(i).get(j) + " ";
            }
            ordonnancement += "\n";
        }
        return "Ce projet est nommé : " + this.nom + "\n" 
               + "Son unité de temps est : " + this.uniteTemps + "\n" 
               + "Ses tâches sont : \n" + taches
               + "La matrice d'adjacence de son graphe d'ordonnancement est :\n"
               + ordonnancement;
    }
    
}
