package pa1;

import pa1.containment.Containment;
import pa1.directors.Director;
import pa1.directors.HealthMinister;
import pa1.exceptions.AddedContTechException;
import pa1.exceptions.NegativeValException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * A class that represents a player in the game.
 * It contains the player's attribute such as budget and points,
 * as well as the player's assets such as ministers, cities, and virus containment techniques.
 */
public class Player {

    // Assets
    private final List<Director> directors = new ArrayList<>();
    private final List<City> cities = new ArrayList<>();
    private final HashMap<String, Containment> containTechniques = new HashMap<String, Containment>();

    // Attributes
    private final String name;
    private float budget;
    private int tourismIncome;
    private int points;

    /**
     * Initializes member variables.
     */
    public Player(String name, float budget, int tourismIncome, int points) {
        this.name = name;
        this.budget = budget;
        this.tourismIncome = tourismIncome;
        this.points = points;
    }

    @Override
    public String toString() {
        String toStr = String.format("Player: %s | budget: %f | tourism income: %d | points: %d",
                name, budget, tourismIncome, points);
//        cities.forEach(City::toString);
//        ministers.forEach(HealthMinister::toString);

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
    public void addPoint(int increment) throws NegativeValException {
        // TODO
        if (increment < 0)
            throw new NegativeValException(increment);
        else
            points += increment;
    }

    /**
     * Decreases the player's points
     * Cap the value to 0.
     *
     * @param decrement
     */
    public void decreasePoint(int decrement) {
        // TODO
        points = Math.max(0, points - decrement);
    }

    /**
     * Decreases the player's budget by tourismIncome (travelbanned)
     * Cap the value to 0.
     */
    public void deductTourismIncome() {
        // TODO
        budget = Math.max(0, budget - tourismIncome);
    }

    /**
     * @return true if the player has at least one ready director
     */
    public boolean hasReadyDirector() {
        // TODO
        return getDirectors().stream().anyMatch(Director::isReady);
    }

    /**
     * Adds a containment technique.
     *
     * @param cont
     */
    public void addContainmentTech(Containment cont) throws AddedContTechException {
        // TODO
        if (!containTechniques.containsKey(cont.getName()))
            containTechniques.put(cont.getName(), cont);
        else
            throw new AddedContTechException(cont.getName());
    }

    // Trivial getters


    public List<Director> getDirectors() {
        return directors;
    }

    public List<City> getCities() {
        return cities;
    }

    public HashMap<String, Containment> getContainTechniques() {
        return containTechniques;
    }

    public String getName() {
        return name;
    }

    public float getBudget() {
        return budget;
    }

    public int getTourismIncome() {
        return tourismIncome;
    }

    public int getPoints() {
        return points;
    }
}
