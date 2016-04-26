package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.Strategy;

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
    private String lastAction;

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

    @Override
    public GameCharacterFormBase clone() {
        try {
            return (GameCharacterFormBase) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

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
     * 敵チームの誰か1体に攻撃。死んでいるキャラクターには攻撃しない。
     *
     * @param enemies 敵
     */
    void singleAttack(List<GameCharacterFormBase> enemies) {
        Collections.shuffle(enemies);
        for (GameCharacterFormBase enemy : enemies) {
            if (enemy.getHitPoint() == 0) {
                continue;
            }
            attack(enemy);
            return;
        }
    }

    /**
     * 防御する。1ターンの間防御力が2倍になる。
     */
    void guard() {
        this.setDiffenseMagnification(2);
        System.out.println(new StringBuilder(this.getName()).append("は防御している。"));
    }

    /**
     * {@link Strategy}から得られたコマンドを取得する。
     *
     * @param thisCharacter 自身
     * @param allys 味方キャラクター
     * @param enemies 敵キャラクター
     * @return コマンド
     */
    final String getCommand(GameCharacterFormBase thisCharacter,
            List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        return strategy.choiceAction(thisCharacter, cloneCharacters(allys), cloneCharacters(enemies));
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
     * 名前をゲットする。
     *
     * @return name 名前
     */
    public String getName() {
        return name;
    }

    /**
     * 名前をセットする。
     *
     * @param name セットする name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * 職業名をゲットする。
     *
     * @return jobName 職業名
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 職業名をセットする。
     *
     * @param jobName セットする jobName
     */
    void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * ヒットポイントをゲットする。
     *
     * @return hitPoint ヒットポイント
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * 最大ヒットポイントをゲットする。
     *
     * @return maxHitPoint 最大ヒットポイント
     */
    public int getMaxHitPoint() {
        return maxHitPoint;
    }

    /**
     * 攻撃力をゲットする。
     *
     * @return attack 攻撃力
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 攻撃力をセットする。
     *
     * @param attack セットする attack
     */
    void setAttack(int attack) {
        this.attack = attack;
    }


    /**
     * 攻撃倍率をゲットする。
     *
     * @return attackMagnification 攻撃倍率
     */
    public double getAttackMagnification() {
        return attackMagnification;
    }

    /**
     * 攻撃倍率をセットする。
     *
     * @param attackMagnification セットする attackMagnification
     */
    public void setAttackMagnification(double attackMagnification) {
        this.attackMagnification = attackMagnification;
    }

    /**
     * 防御力をゲットする。
     *
     * @return diffense 防御力
     */
    public int getDiffense() {
        return diffense;
    }

    /**
     * 防御力をセットする。
     *
     * @param diffense セットする diffense
     */
    void setDiffense(int diffense) {
        this.diffense = diffense;
    }

    /**
     * 防御倍率をゲットする。
     *
     * @return diffenseMagnification 防御倍率
     */
    public double getDiffenseMagnification() {
        return diffenseMagnification;
    }

    /**
     * 防御倍率をセットする。
     *
     * @param diffenseMagnification セットする diffenseMagnification
     */
    public void setDiffenseMagnification(double diffenseMagnification) {
        this.diffenseMagnification = diffenseMagnification;
    }

    /**
     * 作戦をセットする。
     *
     * @param strategy セットする strategy
     */
    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 最後に行ったアクションをゲットする。
     *
     * @return lastAction 最後に行ったアクション
     */
    public String getLastAction() {
        return lastAction;
    }

    /**
     * 最後に行ったアクションをセットする。
     *
     * @param lastAction セットする lastAction
     */
    void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }
}

