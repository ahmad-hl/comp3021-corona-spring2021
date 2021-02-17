package pa1;

import pa1.exceptions.MedicalException;

import java.util.ArrayList;
import java.util.List;

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
    private int numNewCases;
    private List<Integer> newCases;

    // Improvements
    private int medicationFacilities;

    public City(int id, String name, int population,boolean travelBanned, int infectedCases, int recoveredCases) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.travelBanned = travelBanned;
        this.infectedCases = infectedCases;
        this.recoveredCases = recoveredCases;
        this.newCases = new ArrayList<>();
    }

    /**
     * Adds number of hospitals by one
     */
    public void addMedicationFacilities() {
        // TODO
        medicationFacilities++;
    }

    /**
     * set Medication Facility Out Of Service
     * 1. decrement facilities by 1
     */
    public void setMedicationFacilityOutOfService() {
        // TODO
        if(medicationFacilities > 0)
            medicationFacilities--;
    }

    /**
     * Increases number of cases by the amount specified
     * throw negative exception if decrease operation leads to negative # doctors
     *
     * @param increment
     */
    public void increaseInfectedCases(int increment) throws MedicalException {
        // TODO
        if (increment < 0)
            increaseInfectedCases(-increment);
        else if (infectedCases+ increment >= population) {
            infectedCases = Math.min(population, infectedCases + increment);
            throw new MedicalException(population, infectedCases);
        }
        else infectedCases = infectedCases+ increment;
    }

    /**
     * Decrease number of infected cases and increase recovered cases by the amount specified
     * throw negative exception if decrease operation leads to negative # doctors
     *
     * @param decrement
     */
    public void decreaseInfectedCases(int decrement) {
        // TODO
        if (decrement < 0)
            decreaseInfectedCases(-decrement);
        else {
            infectedCases = Math.max(0, infectedCases - decrement);
            recoveredCases = Math.min(population, recoveredCases + decrement);
        }
    }

    public void addNewCases(int newCasesToAdd){
        newCases.add(newCasesToAdd);
    }

    /**
     * set banTravel to a value
     * @param val
     */
    public void setTravelBanned(boolean val) {
        // TODO
        travelBanned = val;
    }

    public void setNumNewCases(int numNewCases) {
        this.numNewCases = numNewCases;
    }

    public int getNumNewCases() {
        return numNewCases;
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

    public int getMedicationFacilities() {
        return medicationFacilities;
    }

    public List<Integer> getNewCases() {
        return newCases;
    }

    public boolean isGreaterNewCases(){
        int newCasesLen = newCases.size();
        if (newCasesLen >= 2){
            if(newCases.get(newCasesLen -1)> newCases.get(newCasesLen -2))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String toStr = String.format("%s | infectedCases %d | recoveredCases %d | newCases %d |  population: %d | # of medication facilities: %d",
                name, infectedCases, recoveredCases, numNewCases , population, medicationFacilities);
        return toStr;
    }
}
