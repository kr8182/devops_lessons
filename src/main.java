import java.util.Scanner;
import java.util.Arrays;

public class main {  // Исправлено название класса (с заглавной буквы)

    public static void main(String[] args) {
        System.out.println("Вас приветствует список покупок!");

        String[] shoppingList = new String[8];
        int productCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите одну из команд:");
            System.out.println("1. Добавить товар в список");
            System.out.println("2. Отобразить список");
            System.out.println("3. Очистить список");
            System.out.println("4. Завершить программу");

            int actionNumber = scanner.nextInt();

            if (actionNumber == 1) {
                if (productCount == shoppingList.length) {
                    System.out.println("Извините, список полон!");
                } else {
                    System.out.println("Введите название товара:");
                    String productName = scanner.next();
                    boolean productAlreadyExist = false;  // Инициализация флага

                    // Проверка на дубликат
                    for (int i = 0; i < productCount; i++) {
                        if (productName.equals(shoppingList[i])) {
                            productAlreadyExist = true;
                            break;
                        }
                    }

                    if (productAlreadyExist) {
                        System.out.println("Такой товар уже есть в списке!");
                    } else {
                        shoppingList[productCount] = productName;  // Добавление в массив
                        System.out.println("Товар " + productName + " добавлен в список под номером " + (productCount + 1));
                        productCount++;
                    }
                }
            } else if (actionNumber == 2) {
                for (int i = 0; i < productCount; i++) {
                    System.out.println((i + 1) + ". " + shoppingList[i]);
                }
            } else if (actionNumber == 3) {
                Arrays.fill(shoppingList, null);
                System.out.println("Список очищен!");
                productCount = 0;
            } else if (actionNumber == 4) {
                return;
            } else {
                System.out.println("Неизвестная команда!");
            }
        }
    }
}