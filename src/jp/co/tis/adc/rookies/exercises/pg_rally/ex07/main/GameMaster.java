/**
 *
 */
package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Boss;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.BossStrategy;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Brave;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.LetCommanded;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Monk;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Strategy;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Wizard;

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

        Strategy letCommand = new LetCommanded();
        players.add(new Brave("たけし", letCommand));

        players.add(new Wizard("はなこ", letCommand));

        players.add(new Monk("たかし", letCommand));

        if (players.size() > 3) {
            System.err.println("プレイヤーは3人以下にしてください。");
        }

        List<GameCharacterFormBase> boss = new ArrayList<>();
        boss.add(new Boss("魔王", new BossStrategy()));

        battle(players, boss);
    }

    /**
     * 敵味方のどちらかが全滅するまで戦う。
     *
     * @param players プレイヤー
     * @param enemies 敵キャラクター
     * @throws IOException 想定外の例外
     */
    private static void battle(List<GameCharacterFormBase> players, List<GameCharacterFormBase> enemies) throws IOException {
        while (true) {
            refresh(players);
            displayStatus(players, enemies);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            for (GameCharacterFormBase player : players) {
                if (player.getHitPoint() == 0){
                    continue;
                }
                player.action(players, enemies);
                if (!aliveMonster(enemies)) {
                    return;
                }
                new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
            refresh(enemies);
            for (GameCharacterFormBase monster : enemies) {
                if (monster.getHitPoint() == 0){
                    continue;
                }
                monster.action(enemies, players);
                if (!alivePlayer(players)) {
                    return;
                }
                new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
        }

    }

    /**
     * 攻撃/防御倍率を元に戻す。
     *
     * @param characters プレイヤー
     */
    private static void refresh(List<GameCharacterFormBase> characters) {
        final double defaultMagnification = 1.0;
        for (GameCharacterFormBase player : characters) {
            player.setAttackMagnification(defaultMagnification);
            player.setDiffenseMagnification(defaultMagnification);
        }
    }

    /**
     * プレイヤーの生存確認。
     *
     * @param players プレイヤー
     * @return 生存:true、全滅:false
     */
    private static boolean alivePlayer(List<GameCharacterFormBase> players) {
        for (GameCharacterFormBase character : players) {
            if (character.getHitPoint() > 0) {
                return true;
            }
        }
        System.out.println("全滅しました。【GAME OVER】");
        return false;
    }

    /**
     * 敵の生存確認。
     *
     * @param enemies 敵キャラクター
     * @return 生存:true、全滅:false
     */
    private static boolean aliveMonster(List<GameCharacterFormBase> enemies) {
        for (GameCharacterFormBase character : enemies) {
            if (character.getHitPoint() > 0) {
                return true;
            }
        }
        System.out.println("★敵を倒した!★");
        return false;
    }

    /**
     * ステータス表示。
     *
     * @param players プレイヤー
     * @param enemies 敵キャラクター
     */
    private static void displayStatus(List<GameCharacterFormBase> players, List<GameCharacterFormBase> enemies) {
        String spliter = "---------------------------------------------";
        System.out.println(spliter);
        for (GameCharacterFormBase enemy : enemies) {
            characterStatus(enemy);
        }
        System.out.println(spliter);
        for (GameCharacterFormBase player : players) {
            characterStatus(player);
        }
        System.out.println(spliter);
    }

    /**
     * キャラクターのステータスを表示する。
     *
     * @param character キャラクター
     */
    private static void characterStatus(GameCharacterFormBase character) {
        System.out.print(character.getName());
        if (character.getJobName() != null){
            System.out.print(" " + character.getJobName());
        }
        System.out.print(" HP: " + character.getHitPoint());
        System.out.print(" 攻撃: " + character.getAttack());
        System.out.println(" 防御: " + character.getDiffense());
    }

}
