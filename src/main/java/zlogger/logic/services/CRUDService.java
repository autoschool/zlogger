package zlogger.logic.services;

import java.util.List;

public interface CRUDService<E, K> {

    public List<E> list();

    public K add(E entity);

    public E get(K key);

    public K update(E entity);

    public void delete(K key);

}
