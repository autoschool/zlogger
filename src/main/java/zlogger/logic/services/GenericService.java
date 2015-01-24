package zlogger.logic.services;

import zlogger.logic.models.PagedList;

import java.util.List;

public interface GenericService<E, K> {

    public List<E> list();

    public PagedList<E> list(int pageNumber, int pageSize);

    public Long countAll();

    public K add(E entity);

    public E get(K key);

    public K update(E entity);

    public void delete(E entity);

}
