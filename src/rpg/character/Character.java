/**
 *
 */
package rpg.character;

import java.util.List;

/**
 * @author nishi
 *
 */
public abstract class Character {

    private String name;
    private int hitPoint;
    private int maxHitPoint;
    private int attack;
    private int diffense;
    private Strategy strategy;

    public final void damage(int damage){
        hitPoint -= damage;
        if(hitPoint < 0){
            hitPoint = 0;
        }else if(hitPoint > maxHitPoint){
            hitPoint = maxHitPoint;
        }
    };

    public abstract void action(String comand, List<Monster> monsters, List<Player> players);

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return hitPoint
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * @return attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return diffense
     */
    public int getDiffense() {
        return diffense;
    }

    /**
     * @return strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * @param name セットする name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @param attack セットする attack
     */
    void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @param diffense セットする diffense
     */
    void setDiffense(int diffense) {
        this.diffense = diffense;
    }

    /**
     * @param strategy セットする strategy
     */
    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * @param hitPoint セットする hitPoint
     */
    void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    /**
     * @param maxHitPoint セットする maxHitPoint
     */
    void setMaxHitPoint(int maxHitPoint) {
        this.maxHitPoint = maxHitPoint;
    }
}
