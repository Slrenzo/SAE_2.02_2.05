/*
 * Tache.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */

package sae.pert;

import java.util.ArrayList;
import java.util.List;

/**
 * Tache permettant de faire avancer un projet.
 * @author Thomas Nalix
 * @author Lorentin Nicolas
 * @author Emilien Restoueix
 * @author Enzo Soulier
 */
public class Tache {
    
    /* Nom attribué a la tache */
    private String nom;
    
    /* Description synthétique de la tache */
    private String description;
    
    /* 
     * Durée que la tache prendra a être réalisée
     * (L'unité de temps sera défini dans le projet ou est utilise la tâche)
     */
    private double duree;
    
    /*
     * Date au plus tôt de cette tâche défini par un projet
     */
    private double dateAuPlusTot;
    
    
    /*
     * Date au plus tard de cette tâche défini par un projet
     */
    private double dateAuPlusTard;
    
    /*
     * Liste des tâches a réaliser avant de réaliser cette tâche
     */
    private List<Tache> tachesPrealables;
    
    /*
     * Liste des tâches successeurs de cette tâche
     */
    private List<Tache> tachesSuccesseurs;
    
    /*
     * Marge libre de cette tâche défini par un projet
     */
    private double margeLibre;
    
    /*
     * Marge totale de cette tâche défini par un projet
     */
    private double margeTotale;
    
