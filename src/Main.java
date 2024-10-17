import java.util.Scanner;
interface IShippingStrategy {
    double calculateShippingCost(double weight, double distance);
}

class StandardShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 0.5 + distance * 0.1;
    }
}

class ExpressShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.75 + distance * 0.2) + 10;
    }
}

class InternationalShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 1.0 + distance * 0.5 + 15;
    }
}

class NightShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.6 + distance * 0.15) + 20;
    }
}

class DeliveryContext {
    private IShippingStrategy shippingStrategy;

    public void setShippingStrategy(IShippingStrategy strategy) {
        this.shippingStrategy = strategy;
    }

    public double calculateCost(double weight, double distance) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Стратегия доставки не установлена.");
        }
        return shippingStrategy.calculateShippingCost(weight, distance);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliveryContext context = new DeliveryContext();

        System.out.println("Выберите тип доставки: 1 - Стандартная, 2 - Экспресс, 3 - Международная, 4 - Ночная");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> context.setShippingStrategy(new StandardShippingStrategy());
            case 2 -> context.setShippingStrategy(new ExpressShippingStrategy());
            case 3 -> context.setShippingStrategy(new InternationalShippingStrategy());
            case 4 -> context.setShippingStrategy(new NightShippingStrategy());
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        System.out.print("Введите вес посылки (кг): ");
        double weight = scanner.nextDouble();

        System.out.print("Введите расстояние доставки (км): ");
        double distance = scanner.nextDouble();

        if (weight < 0 || distance < 0) {
            System.out.println("Вес и расстояние должны быть положительными числами.");
            return;
        }

        double cost = context.calculateCost(weight, distance);
        System.out.printf("Стоимость доставки: %.2f%n", cost);
    }
}



