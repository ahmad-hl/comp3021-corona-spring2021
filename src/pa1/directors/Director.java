package pa1.directors;


import pa1.City;
import pa1.Player;
import pa1.exceptions.NegativeValException;
import pa1.exceptions.NoEnoughBudgetException;
import pa1.util.Constants;
import pa1.util.Cost;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public abstract class Director{

    // Attributes
    protected final int leadership, experience, science;
    private boolean isReady = false;

    /**
     * Initializes the attributes of a minister
     */
    public Director() {
        leadership = 1;
        experience = 1;
        science = 1;
    }

    /**
     * Initializes the attributes of a minister
     * @param leadership
     * @param experience
     * @param science
     */
    public Director(int leadership, int experience, int science) {

        this.leadership = leadership;
        this.experience = experience;
        this.science = science;
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

    public int getPromotion(Player player){
        return 1;
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
    public void buildHospital(Player player, City city) throws NoEnoughBudgetException, NegativeValException {
        // TODO

        int hospitalCost = Cost.getHospitalCost();

        boolean cantBuildHospital = hospitalCost > player.getBudget();

        if (cantBuildHospital) throw new NoEnoughBudgetException(player, hospitalCost);

        player.decreaseBudget(hospitalCost);
        player.addPoint(Constants.BUILD_HOSPITAL_POINTS);
        city.addHospital();
    }


    /**
     * Build a mask factory in the city
     * 1. Get the cost of building a mask factory, with applied minister discount
     * 2. Check whether player has enough gold and production points
     * 3. If not, throw an exception
     * 4. Subtract the cost from the player's budget
     * 5. Add number of university in the city by one
     * <p>
     * HINT:
     * - the Cost class has a getDiscountedCost() method
     * - the Minister class has a getImprovementDiscountRate() method, to obtain the
     * discount rate of building a medicine factory
     *
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     */
    public void buildMasksFactory(Player player, City city) throws NoEnoughBudgetException, NegativeValException {
        // TODO

        int maskFactoryCost = Cost.getMasksFactoryCost();

        boolean cantBuildMedFactory = maskFactoryCost > player.getBudget();

        if (cantBuildMedFactory) throw new NoEnoughBudgetException(player, maskFactoryCost);

        player.decreaseBudget(maskFactoryCost);
        player.addPoint(Constants.BUILD_MASK_FACTORY_POINTS);
        city.addMaskFactory();
    }

    /**
     * Develop a Vaccine
     * 1. update city's travelBanned to true
     * <p>
     * HINT:
     *
     * @param player
     * @param city
     */
    public void developVaccine(Player player, City city) throws NegativeValException {
        // TODO
        city.setVaccineAvailable(true);
        player.addPoint( Constants.DEVELOP_VACCINE_POINTS);
    }

    /**
     * Ban Travel
     * 1. update city's travelBanned to true
     * <p>
     * HINT:
     *
     * @param player
     * @param city
     */
    public void banTravel(Player player, City city) throws NegativeValException {
        // TODO
        city.setTravelBanned(true);
        player.addPoint( Constants.BAN_TRAVEL_POINTS);
    }

    /**
     * This method is called when you want to transfer cases to another city.
     * <p>
     * Print the information of a City's neighbors
     * <p>
     * HINT:
     * - GameMap class has a getNeighboringCities() method
     * - City class overrides the toString() method which returns the
     * String representation of a city
     *
     * @param source, dest
     */
    public void TransferInfectedCases(Player player, City source, City dest, int cases) throws NegativeValException {
        // TODO
        if (!dest.isTravelBanned()){
            dest.increaseInfectedCases(cases);
            source.decreaseInfectedCases(cases);
            player.addPoint( Constants.TRANSFER_ACASE_POINTS * cases);
        }
    }

    /**
     * Example string representation:
     * "Director | READY" - when isReady() == true
     * "Director | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("Director | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}

