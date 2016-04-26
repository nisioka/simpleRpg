package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;

/**
 * キャラクターの作戦を決めるインターフェース。
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public interface Strategy {

    /**
     * キャラクターの行動を決める。
     *
     * @param thisCharacter 自身
     * @param allys 味方キャラクター
     * @param enemies 相手キャラクター
     * @return コマンド
     */
    public String choiceAction(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies);
}
