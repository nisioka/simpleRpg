package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.Collections;
import java.util.List;

/**
 * 勇者のクラス。<br>
 * 行動パターン：{@link CommandConstants#ATTACK}、{@link CommandConstants#GUARD}、{@link CommandConstants#RESUSCITATION}
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public final class Brave extends GameCharacterFormBase {

    /**
     * コンストラクタ。ステータスは以下。
     * <ul>
     * <li>職業：勇者</li>
     * <li>HP：100</li>
     * <li>攻撃：90</li>
     * <li>防御：50</li>
     * </ul>
     * @param name キャラクター名称
     * @param strategy 作戦
     */
    public Brave(String name, Strategy strategy) {
        super(100);
        setName(name);
        setJobName("勇者");
        setAttack(90);
        setDiffense(50);
        setStrategy(strategy);
    }

    @Override
    public final void action(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        String command = getCommand(allys, enemies);
        System.out.println(new StringBuilder(this.getName()).append("の").append(command).append("!"));
        switch (command) {

        case CommandConstants.ATTACK:
            Collections.shuffle(enemies);
            attack(enemies.get(0));
            break;

        case CommandConstants.GUARD:
            guard();
            break;

        case CommandConstants.RESUSCITATION:
            resuscitation(allys);
            break;

        default:
            System.out.println("しかし上手く決まらなかった。");
        }
    }

    /**
     * 蘇生する。回復量は最大HPの半分。
     *
     * @param allys 味方キャラクター
     */
    private void resuscitation(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() == 0) {
                ally.damage(ally.getMaxHitPoint() / -2);
                System.out.println(new StringBuilder(ally.getName()).append("は生き返った!"));
                return;
            }
        }
        System.out.println("しかし、誰も死んでいなかった。");
    }
}
