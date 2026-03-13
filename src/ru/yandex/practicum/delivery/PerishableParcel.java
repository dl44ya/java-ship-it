package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private static final int BASE_PRICE = 3;
    private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return (sendDay + timeToLive) < currentDay;
    }

    @Override
    public int getBasePrice() {
        return BASE_PRICE;
    }
}
