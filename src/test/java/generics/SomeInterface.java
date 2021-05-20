package generics;

public interface SomeInterface<T extends SomeInterface<T>> {
    
    SomeInterface<T> getSomeClass();

}
