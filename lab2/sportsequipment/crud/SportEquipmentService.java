
package javalabs.lab2.sportsequipment.crud;

import javalabs.lab2.sportsequipment.SportEquipment;

import java.util.List;

public interface SportEquipmentService
        extends CRUDService<SportEquipment> {

    List<SportEquipment>
        findAllSortedByNameAndByPrice();
}
