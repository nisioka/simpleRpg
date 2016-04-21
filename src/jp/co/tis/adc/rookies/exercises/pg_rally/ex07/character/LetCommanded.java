package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 命令させろ。<br>
 * 標準入力に従い行動する。
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class LetCommanded implements Strategy {

    @Override
    public String choiceAction(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        try {
            System.out.println("コマンドを入力して下さい。");
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
