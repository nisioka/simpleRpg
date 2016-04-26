package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.CommandConstants;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * 魔法使いの行動パターン。(裏ボス対策版)<br>
 * <ol>
 * <li>魔王が溜めていたら防御する。</li>
 * <li>全員の防御力が200を超えるまで防御力アップ魔法。</li>
 * <li>それ以外は攻撃力アップ魔法。</li>
 * </ol>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class WizardStrategyImpl2 implements Strategy {

    @Override
    public String choiceAction(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        for (GameCharacterFormBase enemy : enemies) {
            if (CommandConstants.ENERGY_CHARGE.equals(enemy.getLastAction())) {
                return CommandConstants.GUARD;
            }
        }
        if (!diffenceCheck(allys)) {
            return CommandConstants.DIFFENCE_UP;
        }
        return CommandConstants.ATTACK_UP;
    }

    /**
     * 味方全員の防御力が200を超えているか判定。
     *
     * @param allys 味方キャラクター
     * @return 判定結果
     */
    private boolean diffenceCheck(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getDiffense() < 150) {
                return false;
            }
        }
        return true;
    }
}
