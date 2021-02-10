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

    /**
     * Constructs an error message in the form:
     * "need (%d golds, %d production points, %d science points), have (%d golds, %d production points, %d science points)"
     *
     * @return
     */
    @Override
    public String getMessage() {
        return String.format("require value >0, enter %d ", val);
    }
}
