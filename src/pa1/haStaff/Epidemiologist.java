package pa1.haStaff;


import pa1.City;
import pa1.containment.Vaccination;
import pa1.Player;
import pa1.exceptions.NoEnoughBudgetException;

public class Epidemiologist extends HealthAuthorityStaff {

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
