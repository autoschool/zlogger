package zlogger.logic.dao;

import java.util.List;

public interface GenericDao<K, E> {

    public List<E> list();

    public E get(K key);

    public void delete(E entity);

    public K create(E entity);

    public K update(E entity);

}
