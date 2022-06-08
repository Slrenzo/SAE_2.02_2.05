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

/** 
 * Un projet est un ensemble de tâches que l'on doit ordonnée pour arriver a
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

    /* Listes des tâches à effectuer dans pour ce projet */
    private List<Tache> taches;
    
    /* Listes des taches critiques de ce projet */
    private List<Tache> tachesCritiques;

    /* Unité de temps utilisé pour parler des durées des tâches */
    private String uniteTemps;

    /* Date au plus tôt du projet */
    private double dateAuPlusTotProjet;

    /* Date au plus tard du projet */
    private double dateAuPlusTardProjet;    


    /**
     * Tableau d'unités de temps possibles
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
     * et qui possède une unité de temps
     * @param nom le nom du projet
     * @param description description du projet
     * @param uniteTemps l'unité de temps défini pour ce projet
     * @throws IllegalArgumentException 
     *         <ul>
     *             <li>si le nom est vide</li>
     *             <li>si la description est vide</li>
     *             <li>si l'unité de temps est invalide</li>
     *         </ul>
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
            throw new IllegalArgumentException("L'unité de temps est invalide");
        }
        this.nom = nom;
        this.description = description;
        this.uniteTemps = uniteTemps;
        this.taches = new ArrayList<Tache>();
        this.tachesCritiques = new ArrayList<Tache>();
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
     * @return taches les tâches de ce projet
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
     * Ajoute une tâche a ce projet
     * @param tacheAAjouter la tâche a ajouter au projet
     * @throws IllegalArgumentException si la tâche est déjà dans le projet
     */
    public void ajouterTache(Tache tacheAAjouter) {
        for (int i = 0; i < this.taches.size(); i++) {
            if (tacheAAjouter.getNom().equals(this.taches.get(i).getNom())) {
                throw new IllegalArgumentException("Cette tâche est déjà "
                                                   + "présente");
            }
        }
        this.taches.add(tacheAAjouter);
    }

    /** 
     * Enlève une tâche a ce projet
     * @param tacheAEnlever la tâche a enlever au projet
     * @throws IllegalArgumentException si la tâche n'est pas dans le projet
     */
    public void enleverTache(Tache tacheAEnlever) {
        boolean estPresent = false;
        for (int i = 0; !estPresent && i < this.taches.size(); i++) {
            estPresent = this.taches.get(i).equals(tacheAEnlever);
        }
        if (!estPresent) {
            throw new IllegalArgumentException("Cette tâche n'est pas dans "
                                               + "le projet");
        }
        if (tacheAEnlever.nombreTachesSuccesseurs() != 0) {
            throw new IllegalArgumentException("Cette tâche est une contrainte"
                                               + " d'une autre tâche, veuillez "
                                               + "enlever ces containtes avant "
                                               + "de la supprimer.");
        }
        this.taches.remove(tacheAEnlever);
    }

    /** 
     * Teste si le projet possède un circuit ayant la tâche tacheModifiee
     * @param tacheModifiee dernière tâche modifier
     * @return true s'il y a un circuit ayant la tâche tacheModifiee
     */
    public boolean aUnCircuit(Tache tacheModifiee) {
        List<Tache> tacheATester = new ArrayList<Tache>();
        for (int i = 0; i < tacheModifiee.nombreTachesPrealables(); i ++) {
            tacheATester.add(tacheModifiee.avoirTachePrealable(i));
        }
        while (!tacheATester.isEmpty()) {
            if (tacheATester.get(0).equals(tacheModifiee)) {
                return true;
            }
            for (int i = 0; i < tacheATester.get(0).nombreTachesPrealables()
                 ; i++) {
                tacheATester.add(tacheATester.get(0).avoirTachePrealable(i));
            }
            tacheATester.remove(0);
        }
        return false;
    }
    
    /** 
     * Détermine les successeurs de chaque tâche du projet
     */
    public void determinerLesSuccesseurs() {
        List<Tache> successeurs;
        for (int i = 0; i < taches.size(); i++) {
            successeurs = new ArrayList<Tache>();
            for (int j = 0; j < taches.size(); j++) {
                if (taches.get(j).aLaTachePrealable(taches.get(i))) {
                    successeurs.add(taches.get(j));
                }
            }
            taches.get(i).setTachesSuccesseurs(successeurs);
        }
    }
    
    
    /**
     * Algorithme trouvant les premières tâches du projet
     * @return la liste des premières tâches du projet
     */
    public List<Tache> premieresTaches() {
        List<Tache> taches = new ArrayList<Tache>();
        for (int i = 0; i < this.taches.size(); i++) {
            if (this.taches.get(i).nombreTachesPrealables() == 0) {
                taches.add(this.taches.get(i));
            }
            this.taches.get(i).setDateAuPlusTot(0.0);
        }
        return taches;
    }
    
    /** 
     * Algorithme calculant la date au plus tôt de chaque tâche
     * et la date au plus tôt de fin de projet
     */
    public void calculerDateAuPlusTot() {
        List<Tache> tachesTester = this.premieresTaches();
        double dateAuPlusTot;
        for (int i = 0; i < tachesTester.size(); i++) {
            for (int j = 0; j < tachesTester.get(i).nombreTachesSuccesseurs()
                 ; j++) {
                dateAuPlusTot = tachesTester.get(i).getDateAuPlusTot() 
                                + tachesTester.get(i).getDuree();
                if (dateAuPlusTot > tachesTester.get(i).avoirTacheSuccesseur(j)
                    .getDateAuPlusTot()) {
                    tachesTester.get(i).avoirTacheSuccesseur(j)
                                .setDateAuPlusTot(dateAuPlusTot);
                }
                tachesTester.add(tachesTester.get(i).avoirTacheSuccesseur(j));
            }
        }
    }
    
    /**
     * Algorithme calculant la date au plus tôt de fin de projet
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
     * Algorithme trouvant les dernières tâches du projet
     * @return la liste des dernières tâches du projet
     */
    public List<Tache> dernieresTaches() {
        List<Tache> dernieresTaches = new ArrayList<Tache>();
        if (this.taches.isEmpty()) {
            throw new IllegalArgumentException("Ce projet n'a pas de tache");
        }
        for (int i = 0; i < this.taches.size(); i++) {
            if (this.taches.get(i).nombreTachesSuccesseurs() == 0) {
                dernieresTaches.add(this.taches.get(i));
            }
            this.taches.get(i).setDateAuPlusTard(Double.POSITIVE_INFINITY);
        }
        return dernieresTaches;
    }
    
    /** 
     * Algorithme calculant la date au plus tard de chaque tâche
     * et la date au plus tard de fin de projet
     */
    public void calculerDateAuPlusTard() {
        this.dateAuPlusTardProjet = this.dateAuPlusTotProjet;
        List<Tache> tachesATester = this.dernieresTaches();
        Tache tacheTest;
        for (int i = 0; i < tachesATester.size(); i++) {
            tachesATester.get(i).setDateAuPlusTard(dateAuPlusTardProjet 
                                - tachesATester.get(i).getDuree());
        }
        for (int i = 0; i < tachesATester.size(); i++) {
            List<Tache> tachesPrealables = new ArrayList<Tache>();
            for (int j = 0; j < tachesATester.get(i).nombreTachesPrealables()
                 ; j++) {
                tacheTest = tachesATester.get(i).avoirTachePrealable(j);
                tachesATester.add(tacheTest);
                tachesPrealables.add(tacheTest);
            }
            if (tachesATester.get(i).nombreTachesPrealables() == 0) {
                tachesATester.get(i).setDateAuPlusTard(0.0);
            } else {
                calculerDateAuPLusTardTache(tachesPrealables);
            }
        }
    }
    
    /** 
     * Calcule les dates au plus tard des successeurs et de la tâche tacheTest
     * @param tacheTest tâche dont l'on calcul la date au plus tard des
     *                  successeurs
     */
    private static void calculerDateAuPLusTardTache(List<Tache> tachesPrealables) {
        double dateAuPlusTard = Double.POSITIVE_INFINITY;
        boolean ok;
        List<Tache> tachesAModifier = new ArrayList<Tache>();
        for (int i = 0; i < tachesPrealables.get(0).nombreTachesSuccesseurs()
             ; i++) {
            Tache tacheTest = tachesPrealables.get(0).avoirTacheSuccesseur(i);
            ok = tacheTest.nombreTachesPrealables()
                 == tachesPrealables.size();
            for (int j = 0; ok && j < tachesPrealables.size(); j++) {
                ok = tacheTest.aLaTachePrealable(tachesPrealables.get(j));
            }
            if (ok) {
                tachesAModifier.add(tacheTest);
                if (tacheTest.getDateAuPlusTard()< dateAuPlusTard) {
                    dateAuPlusTard = tacheTest.getDateAuPlusTard();
                }
           }
        }
        for (int i = 0; i < tachesAModifier.size(); i++) {
            tachesAModifier.get(i).setDateAuPlusTard(dateAuPlusTard);
        }
        for (int i = 0; i < tachesPrealables.size(); i++) {
            if (tachesPrealables.get(i).getDateAuPlusTard()
                > dateAuPlusTard - tachesPrealables.get(i).getDuree()) {
                tachesPrealables.get(i)
                                .setDateAuPlusTard(dateAuPlusTard 
                                - tachesPrealables.get(i).getDuree());
            }
        }
    }
    
    /** 
     * Détermine les tâches critiques de ce projet
     */
    public void determinerTachesCritiques() {
        this.tachesCritiques.clear();
         Tache tacheTest;
         for (int i = 0; i < this.taches.size(); i++) {
             tacheTest = this.taches.get(i);
             if (tacheTest.estTacheCritique()){
                 this.tachesCritiques.add(tacheTest);
             }
         }
    }
    
    /** 
     * Détermine les marges de chaque tâche de ce projet 
     */
    public void determinerMarges() {
        for (int i = 0; i < taches.size(); i++) {
            if (taches.get(i).estTacheCritique()) {
                taches.get(i).setMargeLibre(0.0);
                taches.get(i).setMargeTotale(0.0);
            } else {
                taches.get(i).calculMarges();
            }
        }
    }
    
    /**
     * Lance les méthodes d'ordonnancement
     */
    public void ordonnancement() {
        this.determinerLesSuccesseurs();
        this.calculerDateAuPlusTot();
        this.calculerDateAuPlusTotFinDeProjet();
        this.calculerDateAuPlusTard();
        this.determinerTachesCritiques();
        this.determinerMarges();
    }

    /**
     * Sauvegarde dans un fichier texte les informations du projet
     * @param chemin chemin du fichier
     */
    public void sauvegarder(String chemin) {
     
        File file = new File(chemin + ".txt");
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
            System.out.println("Erreur lors de la sauvegarde"
                               + " (erreur dans le chemin)");
        }
    }

    /** 
     *  Récupère le nom des tâches préalables aux différentes tâches du projet
     *  @return toutes les tâches préalables
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
     * Récupère toutes les informations d'une tâche et les mets en forme
     * @return toutes les tâches 
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
     * Importer à partir d'un fichier texte les informations du projet
     * @param chemin chemin du fichier
     * @return le projet
     */
    public static Projet importer(String chemin) {

        Projet projet = new Projet("nom","desc","Mois");
        int nbTaches = 0;
        BufferedReader lecteur;

        try {
            lecteur = new BufferedReader(new FileReader(chemin));
            projet = infoProjet(lecteur);
            nbTaches = Integer.parseInt(lecteur.readLine());
            
            projet = projetTaches(lecteur, projet, nbTaches);
            lecteur.readLine(); // saut de ligne
            projetTachesPrealables(lecteur, projet, nbTaches);

            lecteur.close();
        } catch (IOException e) {
        }
        return projet;
    }
    
    /** 
     * Ajoute au projet les informations (nom, description, dates...)
     * @param lecteur lecteur du fichier
     * @return le projet
     */
    public static Projet infoProjet(BufferedReader lecteur) {

        Projet projet = new Projet("nom","desc","Mois");
        String[] projetInfo = new String[3];
        
        try {
            String line = lecteur.readLine();
            /* informations du projet */
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
     * Ajoute les tâches au projet lors de l'importation
     * @param lecteur lecteur du fichier
     * @param projetActuel projet en cours
     * @param nbTache le nombre de tâches total du projet
     * @return le projet actualisé
     */
    public static Projet projetTaches(BufferedReader lecteur, 
                                      Projet projetActuel,
                                      int nbTache) {

        Projet projet = projetActuel;
        
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
     * Ajoute les tâches préalable au projet lors de l'importation
     * @param lecteur lecteur du fichier
     * @param projetActuel projet en cours
     * @param nbTache le nombre de tâches total du projet
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
        Tache tache = new Tache("nom","desc",2.0);
        
        try {
            for (int i = 0; i < nbTache; i++) {
                // Tâche à ajouter les préalables
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
    /**
     * Renvoi le nombre de tâches d'un projet donnée
     * @param projet projet dont on veut connaître le nombre de taches
     * @return le nombre de tache du projet
     */
    public static int nombreTaches(Projet projet) {
        return projet.taches.size();
    }
    
    /**
     * Renvoi une tâche à un index donné
     * @param index index dans la liste des tâches du projet
     * @return la tache de l'index donné
     */
    public Tache avoirTache(int index) {
        return taches.get(index);
    }
    
    @Override
    public String toString() {
        String taches = this.taches.size() == 0 ? "Ce projet ne contient pas "
                        + "encore de tâche" : "Ses tâches sont : \n";
        String tachesCritiques = this.tachesCritiques.size() == 0 ? "Ce projet "
                                 + "ne contient pas encore de tâche critique" 
                                 : "Ses tâches critiques sont : \n";
        for (int i = 0; i < this.taches.size(); i++) {
            taches += this.taches.get(i) + "\n";
        }
        for (int i = 0; i < this.tachesCritiques.size(); i++) {
            tachesCritiques += this.tachesCritiques.get(i).getNom() + "\n";
        }
        return "Ce projet est nommé : " + this.nom + "\n" 
                + "Sa description est : " + this.description + "\n"
                + "Son unité de temps est : " + this.uniteTemps + "\n" 
                + taches + "\n"
                + "Sa date au plus tôt est : " + this.dateAuPlusTotProjet + "\n" 
                + "Sa date au plus tard est : " 
                + this.dateAuPlusTardProjet + "\n"
                + tachesCritiques;
    }

}