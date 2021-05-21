package generics;

/**
 * Vergleiche mit {@link ExampleInterface}. Dort erzwingt der Typparameter, dass er vom Typ der Schnittstelle sein
 * muss. Hier kann der Typparameter ein beliebiger Typ sein.
 * 
 * @author winkler
 */
public class ExampleClass implements ExampleInterface<ExampleClass> {

    @Override
    public ExampleClass self() {
        return this;
    }

}
