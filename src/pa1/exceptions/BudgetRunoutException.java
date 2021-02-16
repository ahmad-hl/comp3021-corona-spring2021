package pa1.exceptions;

import pa1.Player;

/**
 * Exception thrown when player does not have enough budget to afford a cost
 */
public class BudgetRunoutException extends Exception {

    private final float playerBudget;
    /**
     * Initializes member variables
     *
     * @param player
     */
    public BudgetRunoutException(Player player) {
        this.playerBudget = player.getBudget();
    }

    /**
     * Constructs an error message in the form:
     * "need (%d cost), have (%d budget)"
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("run out of budget %d ", playerBudget);
    }
}
