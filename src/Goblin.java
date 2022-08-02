public class Goblin extends Person{

    private static final int INITIAL_AGILITY = 10;
    private static final int INITIAL_HEALTH = 100;
    private static final int INITIAL_STRENGTH = 10;
    private static final int GOLD_AWARD = 300;
    private static final int EXPERIENCE_AWARD = 150;

    public Goblin(int level) {
        super("Goblin");

        this.level = level;

        this.strength = Goblin.INITIAL_STRENGTH * level;
        this.agility = Goblin.INITIAL_AGILITY * level;
        this.health = Goblin.INITIAL_HEALTH * level;
        this.experience = Goblin.EXPERIENCE_AWARD * level;
        this.gold = Goblin.GOLD_AWARD * level;

    }

}
