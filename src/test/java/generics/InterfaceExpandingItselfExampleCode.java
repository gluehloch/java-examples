package generics;

import java.util.ArrayList;
import java.util.List;

public class InterfaceExpandingItselfExampleCode {

    public SomeEnum getEnum() {
        return SomeEnum.ONE;
    }

    public void doSomething() {
        AnotherClass anotherClass = new AnotherClass();
        AndAnotherClass andAnotherClass = new AndAnotherClass();
        AnotherAnotherClass anotherAnotherClass = new AnotherAnotherClass();

        InterfaceExpandingItself<AnotherClass> another = anotherClass.getSomeClass();
        AnotherClass anotherX = anotherClass.self();
        // AnotherClass anotherX = anotherClass.getSomeClass(); // Does not compile!

        InterfaceExpandingItself<AndAnotherClass> andAnother = andAnotherClass.getSomeClass();
        AndAnotherClass andAnotherX = andAnotherClass.self();
        AndAnotherClass andAnotherX1 = andAnotherClass.getSomeClass(); // Compiles! Siehe Klassendefinition unten.

        InterfaceExpandingItself<AnotherAnotherClass> anotherAnother = anotherAnotherClass.getSomeClass();

        List<InterfaceExpandingItself<?>> list = new ArrayList<InterfaceExpandingItself<?>>();
        list.add(another);
        list.add(andAnother);
        list.add(anotherAnother);

        InterfaceExpandingItself<?> aObjectA = list.get(0).getSomeClass();
        InterfaceExpandingItself<?> aObjectB = aObjectA.getSomeClass().getSomeClass();
    }

    public static class AnotherClass implements InterfaceExpandingItself<AnotherClass> {
        @Override
        public InterfaceExpandingItself<AnotherClass> getSomeClass() {
            return this;
        }

        @Override
        public AnotherClass self() {
            return this;
        }

        @Override
        public int compareTo(AnotherClass t) {
            return 0;
        }
    }

    public static class AndAnotherClass implements InterfaceExpandingItself<AndAnotherClass> {
        @Override
        public AndAnotherClass getSomeClass() {
            return this;
        }

        @Override
        public AndAnotherClass self() {
            return this;
        }

        @Override
        public int compareTo(AndAnotherClass t) {
            return 0;
        }
    }

    public static class AnotherAnotherClass implements InterfaceExpandingItself<AnotherAnotherClass> {
        @Override
        public AnotherAnotherClass getSomeClass() {
            return this;
        }

        @Override
        public AnotherAnotherClass self() {
            return this;
        }

        @Override
        public int compareTo(AnotherAnotherClass t) {
            return 0;
        }
    }

    public static class XxxClass implements InterfaceExpandingItself<AnotherClass> {
        @Override
        public InterfaceExpandingItself<AnotherClass> getSomeClass() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public AnotherClass self() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public int compareTo(AnotherClass t) {
            return 0;
        }
    }

}
