package pa1.directors;

import pa1.City;
import pa1.containment.Treatment;
import pa1.util.Constants;
import pa1.Player;

import pa1.exceptions.NoEnoughBudgetException;
import pa1.exceptions.NegativeValException;

/**
 * An abstract class that represents a minister in the game.
 * All actions in the game are done through ministers.
 * Therefore this class will contain the bulk of your implementation of the game.
 */
public class HealthMinister extends Director{

    /**
     * Initializes the attributes of a minister
     * @param leadership
     * @param experience
     * @param science
     */
    public HealthMinister(int leadership, int experience, int science) {
        super(leadership, experience, science);
    }

    @Override
    public int getPromotion(){
        int promotion = leadership + experience;
        System.out.printf("**** HealthMinister: getPromotion %d *****\n", promotion);
        return promotion;
    }

    /**
     * Override build a hospital in the city
     * 1. call parent build a hospital
     * 2. update player's points according to promotion
     * <p>
     * HINT:
     * @param player
     * @param city
     * @throws NoEnoughBudgetException
     * @throws NegativeValException
     */
    @Override
    public void buildHospital(Player player, City city) throws NoEnoughBudgetException, NegativeValException {
        // TODO
        super.buildHospital(player, city);
        player.addPoint(Constants.BUILD_HOSPITAL_POINTS * getPromotion());
        int pos = player.getContainTechniques().size();
        player.addContainmentTech(new Treatment(pos));
    }

    @Override
    public void buildMasksFactory(Player player, City city) throws NoEnoughBudgetException, NegativeValException {
        super.buildMasksFactory(player, city);
        player.addPoint(Constants.BUILD_MASK_FACTORY_COST * getPromotion());
        int pos = player.getContainTechniques().size();
        player.addContainmentTech(new Treatment(pos));
    }

    /**
     * Example string representation:
     * "HealthMinister | READY" - when isReady() == true
     * "HealthMinister | DONE" - when isReady() == false
     *
     * @return string representation of this object
     */
    @Override
    public String toString() {
        // TODO
        String toStr = String.format("HealthMinister | %s", isReady() ? "READY" : "DONE");
        return toStr;
    }
}

