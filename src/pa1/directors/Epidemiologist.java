package pa1.directors;


import pa1.City;
import pa1.exceptions.NegativeValException;
import pa1.util.Constants;
import pa1.util.Cost;
import pa1.Player;
import pa1.exceptions.NoEnoughBudgetException;

public class Epidemiologist extends Director {

    /**
     * Calls the superclass' constructor
     *
     */
    public Epidemiologist() {
        // TODO
        super();
    }

    public Epidemiologist(int leasdership, int experience, int science) {
        super(leasdership, experience, science);
    }

    @Override
    public int getPromotion(){
        int promotion = experience + science;
        System.out.printf("**** Epidemiologist: getPromotion %d *****\n", promotion);
        return promotion;
    }


    /**
     * Example string representation:
     * "Epidemiologist | READY" - when isReady() == true
     * "Epidemiologist | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("Epidemiologist | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}
