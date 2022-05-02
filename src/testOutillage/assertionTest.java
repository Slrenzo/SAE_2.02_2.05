/*
 * assertionTest.java                                  27 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package testOutillage;

/** TODO commenter la responsabilité de cette classe
 * @author resto
 *
 */
public class assertionTest {
    
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param attendu
     * @param obtenu
     * @return test d'égalité
     */
    public static boolean assurerEgalite(String attendu, String obtenu) {
        return attendu.equals(obtenu);
    }
    
    /** TODO commenter le rôle de cette méthode (SRP)
     * @param attendu
     * @param obtenu
     * @return test d'égalité
     */
    public static boolean assurerEgaliteDouble(double attendu, double obtenu) {
        if(attendu == obtenu) {
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
