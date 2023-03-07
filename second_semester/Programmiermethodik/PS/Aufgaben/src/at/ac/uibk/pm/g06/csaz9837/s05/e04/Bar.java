package at.ac.uibk.pm.g06.csaz9837.s05.e04;

import java.util.ArrayList;

public class Bar {
    private String name;
    private final Barkeeper barkeeper;
    private ArrayList<Guest> guests;
    private int maxNumberOfGuest;
    private int currentAmountOfGuests;

    public Bar(String name, Barkeeper barkeeper, ArrayList<Guest> guests, int maxNumberOfGuest, int currentAmountOfGuests) {
        this.name = name;
        this.barkeeper = barkeeper;
        this.guests = guests;
        this.maxNumberOfGuest = maxNumberOfGuest;
        this.currentAmountOfGuests = currentAmountOfGuests;
    }

    public boolean enterBar(Guest newGuest) throws CurrentAmountOfGuestException, GuestAlreadyInBarException {
        if (currentAmountOfGuests > maxNumberOfGuest) {
            throw new CurrentAmountOfGuestException();
        }
        if (guests.size() + currentAmountOfGuests == maxNumberOfGuest) {
            throw new BarFullException();
        }
        if (guests.contains(newGuest)) {
            throw new GuestAlreadyInBarException(newGuest);
        }
        System.out.println(newGuest.getName() + " entered " + name);
        return guests.add(newGuest);
    }

    public boolean leaveBar(Guest existingGuest) {
        return guests.remove(existingGuest);
    }

    public void orderDrink(Guest guest1, Drink drink, int amount) throws GuestNotInBarException, NoNegativeDrinksException {
        if (amount < 1) {
            throw new NoNegativeDrinksException();
        }
        if (guests.contains(guest1)) {
            System.out.println(guest1.getName() + " wants " + amount + " " + drink.toString());
            guest1.setDrink(drink);
            guest1.setCurrentAmountOfDrink(amount);
        } else {
            throw new GuestNotInBarException(guest1);
        }
    }

    public void callBarkeeper() {
        System.out.println(barkeeper.toString());
    }

    @Override
    public String toString() {
        return "Current Bar: " + name;
    }
}

