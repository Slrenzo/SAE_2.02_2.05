/*
 * Projet.java                                  22 avr. 2022
 * IUT de Rodez, Info1 2021-2022, pas de copyright 
 */
package sae.pert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Un projet est un ensemble de taches que l'on doit ordonnee pour arriver a
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

    /* Listes des taches a effectuer dans pour ce projet */
    private List<Tache> taches;

    /* Unite de temps utilise pour parler des durees des taches */
    private String uniteTemps;

    /* Date au plus tot du projet */
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
                    "Annee(s)"
    };
    
    /**
     * Définition d'un projet qui possède un nom 
     * et qui possede une unite de temps
     * @param nom le nom du projet
     * @param description description du projet
     * @param uniteTemps l'unite de temps d fini pour ce projet
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
            uniteTempsOk = uniteTemps.equals(UNITE_TEMPS[i]);
        }
        if (!uniteTempsOk) {
            throw new IllegalArgumentException("L'unite de temps est invalide");
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
     * @return taches les taches de ce projet
     */
    public List<Tache> getTaches() {
        List<Tache> cloneTaches = new ArrayList<Tache>(0);
        Tache cloneTache;
        List<Tache> cloneTachesPrealables;
        for (int i = 0; i < this.taches.size(); i++) {
            cloneTache = new Tache(this.taches.get(i).getNom(),
                                   this.taches.get(i).getDescription(),
                                   this.taches.get(i).getDuree());
            cloneTache.setDateAuPlusTot(this.taches.get(i).getDateAuPlusTot());
            cloneTache.setDateAuPlusTard(this.taches.get(i)
                                         .getDateAuPlusTard());
            cloneTachesPrealables = this.taches.get(i).getTachesPrealables();
            for (int j = 0; j < cloneTachesPrealables.size(); j++) {
                cloneTache.ajouterTachePrealable(cloneTachesPrealables.get(j));
            }
            cloneTaches.add(cloneTache);
        }
        return cloneTaches;
    }
    
    /** 
     * Ajoute une tache a ce projet
     * @param tacheAAjouter la tache a ajouter au projet
     * @throws IllegalArgumentException si la tache est deja dans le projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        for (int i = 0; i < this.taches.size(); i++) {
            if (tacheAAjouter.getNom().equals(this.taches.get(i).getNom())) {
                throw new IllegalArgumentException("Cette tache est deja "
                                + "presente");
            }
        }
        this.taches.add(tacheAAjouter);
    }

    /** 
     * Enleve une tache a ce projet
     * @param tacheAEnlever la tache a enlever au projet
     * @throws IllegalArgumentException si la tache n'est pas dans le projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = this.taches.get(i).equals(tacheAEnlever);
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tache n'est pas dans "
                            + "le projet");
        }
        this.taches.remove(tacheAEnlever);
    }

    /** 
     * teste si le projet possede un circuit
     * @return true s'il y a un circuit
     */
    public boolean aUnCircuit() {
        boolean[] marquagesTaches = new boolean[this.taches.size()];
        List<Tache> tachesATester = new ArrayList<Tache>();
        int indexTest = 0;
        tachesATester.add(this.taches.get(indexTest));
        while (indexTest < this.taches.size() - 1) {
            if (tachesATester.isEmpty()) {
                indexTest++;
                tachesATester.add(this.taches.get(indexTest));
                marquagesTaches = new boolean[this.taches.size()];
            }
            Tache tacheTest = tachesATester.get(0);
            int indexTache = this.taches.indexOf(tacheTest);
            if (marquagesTaches[indexTache]) {
                return true;
            }
            marquagesTaches[indexTache] = true;
            for (int i = 0; i < tacheTest.nombreTachesPrealables(); i++) {
                tachesATester.add(tacheTest.avoirTachePrealable(i));
            }
            tachesATester.remove(0);
        }
        return false;
    }
    
    /** 
     * Algorithme calculant la date au plus tot de chaque tache
     * et la date au plus tot de fin de projet
     */
    public void calculerDateAuPlusTot() {
        List<Tache> tacheTester = new ArrayList<Tache>();
        Tache tacheTest = null;
        double dateAuPlusTot;
        boolean ok;
        for (int i = 0; i < this.taches.size(); i++) {
            if (this.taches.get(i).nombreTachesPrealables() == 0) {
                tacheTester.add(this.taches.get(i));
            }
            this.taches.get(i).setDateAuPlusTot(0.0);
        }
        while (tacheTester.size() < this.taches.size()) {
            ok = false;
            for (int i = 0; !ok && i < this.taches.size(); i++) {
                tacheTest = this.taches.get(i);
                if (!tacheTester.contains(tacheTest)) {
                    ok = true;
                    for (int j = 0; ok && j < tacheTest.nombreTachesPrealables()
                         ; j++) {
                        ok = tacheTester.contains(tacheTest
                                        .avoirTachePrealable(j));
                    }
                }
            }
            for (int i = 0; i < tacheTest.nombreTachesPrealables(); i++) {
                dateAuPlusTot = tacheTest.avoirTachePrealable(i)
                                .getDateAuPlusTot() + tacheTest
                                .avoirTachePrealable(i).getDuree();
                if (dateAuPlusTot > tacheTest.getDateAuPlusTot()) {
                    tacheTest.setDateAuPlusTot(dateAuPlusTot);
                }
            }
            tacheTester.add(tacheTest);
        }
    }
    
    /**
     * Algorithme calculant la date au plus tot de fin de projet
     */
    public void calculerDateAuPlusTotFinDeProjet() {
        double dateAuPlusTotDeFinDeProjet;
        this.dateAuPlusTotProjet = 0.0;
        List<Tache> dernieresTaches = this.dernieresTaches();
        for (int i= 0; i < dernieresTaches.size(); i++) {
            dateAuPlusTotDeFinDeProjet = dernieresTaches.get(i)
                                         .getDateAuPlusTot() 
                                         + dernieresTaches.get(i).getDuree();
            this.dateAuPlusTotProjet = dateAuPlusTotDeFinDeProjet 
                                       > this.dateAuPlusTotProjet
                                       ? dateAuPlusTotDeFinDeProjet
                                       : this.dateAuPlusTotProjet;
        }
    }
    
    /**
     * Algorithme trouvant les dernieres taches du projet
     * @return la liste des dernieres taches du projet
     */
    public List<Tache> dernieresTaches() {
        List<Tache> dernieresTaches = new ArrayList<Tache>();
        boolean ok = true;
        if (this.taches.isEmpty()) {
            throw new IllegalArgumentException("Ce projet n'a pas de tache");
        }
        for (int i = 0; i < this.taches.size(); i++) {
            ok = true;
            for (int j = 0; ok && j < this.taches.size(); j++) {
                ok = !this.taches.get(j).aLaTachePrealable(this.taches.get(i));
            }
            if (ok) {
                dernieresTaches.add(this.taches.get(i));
            }
        }
        return dernieresTaches;
    }
    
    /** 
     * Algorithme calculant la date au plus tard de chaque tache
     * et la date au plus tard de fin de projet
     */
    public void calculerDateAuPlusTard() {
        List<Tache> tacheTester = this.dernieresTaches();
        double dateAuPlusTard;
        Tache tacheTest;
        List<Tache> tachesContraintes = new ArrayList<Tache>();
        boolean ok;
        this.dateAuPlusTardProjet = this.dateAuPlusTotProjet;
        for (int i = 0; i < this.taches.size(); i++) {
            taches.get(i).setDateAuPlusTard(Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < tacheTester.size(); i++) {
            dateAuPlusTard = this.dateAuPlusTardProjet - tacheTester.get(i)
                             .getDuree();
            tacheTester.get(i).setDateAuPlusTard(dateAuPlusTard); 
        }
        while (tacheTester.size() < this.taches.size()) {
            ok = false;
            for (int i = 0; !ok && i < this.taches.size(); i++) {
                tacheTest = this.taches.get(i);
                if (!tacheTester.contains(tacheTest)) {
                    tachesContraintes = new ArrayList<Tache>();
                    for (int j = 0; j < this.taches.size(); j++) {
                        if (this.taches.get(j).aLaTachePrealable(tacheTest)) {
                            tachesContraintes.add(this.taches.get(j));
                        }
                    }
                    if (tacheTester.containsAll(tachesContraintes)) {
                        for (int j = 0; j < tachesContraintes.size(); j++) {
                            dateAuPlusTard = tachesContraintes.get(j)
                                            .getDateAuPlusTard() 
                                            - tacheTest.getDuree();
                            if (dateAuPlusTard < tacheTest.getDateAuPlusTard()){
                                tacheTest.setDateAuPlusTard(dateAuPlusTard);
                            }
                        }
                        tacheTester.add(tacheTest);
                    }
                }
            }
        }
        for (int i = 0; i < this.taches.size(); i++) {
            tacheTest = this.taches.get(i);
            tachesContraintes.clear();
            dateAuPlusTard = Double.POSITIVE_INFINITY;
            for (int j = i + 1; j < this.taches.size(); j++) {
                ok = this.taches.get(j).nombreTachesPrealables()
                     == tacheTest.nombreTachesPrealables();
                for (int k = 0; ok && k < tacheTest.nombreTachesPrealables()
                     ; k++) {
                    ok = this.taches.get(j).aLaTachePrealable(tacheTest
                         .avoirTachePrealable(k));
                }
                if (ok) {
                    tachesContraintes.add(this.taches.get(j));
                }
            }
            tachesContraintes.add(tacheTest);
            for (int j = 0; j < tachesContraintes.size(); j ++) {
                if (dateAuPlusTard > tachesContraintes.get(j)
                    .getDateAuPlusTard()) {
                    dateAuPlusTard = tachesContraintes.get(j)
                                     .getDateAuPlusTard();
                }
            }
            for (int j = 0; j < tachesContraintes.size(); j ++) {
                tachesContraintes.get(j).setDateAuPlusTard(dateAuPlusTard);
            }
        }
    }
    
    /**
     * Sauvegarde dans un fichier les informations du projet
     */
    public void sauvegarder() {
     
        File file = new File("sauv.txt");
        FileWriter fichier;
        
        try {
            fichier = new FileWriter(file);
            fichier.write( this.nom + "\n"
                           + this.description + "\n" 
                           + this.uniteTemps + "\n" 
                           + "\n" + taches.size()
                           + "\n" + tacheToString()
                           + "\n" + tachePrealableToString()
                         );

            fichier.close();
        } catch (IOException erreur) {
            System.err.println("Erreur lors de la sauvegarde");
        }
    }

    /** 
     *  Récupere le nom des taches préalables aux differentes taches du projet
     *  @return toutes les taches préalable
     */
    private String tachePrealableToString() {
        String nomTachesPrealable = "";
        for (int i = 0; i < taches.size(); i++) {
            
            nomTachesPrealable += taches.get(i).getNom() + "\n"
                                  + taches.get(i).getTachesPrealables().size()
                                  + "\n";
            for (int index = 0; 
                 index < taches.get(i).getTachesPrealables().size(); 
                 index++) {
                nomTachesPrealable += taches.get(i).getTachesPrealables()
                                .get(index).getNom() + "\n";
            }
        }
        return nomTachesPrealable;
        
    }
    
    /**
     * Récupere toutes les informations d'une tâche et les mets en forme
     * @return toutes les taches 
     */
    private String tacheToString() {
        String listeTaches = "";
        for (int i = 0; i < taches.size(); i++) {
            listeTaches += taches.get(i).getNom() + "\n" 
                            + taches.get(i).getDescription() + "\n"
                            + taches.get(i).getDuree() + "\n";
        }
        return listeTaches;
    }
    
    /**
     * Importer à partir d'un fichier les informations du projet
     * @return le projet
     */
    public static Projet importer() {

        Projet projet = new Projet("nom","desc","Mois");
        int nbTaches = 0;
        BufferedReader lecteur;

        try {
            lecteur = new BufferedReader(new FileReader("sauv.txt"));
            projet = infoProjet(lecteur);
            nbTaches = Integer.parseInt(lecteur.readLine());
            
            projet = projetTaches(lecteur, projet, nbTaches);
            lecteur.readLine(); // saut de ligne
            projetTachesPrealables(lecteur, projet, nbTaches);

            lecteur.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'importation");
        }
        return projet;
    }
    
    /** 
     * Ajoute au projet les informations (nom, description, dates...)
     * @param lecteur du fichier
     * @return le projet
     */
    public static Projet infoProjet(BufferedReader lecteur) {

        Projet projet = new Projet("nom","desc","Mois");
        String[] projetInfo = new String[3];
        
        try {
            String line = lecteur.readLine();
            /* info du projet */
            for (int index = 0; index < projetInfo.length; index++) {
                projetInfo[index] = line;
                line = lecteur.readLine();
            }
            projet = new Projet (projetInfo[0], projetInfo[1], projetInfo[2]);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'importation");
        }     
        return projet;
    }
    
    /** 
     * Ajoute les taches au projet lors de l'importation
     * @param lecteur du fichier
     * @param projetActuel
     * @param nbTache total du projet
     * @return le projet actualisé
     */
    public static Projet projetTaches(BufferedReader lecteur, 
                                      Projet projetActuel,
                                      int nbTache) {

        Projet projet = projetActuel;
        String[] projetInfo = new String[5];
        
        try {
            for (int i = 0; i < nbTache; i++) {
                Tache tache = new Tache(lecteur.readLine(),lecteur.readLine(),
                                Double.parseDouble(lecteur.readLine()));
                projet.ajouterTache(tache);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'importation");
        } 
        return projet;
    }
    
    /** 
     * Ajoute les taches préalable au projet lors de l'importation
     * @param lecteur du fichier
     * @param projetActuel 
     * @param nbTache total du projet
     * @return le projet actualisé
     */
    public static Projet projetTachesPrealables(BufferedReader lecteur, 
                                                Projet projetActuel,
                                                int nbTache) {
        String nomTache = "";
        String nomTachePrea = "";
        int indexTache = 0;
        int nbTachesPrealable = 0;
        boolean trouve;  
        Projet projet = projetActuel;
        String[] projetInfo = new String[5];
        Tache tache = new Tache("nom","desc",2.0);
        
        try {
            for (int i = 0; i < nbTache; i++) {
                // Tache à ajouter les prealables
                nomTache = lecteur.readLine();
    
                trouve = false;
                for (indexTache = 0; 
                     !trouve && indexTache < projet.taches.size(); indexTache++) {
                    trouve = projet.taches.get(indexTache).getNom().equals(nomTache);
                }
                if (trouve) {
                    tache = projet.taches.get(indexTache - 1);
                }
                                      
                nbTachesPrealable = Integer.parseInt(lecteur.readLine());
                for (int index = 0; index < nbTachesPrealable; index++) {
                    nomTachePrea = lecteur.readLine();
                    trouve = false;
                    for (indexTache = 0; 
                              !trouve && indexTache < projet.taches.size(); indexTache++) {
                        trouve = projet.taches.get(indexTache).getNom().equals(nomTachePrea);
                    }
                    if (trouve) {
                        tache.ajouterTachePrealable(projet.taches.get(indexTache - 1));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'importation");
        }       
        return projet;
    }
    
    
    @Override
    public String toString() {
        String taches = this.taches.size() == 0 ? "Ce projet ne contient pas "
                        + "encore de tache" : "Ses taches sont : \n";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        return "Ce projet est nomme : " + this.nom + "\n" 
        + "Sa description est : " + this.description + "\n"
        + "Son unite de temps est : " + this.uniteTemps + "\n" 
        + taches + "\n"
        + "Sa date au plus tot est : " + this.dateAuPlusTotProjet + "\n" 
        + "Sa date au plus tard est : " + this.dateAuPlusTardProjet;
    }

}