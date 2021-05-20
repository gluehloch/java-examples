package generics;

public class SomeClass implements SomeInterface<SomeClass> {

    public SomeClass getSomeClass() {
        return this;
    }
    
    public SomeEnum getEnum() {
        return SomeEnum.ONE;
    }
    
}
