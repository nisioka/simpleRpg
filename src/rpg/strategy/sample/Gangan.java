/**
 *
 */
package rpg.strategy.sample;

import rpg.character.Player;
import rpg.character.Strategy;

/**
 * @author nishi
 *
 */
public class Gangan implements Strategy {

    /* (非 Javadoc)
     * @see rpg.character.strategy#choiceAction()
     */
    @Override
    public String choiceAction() {
        return Player.ATTACK;
    }

}
