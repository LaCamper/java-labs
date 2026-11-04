
package javalabs.lab2.sportsequipment.crud;

import javalabs.lab2.sportsequipment.SportEquipment;

import java.util.Comparator;
import java.util.List;

public class SportEquipmentCrudService
        extends InMemoryCrudService<SportEquipment>
        implements SportEquipmentService {

    @Override
    public List<SportEquipment>
            findAllSortedByNameAndByPrice() {

        Comparator<SportEquipment> comparator =
            Comparator.comparing(
                SportEquipment::getName,
                String.CASE_INSENSITIVE_ORDER
            ).thenComparingDouble(
                SportEquipment::getPricePerUnit
            );

        return findAllSorted(comparator);
    }
}
