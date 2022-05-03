/*
 * Tache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */

package sae.pert;

import java.util.ArrayList;

/**
 * Tache permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Tache {
    
    /* Nom attribué à la tache */
    private String nom;
    
    /* Description synthétique de la tache */
    private String description;
    
    /* 
     * Durée que la tache prendra a être réalisée
     * (L'unité de temps sera défini dans le projet ou est utilisé la tache)
     */
    private double duree;
    
    /*
     * Liste des taches à réaliser avant de réalisé cette tache
     */
    private ArrayList<Tache> tachesPrealables;
    
    /**
     * Définition d'une tache ayant une durée de réalisation
     * @param nom nom ni vide ni null de la tache
     * @param description description ni vide ni null de la tache
     * @param duree durée de la tache supérieur à 0.0
     * @throws IllegalArgumentException nom est vide
     * @throws IllegalArgumentException description est vide
     * @throws IllegalArgumentException duree inférieur ou égal à 0.0
     */
    public Tache(String nom, String description, double duree) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est "
                                               + "vide ou null");
        }
        if (duree <= 0.0) {
            throw new IllegalArgumentException("La durée est nulle "
                                               + "ou négative");
        }
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.tachesPrealables = new ArrayList<Tache>(0);
    }

    /**
     * Affiche le nom de la tache
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de la tache
     * @param nom nom à modifier
     */
    public void setNom(String nom) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        this.nom = nom;
    }

    /**
     * Affiche la description de la tache
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la tache
     * @param description description à modifier
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("La description est "
                                               + "vide ou null");
        }
        this.description = description;
    }

    /**
     * Affiche la durée de la tache
     * @return duree
     */
    public double getDuree() {
        return duree;
    }

    /**
     * Modifie la durée de la tache
     * @param duree durée à modifier
     */
    public void setDuree(double duree) {
        if (duree <= 0.0) {
            throw new IllegalArgumentException("La durée est nulle "
                                               + "ou négative");
        }
        this.duree = duree;
    }
    
    /**
     * Ajoute une tache à aux taches préalables de cette tache
     * @param tache tache que l'on souhaite ajouter aux taches préalables
     * @throws IllegalArgumentException tache est déja une tache préalable 
     *         de cette tache
     */
    public void ajouterTachePrealable(Tache tache) {
        if (this.nom == tache.nom) {
            throw new IllegalArgumentException("Cette tache est déja une "
                                               + "tache préalable");
        }
        this.tachesPrealables.add(tache);
    }
    
    /**
     * Enlève une tache à aux taches préalables de cette tache
     * @param tache tache que l'on souhaite ajouter aux taches préalables
     * @throws IllegalArgumentException tache ne fait pas partie des taches 
     *         préalables de cette tache
     */
    public void enleverTachePrealable(Tache tache) {
        boolean ok = false;
        for (int i = 0; !ok && i < this.tachesPrealables.size(); i++) {
            ok = this.nom == tache.nom;
        }
        if (!ok) {
            throw new IllegalArgumentException("Cette tache n'est pas une "
                                               + "tache préalable");
        }
        this.tachesPrealables.remove(tache);
    }
    
    @Override
    public String toString() {
        String tachesPrealables = this.tachesPrealables.size() != 0 
                                  ? "\n  Taches préalables : "
                                  : "\n  Cette tache n'a pas de taches "
                                  + "préalables";
        for (int i = 0; i < this.tachesPrealables.size(); i++) {
            tachesPrealables += this.tachesPrealables.get(i).nom + " | ";
        }
        return "Cette tache est défini par :\n  Nom : " + this.nom 
               + "\n  Description : " + this.description
               + "\n  Duree : " + this.duree
               + tachesPrealables;
    }
}
