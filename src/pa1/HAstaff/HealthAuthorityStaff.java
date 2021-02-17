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
     * Build a hospital in the city
     * 1. Get the cost of building a hospital
     * 2. Check whether player has enough budget
     * 3. If not, throw an exception
     * 4. Subtract the cost from the player's budget
     * 5. Add number of hospital in the city by one
     * <p>
     * HINT:
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void buildHospital(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        int hospitalCost = Constants.BUILD_HOSPITAL_COST;
        boolean cantBuildHospital = hospitalCost > player.getBudget();
        if (cantBuildHospital) throw new NoEnoughBudgetException(player, hospitalCost);
        player.decreaseBudget(hospitalCost);
        city.addHospital();

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

        //update medication level: medication level = #hospitals * capacity * 100/ #infected cases
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Treatment) {
                int index = player.getContainTechniques().indexOf(cont);
                if (city.getInfectedCases() - city.getHospitals() * Constants.HOSPITAL_CAPACITY <= 0)
                    player.getContainTechniques().get(index).setMedication_level(100);
                else {
                    player.getContainTechniques().get(index).setMedication_level((city.getHospitals() * Constants.HOSPITAL_CAPACITY * 100) / city.getInfectedCases());
                }
                System.out.printf(" medication level: %d\n", player.getContainTechniques().get(index).getMedication_level());
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
                System.out.printf("Before Build Mask Factory: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementProtection_level(Constants.MASK_PROTECTION_Percentage);
                System.out.printf(" After Build Mask Factory: %s\n",cont);
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
                System.out.printf("Containment Before Upgrade Mask Quality: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementProtection_level(Constants.UPGRADE_MASK_PROTECTION_Percentage);
                System.out.printf(" After Upgrade Mask Quality: %s\n",cont);
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
        city.setVaccineAvailable(true);
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
                System.out.printf("Before Develop Vaccine: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementVaccination_level(Constants.DEVELOP_VACCINE_Percentage);
                System.out.printf(" After Develop Vaccine: %s\n",cont);
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
                System.out.printf("Before vaccine Upgrade: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementVaccination_level(Constants.UPGRADE_VACCINE_Percentage);
                System.out.printf(" After vaccine Upgrade: %s\n",cont);
            }
        }
    }

    /**
     * Ban Travel
     * 1. update city's travelBanned to true
     * <p>
     * HINT:
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws BudgetRunoutException
     */
    public void banTravel(Player player, City city) throws NoEnoughBudgetException, BudgetRunoutException {
        // TODO
        if (player.getBudget()<Constants.MIN_ALLOWED_BUDGET)
            throw new BudgetRunoutException(player);

        boolean cantBanTravel = (player.getBudget() - player.getTourismIncome()) < 0;

        if (cantBanTravel) throw new NoEnoughBudgetException(player, player.getTourismIncome());

        //update the vaccination level
        player.decreaseBudget(player.getTourismIncome());
        city.setTravelBanned(true);

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
                System.out.printf("Containment Before TravelBan: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementProtection_level(Constants.TRAVELBAN_PROTECTION_Percentage);
                System.out.printf(" After TravelBan: %s\n",cont);
            }
        }

    }

    /**
     * Example string representation:
     * "HAStaff | READY" - when isReady() == true
     * "HAStaff | DONE" - when isReady() == false
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
