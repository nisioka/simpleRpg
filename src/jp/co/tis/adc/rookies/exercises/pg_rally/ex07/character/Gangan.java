package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.List;

/**
 * ガンガンいこうぜ。<br>
 * 攻撃しかしない。
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class Gangan implements Strategy {

    @Override
    public String choiceAction(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        return CommandConstants.ATTACK;
    }
}
