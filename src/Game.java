import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Seller seller = new Seller();

        System.out.println("Добро пожаловать в игру!");
        System.out.println("Введите имя своего героя...");

        Hero hero = new Hero(scanner.nextLine());

        Path path = Paths.get("src/Menu.txt");
        List<String> listMenu;

        try {
            listMenu = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (true) {

            listMenu.forEach(System.out::println);

            int action = scanner.nextInt();

            if (action == 1) {
                seller.interaction(hero);
            } else if (action == 2) {

                if (!goToTheForest(hero)) {
                    break;
                }

            }else if (action == 3) {
                hero.interactionWithInventory();
            } else if (action == 4) {
                hero.viewHeroInformation();
            } else if (action == 5) {
                break;
            } else {
                System.out.println("Вы ввели некорректную команду, попробуйте еще раз!");
            }

        }



    }


    public static boolean goToTheForest(Hero hero) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            Battle battle = new Battle(hero);
            Thread threadBattle = new Thread(battle);
            threadBattle.start();

            try {
                threadBattle.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!battle.getHeroIsWin()) {
                return false;
            }else {

                String continueHike = "";

                while ((!continueHike.equals("да")) && (!continueHike.equals("нет"))) {
                    System.out.println("Желаете продолждить поход? (да/нет)");
                    continueHike = scanner.nextLine();

                    if (continueHike.equals("нет")) {
                        return true;
                    } else if (!continueHike.equals("да")) {
                        System.out.println("Вы ввели некорректный ответ, попробуйте еще раз...");
                    }
                }
            }

        }
    }

}
