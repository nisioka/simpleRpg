package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.List;

import jp.co.tis.adc.rookies.exercises.pg_rally.ex07.strategy.Strategy;

/**
 * ボスのクラス。<br>
 * 行動パターン：
 * {@link CommandConstants#ATTACK}、
 * {@link CommandConstants#DETH_ATTACK}、
 * {@link CommandConstants#ENERGY_CHARGE}、
 * {@link CommandConstants#RELEASE_OF_THE_ENERGY}、
 * {@link CommandConstants#ATTACK_DIFFENCE_UP}
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public final class Boss extends GameCharacterFormBase {

    /**
     * コンストラクタ。ステータスは以下。
     * <ul>
     * <li>HP：300</li>
     * <li>攻撃：100</li>
     * <li>防御：60</li>
     * </ul>
     * @param name キャラクター名称
     * @param strategy 作戦
     */
    public Boss(String name, Strategy strategy) {
        super(300);
        setName(name);
        setAttack(100);
        setDiffense(60);
        setStrategy(strategy);
    }

    @Override
    public final void action(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        String command = getCommand(this, allys, enemies);

        if (CommandConstants.ENERGY_CHARGE.equals(this.getLastAction())){
            // 力を溜める状態だった場合は、力の解放を発動する。
            command = CommandConstants.RELEASE_OF_THE_ENERGY;
        }
        System.out.println(new StringBuilder(this.getName()).append("の").append(command).append("!"));
        switch (command) {

        case CommandConstants.ATTACK:
            setLastAction(CommandConstants.ATTACK);
            singleAttack(enemies);
            break;

        case CommandConstants.DETH_ATTACK:
            setLastAction(CommandConstants.DETH_ATTACK);
            // 攻撃力2倍で単体攻撃。
            setAttackMagnification(2);
            singleAttack(enemies);
            break;

        case CommandConstants.ENERGY_CHARGE:
            setLastAction(CommandConstants.ENERGY_CHARGE);
            accumulatingForce();
            break;

        case CommandConstants.RELEASE_OF_THE_ENERGY:
            setLastAction(CommandConstants.RELEASE_OF_THE_ENERGY);
            releaseOfTheForce(enemies);
            break;

        case CommandConstants.ATTACK_DIFFENCE_UP:
            setLastAction(CommandConstants.ATTACK_DIFFENCE_UP);
            attackDiffenceUp(allys);
            break;

        default:
            System.out.println(new StringBuilder(this.getName()).append("は不気味に笑っている。。。"));
        }
    }

    /**
     * 力を溜める。
     */
    private void accumulatingForce() {
        System.out.println(new StringBuilder(this.getName()).append("は力を溜めている。"));
    }

    /**
     * 力を解放する。攻撃力1.5倍で敵全体に攻撃。
     *
     * @param enemies 敵キャラクター
     */
    private void releaseOfTheForce(List<GameCharacterFormBase> enemies) {
        this.setAttackMagnification(1.5);
        System.out.println("凶悪な力を解放して全体攻撃!");
        for (GameCharacterFormBase enemy : enemies) {
            attack(enemy);
        }
    }

    /**
     * 味方キャラクターの攻撃力と防御力を10上げる。
     *
     * @param allys 味方キャラクター
     */
    private void attackDiffenceUp(List<GameCharacterFormBase> allys) {
        for (GameCharacterFormBase ally : allys) {
            if (ally.getHitPoint() == 0) {
                continue;
            }
            ally.setAttack(ally.getAttack() + 10);
            System.out.println(new StringBuilder(ally.getName()).append("の攻撃力が").append(ally.getAttack()).append("になった。"));
            ally.setDiffense(ally.getDiffense() + 10);
            System.out.println(new StringBuilder(ally.getName()).append("の防御力が").append(ally.getDiffense()).append("になった。"));
        }
    }
}
