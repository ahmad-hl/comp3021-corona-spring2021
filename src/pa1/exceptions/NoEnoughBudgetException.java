package pa1.exceptions;

import pa1.util.Cost;
import pa1.Player;

/**
 * Exception thrown when player does not have enough budget to afford a cost
 */
public class NoEnoughBudgetException extends Exception {

    private final int cost;
    private final float playerBudget;
    /**
     * Initializes member variables
     *
     * @param player
     * @param cost
     */
    public NoEnoughBudgetException(Player player, int cost) {
        this.cost = cost;
        this.playerBudget = player.getBudget();
    }

    /**
     * Constructs an error message in the form:
     * "need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points)"
     *
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("need %d cost, have only %f budget",
                cost, playerBudget);
    }
}
