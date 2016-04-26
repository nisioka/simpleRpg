/**
 *
 */
package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Brave;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Monk;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Wizard;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.BraveStrategyImpl2;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.MonkStrategyImpl;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.WizardStrategyImpl2;

/**
 * ゲームを制御する。
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class GameMaster {

    /**
     * ゲームを制御する。
     *
     * @param args コマンドライン引数
     * @throws IOException 想定外の例外
     */
    public static void main(String[] args) throws IOException {

        List<GameCharacterFormBase> players = new ArrayList<>();

        players.add(new Wizard("はなこ", new WizardStrategyImpl2()));

        players.add(new Brave("たけし", new BraveStrategyImpl2()));

        players.add(new Monk("たかし", new MonkStrategyImpl()));

        GameUtil.gameStart(players);
    }

}
