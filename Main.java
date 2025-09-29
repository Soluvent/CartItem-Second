// TASK 1

class Item {
    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price < 0 ? 0 : price;
    }

    public void increasePrice(float percent) {
        price += price * (percent / 100);
    }

    public void decreasePrice(float percent) {
        float newPrice = price - price * (percent / 100);
        price = newPrice < 0 ? 0 : newPrice;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f грн", name, price);
    }
}


// TASK 2

class Cart {
    private Item[] items;
    private int top;
    private int maxSize;

    public Cart(int maxSize) {
        this.maxSize = maxSize;
        this.items = new Item[maxSize];
        this.top = -1;
    }

    public void addItem(Item item) {
        if (isFull()) {
            System.out.println("Помилка: Кошик переповнений!");
            return;
        }
        items[++top] = item;
        System.out.println("Додано: " + item);
    }

    public Item removeItem() {
        if (isEmpty()) {
            System.out.println("Помилка: Кошик порожній!");
            return null;
        }
        Item item = items[top];
        items[top--] = null;
        System.out.println("Видалено: " + item);
        return item;
    }

    public float calculateTotal() {
        float total = 0;
        for (int i = 0; i <= top; i++) {
            total += items[i].getPrice();
        }
        return total;
    }

    public void increaseAllPrices(float percent) {
        for (int i = 0; i <= top; i++) {
            items[i].increasePrice(percent);
        }
        System.out.println("Ціни підвищено на " + percent + "%");
    }

    public void decreaseAllPrices(float percent) {
        for (int i = 0; i <= top; i++) {
            items[i].decreasePrice(percent);
        }
        System.out.println("Ціни знижено на " + percent + "%");
    }

    private boolean isEmpty() {
        return top == -1;
    }

    private boolean isFull() {
        return top == maxSize - 1;
    }

    public void displayItems() {
        if (isEmpty()) {
            System.out.println("Кошик порожній");
            return;
        }
        System.out.println("\nТовари в кошику:");
        for (int i = 0; i <= top; i++) {
            System.out.println((i + 1) + ". " + items[i]);
        }
    }
}


// TASK 4
// Щоб використати чергу замість стека:
// 1. Закоментуй клас Cart вище
// 2. Розкоментуй клас CartQueue нижче
// 3. У Main нічого міняти не треба!

/*
class Cart {
    private Item[] items;
    private int front;
    private int rear;
    private int size;
    private int maxSize;

    public Cart(int maxSize) {
        this.maxSize = maxSize;
        this.items = new Item[maxSize];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void addItem(Item item) {
        if (isFull()) {
            System.out.println("Помилка: Кошик переповнений!");
            return;
        }
        rear = (rear + 1) % maxSize;
        items[rear] = item;
        size++;
        System.out.println("Додано: " + item);
    }

    public Item removeItem() {
        if (isEmpty()) {
            System.out.println("Помилка: Кошик порожній!");
            return null;
        }
        Item item = items[front];
        items[front] = null;
        front = (front + 1) % maxSize;
        size--;
        System.out.println("Видалено: " + item);
        return item;
    }

    public float calculateTotal() {
        float total = 0;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % maxSize;
            total += items[index].getPrice();
        }
        return total;
    }

    public void increaseAllPrices(float percent) {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % maxSize;
            items[index].increasePrice(percent);
        }
        System.out.println("Ціни підвищено на " + percent + "%");
    }

    public void decreaseAllPrices(float percent) {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % maxSize;
            items[index].decreasePrice(percent);
        }
        System.out.println("Ціни знижено на " + percent + "%");
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == maxSize;
    }

    public void displayItems() {
        if (isEmpty()) {
            System.out.println("Кошик порожній");
            return;
        }
        System.out.println("\nТовари в кошику:");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % maxSize;
            System.out.println((i + 1) + ". " + items[index]);
        }
    }
}
*/


// TASK 3

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(10);

        cart.addItem(new Item("Молоко", 35.50f));
        cart.addItem(new Item("Хліб", 20.00f));
        cart.addItem(new Item("Яйця", 45.00f));
        cart.addItem(new Item("Сир", 120.00f));
        cart.addItem(new Item("Масло", 65.50f));
        cart.addItem(new Item("Цукор", 28.00f));

        cart.displayItems();
        System.out.println("\nЗагальна сума: " + String.format("%.2f", cart.calculateTotal()) + " грн");

        cart.increaseAllPrices(15);
        System.out.println("Нова сума: " + String.format("%.2f", cart.calculateTotal()) + " грн");

        cart.decreaseAllPrices(30);
        System.out.println("Нова сума: " + String.format("%.2f", cart.calculateTotal()) + " грн");
    }
}