
package javalabs.lab2.sportsequipment.crud;

import javalabs.lab2.sportsequipment.BaseEntity;

import java.util.Comparator;
import java.util.List;

public interface CRUDService<T extends BaseEntity> {

    T findById(long id);

    T save(T entity);

    boolean delete(long id);

    List<T> findAllSorted(
        Comparator<? super T> comparator
    );

    default List<T> findAllSortedByName() {
        return findAllSorted(
            Comparator.comparing(
                BaseEntity::getName,
                String.CASE_INSENSITIVE_ORDER
            )
        );
    }
}
