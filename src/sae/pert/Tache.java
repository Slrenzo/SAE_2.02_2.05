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
    
    /* Nom attribue a la tache */
    private String nom;
    
    /* Description synthetique de la tache */
    private String description;
    
    /* 
     * Duree que la tache prendra a etre realisee
     * (L'unite de temps sera defini dans le projet ou est utilise la tache)
     */
    private double duree;
    
    /*
     * Date au plus tot de cette tache defini par un projet
     */
    private double dateAuPlusTot;
    
    
    /*
     * Date au plus tard de cette tache defini par un projet
     */
    private double dateAuPlusTard;
    
    /*
     * Liste des taches a realiser avant de realiser cette tache
     */
    private ArrayList<Tache> tachesPrealables;
    
    /**
     * Definition d'une tache ayant une duree de realisation
     * @param nom nom ni vide ni null de la tache
     * @param description description ni vide ni null de la tache
     * @param duree duree de la tache superieur a 0.0
     * @throws IllegalArgumentException nom est vide
     * @throws IllegalArgumentException description est vide
     * @throws IllegalArgumentException duree inferieur ou egal a 0.0
     */
    public Tache(String nom, String description, double duree) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        if (duree < 0.0) {
            throw new IllegalArgumentException("La duree est negative");
        }
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.dateAuPlusTard = 0.0;
        this.dateAuPlusTot = 0.0;
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
     * Affiche la description de la tache
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la tache
     * @param description description a modifier
     * @throws IllegalArgumentException description est vide
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        this.description = description;
    }

    /**
     * Affiche la duree de la tache
     * @return duree
     */
    public double getDuree() {
        return duree;
    }

    /**
     * Modifie la duree de la tache
     * @param duree duree a modifier
     * @throws IllegalArgumentException duree inferieur ou egal a 0.0
     */
    public void setDuree(double duree) {
        if (duree <= 0.0) {
            throw new IllegalArgumentException("La dur�e est nulle "
                                               + "ou n�gative");
        }
        this.duree = duree;
    }
    
    /**
     * @return dateAuPlusTot de cette tache
     */
    public double getDateAuPlusTot() {
        return dateAuPlusTot;
    }

    /**
     * @param dateAuPlusTot modifie la date au plus tot de cette tache
     * @throws IllegalArgumentException si la date au plus tot est negative
     */
    public void setDateAuPlusTot(double dateAuPlusTot) {
        if (dateAuPlusTot < 0.0) {
            throw new IllegalArgumentException("La date est negative");
        }
        this.dateAuPlusTot = dateAuPlusTot;
    }

    /**
     * @return dateAuPlusTard de cette tache
     */
    public double getDateAuPlusTard() {
        return dateAuPlusTard;
    }

    /**
     * @param dateAuPlusTard modifie la date au plus tard de cette tache
     * @throws IllegalArgumentException si la date au plus tard est negative
     */
    public void setDateAuPlusTard(double dateAuPlusTard) {
        if (dateAuPlusTard < 0.0) {
            throw new IllegalArgumentException("La date est negative");
        }
        this.dateAuPlusTard = dateAuPlusTard;
    }

    /**
     * @return tachesPrealables les taches prealables de cette tache
     */
    public ArrayList<Tache> getTachesPrealables() {
        ArrayList<Tache> cloneTachesPrealables = new ArrayList<Tache>(0);
        Tache cloneTache;
        for (int i = 0; i < this.tachesPrealables.size(); i++) {
            cloneTache = new Tache(this.tachesPrealables.get(i).getNom(),
                                   this.tachesPrealables.get(i).getDescription(),
                                   this.duree);
            cloneTache.setDateAuPlusTot(
                            this.tachesPrealables.get(i).getDateAuPlusTot()
            );
            cloneTache.setDateAuPlusTard(
                            this.tachesPrealables.get(i).getDateAuPlusTard()
            );
            cloneTachesPrealables.add(cloneTache);
        }
        return cloneTachesPrealables;
    }
    
    /** 
     * @return nombre de taches prealables de cette tache
     */
    public int nombreTachesPrealables() {
        return this.tachesPrealables.size();
    }

    /**
     * Ajoute une tache aux taches prealables de cette tache
     * @param tache tache que l'on souhaite ajouter aux taches prealables
     * @throws IllegalArgumentException tache est deja une tache prealable 
     *         de cette tache
     * @throws IllegalArgumentException la tache que l'on ajoute est la meme
     *         que cette tache
     */
    public void ajouterTachePrealable(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesPrealables.size(); i++) {
            estPresent = this.tachesPrealables.get(i).nom == tache.nom;
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tache est deja une "
                                               + "tache prealable");
        }
        if (this.nom == tache.nom) {
            throw new IllegalArgumentException("Cette tache et la tache que"
                                               + " vous souhaitez ajouter"
                                               + " sont les meme");
        }
        this.tachesPrealables.add(tache);
    }
    
    /**
     * @param tacheATester tache que l'on test si elle est une tache prealable
     * @return true si tacheATester est une tache prealable
     */
    public boolean aLaTachePrealabale(Tache tacheATester) {
        return this.tachesPrealables.contains(tacheATester);
    }
    
    /** 
     * @param i l'index de la tache que l'on veut recuperer
     * @return la tache a l'index i
     */
    public Tache avoirTachePrealable(int i) {
        return this.tachesPrealables.get(i);
    }
    
    /**
     * Enleve une tache aux taches prealables de cette tache
     * @param tache tache que l'on souhaite ajouter aux taches prealables
     * @throws IllegalArgumentException tache ne fait pas partie des taches 
     *         prealables de cette tache
     */
    public void enleverTachePrealable(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesPrealables.size(); i++) {
            estPresent = this.tachesPrealables.get(i).nom == tache.nom;
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tache n'est pas une "
                                               + "tache prealable");
        }
        this.tachesPrealables.remove(tache);
    }
    
    @Override
    public String toString() {
        String tachesPrealables = this.tachesPrealables.size() != 0 
                                  ? "\n  Taches prealables : "
                                  : "\n  Cette tache n'a pas de taches "
                                  + "prealables";
        for (int i = 0; i < this.tachesPrealables.size(); i++) {
            tachesPrealables += this.tachesPrealables.get(i).nom + " | ";
        }
        return "Cette tache est defini par :\n  Nom : " + this.nom 
               + "\n  Description : " + this.description
               + "\n  Duree : " + this.duree
               + "\n  Date au plus tot : " + this.dateAuPlusTot
               + "\n  Date au plus tard : " + this.dateAuPlusTard
               + tachesPrealables;
    }
}
