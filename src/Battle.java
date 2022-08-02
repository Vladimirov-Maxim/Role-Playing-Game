import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Battle implements Runnable{

    private final Hero hero;
    private final AtomicBoolean heroIsWin = new AtomicBoolean();

    public Battle(Hero hero) {
        this.hero = hero;
        heroIsWin.set(false);
    }

    public boolean getHeroIsWin() {
        return heroIsWin.get();
    }

    @Override
    public void run() {

        Person enemy;
        int numberOfMoves = 1;
        int damage;
        Random random = new Random();

        String enemyAttackPattern = "Враг нанес вам %d единиц урона, у вас осталось %d единиц жизни.\n";
        String heroAttackPattern = "Вы нанесли врагу %d единиц урона, у него осталось %d единиц жизни.\n";

        int levelEnemy = random.nextInt(hero.getLevel(), hero.getLevel() + 3);

        if (random.nextInt(2) == 0) {
            enemy = new Goblin(levelEnemy);
        } else {
            enemy = new Skeleton(levelEnemy);
        }

        System.out.println("Ваш враг: " + enemy);

        synchronized (hero) {

            while (true) {

                System.out.println("-----Ход: " + numberOfMoves + "-----");

                if (numberOfMoves % 2 == 0) {

                    damage = enemy.attack();
                    hero.makeDamage(damage);

                    if (damage == 0) {
                        System.out.println("Враг промахнулся");
                    } else {
                        System.out.printf(enemyAttackPattern, damage, hero.getHealth());
                    }

                    if (!hero.isAlive()) {
                        heroIsWin.set(false);

                        System.out.println("Вы проиграли! Игра окончена...");

                        break;
                    }


                } else {

                    damage = hero.attack();
                    enemy.makeDamage(damage);

                    if (damage == 0) {
                        System.out.println("Вы промахнулись");
                    } else {
                        System.out.printf(heroAttackPattern, damage, enemy.getHealth());
                    }

                    if (!enemy.isAlive()) {
                        heroIsWin.set(true);

                        int levelDifference = (enemy.getLevel() - hero.getLevel()) + 1;
                        int experienceAward = levelDifference * enemy.getExperience();
                        int goldAward = levelDifference * enemy.getGold();

                        hero.addExperience(experienceAward);
                        hero.addGold(goldAward);

                        System.out.printf("\nВы победили! Вы получаете %d опыта и %d золота.\n",
                                experienceAward,
                                goldAward);

                        break;
                    }

                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                numberOfMoves++;

            }

        }



    }
}
