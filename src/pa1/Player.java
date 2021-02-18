package pa1;

import pa1.containment.*;
import pa1.exceptions.MedicalException;
import pa1.HAstaff.HealthAuthorityStaff;
import pa1.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents a player in the game.
 * It contains the player's attribute such as budget and points,
 * as well as the player's assets such as ministers, cities, and virus containment techniques.
 */
public class Player {

    // Assets
    private final List<HealthAuthorityStaff> healthAuthorityStaffs = new ArrayList<>();
    private final List<Containment> containTechniques = new ArrayList<>();

    // Attributes
    private final String name;
    private int budget;
    private int tourismIncome;
    private int points;

    private City city;

    /**
     * Initializes member variables.
     * @param name
     * @param budget
     * @param tourismIncome
     * @param points
     */
    public Player(String name, int budget, int tourismIncome, int points) {
        this.name = name;
        this.budget = budget;
        this.tourismIncome = tourismIncome;
        this.points = points;
    }

    @Override
    public String toString() {
        String toStr = String.format("Player: %s | budget: %d | tourism income: %d | points: %d\n",
                name, budget, tourismIncome, points);
        toStr += String.format("City: %s | infectedCases %d | recoveredCases %d | newCases %d | population %d | # of medication facilities %d",
        city.getName(), city.getActiveCases(), city.getRecoveredCases(), city.getNumNewCases(), city.getPopulation(), city.getMedicationFacilities());
        return toStr;
    }

    /**
     * Decreases the player's gold
     * Cap the value to 0.
     *
     * @param decrement
     */
    public void decreaseBudget(int decrement) {
        // TODO
        budget = Math.max(0, budget - decrement);
    }


    /**
     * Adds player's points by specified increment.
     * If the increment is negative, throw NegativeValException
     *
     * @param increment
     */
    public void addPoints(int increment) {
        // TODO
        if (increment < 0)
           addPoints(-increment);
        else
            points += increment;
    }

    /**
     * Decreases the player's points
     * Cap the value to 0.
     *
     * @param decrement
     */
    public void decreasePoints(int decrement) {
        // TODO
        points = Math.max(0, points - decrement);
    }

    /**
     * @return true if the player has at least one ready HAStaff
     */
    public boolean hasReadyHAStaff() {
        // TODO
        return getHAStaffs().stream().anyMatch(HealthAuthorityStaff::isReady);
    }

    /**
     * Compute new infected cases and updated total infected cases
     *
     * 1. get current protection and vaccination level
     * 2. compute: IF = .5*(100-protection level) + .5*(100-vaccination level)
     * 3. compute: new infected cases = IF * infectedCases * population
     * 4. add new cases to city's total infected cases
     * @throws MedicalException
     */
    public void computeNewInfectedCases() throws MedicalException {
        int currProtectionLevel = 0;
        int currVaccinationLevel = 0;
        for (Containment contTech: containTechniques) {
            if (contTech instanceof Isolation || contTech instanceof FaceMask)
                currProtectionLevel += contTech.getProtection_level();
            else if (contTech instanceof Vaccination)
                currVaccinationLevel = contTech.getVaccination_level();
        }
        double increaseFactor = 0.5 * (Constants.MAX_LEVEL - currProtectionLevel) + 0.5 * (Constants.MAX_LEVEL - currVaccinationLevel);
        int newInfectedCases = (int) Math.ceil(increaseFactor * city.getActiveCases() );
        city.setNumNewCases(newInfectedCases);
        city.addNewCases(newInfectedCases);
        city.increaseInfectedCases(newInfectedCases);
    }

    /**
     * Unpredicted disasters at the end of turn.
     * There are two types of disasters, that affect both protection level, vaccination_level, and medical level.
     * A disaster happens when disasterOccured <= 0.2, it halves the level.
     * Otherwise the level is left unchanged
     * <p>
     * disasterType [0: Fake face masks, 1: drop vaccination efficiency, 3: destruction of medication facility]
     */
    public void generateUnexpectedDistasters() {
        // TODO
        Random rand = new Random();
        int disasterType = rand.nextInt(3);
        boolean disasterOccured = rand.nextDouble() <= 0.4;

        if (disasterOccured){
            switch (disasterType) {
                case 0:
                    for (Containment cont:containTechniques) {
                        if (cont instanceof FaceMask) {
                            int index = containTechniques.indexOf(cont);
                            containTechniques.get(index).halfProtection_level();
                            System.out.println("Disaster: Fake face masks that halves the protection");
                            break;
                        }
                    }
                    break;
                case 1:
                    for (Containment cont:containTechniques) {
                        if (cont instanceof Vaccination) {
                            int index = containTechniques.indexOf(cont);
                            containTechniques.get(index).halfVaccination_level();
                            System.out.println("Disaster: Weather/physical changes that halves the vaccination efficiency");
                            break;
                        }
                    }
                    break;
                case 2:
                    city.setMedicationFacilityOutOfService();
                    System.out.println("Disaster: Medication facility is out of service");
                    break;
            }
        }
    }


    /**
     * Adds a containment technique.
     *
     * @param cont
     */
    public void addContainmentTech(Containment cont) {
        // TODO
        containTechniques.add(cont);
    }

    public void setCity(City city) {
        this.city = city;
    }

    // Trivial getters
    public List<HealthAuthorityStaff> getHAStaffs() {
        return healthAuthorityStaffs;
    }

    public City getCity() {
        return city;
    }

    public List<Containment> getContainTechniques() {
        return containTechniques;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }

    public List<HealthAuthorityStaff> getHealthAuthorityStaffs() {
        return healthAuthorityStaffs;
    }

    public int getTourismIncome() {
        return tourismIncome;
    }

    public int getPoints() {
        return points;
    }
}
