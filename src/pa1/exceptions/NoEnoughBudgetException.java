package pa1.exceptions;

import pa1.Player;

/**
 * Exception thrown when player does not have enough budget to afford a cost
 */
public class NoEnoughBudgetException extends Exception {

    private final Player player;
    private final int cost;
    private final float playerBudget;
    /**
     * Initializes member variables
     *
     * @param player
     * @param cost
     */
    public NoEnoughBudgetException(Player player, int cost) {
        this.player = player;
        this.cost = cost;
        this.playerBudget = player.getBudget();
    }

    /**
     * Constructs an error message in the form:
     * "need (%d cost), have (%d budget)"
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("need %d cost, have only %f budget",
                cost, playerBudget);
    }

    @Override
    public String toString() {
        return String.format("NoEnoughBudgetException for player %s", player.getName());
    }
}
