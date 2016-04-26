package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.Strategy;

/**
 * 魔法使いのクラス。<br>
 * 行動パターン：
 * {@link CommandConstants#ATTACK}、
 * {@link CommandConstants#GUARD}、
 * {@link CommandConstants#ATTACK_UP}、
 * {@link CommandConstants#DIFFENCE_UP}
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public final class Wizard extends GameCharacterFormBase {

    /**
     * コンストラクタ。ステータスは以下。
     * <ul>
     * <li>職業：魔法使い</li>
     * <li>HP：100</li>
     * <li>攻撃：70</li>
     * <li>防御：40</li>
     * </ul>
     * @param name キャラクター名称
     * @param strategy 作戦
     */
    public Wizard(String name, Strategy strategy) {
        super(100);
        setName(name);
        setJobName("魔法使い");
        setAttack(70);
        setDiffense(40);
        setStrategy(strategy);
    }

    @Override
    public final void action(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        String command = getCommand(this, allys, enemies);
        System.out.println(new StringBuilder(this.getName()).append("の").append(command).append("!"));
        switch (command) {

        case CommandConstants.ATTACK:
            setLastAction(CommandConstants.ATTACK);
            singleAttack(enemies);
            break;

        case CommandConstants.GUARD:
            setLastAction(CommandConstants.GUARD);
            guard();
            break;

        case CommandConstants.ATTACK_UP:
            setLastAction(CommandConstants.ATTACK_UP);
            attackUp(allys);
            break;

        case CommandConstants.DIFFENCE_UP:
            setLastAction(CommandConstants.DIFFENCE_UP);
            diffenceUp(allys);
            break;

        default:
            System.out.println("しかし上手く決まらなかった。");
        }
    }

    /**
     * 味方キャラクターの攻撃を10上げる。
     *
     * @param allys 味方キャラクター
     */
    private void attackUp(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() == 0) {
                continue;
            }
            ally.setAttack(ally.getAttack() + 10);
            System.out.println(new StringBuilder(ally.getName()).append("の攻撃力が").append(ally.getAttack()).append("になった。"));
        }
    }

    /**
     * 味方キャラクターの防御を10上げる。
     *
     * @param allys 味方キャラクター
     */
    private void diffenceUp(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() == 0) {
                continue;
            }
            ally.setDiffense(ally.getDiffense() + 10);
            System.out.println(new StringBuilder(ally.getName()).append("の防御力が").append(ally.getAttack()).append("になった。"));
        }
    }
}
