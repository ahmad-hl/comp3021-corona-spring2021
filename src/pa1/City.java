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
    private int activeCases;
    private int recoveredCases;
    private int numNewCases;
    private int numFormerCases;

    // Improvements
    private int medicationFacilities;

    /**
     *  Initializes member variables.
     *
     * @param id
     * @param name
     * @param population
     * @param travelBanned
     * @param activeCases
     * @param recoveredCases
     */
    public City(int id, String name, int population,boolean travelBanned, int activeCases, int recoveredCases) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.travelBanned = travelBanned;
        this.activeCases = activeCases;
        this.recoveredCases = recoveredCases;
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
    public void decreaseMedicationFacility() {
        // TODO
        if(medicationFacilities > 0)
            medicationFacilities--;
    }

    /**
     * Increases number of active cases by the amount specified
     *
     * 1. check if the increment amount is positive
     * 1.1. if increase operation leads to active cases >= population
     *              - limit the upperbound active cases to population
     *              - throw medical exception
     * 1.2. otherwise: increase by increment amount
     *
     * @param increment
     * @throws MedicalException
     */
    public void increaseActiveCases(int increment) throws MedicalException {
        // TODO
        if (increment > 0) {
            if (activeCases + increment >= population) {
                activeCases = Math.min(population, activeCases + increment);
                throw new MedicalException(population, activeCases);
            } else
                activeCases += increment;
        }
    }

    /**
     * Decrease number of active cases and increase recovered cases by the amount specified
     *
     * 1. check if the decrement amount is positive
     *  1.1. decrease the active cases and limit the lowerbound to 0
     *  1.1. increase the recovered cases and limit the upperbound to population
     *
     * @param decrement
     */
    public void decreaseActiveCases(int decrement) {
        // TODO
        if (decrement > 0){
            activeCases = Math.max(0, activeCases - decrement);
            recoveredCases = Math.min(population, recoveredCases + decrement);
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

    public void setNumNewCases(int numNewCases) {
        this.numNewCases = numNewCases;
    }

    public void setNumFormerCases(int numFormerCases) {
        this.numFormerCases = numFormerCases;
    }

    public int getNumNewCases() {
        return numNewCases;
    }

    public int getNumFormerCases() {
        return numFormerCases;
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

    public int getActiveCases() {
        return activeCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public int getMedicationFacilities() {
        return medicationFacilities;
    }

    public boolean isNewCasesIncreasing(){
        if(numNewCases > numFormerCases)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        String toStr = String.format("%s | activeCases %d | recoveredCases %d | newCases %d |  population: %d | # of medication facilities: %d",
                name, activeCases, recoveredCases, numNewCases , population, medicationFacilities);
        return toStr;
    }
}
