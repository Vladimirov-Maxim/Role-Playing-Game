public class Skeleton extends Person{

    private static final int INITIAL_AGILITY = 15;
    private static final int INITIAL_HEALTH = 70;
    private static final int INITIAL_STRENGTH = 5;
    private static final int GOLD_AWARD = 200;
    private static final int EXPERIENCE_AWARD = 100;

    public Skeleton(int level) {
        super("Skeleton");

        this.level = level;

        this.strength = Skeleton.INITIAL_STRENGTH * level;
        this.agility = Skeleton.INITIAL_AGILITY * level;
        this.health = Skeleton.INITIAL_HEALTH * level;
        this.experience = Skeleton.EXPERIENCE_AWARD * level;
        this.gold = Skeleton.GOLD_AWARD * level;

    }

}
