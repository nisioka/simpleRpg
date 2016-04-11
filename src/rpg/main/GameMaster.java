/**
 *
 */
package rpg.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rpg.character.Boss;
import rpg.character.Brave;
import rpg.character.Character;
import rpg.character.Monster;
import rpg.character.Player;
import rpg.character.Strategy;
import rpg.strategy.sample.Gangan;

/**
 * @author nishi
 *
 */
public class GameMaster {

    public static void main(String[] args) throws IOException {

        List<Player> players = new ArrayList<>();

        Strategy gangan = new Gangan();
        players.add(new Brave("たけし", gangan));

        players.add(new Brave("はなこ", gangan));

        players.add(new Brave("エバンス", gangan));

        play(players);
    }

    /**
     * @param players
     * @throws IOException
     */
    private static void play(List<Player> players) throws IOException {

        if (players.size() > 3) {
            System.err.println("プレイヤーは3人にしてください。");
        };

        List<Monster> boss = new ArrayList<>();
        boss.add(new Boss("魔王", new Gangan()));

        battle(players, boss);
    }

    /**
     * @param players
     * @param boss
     * @throws IOException
     */
    private static void battle(List<Player> players, List<Monster> boss) throws IOException {
        while (true) {
            displayStatus(boss.get(0), players);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            for (Player player : players) {
                player.action(player.getStrategy().choiceAction(), boss, players);
                if (!aliveMonster(boss)) {
                    return;
                }
                new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
            for (Monster monster : boss) {
                monster.action(monster.getStrategy().choiceAction(), boss, players);
                if (!alivePlayer(players)) {
                    return;
                }
                new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
        }
    }

    /**
     * @param <E>
     * @return
     */
    private static boolean alivePlayer(List<Player> players) {
        for (Character character : players) {
            if (character.getHitPoint() > 0) {
                return true;
            }
        }
        System.out.println("全滅しました。GAME OVER");
        return false;
    }

    /**
     * @param <E>
     * @return
     */
    private static boolean aliveMonster(List<Monster> Monsters) {
        for (Character character : Monsters) {
            if (character.getHitPoint() > 0) {
                return true;
            }
        }
        System.out.println(Monsters.get(0).getName() + "を倒した!");
        return false;
    }

    /**
     * @param players
     */
    private static void displayStatus(Monster monster, List<Player> players) {
        String spliter = "---------------------------------------------";
        System.out.println(spliter);
        System.out.println(monster.getName());
        System.out.println("HP: " + monster.getHitPoint());
        System.out.println("攻撃: " + monster.getAttack());
        System.out.println("防御: " + monster.getDiffense());
        System.out.println(spliter);
        System.out.printf("%-20.20s\t%-20.20s\t%-20.20s\n", players.get(0).getName(), players.get(1).getName(),
                players.get(2).getName());
        System.out.printf("HP:%5.5s\tHP:%5.5s\tHP:%5.5s\n", players.get(0).getHitPoint(), players.get(1).getHitPoint(),
                players.get(2).getHitPoint());
        System.out.printf("攻撃:%4.4s\t攻撃:%4.4s\t攻撃:%4.4s\n", players.get(0).getAttack(), players.get(1).getAttack(),
                players.get(2).getAttack());
        System.out.printf("防御:%4.4s\t防御:%4.4s\t防御:%4.4s\n", players.get(0).getDiffense(), players.get(1).getDiffense(),
                players.get(2).getDiffense());
        System.out.println(spliter);
    }

}
