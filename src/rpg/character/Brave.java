/**
 *
 */
package rpg.character;

import java.util.List;

/**
 * @author nishi
 *
 */
public final class Brave extends Player {

    public static String JOB_NAME = "勇者";
    public static int DEFAULT_ATTACK = 100;
    public static int DEFAULT_DIFFENSE = 50;

    public final void action(String comand, List<Monster> monsters, List<Player> players){
        System.out.println(new StringBuilder().append(this.getName()).append("の").append(comand).append("!"));
        switch (comand) {
        case Player.ATTACK:
            attack(monsters.get(0));
            break;

        default:
            System.out.println("しかし上手く決まらなかった。");
        }
    }

    /**
     * @param monster
     * @return
     */
    private void attack(Character monster) {
        int damage = this.getAttack() - monster.getDiffense();
        monster.damage(damage);
        System.out.println(new StringBuilder().append(monster.getName()).append("に").append(damage).append("のダメージ!"));
    }

    /**
     *
     */
    public Brave(String name, Strategy strategy) {
        super(100);
        setName(name);
        setJobName(JOB_NAME);
        setAttack(DEFAULT_ATTACK);
        setDiffense(DEFAULT_DIFFENSE);
        setStrategy(strategy);
    }
}
