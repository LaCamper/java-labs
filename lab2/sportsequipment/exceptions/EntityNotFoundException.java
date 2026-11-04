
package javalabs.lab2.sportsequipment.exceptions;

public class EntityNotFoundException
        extends RuntimeException {

    private final long id;

    public EntityNotFoundException(long id) {
        super("Сутність з ID " + id + " не знайдена");
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
