import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller {

    private final List<Goods> goods = new ArrayList<>();

    public Seller() {

        for (int i = 1; i <= 5; i++) {
            goods.add(new HealthPotion(i));
        }

    }

    public void showGoods() {
        for (int i = 0; i < goods.size(); i++) {
            System.out.printf("%d.%s\n", (i + 1), goods.get(i));
        }
    }

    public void interaction(Hero hero) {

        System.out.println("\nДобро пожаловать в лавку торговца!");

        Scanner scanner = new Scanner(System.in);
        String action;

        while (true) {

            System.out.println("\n1 - выбрать предмет");
            System.out.println("q - выход");

            action = scanner.nextLine();

            if (action.equals("q")) {
                break;
            } else if (action.equals("1")) {

                System.out.println();
                showGoods();
                System.out.println("q - выход");
                System.out.println("Выберите предмет:");

                while (true) {

                    String selectProduct = scanner.nextLine();

                    if (selectProduct.equals("q")) {
                        break;
                    }

                    try {
                        int indexProduct = Integer.parseInt(selectProduct) - 1;

                        if (indexProduct >= goods.size() || indexProduct < 0) {
                            System.out.println("Товара с таким номер нет, введите еще раз:");
                        }else {
                            Goods product = goods.get(indexProduct);

                            if (hero.makeAPurchase(product.getPrice())) {
                                hero.putItemInInventory(product);
                                System.out.println(product.getName() + " добавлен в инвентарь.");
                                break;
                            } else {
                                System.out.println("У вас недостаточно золота!");
                            }
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Вы ввели некорретное значение, попробуйте еще раз:");
                    }

                }


            } else {
                System.out.println("Введена некорректная команда, попробуйте еще раз:");
            }

        }

        System.out.println("Прощай, мой друг, буть осторожен, наш лес хранит много тайн...");

    }

}
