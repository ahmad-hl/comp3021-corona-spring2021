package pa1.haStaff;

import pa1.City;
import pa1.Player;
import pa1.containment.FaceMask;
import pa1.containment.Isolation;

public class ChiefExecutive extends HealthAuthorityStaff {

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
