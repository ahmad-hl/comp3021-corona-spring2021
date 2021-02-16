package pa1.directors;

import pa1.City;
import pa1.containment.*;
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
    public int getPromotion(Player player){
        int promotion = leadership + experience;

        for (Containment cont:player.getContainTechniques()) {
            if(cont instanceof Isolation)
                promotion *= 2;
        }
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
        player.addPoint(Constants.BUILD_HOSPITAL_POINTS * getPromotion(player));
        int pos = player.getContainTechniques().size();
        player.addContainmentTech(new Treatment(pos));
    }

    @Override
    public void buildMasksFactory(Player player, City city) throws NoEnoughBudgetException, NegativeValException {
        super.buildMasksFactory(player, city);
        player.addPoint(Constants.BUILD_MASK_FACTORY_COST * getPromotion(player));
        int pos = player.getContainTechniques().size();
        player.addContainmentTech(new FaceMask(pos));
    }

    /**
     * Apply Vaccination
     * 1. call parent's applyVaccination
     * 2. add new points according to promotion
     * <p>
     * HINT:
     *
     * @param player
     * @param city
     */
    public void applyVaccination(Player player, City city) throws NegativeValException {
        // TODO
        super.applyVaccination(player,city);
        for (Containment cont:player.getContainTechniques()) {
            if(cont instanceof Vaccination)
                player.addPoint( Constants.APPLY_VACCINE_POINTS * getPromotion(player));
        }
    }

    /**
     * Treat Infected Cases
     * 1.  call parent's treatInfectedCases
     * 2. add new points according to promotion
     * <p>
     * HINT:
     *
     * @param player
     * @param city
     */
    public void treatInfectedCases(Player player, City city) throws NegativeValException {
        // TODO
        super.treatInfectedCases(player, city);
        player.addPoint( Constants.TREAT_INFECTED_POINTS * getPromotion(player));
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

