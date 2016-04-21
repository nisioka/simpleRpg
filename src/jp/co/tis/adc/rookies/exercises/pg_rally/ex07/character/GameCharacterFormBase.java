package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.ArrayList;
import java.util.List;

/**
 * ゲームキャラクターのクラス。
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public abstract class GameCharacterFormBase implements Cloneable{

    private String name;
    private String jobName;
    private int hitPoint;
    private int maxHitPoint;
    private int attack;
    private double attackMagnification = 1.0;
    private int diffense;
    private double diffenseMagnification = 1.0;
    private Strategy strategy;
    private String lastMessage;

    /**
     * コンストラクタ。HPを設定する。
     *
     * @param hitPoint ヒットポイント
     */
    public GameCharacterFormBase(int hitPoint) {
        this.hitPoint = hitPoint;
        this.maxHitPoint = hitPoint;
    }

    /**
     * ダメージを与える。<br>
     * 負数を引数で与えれば回復となる。<br>
     * 0以上、最大HP以下の範囲である。
     *
     * @param damage ダメージ
     */
    public final void damage(int damage) {
        if (damage < 0){
            hitPoint -= damage;
            if (hitPoint > maxHitPoint) {
                hitPoint = maxHitPoint;
            }
            return;
        }
        if (hitPoint == 0){
            return;
        }
        hitPoint -= damage;
        if (hitPoint <= 0) {
            hitPoint = 0;
            System.out.println(new StringBuilder(this.name).append("は倒れた!"));
        }
    };

    /**
     * 行動する。<br>
     * 行動パターンは{@link Strategy}によって決定される。
     *
     * @param allys 味方キャラクター
     * @param enemies 敵キャラクター
     */
    public abstract void action(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies);

    /**
     * 攻撃する。
     *
     * @param enemy 敵キャラクター
     */
    void attack(GameCharacterFormBase enemy) {
        if (enemy.getHitPoint() == 0){
            return;
        }
        int damage = (int) (this.getAttack() * this.getAttackMagnification()) - (int) (enemy.getDiffense() * enemy.getDiffenseMagnification());
        if (damage <= 0){
            System.out.println("ダメージを与えられなかった。。。");
            return;
        }
        System.out.println(new StringBuilder(enemy.getName()).append("に").append(damage).append("のダメージ!"));
        enemy.damage(damage);
    }

    /**
     * 防御する。
     */
    void guard() {
        this.setDiffenseMagnification(2);
        System.out.println(new StringBuilder(this.getName()).append("は防御している。"));
    }

    /**
     * {@link Strategy}から得られたコマンドを取得する。
     *
     * @param allys 味方キャラクター
     * @param enemies 敵キャラクター
     * @return コマンド
     */
    final String getCommand(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        return strategy.choiceAction(cloneCharacters(allys), cloneCharacters(enemies));
    }

    /**
     * キャラクターをクローンしたリストを作成する。
     *
     * @param characters キャラクター
     * @return ディープコピーオブジェクト
     */
    private List<GameCharacterFormBase> cloneCharacters(List<GameCharacterFormBase> characters) {
        List<GameCharacterFormBase> copyList = new ArrayList<>();
        for ( GameCharacterFormBase character : characters){
            copyList.add(character.clone());
        }
        return copyList;
    }

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
     * @param name セットする name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     *
     * @param jobName セットする jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
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
     * @return lastMessage
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * @param lastMessage セットする lastMessage
     */
    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    /**
     * @return attackMagnification
     */
    public double getAttackMagnification() {
        return attackMagnification;
    }

    /**
     * @param attackMagnification セットする attackMagnification
     */
    public void setAttackMagnification(double attackMagnification) {
        this.attackMagnification = attackMagnification;
    }

    /**
     * @return diffenseMagnification
     */
    public double getDiffenseMagnification() {
        return diffenseMagnification;
    }

    /**
     * @param diffenseMagnification セットする diffenseMagnification
     */
    public void setDiffenseMagnification(double diffenseMagnification) {
        this.diffenseMagnification = diffenseMagnification;
    }

    /**
     * @return maxHitPoint
     */
    int getMaxHitPoint() {
        return maxHitPoint;
    }

    @Override
    public GameCharacterFormBase clone() {
        try {
            return (GameCharacterFormBase) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
