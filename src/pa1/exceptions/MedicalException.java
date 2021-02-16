package pa1.exceptions;

import pa1.Player;

/**
 * Exception thrown when player does not have enough budget to afford a cost
 */
public class MedicalException extends Exception {

    private Player player = null;
    private final int population;
    private final int infectedCases;
    /**
     * Initializes member variables
     *
     * @param player
     */
    public MedicalException(Player player) {
        this.player = player;
        this.population = player.getCity().getPopulation();
        this.infectedCases = player.getCity().getInfectedCases();
    }

    /**
     * Initializes member variables
     *
     * @param population
     * @param infectedCases
     */
    public MedicalException(int population, int infectedCases) {
        this.population = population;
        this.infectedCases = infectedCases;
    }

    /**
     * Constructs an error message in the form:
     * Infected cases %d reached city's population %d
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("Infected cases %d reached city's population %d",
                infectedCases, population);
    }

    @Override
    public String toString() {
        return String.format("MedicalException");
    }
}
