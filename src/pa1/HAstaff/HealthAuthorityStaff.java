package pa1.HAstaff;


import pa1.City;
import pa1.Player;
import pa1.containment.*;
import pa1.exceptions.BudgetRunoutException;
import pa1.exceptions.NoEnoughBudgetException;
import pa1.util.Constants;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public abstract class HealthAuthorityStaff {

    // Attributes
    protected final int leadership, medicine, experience;
    private boolean isReady = false;

    /**
     * Initializes the attributes of a HAstaff
     * @param leadership
     * @param medicine
     * @param experience
     */
    public HealthAuthorityStaff(int leadership, int medicine, int experience) {
        this.leadership = leadership;
        this.medicine = medicine;
        this.experience = experience;
    }

    /**
     * @return Whether or not the minister is ready
     */
    public boolean isReady() {
        // TODO
        return isReady;
    }

    /**
     * Changes isReady to true
     */
    public void beginTurn() {
        // TODO
        isReady = true;
    }

    /**
     * Changes isReady to false
     */
    public void endTurn() {
        // TODO
        isReady = false;
    }

    protected int getBonusPoints(){
        return 0;
    }

    /**
     * Develop a medication facility in the city
     * 1. Get the cost of developing a facility
     * 2. Check whether player has enough budget
     * 3. If not, throw an exception
     * 4. Subtract the cost from the player's budget
     * 5. Add number of facilities in the city by one
     * 6. set medication level to 100 if active =0
     *                         or recovered * 100/ (active + recovered)
     * <p>
     * HINT:
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void developMedicationFacility(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        int facilityCost = Constants.MEDICATION_FACILITY_COST;
        boolean cantDevelopFacility = facilityCost > player.getBudget();
        if (cantDevelopFacility) throw new NoEnoughBudgetException(player, facilityCost);
        player.decreaseBudget(facilityCost);
        city.addMedicationFacilities();
        city.decreaseActiveCases(Constants.MEDICATION_FACILITY_CAPACITY );

        //update the medication level
        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Treatment){
                alreadyExists = true;
                break;
            }
        }

        if (!alreadyExists){
            Treatment treat = new Treatment();
            player.addContainmentTech(treat);
        }

        //update medication level: medication level = #med facilities * capacity * 100/ #recovered cases
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Treatment) {
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).setMedication_level((city.getRecoveredCases() * 100) / (city.getActiveCases() + city.getRecoveredCases()));
            }
        }

    }


    /**
     * Build a mask factory in the city
     * 1. check the sufficiency of player's budget
     * 2. if not sufficient, throw exception, else decrease budget according to build cost
     * 3. set initial protection level for 'face mask' containment tech
     * <p>
     * HINT:
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void buildMasksFactory(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        int maskFactoryCost = Constants.BUILD_MASK_FACTORY_COST;
        boolean cantBuildMedFactory = maskFactoryCost > player.getBudget();
        if (cantBuildMedFactory) throw new NoEnoughBudgetException(player, maskFactoryCost);
        player.decreaseBudget(maskFactoryCost);

        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof FaceMask){
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            FaceMask fmask = new FaceMask();
            player.addContainmentTech(fmask);
        }

        //update protection level
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof FaceMask){
                player.incrementProtection_level(Constants.MASK_PROTECTION_Percentage,cont);
            }
        }

    }

    /**
     * upgrade quality of face masks
     * 1. check the sufficiency of player's budget
     * 2. if not sufficient, throw exception, else decrease budget according to build cost
     * 3. set initial protection level for 'face mask' containment tech
     * <p>
     * HINT:
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void upgradeFMaskQuality(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        int upgradeMaskQualityCost = Constants.UPGRADE_MASK_QUALITY_COST;
        boolean cantUpgradeMaskQuality = upgradeMaskQualityCost > player.getBudget();
        if (cantUpgradeMaskQuality) throw new NoEnoughBudgetException(player, upgradeMaskQualityCost);
        player.decreaseBudget(upgradeMaskQualityCost);

        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof FaceMask){
                player.incrementProtection_level(Constants.UPGRADE_MASK_PROTECTION_Percentage,cont);
            }
        }
    }

    /**
     * Develop a Vaccine
     * 1. check the sufficiency of player's budget
     * 2. if not sufficient, throw exception, else decrease budget according to upgrade cost
     * 3. update the vaccination level of containment tech
     * <p>
     * HINT:
     *
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void developVaccine(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        int developVaccineCost = Constants.DEVELOP_VACCINE_COST;
        boolean cantDevelopVaccine = developVaccineCost > player.getBudget();

        if (cantDevelopVaccine) throw new NoEnoughBudgetException(player, developVaccineCost);

        //update the vaccination level
        player.decreaseBudget(developVaccineCost);
        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Vaccination){
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            Vaccination vacc = new Vaccination();
            player.addContainmentTech(vacc);
        }

        //update vaccination level
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Vaccination){
                player.incrementVaccination_level(Constants.DEVELOP_VACCINE_Percentage,cont);
            }
        }

    }

    /**
     * Upgrade a Vaccine
     * 1. check the sufficiency of player's budget
     * 2. if sufficient, decrease budget according to upgrade cost
     * 3. update the vaccination level of containment tech
     * <p>
     * HINT:
     *
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void upgradeVaccine(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        int upgradeVaccineCost = Constants.UPGRADE_VACCINE_COST;
        boolean cantUpgradeVaccine = upgradeVaccineCost > player.getBudget();
        if (cantUpgradeVaccine) throw new NoEnoughBudgetException(player, upgradeVaccineCost);

        //update vaccination level
        player.decreaseBudget(upgradeVaccineCost);
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Vaccination){
                player.incrementVaccination_level(Constants.UPGRADE_VACCINE_Percentage,cont);
            }
        }
    }

    /**
     * Ban Travel
     * 1. set city's travelBanned to true
     * 2. if doesn't, add Isolation to player containment techniques
     * 3. set initial protection level for 'isolation' containment tech
     * <p>
     * HINT:
     * @param player
     * @param city
     */
    public void banTravel(Player player, City city) {
        // TODO
        city.setTravelBanned(true);

        //update the protection level
        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Isolation){
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            Isolation iso = new Isolation();
            player.addContainmentTech(iso);
        }

        //update protection level
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Isolation){
                player.incrementProtection_level(Constants.TRAVELBAN_PROTECTION_Percentage, cont);
            }
        }
    }


    /**
     * Lift the travel ban
     * 1. set city's travelBanned to false
     * 2. if exists, remove Isolation to player containment techniques
     * <p>
     * HINT:
     * @param player
     * @param city
     */
    public void liftTravelBan(Player player, City city) {
        // TODO
        //update the protection level
        city.setTravelBanned(false);

        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Isolation){
                player.removeContainmentTech(cont);
            }
        }
    }

    /**
     * Example string representation:
     * "HAStaff | READY, leadership, medicine, experience" - when isReady() == true
     * "HAStaff | DONE, leadership, medicine, experience" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("HAStaff | %s, leadership %d, medicine %d, experience %d", isReady() ? "READY" : "DONE",leadership, medicine, experience);
        return toStr;
    }
}

