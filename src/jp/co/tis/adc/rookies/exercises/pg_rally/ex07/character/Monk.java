package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.Strategy;

/**
 * 僧侶のクラス。<br>
 * 行動パターン：
 * {@link CommandConstants#ATTACK}、
 * {@link CommandConstants#GUARD}、
 * {@link CommandConstants#RECOVERY}、
 * {@link CommandConstants#RESUSCITATION}
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public final class Monk extends GameCharacterFormBase {

    /**
     * コンストラクタ。ステータスは以下。
     * <ul>
     * <li>職業：僧侶</li>
     * <li>HP：100</li>
     * <li>攻撃：50</li>
     * <li>防御：30</li>
     * </ul>
     * @param name キャラクター名称
     * @param strategy 作戦
     */
    public Monk(String name, Strategy strategy) {
        super(100);
        setName(name);
        setJobName("僧侶");
        setAttack(50);
        setDiffense(30);
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

        case CommandConstants.RECOVERY:
            setLastAction(CommandConstants.RECOVERY);
            recovery(allys);
            break;

        case CommandConstants.RESUSCITATION:
            setLastAction(CommandConstants.RESUSCITATION);
            resuscitation(allys);
            break;

        default:
            System.out.println("しかし上手く決まらなかった。");
        }
    }

    /**
     * 味方キャラクター全体の体力を100回復する。
     *
     * @param allys 味方キャラクター
     */
    private void recovery(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys){
            if (ally.getHitPoint() == 0){
                continue;
            }
            ally.damage(-100);
            System.out.println(new StringBuilder(ally.getName()).append("の体力が全回復した。"));
        }
    }

    /**
     * 蘇生する。体力は全回復。
     *
     * @param allys 味方キャラクター
     */
    private void resuscitation(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() == 0) {
                ally.damage(ally.getMaxHitPoint() / -1);
                System.out.println(new StringBuilder(ally.getName()).append("は生き返った!"));
                return;
            }
        }
        System.out.println("しかし、誰も死んでいなかった。");
    }
}
