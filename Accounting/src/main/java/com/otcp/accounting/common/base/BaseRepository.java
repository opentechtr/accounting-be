package com.otcp.accounting.common.base;

import com.otcp.accounting.common.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Long> extends JpaRepository<T, ID > {

    default T getById(ID id) {
        return findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    Optional<T> findByGuid(String guid);

    Optional<T> findByIdAndEntityStatus(ID id, EntityStatus entityStatus);

    Optional<T> findByGuidAndEntityStatus(String guid, EntityStatus entityStatus);

    default T getByGuid(String guid) {
        return findByGuid(guid)
                .orElseThrow(EntityNotFoundException::new);
    }

    List<T> findAllByEntityStatus(EntityStatus entityStatus);

    default void activate(T entity) {
        if (EntityStatus.DELETED.equals(entity.getEntityStatus())) {
            entity.activate();
            save(entity);
        }
    }

    default void delete(T entity) {
        if (EntityStatus.ACTIVE.equals(entity.getEntityStatus())) {
            entity.delete();
            save(entity);
        }
    }
}
