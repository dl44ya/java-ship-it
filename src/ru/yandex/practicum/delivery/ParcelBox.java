package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels = new ArrayList<>();
    private final int maxWeight;
    private int currentWeight;
    private boolean isFull = false;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if ((currentWeight + parcel.weight) <= maxWeight) {
            parcels.add(parcel);
            currentWeight += parcel.weight;
            System.out.println("Посылка " + parcel.description + " в коробке");
            if (currentWeight == maxWeight) {
                isFull = true;
            }
        } else {
            System.out.println("Коробка заполнена");
        }
    }

    public void getAllParcels() {
        for (T parcel : parcels) {
            System.out.println(parcel.description);
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public List<T> getParcels() {
        return parcels;
    }
}
