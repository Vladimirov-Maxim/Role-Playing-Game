import java.util.Objects;

public abstract class Goods{

    private final String name;
    private int price;
    private int level;

    public abstract void use(Hero hero);

    public Goods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    protected void setPrice(int price) {
        this.price = price;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(name, goods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }





}