    /**
     * Definition d'une tâche ayant une durée de réalisation
     * @param nom nom ni vide ni null de la tâche
     * @param description description ni vide ni null de la tâche
     * @param duree durée de la tâche supérieur a 0.0
     * @throws IllegalArgumentException 
     *                  <ul>
     *                      <li>nom est vide</li>
     *                      <li>description est vide</li>
     *                      <li>durée inférieur ou égal a 0.0</li>
     *                  </ul>
     */
    public Tache(String nom, String description, double duree) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom est vide");
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Le description est vide");
        }
        if (duree < 0.0) {
            throw new IllegalArgumentException("La durée est négative");
        }
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.dateAuPlusTard = 0.0;
        this.dateAuPlusTot = 0.0;
        this.margeLibre = 0.0;
        this.margeTotale = 0.0;        
        this.tachesPrealables = new ArrayList<Tache>(0);
        this.tachesSuccesseurs = new ArrayList<Tache>(0);
    }

    /**
     * Affiche le nom de la tâche
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Affiche la description de la tâche
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifie la description de la tâche
     * @param description description a modifier
     * @throws IllegalArgumentException description est vide
     */
    public void setDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("La description est vide");
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
     * @param duree durée a modifier
     * @throws IllegalArgumentException durée inférieur ou égal a 0.0
     */
    public void setDuree(double duree) {
        if (duree <= 0.0) {
            throw new IllegalArgumentException("La durée est nulle "
                                               + "ou négative");
        }
        this.duree = duree;
    }
    
    /**
     * @return dateAuPlusTot de cette tâche
     */
    public double getDateAuPlusTot() {
        return dateAuPlusTot;
    }

    /**
     * Modifie la date au plus tôt d'une tâche
     * @param dateAuPlusTot modifie la date au plus tôt de cette tâche
     * @throws IllegalArgumentException si la date au plus tôt est négative
     */
    public void setDateAuPlusTot(double dateAuPlusTot) {
        if (dateAuPlusTot < 0.0) {
            throw new IllegalArgumentException("La date est négative");
        }
        this.dateAuPlusTot = dateAuPlusTot;
    }

    /**
     * @return dateAuPlusTard de cette tâche
     */
    public double getDateAuPlusTard() {
        return dateAuPlusTard;
    }

    /**
     * Modifie la date au plus tard d'une tâche
     * @param dateAuPlusTard modifie la date au plus tard de cette tâche
     * @throws IllegalArgumentException si la date au plus tard est négative
     */
    public void setDateAuPlusTard(double dateAuPlusTard) {
        if (dateAuPlusTard < 0.0) {
            throw new IllegalArgumentException("La date est négative");
        }
        this.dateAuPlusTard = dateAuPlusTard;
    }
    
    

    /**
     * @return margeLibre
     */
    public double getMargeLibre() {
        return margeLibre;
    }

    /**
     * @param margeLibre modifie margeLibre
     */
    public void setMargeLibre(double margeLibre) {
        if (margeLibre < 0.0) {
            throw new IllegalArgumentException("La marge libre ne peut pas "
                                               + "être négatif");
        }
        this.margeLibre = margeLibre;
    }

    /**
     * @return margeTotale
     */
    public double getMargeTotale() {
        return margeTotale;
    }

    /**
     * @param margeTotale modifie margeTotale 
     */
    public void setMargeTotale(double margeTotale) {
        if (margeTotale < 0.0) {
            throw new IllegalArgumentException("La marge totale ne peut pas "
                                               + "être négatif");
        }
        this.margeTotale = margeTotale;
    }

    /**
     * @return tachesPrealables les tâches préalables de cette tâche
     */
    public List<Tache> getTachesPrealables() {
        List<Tache> cloneTachesPrealables = new ArrayList<Tache>(0);
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
     * @return nombre de tâches préalables de cette tâche
     */
    public int nombreTachesPrealables() {
        return this.tachesPrealables.size();
    }

    /**
     * Ajoute une tâche aux tâches préalables de cette tâche
     * @param tache tâche que l'on souhaite ajouter aux tâches préalables
     * @throws IllegalArgumentException tâche est déjà une tâche préalable 
     *         de cette tâche
     * @throws IllegalArgumentException la tâche que l'on ajoute est la même
     *         que cette tâche
     */
    public void ajouterTachePrealable(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesPrealables.size(); i++) {
            estPresent = this.tachesPrealables.get(i).nom == tache.nom;
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tâche est déjà une "
                                               + "tâche préalable");
        }
        if (this.nom == tache.nom) {
            throw new IllegalArgumentException("Cette tâche et la tâche que"
                                               + " vous souhaitez ajouter"
                                               + " sont les mêmes");
        }
        this.tachesPrealables.add(tache);
    }
    
    /**
     * @param tacheATester tâche que l'on test si elle est une tâche préalable
     * @return true si tacheATester est une tâche préalable
     */
    public boolean aLaTachePrealable(Tache tacheATester) {
        return this.tachesPrealables.contains(tacheATester);
    }
    
    /** 
     * Récupère une tâche a l'indice donné
     * @param i l'index de la tâche que l'on veut récupérer
     * @return la tâche a l'index i
     */
    public Tache avoirTachePrealable(int i) {
        return this.tachesPrealables.get(i);
    }
    
    /**
     * Enlève une tache aux tâches préalables de cette tâche
     * @param tache tâche que l'on souhaite retirer aux tâches préalables
     * @throws IllegalArgumentException tâche ne fait pas partie des tâches 
     *         Préalables de cette tache
     */
    public void enleverTachePrealable(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesPrealables.size(); i++) {
            estPresent = this.tachesPrealables.get(i).nom == tache.nom;
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tâche n'est pas une "
                                               + "tâche préalable");
        }
        this.tachesPrealables.remove(tache);
    }
    
    /**
     * Récupère une tâche successrice
     * @return tachesPrealables les tâches successrices de cette tâche
     */
    public List<Tache> getTachesSuccesseurs() {
        List<Tache> cloneTachesSuccesseurs = new ArrayList<Tache>(0);
        Tache cloneTache;
        for (int i = 0; i < this.tachesSuccesseurs.size(); i++) {
            cloneTache = new Tache(this.tachesSuccesseurs.get(i).getNom(),
                                   this.tachesSuccesseurs.get(i)
                                   .getDescription(),
                                   this.duree);
            cloneTache.setDateAuPlusTot(
                            this.tachesSuccesseurs.get(i).getDateAuPlusTot()
            );
            cloneTache.setDateAuPlusTard(
                            this.tachesSuccesseurs.get(i).getDateAuPlusTard()
            );
            cloneTachesSuccesseurs.add(cloneTache);
        }
        return cloneTachesSuccesseurs;
    }
    
    /**
     * Ajoute une tâche aux tâches successrices de cette tâche
     * @param tache tâche que l'on souhaite ajouter aux tâches successrices
     * @throws IllegalArgumentException tâche est déjà une tâche successrice 
     *         de cette tâche
     * @throws IllegalArgumentException la tâche que l'on ajoute est la même
     *         que cette tâche
     */
    public void ajouterTacheSuccesseur(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesSuccesseurs.size(); i++) {
            estPresent = this.tachesSuccesseurs.get(i).nom == tache.nom;
        }
        if (estPresent) {
            throw new IllegalArgumentException("Cette tâche est déjà une "
                                               + "tâche successrice");
        }
        if (this.nom == tache.nom) {
            throw new IllegalArgumentException("Cette tâche et la tâche que"
                                               + " vous souhaitez ajouter"
                                               + " sont les êmes");
        }
        this.tachesSuccesseurs.add(tache);
    }
    
    /** 
     * @return nombre de tâches successrices de cette tâche
     */
    public int nombreTachesSuccesseurs() {
        return this.tachesSuccesseurs.size();
    }
    
    /**
     * Ajoute une liste de tâches aux tâches successrice de cette tâche
     * @param taches tâches que l'on souhaite ajouter aux tâches successrices
     */
    public void setTachesSuccesseurs(List<Tache> taches) {
        tachesSuccesseurs = taches;
    }
    
    /**
     * @param tacheATester tâche que l'on test si elle est une tâche successrices
     * @return true si tacheATester est une tâche successrices
     */
    public boolean aLaTacheSuccesseur(Tache tacheATester) {
        return this.tachesSuccesseurs.contains(tacheATester);
    }
    
    /** 
     * @param i l'index de la tâche que l'on veut récupérer
     * @return la tâche a l'index i
     */
    public Tache avoirTacheSuccesseur(int i) {
        return this.tachesSuccesseurs.get(i);
    }
    
    /**
     * Enlève une tâche aux tâches successrices de cette tâche
     * @param tache tache que l'on souhaite ajouter aux tâches successrices
     * @throws IllegalArgumentException tâche ne fait pas partie des tâches 
     *         successrices de cette tâche
     */
    public void enleverTacheSuccesseur(Tache tache) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.tachesSuccesseurs.size(); i++) {
            estPresent = this.tachesSuccesseurs.get(i).nom == tache.nom;
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tâche n'est pas une "
                                               + "tâche successeur");
        }
        this.tachesSuccesseurs.remove(tache);
    }
    
    /** 
     * Test si la tâche est critique
     * @return true si cette tâche est une tâche critique
     */
    public boolean estTacheCritique() {
        return dateAuPlusTard == dateAuPlusTot;
    }
    
    @Override
    public String toString() {
        String tachesPrealables = this.tachesPrealables.size() != 0 
                                  ? "\n  Tâches préalables : "
                                  : "\n  Cette tâche n'a pas de tâches "
                                  + "préalables";
        for (int i = 0; i < this.tachesPrealables.size(); i++) {
            tachesPrealables += this.tachesPrealables.get(i).nom + " | ";
        }
        return "Cette tâche est défini par :\n  Nom : " + this.nom 
               + "\n  Description : " + this.description
               + "\n  Durée : " + this.duree
               + "\n  Date au plus tôt : " + this.dateAuPlusTot
               + "\n  Date au plus tard : " + this.dateAuPlusTard
               + tachesPrealables
               + "\n  Marge libre : " + margeLibre
               + "\n  Marge totale : " + margeTotale;
    }

    /** 
     * Calcul la marge de cette tâche 
     */
    public void calculMarges() {
        double dateAuPlusTotMin = Double.POSITIVE_INFINITY;
        double dateAuPlusTardMin = Double.POSITIVE_INFINITY;
        for (int i = 0; i < tachesSuccesseurs.size(); i++) {
            if (tachesSuccesseurs.get(i).getDateAuPlusTot() 
                < dateAuPlusTotMin) {
                dateAuPlusTotMin = tachesSuccesseurs.get(i)
                                   .getDateAuPlusTot();
            }
            if (tachesSuccesseurs.get(i).getDateAuPlusTard() 
                < dateAuPlusTardMin) {
                dateAuPlusTardMin = tachesSuccesseurs.get(i)
                                    .getDateAuPlusTard();
            }
            margeLibre = dateAuPlusTotMin - dateAuPlusTot - duree;
            margeTotale = dateAuPlusTardMin - dateAuPlusTard - duree;
        }
    }
}
