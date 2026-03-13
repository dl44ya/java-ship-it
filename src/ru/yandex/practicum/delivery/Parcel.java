package ru.yandex.practicum.delivery;

public abstract class Parcel {
    protected String description;
    protected int weight;
    protected String deliveryAddress;
    protected int sendDay;


    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка " + this.description + " упакована");
    }

    public int calculateDeliveryCost() {
        return weight * getBasePrice();
    };

    public void deliver() {
        System.out.println("Посылка " + this.description + " доставлена по адресу " + this.deliveryAddress + ".");
    }

    public abstract int getBasePrice();
}
