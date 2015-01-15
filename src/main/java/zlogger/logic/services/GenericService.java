package zlogger.logic.services;

import org.springframework.security.access.prepost.PreAuthorize;
import zlogger.logic.models.PagedList;

import java.util.List;

public interface GenericService<E, K> {

    public List<E> list();

    public PagedList<E> list(int pageNumber, int pageSize);

    public Long countAll();

    public K add(E entity);

    public E get(K key);

    @PreAuthorize("hasPermission(#entity, 'WRITE')")
    public K update(E entity);

    @PreAuthorize("hasPermission(#entity, 'WRITE')")
    public void delete(E entity);

}
