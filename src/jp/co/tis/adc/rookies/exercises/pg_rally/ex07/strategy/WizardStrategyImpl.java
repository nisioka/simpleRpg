package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.CommandConstants;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * 魔法使いの行動パターン。<br>
 * <ol>
 * <li>魔王が溜めていたら防御する。</li>
 * <li>それ以外は攻撃力アップ魔法。</li>
 * </ol>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class WizardStrategyImpl implements Strategy {

    @Override
    public String choiceAction(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        for (GameCharacterFormBase enemy : enemies) {
            if (CommandConstants.ENERGY_CHARGE.equals(enemy.getLastAction())) {
                return CommandConstants.GUARD;
            }
        }
        return CommandConstants.ATTACK_UP;
    }
}
