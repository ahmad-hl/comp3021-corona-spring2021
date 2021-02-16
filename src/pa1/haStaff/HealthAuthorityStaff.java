package pa1.haStaff;


import pa1.City;
import pa1.Player;
import pa1.containment.*;
import pa1.exceptions.NoEnoughBudgetException;
import pa1.util.Constants;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public abstract class HealthAuthorityStaff {

    // Attributes
    private boolean isReady = false;

    /**
     * Initializes the attributes of a minister
     */
    public HealthAuthorityStaff() {
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
     */
    public void buildHospital(Player player, City city) throws NoEnoughBudgetException {
        // TODO
        int hospitalCost = Constants.BUILD_HOSPITAL_COST;
        boolean cantBuildHospital = hospitalCost > player.getBudget();
        if (cantBuildHospital) throw new NoEnoughBudgetException(player, hospitalCost);
        player.decreaseBudget(hospitalCost);
        city.addHospital();

        //update the medication level
        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Treatment){
                System.out.printf("Containment Before Build Hospital: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementMedication_level(Constants.HOSPITAL_MEDICATION_Percentage);
                System.out.printf(" After Build Hospital: %s\n",cont);
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            Treatment treat = new Treatment();
            treat.incrementMedication_level(Constants.HOSPITAL_MEDICATION_Percentage);
            player.addContainmentTech(treat);
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
     */
    public void buildMasksFactory(Player player, City city) throws NoEnoughBudgetException {
        // TODO
        int maskFactoryCost = Constants.BUILD_MASK_FACTORY_COST;
        boolean cantBuildMedFactory = maskFactoryCost > player.getBudget();
        if (cantBuildMedFactory) throw new NoEnoughBudgetException(player, maskFactoryCost);
        player.decreaseBudget(maskFactoryCost);

        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof FaceMask){
                System.out.printf("Before Build Mask Factory: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementProtection_level(Constants.MASK_PROTECTION_Percentage);
                System.out.printf(" After Build Mask Factory: %s\n",cont);
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            FaceMask fmask = new FaceMask();
            fmask.incrementProtection_level(Constants.MASK_PROTECTION_Percentage);
            player.addContainmentTech(fmask);
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
     */
    public void upgradeFMaskQuality(Player player, City city) throws NoEnoughBudgetException {
        // TODO
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
     */
    public void developVaccine(Player player, City city) throws NoEnoughBudgetException {
        // TODO
        int developVaccineCost = Constants.DEVELOP_VACCINE_COST;
        boolean cantDevelopVaccine = developVaccineCost > player.getBudget();

        if (cantDevelopVaccine) throw new NoEnoughBudgetException(player, developVaccineCost);

        //update the vaccination level
        city.setVaccineAvailable(true);
        player.decreaseBudget(developVaccineCost);
        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Vaccination){
                System.out.printf("Containment Before Develop Vaccine: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementVaccination_level(Constants.DEVELOP_VACCINE_Percentage);
                System.out.printf(" After Develop Vaccine: %s\n",cont);
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            Vaccination vacc = new Vaccination();
            vacc.incrementVaccination_level(Constants.DEVELOP_VACCINE_Percentage);
            player.addContainmentTech(vacc);
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
     */
    public void upgradeVaccine(Player player, City city) throws NoEnoughBudgetException {
        // TODO
        int upgradeVaccineCost = Constants.UPGRADE_VACCINE_COST;
        boolean cantUpgradeVaccine = upgradeVaccineCost > player.getBudget();
        if (cantUpgradeVaccine) throw new NoEnoughBudgetException(player, upgradeVaccineCost);

        //update the vaccination level
        player.decreaseBudget(upgradeVaccineCost);
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Vaccination){
                System.out.printf("Containment Before Upgrade: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementVaccination_level(Constants.UPGRADE_VACCINE_Percentage);
                System.out.printf(" After Upgrade: %s\n",cont);
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
     */
    public void banTravel(Player player, City city) throws NoEnoughBudgetException {
        // TODO
        boolean cantBanTravel = (player.getBudget() - player.getTourismIncome()) < 0;

        if (cantBanTravel) throw new NoEnoughBudgetException(player, player.getTourismIncome());

        //update the vaccination level
        player.decreaseBudget(player.getTourismIncome());
        city.setTravelBanned(true);

        boolean alreadyExists = false;
        for (Containment cont:player.getContainTechniques()) {
            if (cont instanceof Isolation){
                System.out.printf("Containment Before TravelBan: %s",cont);
                int index = player.getContainTechniques().indexOf(cont);
                player.getContainTechniques().get(index).incrementProtection_level(Constants.TRAVELBAN_PROTECTION_Percentage);
                System.out.printf(" After TravelBan: %s\n",cont);
                alreadyExists = true;
            }
        }

        if (!alreadyExists){
            Vaccination vacc = new Vaccination();
            vacc.incrementVaccination_level(Constants.DEVELOP_VACCINE_Percentage);
            player.addContainmentTech(vacc);
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
        String toStr = String.format("HAStaff | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}

