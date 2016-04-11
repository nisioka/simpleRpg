package rpg.character;

public abstract class Player extends Character{

    public static final String ATTACK = "攻撃";
    public static final String DIFFENSE = "防御";

    private String jobName;

    /**
     *
     */
    public Player(int hitPoint) {
        setHitPoint(hitPoint);
        setMaxHitPoint(hitPoint);
    }

    /**
     * @return jobName
     */
    public String getJobName() {
        return jobName;
    }
    /**
     * @param jobName セットする jobName
     */
    void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
