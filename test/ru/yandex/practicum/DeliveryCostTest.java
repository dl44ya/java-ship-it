package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {
    StandardParcel standardParcel = new StandardParcel("standard", 4, "a", 10);
    StandardParcel anotherStandardParcel = new StandardParcel("standard", 10, "a", 10);
    FragileParcel fragileParcel = new FragileParcel("fragile", 6, "a", 10);
    PerishableParcel perishableParcel = new PerishableParcel("perishable", 5, "a", 10, 5);
    ParcelBox<StandardParcel> parcelBox;


    @Test
    public void shouldReturn8WhenStandardParcelWeight4kg() {
        assertEquals(8, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn24WhenFragileParcelWeight6kg() {
        assertEquals(24, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturn15WhenPerishableParcelWeight5kg() {
        assertEquals(15, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturnTrueWhenPerishableParcelIsExpired() {
        assertTrue(perishableParcel.isExpired(16));
    }

    @Test
    public void shouldReturnFalseWhenPerishableParcelIsNotExpiredAtTheLastDayOfTimeToLive() {
        assertFalse(perishableParcel.isExpired(15));
    }

    @Test
    public void shouldReturnFalseWhenPerishableParcelIsNotExpired() {
        assertFalse(perishableParcel.isExpired(14));
    }

    @Test
    public void shouldBeAddedInParcelsBoxWhenParcelsWeightLessThanMaxBoxWeight10kg() {
        parcelBox = new ParcelBox<>(10);
        parcelBox.addParcel(standardParcel);
        assertEquals(1, parcelBox.getParcels().size());
    }

    @Test
    public void shouldBeAddedInParcelsBoxWhenParcelsWeightIsMaxBoxWeight10kg() {
        parcelBox = new ParcelBox<>(10);
        parcelBox.addParcel(anotherStandardParcel);
        assertEquals(1, parcelBox.getParcels().size());
    }

    @Test
    public void shouldNotBeAddedInParcelsBoxAndReturnTrueWhenParcelsWeightMoreThanMaxBoxWeight10kg() {
        parcelBox = new ParcelBox<>(10);
        parcelBox.addParcel(standardParcel);
        parcelBox.addParcel(anotherStandardParcel);
        assertEquals(1, parcelBox.getParcels().size());
    }

}
