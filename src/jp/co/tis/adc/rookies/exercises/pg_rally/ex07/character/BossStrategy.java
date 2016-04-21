package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.List;

/**
 * ボスの行動パターン。<br>
 * <ul>
 * <li>1/4の確率で、何もしない。</li>
 * <li>通常攻撃</li>
 * <li>力を溜める(次のターンに力の解放を発動)。</li>
 * <li>力の解放。攻撃力1.5倍で全体攻撃。</li>
 * </ul>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class BossStrategy implements Strategy {

    @Override
    public String choiceAction(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        switch ((int) (Math.random() * 4)) {
        case 0:
            return CommandConstants.ATTACK;
        case 1:
        case 2:
            return CommandConstants.ACCUMULATING_FORCE;
        default:
            return CommandConstants.SMILE;
        }
    }
}
