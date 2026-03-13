package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardParcelsBox= new ParcelBox<>(5);
    private static ParcelBox<FragileParcel> fragileParcelsBox= new ParcelBox<>(5);
    private static ParcelBox<PerishableParcel> perishableParcelsBox= new ParcelBox<>(5);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatus();
                    break;
                case 5:
                    getAllBoxedParcels();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылку");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");

        int cmd = Integer.parseInt(scanner.nextLine());

        System.out.println("Добавьте описание посылки:");
        String description = scanner.nextLine();
        System.out.println("Укажите вес:");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Адрес доставки:");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Дата отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (cmd) {
            case 1:
                StandardParcel newStandardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                standardParcelsBox.addParcel(newStandardParcel);
                if(!standardParcelsBox.isFull()) {
                    allParcels.add(newStandardParcel);
                }
                break;
            case 2:
                FragileParcel newFragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                fragileParcelsBox.addParcel(newFragileParcel);
                if(!fragileParcelsBox.isFull()) {
                    allParcels.add(newFragileParcel);
                    trackableParcels.add(newFragileParcel);
                }
                break;
            case 3:
                System.out.println("Срок годности:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel newPerishableParcel = new PerishableParcel(description, weight, deliveryAddress,
                        sendDay, timeToLive);
                perishableParcelsBox.addParcel(newPerishableParcel);
                if(!perishableParcelsBox.isFull()) {
                    allParcels.add(newPerishableParcel);
                }
                break;
            default:
                System.out.println("Неверный вид посылки.");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость посылок = " + sum);
    }

    private static void reportStatus() {
        System.out.println("Новое местоположение:");
        String newLocation = scanner.nextLine();
        for(Trackable parcel : trackableParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void getAllBoxedParcels() {
        System.out.println("1 - Коробка стандартных посылок");
        System.out.println("2 - Коробка хрупких посылок");
        System.out.println("3 - Коробка скоропортящихся посылок");
        int cmd = Integer.parseInt(scanner.nextLine());

        switch (cmd) {
            case 1:
                standardParcelsBox.getAllParcels();
                break;
            case 2:
                fragileParcelsBox.getAllParcels();
                break;
            case 3:
                perishableParcelsBox.getAllParcels();
                break;
        }
    }
}

