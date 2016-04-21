package jp.co.tis.adc.rookies.exercises.pg_rally.ex07.character;

import java.util.Collections;
import java.util.List;

/**
 * ボスのクラス。<br>
 * 行動パターン：{@link CommandConstants#ATTACK}、
 *
 * @author Daisuke Nishioka
 * @since 1.0
 */
public final class Boss extends GameCharacterFormBase {

    /**
     * コンストラクタ。ステータスは以下。
     * <ul>
     * <li>HP：500</li>
     * <li>攻撃：100</li>
     * <li>防御：60</li>
     * </ul>
     * @param name キャラクター名称
     * @param strategy 作戦
     */
    public Boss(String name, Strategy strategy) {
        super(500);
        setName(name);
        setAttack(100);
        setDiffense(60);
        setStrategy(strategy);
    }

    @Override
    public final void action(List<GameCharacterFormBase> allys, List<GameCharacterFormBase> enemies) {
        String command = getCommand(allys, enemies);

        if (CommandConstants.ACCUMULATING_FORCE.equals(this.getLastMessage())){
            // 力を溜める状態だった場合は、力の解放を発動する。
            command = CommandConstants.RELEASE_OF_THE_FORCE;
        }
        System.out.println(new StringBuilder(this.getName()).append("の").append(command).append("!"));
        switch (command) {

        case CommandConstants.ATTACK:
            Collections.shuffle(enemies);
            attack(enemies.get(0));
            break;

        case CommandConstants.ACCUMULATING_FORCE:
            accumulatingForce();
            break;

        case CommandConstants.RELEASE_OF_THE_FORCE:
            releaseOfTheForce(enemies);
            break;

        default:
            System.out.println(new StringBuilder(this.getName()).append("は不気味に笑っている。。。"));
        }
    }

    /**
     * 力を溜める。
     */
    private void accumulatingForce() {
        setLastMessage(CommandConstants.ACCUMULATING_FORCE);
        System.out.println(new StringBuilder(this.getName()).append("は力を溜めている。"));
    }

    /**
     * 力を解放する。攻撃力1.5倍で敵全体に攻撃。
     *
     * @param enemies 敵キャラクター
     */
    private void releaseOfTheForce(List<GameCharacterFormBase> enemies) {
        setLastMessage(null);
        this.setAttackMagnification(1.5);
        System.out.println("凶悪な力を解放して全体攻撃!");
        for (GameCharacterFormBase enemy : enemies) {
            attack(enemy);
        }
    }
}