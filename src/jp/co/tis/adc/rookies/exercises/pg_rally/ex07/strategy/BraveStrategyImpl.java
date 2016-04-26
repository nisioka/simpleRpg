package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.CommandConstants;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * 勇者の行動パターン。<br>
 * <ol>
 * <li>誰かが死んでいたら蘇生する。</li>
 * <li>魔王が溜めていたら防御する。</li>
 * <li>それ以外は攻撃。</li>
 * </ol>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class BraveStrategyImpl implements Strategy {

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
        return CommandConstants.ATTACK;
    }
}
