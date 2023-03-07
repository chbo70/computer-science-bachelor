package at.ac.uibk.pm.g06.csaz9837.s05.e04;

import java.util.ArrayList;

public class BarApplication {
    public static void main(String[] args) throws CurrentAmountOfGuestException {
        Barkeeper barkeeper1 = new Barkeeper("Johnny Depp");
        ArrayList<Guest> guestlist = new ArrayList<>();
        Bar rauchclub = new Bar("Rauch", barkeeper1, guestlist, 10, 2);
        Bar jimmys = new Bar("Jimmy's", barkeeper1, guestlist, 6, 10);
        Beer frastanzer = new Beer();
        OrangeJuice orange = new OrangeJuice();
        Guest guest1 = new Guest("Brad");
        Guest guest2 = new Guest("Brady");
        try {
            rauchclub.enterBar(guest1);
            rauchclub.enterBar(guest2);
            guest1.setCurrentlyVisitedBar(rauchclub);
            rauchclub.orderDrink(guest1, frastanzer, 2);
            frastanzer.checkOrderAmount(2);
            rauchclub.orderDrink(guest1, frastanzer, 3);
            frastanzer.checkOrderAmount(3);
            rauchclub.orderDrink(guest1, frastanzer, 5);
            frastanzer.checkOrderAmount(5);
            //rauchclub.orderDrink(guest1,frastanzer,7);
            //frastanzer.checkOrderAmount(7);
            rauchclub.callBarkeeper();
            barkeeper1.serveDrink(guest1, frastanzer, 2);
            guest1.getCurrentlyVisitedBar();
            guest1.getCurrentAmountOfDrink();
            guest1.getCurrentDrink();
            guest1.consumeDrink(frastanzer);
            //guest1.consumeDrink(orange);
            guest1.getCurrentAmountOfDrink();
            guest1.checkInstanceOfOtherGuest(guest2);
            double x = 2234.89879;
            System.out.println(String.format("The number is %.10f\n", x));

            jimmys.enterBar(guest1);
        } catch (AmountToHighException |
                NoNegativeDrinksException |
                BarFullException |
                CurrentAmountOfGuestException |
                GuestNotInBarException |
                GuestAlreadyInBarException |
                NoDrinktoConsumeException e) {
            System.err.println(e.getMessage());
        }
    }
}
