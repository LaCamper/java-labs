
package javalabs.lab2.sportsequipment.crud;

import javalabs.lab2.sportsequipment.BaseEntity;

import java.util.Collection;

public final class EntityPrinter {

    private EntityPrinter() {
    }

    public static void printAll(
            Collection<? extends BaseEntity> items) {

        for (BaseEntity entity : items) {
            System.out.println(
                "ID: " + entity.getId()
                + " | Назва: " + entity.getName()
            );
        }
    }
}
