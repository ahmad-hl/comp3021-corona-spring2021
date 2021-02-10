package pa1;

import pa1.exceptions.NegativeValException;

/**
 * A class that represents a city of interest
 */
public class City {

    // TODO: define instance variables according to the UML
    // Metadata
    private final int id;
    private final String name;

    // Attributes
    private int population;
    private boolean travelBanned;
    private int infectedCases;
    private int recoveredCases;
    private float spreadRate;

    // Improvements
    private int hospitals;
    private int maskFactories;
    private boolean vaccineAvailable;

    public City(int id, String name, int population,boolean travelBanned, int infectedCases, int recoveredCases, float spreadRate) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.travelBanned = travelBanned;
        this.infectedCases = infectedCases;
        this.recoveredCases = recoveredCases;
        this.spreadRate = spreadRate;
    }

    /**
     * Adds number of hospitals by one
     */
    public void addHospital() {
        // TODO
        hospitals++;
    }


    /**
     * Adds number of medicine factories by one
     */
    public void addMaskFactory() {
        // TODO
        maskFactories++;
    }

    /**
     * Increases number of cases by the amount specified
     * throw negative exception if decrease operation leads to negative # doctors
     *
     * @param increment
     */
    public void increaseInfectedCases(int increment) throws NegativeValException {
        // TODO
        if (increment < 0)
            throw new NegativeValException(increment);
        else
            infectedCases += increment;

    }

    /**
     * Decrease number of infected cases and increase recovered cases by the amount specified
     * throw negative exception if decrease operation leads to negative # doctors
     *
     * @param decrement
     */
    public void decreaseInfectedCases(int decrement) throws NegativeValException {
        // TODO
        if (decrement < 0)
            throw new NegativeValException(decrement);
        else {
            infectedCases -= decrement;
            recoveredCases += decrement;
        }
    }

    /**
     * set banTravel to a value
     * @param val
     */
    public void setTravelBanned(boolean val) {
        // TODO
        travelBanned = val;
    }

    /**
     * set vaccineAvailable to a value
     * @param val
     */
    public void setVaccineAvailable(boolean val) {
        // TODO
        vaccineAvailable = val;
    }

    /**
     * Increase the number of infected cases according to spread ratio
     * throw negative exception if decrease operation leads to negative # doctors
     */
    public void updateInfectedCasesBySpreadRate(){
        infectedCases += infectedCases * spreadRate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public boolean isTravelBanned() {
        return travelBanned;
    }

    public int getInfectedCases() {
        return infectedCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public float getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(float spreadRate) {
        this.spreadRate = spreadRate;
    }

    public int getHospitals() {
        return hospitals;
    }

    public int getMaskFactories() {
        return maskFactories;
    }

    public boolean isVaccineAvailable() {
        return vaccineAvailable;
    }

    @Override
    public String toString() {
        String toStr = String.format("%s | population: %d | # of hospitals: %d | # of mask factories: %d",
                name, population, hospitals, maskFactories);
        return toStr;
    }

    /**
     * Checks whether two cities are equal
     * Two cities are equal when they have the same id
     * Return false if Object o is not an instance of City
     *
     * @param o object to be compared
     * @return result of equality check
     */
    @Override
    public boolean equals(Object o) {
        // TODO
        if (o instanceof City)
            return getId() == ((City) o).getId();

        return false;
    }
}
