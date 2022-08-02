import java.util.*;

public class Hero extends Person{

    private volatile int maxHealth;
    private final Inventory inventory;

    public Hero(String name) {
        super(name);
        this.experience = 0;
        this.strength = 20;
        this.agility = 20;
        this.health = 200;
        this.level = 1;
        this.gold = 0;
        this.maxHealth = 200;
        inventory = new Inventory();
    }


    public void putItemInInventory(Goods item) {
        inventory.putItemInInventory(item);
    }

    public void interactionWithInventory() {

        System.out.println("\nДля взаимодействия с инвентарем необходимо написать название предмета и команду через \\, например: Зелье здоровья (3 lvl)\\d \n"
                + "Команды для взамодействия с инвентарем: \n"
                + "\\u - использовать предмет \n"
                + "\\d - выбросить предмет в количестве: 1 штука \n"
                + "\\q - выйти из инвентаря");

        Scanner scanner = new Scanner(System.in);
        String action;

        while (true) {

            System.out.println("\nВаш инвентарь:");
            inventory.showItems();

            action = scanner.nextLine();

            try {
                String command = action.substring(action.lastIndexOf("\\")).trim();
                String strItem = action.substring(0, action.lastIndexOf("\\")).trim();

                if (command.equals("\\q"))
                    break;

                Goods item = inventory.getItem(strItem);

                if (item == null) {
                    System.out.println("Предмета с наименованием: " + strItem + " нет в инвентаре. Введите команду еще раз.");
                }else if (command.equals("\\d")) {
                    inventory.deleteItem(item);
                    System.out.println("Предмет: " + strItem + " удален из инвентаря в количестве 1 штука");
                }else if (command.equals("\\u")) {
                    item.use(this);
                    inventory.deleteItem(item);
                    System.out.println("Предмет: " + strItem + " использован");
                }else {
                    System.out.println("Вы ввели некорректную команду, попробуйте еще раз");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Вы ввели некорректную команду, попробуйте еще раз");
            }

        }

    }

    synchronized public void addHealth(int health) {
        this.health = Math.min(this.health + health, maxHealth);
    }

    synchronized public void addExperience(int experience) {

        this.experience+=experience;

        int newLevel = level;

        if (this.experience >= 2500) {
            newLevel = 5;
        }else if (this.experience >= 1000) {
            newLevel = 4;
        }else if (this.experience >= 500) {
            newLevel = 3;
        }else if (this.experience >= 250) {
            newLevel = 2;
        }

        if (newLevel != level) {

            level = newLevel;
            System.out.println("\nВы достигли " + level + " уровня!");

            strength*=level;
            agility*=level;
            maxHealth*=level;
            health*=level;

        }

    }

    public void addGold(int gold) {
        this.gold+=gold;
    }

    public boolean makeAPurchase(int gold) {

        if (this.gold < gold) {
            return false;
        } else {
            this.gold-=gold;
            return true;
        }

    }

    public void viewHeroInformation() {
        System.out.println("Hero{" +
                "name=" + name +
                ", level=" + level +
                ", health=" + health +
                ", strength=" + strength +
                ", agility=" + agility +
                ", experience=" + experience +
                ", gold=" + gold +
                '}');
    }

    private class Inventory {

        private final HashMap<Goods, Integer> items = new HashMap<>();

        protected void putItemInInventory(Goods item) {
            int itemQuantity = items.getOrDefault(item, 0);
            items.put(item, itemQuantity + 1);
        }

        protected void showItems() {

            if (items.size() == 0) {
                System.out.println("<Пусто>");
                return;
            }

            for (Map.Entry<Goods, Integer> goodsIntegerEntry: items.entrySet()) {
                System.out.printf("%s (%d lvl) - %d\n",
                        goodsIntegerEntry.getKey().getName(),
                        goodsIntegerEntry.getKey().getLevel(),
                        goodsIntegerEntry.getValue());
            }

        }

        protected void deleteItem(Goods item) {

            if (items.containsKey(item)) {

                int itemQuantity = items.get(item);

                if (itemQuantity == 1) {
                    items.remove(item);
                } else {
                    items.put(item, itemQuantity - 1);
                }

            }

        }

        protected Goods getItem(String strItem) {

            for (Map.Entry<Goods, Integer> mGoodsIntegerEntry: items.entrySet()) {

                String viewGoods = String.format("%s (%d lvl)",
                        mGoodsIntegerEntry.getKey().getName(),
                        mGoodsIntegerEntry.getKey().getLevel());

                if (viewGoods.equals(strItem)) {
                    return mGoodsIntegerEntry.getKey();
                }
            }
            return null;
        }

    }

}
