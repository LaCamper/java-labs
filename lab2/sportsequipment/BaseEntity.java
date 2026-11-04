
package javalabs.lab2.sportsequipment;

import java.util.Objects;

public abstract class BaseEntity {

    private final long id;
    private String name;

    protected BaseEntity(long id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                "ID повинен бути додатним"
            );
        }

        this.id = id;
        this.name = Objects.requireNonNull(
            name, "Назва не може бути null"
        );
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    this.name = Objects.requireNonNull(
        name,
        "Назва не може бути null"
    );
}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BaseEntity other)) {
            return false;
        }

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
