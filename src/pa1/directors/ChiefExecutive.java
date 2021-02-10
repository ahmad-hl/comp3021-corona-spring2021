package pa1.directors;


import pa1.City;
import pa1.Player;
import pa1.exceptions.NegativeValException;
import pa1.util.Constants;

public class ChiefExecutive extends Director {

    public ChiefExecutive() {
        // TODO
        super();
    }

    public ChiefExecutive(int leasdership, int experience, int science) {
        super(leasdership, experience, science);
    }

    @Override
    public int getPromotion() {
        System.out.printf("**** ChiefExecutive: getPromotion %d *****\n", leasdership);
        return leasdership;
    }

    /**
     * Example string representation:
     * "ChiefExecutive | READY" - when isReady() == true
     * "ChiefExecutive | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("ChiefExecutive | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}
