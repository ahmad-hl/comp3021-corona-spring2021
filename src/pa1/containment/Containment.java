package pa1.containment;

import pa1.City;
import pa1.exceptions.NegativeValException;
import pa1.util.Cost;

/**
 * An abstract class that represents in-game technology
 */
public abstract class Containment {

    /**
     * Update spread ratio by the specified percentage
     * throw negative exception if ratio less than zero
     *
     * @param ratio
     */
    public void updateSpreadRate(City city, float ratio) throws NegativeValException {
        // TODO
        if (ratio < 0)
            throw new NegativeValException(ratio);
        else {
            float currSpreadRate = city.getSpreadRate();
            city.setSpreadRate(currSpreadRate * ratio);
        }
    }

}
