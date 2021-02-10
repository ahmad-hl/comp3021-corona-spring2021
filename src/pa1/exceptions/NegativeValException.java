package pa1.exceptions;

/**
 * Exception thrown when player does not have enough currencies to afford a cost
 */
public class NegativeValException extends Exception {

    private final float val;
    /**
     * Initializes member variables
     *
     * @param val
     */
    public NegativeValException(float val) {
        this.val = val;
    }


    @Override
    public String getMessage() {
        return String.format("require value >0, enter %d ", val);
    }
}
