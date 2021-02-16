package pa1.haStaff;

import pa1.City;
import pa1.containment.*;
import pa1.util.Constants;
import pa1.Player;

import pa1.exceptions.NoEnoughBudgetException;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public class HealthMinister extends HealthAuthorityStaff {

    /**
     * Example string representation:
     * "HealthMinister | READY" - when isReady() == true
     * "HealthMinister | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("HealthMinister | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}

