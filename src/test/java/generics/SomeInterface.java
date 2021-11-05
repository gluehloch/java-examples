package generics;

/**
 * Zwingt die Klasse {@link SomeInterface} in der Methode {@link #getSomeClass()} den eigenen Klassentyp wiederzugeben.
 * 
 * @param <T>
 */
public interface SomeInterface<T extends SomeInterface<T>> {

    SomeInterface<T> getSomeClass();

}
