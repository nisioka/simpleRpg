package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.CommandConstants;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * 僧侶の行動パターン。<br>
 * <ol>
 * <li>魔王が溜めていたら防御する。</li>
 * <li>味方がダメージを受けていたら回復魔法。</li>
 * <li>攻撃によりダメージを与えられるなら攻撃。</li>
 * <li>それ以外は防御。</li>
 * </ol>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class MonkStrategyImpl implements Strategy {

    @Override
    public String choiceAction(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() == 0) {
                return CommandConstants.RESUSCITATION;
            }
        }
        for (GameCharacterFormBase enemy : enemies) {
            if (CommandConstants.ENERGY_CHARGE.equals(enemy.getLastAction())) {
                return CommandConstants.GUARD;
            }
        }
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() < ally.getMaxHitPoint()) {
                return CommandConstants.RECOVERY;
            }
        }
        for (GameCharacterFormBase enemy : enemies) {
            if (thisCharacter.getAttack() > enemy.getDiffense()) {
                return CommandConstants.ATTACK;
            }
        }
        return CommandConstants.GUARD;
    }
}
