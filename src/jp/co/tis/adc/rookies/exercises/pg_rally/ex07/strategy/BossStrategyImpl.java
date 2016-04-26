package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.CommandConstants;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * 魔王の行動パターン。<br>
 * <ul>
 * <li>1/6の確率で、何もしない。</li>
 * <li>1/3の確率で、通常攻撃</li>
 * <li>1/6の確率で、攻撃力2倍の狂攻撃。</li>
 * <li>1/3の確率で、エネルギーチャージ(次のターンにエネルギー解放を発動)。</li>
 * <li>エネルギー解放。攻撃力1.5倍で全体攻撃。</li>
 * </ul>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class BossStrategyImpl implements Strategy {

    @Override
    public String choiceAction(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        switch ((int) (Math.random() * 6)) {
        case 0:
        case 1:
            return CommandConstants.ATTACK;
        case 2:
            return CommandConstants.DETH_ATTACK;
        case 3:
        case 4:
            return CommandConstants.ENERGY_CHARGE;
        default:
            return CommandConstants.SMILE;
        }
    }
}
