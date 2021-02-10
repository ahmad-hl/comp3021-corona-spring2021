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
    private int doctors;
    private boolean travelBanned;
    private int infectedCases;
    private int recoveredCases;
    private float spreadRate;

    // Improvements
    private int hospitals;
    private int medicalLabs;
    private int medicineFactories;

    public City(int id, String name, int population, int doctors,boolean travelBanned, int infectedCases, int recoveredCases, float spreadRate) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.doctors = doctors;
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
     * Adds number of medical labs by one
     */
    public void addMedicalLab() {
        // TODO
        medicalLabs++;
    }

    /**
     * Adds number of medicine factories by one
     */
    public void addMedicineFactory() {
        // TODO
        medicineFactories++;
    }

    /**
     * Adds number of doctors by specified increment.
     * If the increment is negative, raise exception.
     *
     * @param increment
     */
    public void addDoctors(int increment) throws NegativeValException {
        // TODO

        if (increment < 0)
            throw new NegativeValException(increment);
        else
            doctors += increment;
    }

    /**
     * Decreases number of doctors by the amount specified
     * throw negative exception if decrease operation leads to negative # doctors
     *
     * @param decrement
     */
    public void decreaseDoctors(int decrement) throws NegativeValException {
        // TODO
        if (decrement > doctors)
            throw new NegativeValException(decrement);
        else
            doctors -= decrement;

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

    public int getDoctors() {
        return doctors;
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

    public int getMedicalLabs() {
        return medicalLabs;
    }

    public int getMedicineFactories() {
        return medicineFactories;
    }

    @Override
    public String toString() {
        String toStr = String.format("%s | population: %d | doctors: %d | # of hospitals: %d | # of medical labs: %d | # of medicine factories: %d",
                name, population, doctors, hospitals, medicalLabs, medicineFactories);
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
