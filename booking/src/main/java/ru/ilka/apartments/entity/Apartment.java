package ru.ilka.apartments.entity;

import java.sql.Timestamp;

/**
 * Ilya_Kisel +375 29 3880490
 */
public class Apartment extends IDatabaseEntity {

    private int apartmentId;

    private Timestamp bookedFrom;

    private Timestamp bookedTo;

    public Apartment(int apartmentId, Timestamp bookedFrom, Timestamp bookedTo) {
        this.apartmentId = apartmentId;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
    }

    public Apartment() {
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Timestamp getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(Timestamp bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public Timestamp getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(Timestamp bookedTo) {
        this.bookedTo = bookedTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (apartmentId != apartment.apartmentId) return false;
        if (!bookedFrom.equals(apartment.bookedFrom)) return false;
        return bookedTo.equals(apartment.bookedTo);

    }

    @Override
    public int hashCode() {
        int result = apartmentId;
        result = 31 * result + bookedFrom.hashCode();
        result = 31 * result + bookedTo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "Id " + apartmentId +
                ", bookedFrom " + bookedFrom +
                ", bookedTo " + bookedTo +
                '}';
    }
}
