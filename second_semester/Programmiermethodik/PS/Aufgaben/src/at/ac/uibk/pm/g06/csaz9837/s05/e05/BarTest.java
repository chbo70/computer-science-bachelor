package at.ac.uibk.pm.g06.csaz9837.s05.e05;

import at.ac.uibk.pm.g06.csaz9837.s05.e04.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BarTest {
    //Use @BeforeEach and create Bar, Guests,...
    @Test
    @DisplayName("Guest enters Bar")
    void enterBar() throws CurrentAmountOfGuestException, GuestAlreadyInBarException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 10, 1);
        Guest guest1 = new Guest("Brady");
        assertTrue(bar1.enterBar(guest1));
    }

    @Test
    @DisplayName("Guest enters Bar twice")
    void enterBarTwice() throws CurrentAmountOfGuestException, GuestAlreadyInBarException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 10, 1);
        Guest guest1 = new Guest("Brad");
        bar1.enterBar(guest1);
        assertThrows(GuestAlreadyInBarException.class, () -> bar1.enterBar(guest1));
    }

    @Test
    @DisplayName("Guest enters a full Bar")
    void enterFullBar() throws CurrentAmountOfGuestException, GuestAlreadyInBarException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        bar1.enterBar(guest1);
        assertThrows(BarFullException.class, () -> bar1.enterBar(guest2));
    }

    @Test
    void leaveBar() throws CurrentAmountOfGuestException, GuestAlreadyInBarException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        bar1.enterBar(guest1);
        assertTrue(bar1.leaveBar(guest1));
    }

    @Test
    void notInBarLeave() throws CurrentAmountOfGuestException, GuestAlreadyInBarException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        bar1.enterBar(guest1);
        assertFalse(bar1.leaveBar(guest2));
    }

    @Test
    void orderOneBeer() throws CurrentAmountOfGuestException, GuestAlreadyInBarException, GuestNotInBarException, NoNegativeDrinksException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        Beer frastanzer = new Beer();
        bar1.enterBar(guest1);
        bar1.orderDrink(guest1, frastanzer, 1);
        assertEquals(frastanzer, guest1.getDrink());
    }

    @Test
    void orderManyBeer() throws CurrentAmountOfGuestException, GuestAlreadyInBarException, GuestNotInBarException, NoNegativeDrinksException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        Beer frastanzer = new Beer();
        bar1.enterBar(guest1);
        bar1.orderDrink(guest1, frastanzer, 999999);
        assertEquals(999999, guest1.getCurrentAmountOfDrink());
    }

    @Test
    void orderNegativeBeer() throws CurrentAmountOfGuestException, GuestAlreadyInBarException, GuestNotInBarException, NoNegativeDrinksException {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        Beer frastanzer = new Beer();
        bar1.enterBar(guest1);
        assertThrows(NoNegativeDrinksException.class, () -> bar1.orderDrink(guest1, frastanzer, -1));
    }

    @Test
    void orderAnythingOther() {
        ArrayList<Guest> guestList = new ArrayList<>();
        Bar bar1 = new Bar("Rauch", new Barkeeper("Jeff"), guestList, 2, 1);
        Guest guest1 = new Guest("Tim");
        Guest guest2 = new Guest("Timo");
        Beer frastanzer = new Beer();

    }
}