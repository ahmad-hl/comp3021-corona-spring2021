package pa1.exceptions;

import pa1.Player;

/**
 * Exception thrown when player does not have enough budget to afford a cost
 */
public class MedicalException extends Exception {

    private final int population;
    private final int infectedCases;
    /**
     * Initializes member variables
     *
     * @param player
     */
    public MedicalException(Player player) {
        this.population = player.getCity().getPopulation();
        this.infectedCases = player.getCity().getInfectedCases();
    }

    /**
     * Initializes member variables
     *
     * @param player
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
}
