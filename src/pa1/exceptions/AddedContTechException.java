package pa1.exceptions;

/**
 * Exception thrown when player does not have enough currencies to afford a cost
 */
public class AddedContTechException extends Exception {

    private final String contName;
    /**
     * Initializes member variables
     *
     * @param contName
     */
    public AddedContTechException(String contName) {
        this.contName = contName;
    }

    @Override
    public String getMessage() {
        return String.format("Containment technique: %s was already added ", contName);
    }
}
