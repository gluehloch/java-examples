package de.awtools.reference;

/**
 * TODO Macht das Sinn?
 * 
 * 
 * @author Andre Winkler
 *
 * @param <T> Reference / business key of the entity
 * @param <E> The entity it self
 */
public interface Reference<T,E> {

    E getEntity();

    T getReference();

}
