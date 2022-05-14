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
    
    /* Description du projet */
    private String description;
    
    /* Listes des taches à effectuer dans pour ce projet */
    private ArrayList<Tache> taches;
    
    /* Unité de temps utilisé pour parler des durées des taches */
    private String uniteTemps;
    
    /* Date au plus tôt du projet */
    private double dateAuPlusTotProjet;
    
    /* Date au plus tard du projet */
    private double dateAuPlusTardProjet;    
    
    
    /**
     * Tableau d'unite de temps possible 
     */
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
     * @param description description du projet
     * @param uniteTemps l'unite de temps défini pour ce projet
     * @throws IllegalArgumentException si le nom est vide
     * @throws IllegalArgumentException si la description est vide
     * @throws IllegalArgumentException si l'unite de temps est invalide
     */
    public Projet(String nom, String description, String uniteTemps) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        boolean uniteTempsOk = false;
        for (int i = 0; !uniteTempsOk && i < UNITE_TEMPS.length; i++) {
            uniteTempsOk = uniteTemps == UNITE_TEMPS[i];
        }
        if (!uniteTempsOk) {
            throw new IllegalArgumentException("L'unité de temps est invalide");
        }
        this.nom = nom;
        this.description = description;
        this.uniteTemps = uniteTemps;
        this.taches = new ArrayList<Tache>();
        this.dateAuPlusTotProjet = 0.0;
        this.dateAuPlusTardProjet = 0.0;
    }

    /**
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return description de ce projet
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description modifie la description de ce projet
     * @throws IllegalArgumentException si la description est vide
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        this.description = description;
    }

    /**
     * @return uniteTemps du projet 
     */
    public String getUniteTemps() {
        return uniteTemps;
    }

    /**
     * @return dateAuPlusTotProjet de ce projet
     */
    public double getDateAuPlusTotProjet() {
        return dateAuPlusTotProjet;
    }

    /**
     * @return dateAuPlusTardProjet de ce projet
     */
    public double getDateAuPlusTardProjet() {
        return dateAuPlusTardProjet;
    }

    /** 
     * Ajoute une tache à ce projet
     * @param tacheAAjouter la tache à ajouter au projet
     * @throws IllegalArgumentException si la tache est déja dans le projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = tacheAAjouter.equals(this.taches.get(i));
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tache est déja présente");
        }
        this.taches.add(tacheAAjouter);
    }
    
    /** 
     * Enlève une tache à ce projet
     * @param tacheAEnlever la tache à enlever au projet
     * @throws IllegalArgumentException si la tache n'est pas dans le projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = tacheAEnlever.equals(this.taches.get(i));
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tache n'est pas dans "
                                               + "le projet");
        }
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
