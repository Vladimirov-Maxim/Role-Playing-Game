public abstract class Person {

    protected String name;
    protected int experience, gold;
    protected volatile int health, strength, agility, level;

    public Person(String name) {
        this.name = name;
    }

    public int attack() {

        int damage = strength;
        int randomForMiss = (int)(Math.random() * 100);
        int randomForCritical = (int)(Math.random() * 100);

        if ((agility * 3) < randomForMiss) {
            damage = 0;
        } else if (randomForCritical > 80) {
            damage*=2;
        }

        return damage;

    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    synchronized public void makeDamage(int damage) {
        health = Math.max(0, health- damage);
    }

    @Override
    public String toString() {
        return name + "(" + level + " lvl)";
    }
}
