package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.CommandConstants;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * 憤怒の大魔王の行動パターン。<br>
 * 以下を繰り返す。<br>
 * <ol>
 * <li>狂攻撃。</li>
 * <li>エネルギーチャージ(次のターンにエネルギー解放を発動)。</li>
 * <li>エネルギー解放。攻撃力1.5倍で全体攻撃。</li>
 * </ol>
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class BossOfRageStrategyImpl implements Strategy {

    private int turnCount = 1;

    @Override
    public String choiceAction(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        switch (turnCount++ % 3) {
        case 1:
            return CommandConstants.DETH_ATTACK;
        case 2:
            return CommandConstants.ENERGY_CHARGE;
        default:
            return CommandConstants.RELEASE_OF_THE_ENERGY;
        }
    }
}
