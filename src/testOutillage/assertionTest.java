/*
 * assertionTest.java                                  27 avr. 2022
 * IUT de Rodez, Info1 2021-2022 Groupe 3, pas de copyright 
 */
package testOutillage;

/** TODO commenter la responsabilit� de cette classe
 * @author resto
 *
 */
public class assertionTest {
    
    
    /** TODO commenter le r�le de cette m�thode (SRP)
     * @param attendu
     * @param obtenu
     * @return test d'�galit�
     */
    public static boolean assurerEgalite(String attendu, String obtenu) {
        if(attendu == obtenu) {
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
