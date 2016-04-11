/**
 *
 */
package rpg.character;

import java.util.List;

/**
 * @author nishi
 *
 */
public final class Boss extends Monster {

    public static int HIT_POINT = 100;
    public static int DEFAULT_ATTACK = 100;
    public static int DEFAULT_DIFFENSE = 50;
    /* (非 Javadoc)
     * @see rpg.character.Monster#attack(rpg.character.Player)
     */
    @Override
    public void action(String comand, List<Monster> monsters, List<Player> players) {

    }

    /**
    *
    */
   public Boss(String name, Strategy strategy) {
       setName(name);
       setHitPoint(HIT_POINT);
       setMaxHitPoint(HIT_POINT);
       setAttack(DEFAULT_ATTACK);
       setDiffense(DEFAULT_DIFFENSE);
       setStrategy(strategy);
   }
}
