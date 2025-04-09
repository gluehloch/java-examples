package generics;

/**
 * Zwingt die Klasse {@link InterfaceExpandingItself} in der Methode
 * {@link #getSomeClass()} den eigenen Klassentyp wiederzugeben.
 * 
 * @param <T>
 */
public interface InterfaceExpandingItself<T extends InterfaceExpandingItself<T>> {

    InterfaceExpandingItself<T> getSomeClass();

    T self();

    int compareTo(T t);

}
