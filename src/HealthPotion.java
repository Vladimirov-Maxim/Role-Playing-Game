import java.util.Objects;

public class HealthPotion extends Goods{

    private static final int INITIAL_PRICE = 200;
    private static final int INITIAL_ADDED_HEALTH = 50;

    private final int addedHealth;

    public HealthPotion(int level) {
        super("Зелье здоровья");
        this.setLevel(level);
        this.addedHealth = HealthPotion.INITIAL_ADDED_HEALTH * level;
        setPrice(HealthPotion.INITIAL_PRICE * level);
    }

    @Override
    public void use(Hero hero) {
        hero.addHealth(addedHealth);
    }

    @Override
    public String toString() {
        return String.format("Зелье здоровья (%d lvl)\n Цена: %d\n Описание: Добавляет %d единиц здоровья",
                getLevel(),
                getPrice(),
                addedHealth);

    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HealthPotion that = (HealthPotion) o;
        return getLevel() == that.getLevel() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLevel(), getName());
    }
}
