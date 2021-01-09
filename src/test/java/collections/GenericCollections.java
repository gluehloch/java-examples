package collections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Chapter 5 GENERICS. Use bounded wildcards to increase API flexibility.
 * 
 * PECS stands for producer-extends, consumer-super.
 *
 * Was bedeutet das? Die Begrifflichkeit ist eher verwirrend. 'extends'
 * scheint dann sinnvoll zu sein, wenn die Methode die Elemente aus
 * der Collection verarbeitet, ohne die Collection selbst zu veraendern.
 * 
 * @author winkler
 */
public class GenericCollections {

    @Test
    void genericProducerCollections() {
        var doItA = new DoItA();
        var doItB = new DoItB();
        
        List<SuperClass> doits = List.of(doItA, doItB);        
        doSomething(doits);
        
        List<DoItA> doitA = List.of(doItA);
        doSomething(doitA);
        
        List<DoItB> doitB = List.of(doItB);
        doSomething(doitB);
        
        doSomethingOnSuper(doits); // Ok!
        // doSomethingOnSuper(doitA); // Don´t work!
        // doSomethingOnSuper(doitB); // Don´t work!
        
        List<? extends SuperClass> superClasses = copy(doits); // Ok!
        // List<SuperClass> superClasses2 = copy(doits); // Don´t work!
        
        List<? extends SuperClass> someOtherSupers = create();
        var someOtherVarSupers = create();
        someOtherVarSupers.forEach(SuperClass::doIt);
    }
    
    @Test
    void genericConsumerCollections() {
        var doits = List.of(new DoItA(), new DoItB());
        List<Object> objects = new ArrayList<>();
        
        Stack<SuperClass> superStack = new Stack<>(doits);
        superStack.moveTo(objects);
        // superStack.moveToWithoutSuperGenericParam(objects); // Don´t work!
    }
    
    /**
     * Ist das ein Consumer oder ein Producer? Da extends hier sinnvoll ist,
     * muss es sich um einen Producer handeln.
     *
     * @param doits
     */
    private void doSomething(List<? extends SuperClass> doits) {
        doits.forEach(SuperClass::doIt);
    }
    
    /**
     * Another consumer: super.
     *
     * @param doits
     */
    private void doSomethingOnSuper(List<SuperClass> doits) {
        doits.forEach(SuperClass::doIt);
    }
    
    private List<? extends SuperClass> copy(List<? extends SuperClass> doits) {
        List<SuperClass> collection1 = doits.stream().collect(Collectors.toList()); // With and without '? extends'.
        List<? extends SuperClass> collection = doits.stream().collect(Collectors.toList());
        return collection;
    }
    
    private List<? extends SuperClass> create() {
        return new ArrayList<SuperClass>();
    }
    
    // -- The super generic classes --------------------
    
    private static interface SuperClass {
        String doIt();
    }
    
    private static class DoItA implements SuperClass {
        @Override
        public String doIt() {
            return "doItA";
        }
    }
    
    private static class DoItB implements SuperClass {
        @Override
        public String doIt() {
            return "doItB";
        }
    }
    
    // -------------------------------------------------
    
    private static class Stack<T> {
        private final List<T> doits;
        public Stack(List<T> doits) {
            this.doits = doits;
        }
        public void moveTo(List<? super T> moveToMe) {
            moveToMe.addAll(doits);
        }
        
        public void moveToWithoutSuperGenericParam(List<T> moveToMe) {
            moveToMe.addAll(doits);
        }
    }

}
