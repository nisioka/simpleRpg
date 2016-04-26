package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.Boss;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character.GameCharacterFormBase;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.BossOfRageStrategyImpl;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.BossOfSlyStrategyImpl;
import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.BossStrategyImpl;

/**
 * ゲームをプレイするためのユーティリティ。
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public class GameUtil {

    /**
     * ゲームを開始する。
     *
     * @param players ゲームプレイヤー
     * @throws IOException 想定外の例外
     */
    public static void gameStart(List<GameCharacterFormBase> players) throws IOException {
        if (players.size() > 3) {
            System.err.println("プレイヤーは3人以下にしてください。");
        }

        List<GameCharacterFormBase> boss = new ArrayList<>();
        boss.add(new Boss("魔王", new BossStrategyImpl()));

        battle(players, boss);

        // 全滅していたら終了。
        if (!alivePlayer(players)) {
            System.exit(0);
        }

        System.out.println("\nしかし、魔王は真の姿を現して復活した！");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println("憤怒の大魔王が現れた！");
        System.out.println("狡猾の大魔王が現れた！");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
        List<GameCharacterFormBase> lastBoss = new ArrayList<>();
        lastBoss.add(new Boss("憤怒の大魔王", new BossOfRageStrategyImpl()));
        lastBoss.add(new Boss("狡猾の大魔王", new BossOfSlyStrategyImpl()));

        battle(players, lastBoss);

        // 全滅していたらヒントを出して終了。
        if (!alivePlayer(players)) {
            System.out.println("\nヒント：課題のエクセルに非表示のシートがあります。そこに真のボスの情報が載っています。");
            System.exit(0);
        }
        System.out.println("クリアおめでとう！これで世界に平和が戻りました！");
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
                    System.out.println("\n【敵を倒した!】");
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
                    System.out.println("\n全滅しました。【GAME OVER】");
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
