import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;


public class ToysShop {  // Класс -Toy - принимает три аргумента: name, id и weight, после чего доступ к полям объекта осуществляется через функции: getName(), getId() и getWeight().
    private String name; // в качестве переменной используем - строку
    private int id; // в качестве переменной используем - Целые числа
    private double weight; // в качестве переменной используем - числа с десятичным значением

    public ToysShop(int id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    String line = "1, Конструктор, 2.0";  // Для выделения нужных строк выделяем id, weight для каждой игрушки (объекта) с помощью функции: String line и line.split(",");
    String[] parts = line.split(",");
    int a = Integer.parseInt(parts[0]);
    String b = parts[1].trim();
    double c = Double.parseDouble(parts[2]);

    public static void main(String[] args) {  // Обозначаем точку входа и создаём объекты 'Toy' для того, чтобы добавить их в массив
        ToysShop[] toys = new ToysShop[10];
        toys[0] = new ToysShop(1, "Конструктор", 2.0);
        toys[1] = new ToysShop(2, "Робот", 2.0);
        toys[2] = new ToysShop(3, "Кукла", 6.0);
        toys[3] = new ToysShop(4, "Самолёт", 1.5);
        toys[4] = new ToysShop(5, "Машинка", 1.0);
        toys[5] = new ToysShop(6, "Трансформер", 1.6);
        toys[6] = new ToysShop(7, "Плюшевый мишка", 2.0);
        toys[7] = new ToysShop(8, "Кубик-рубик", 1.0);
        toys[8] = new ToysShop(9, "Юла", 1.2);
        toys[9] = new ToysShop(10, "Телефон", 1.0);

        PriorityQueue<ToysShop> queue = new PriorityQueue<>(Comparator.comparingDouble(ToysShop::getWeight)); // Добавляем элементы в коллекцию путём создания класса- PriorityQueue и передаём в коллекцию объекты, в приоритете будут размещены элементы по порядку - сортировки
        for (ToysShop toy : toys) {                        // чтобы сортировать - используем интерфейс - Comparator
        queue.add(toy);                 // используем также лямбда-выражение, чтобы преобразовать значение веса игрушки в десятичные числа, и сравниваем элементы коллекции
        }

        PriorityQueue<ToysShop> totalQueue = new PriorityQueue<>(Comparator.comparingDouble(ToysShop::getWeight)); // объединяем очередь коллекций с помощью метода- totalQueue.addAll:
        totalQueue.addAll(queue);

        try (PrintWriter writer = new PrintWriter(new FileWriter("ToysShop.txt"))) { // для получения 10 разных игрушек вызваем функцию get() и записываем значения в текстовый файл
            for (int i = 0; i < 10; i++) {
            ToysShop toy = totalQueue.poll();
            if (toy != null) { // добавили проверку на ноль, чтобы 'toy' не был равен нулю, чтобы не возникало ошибок, так как без данной проверки код будет выдавать ошибку в терминале
            writer.printf("%d. %s (%.2f)%n", (i + 1), toy.getName(), toy.getWeight());
            }
            }
        } catch (IOException ex) {
        System.err.println("Произошла ошибка при осуществлении записи в текстовый файл: " + ex.getMessage());
        }
    }
}








