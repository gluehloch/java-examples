package generics;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GenericContainerTest {

    interface Contained {
    }

    interface Container<T extends Contained> {
        void add(T element);

        List<T> elements();

        Class<T> getElementType();
    }

    class MyContained implements Contained {
        private final String name;

        public MyContained(String name) {
            this.name = name;
        }

        public @Override String toString() {
            return name;
        }
    }

    class MyContainer2 implements Container<MyContained> {
        private final List<MyContained> _elements = new ArrayList<MyContained>();

        public void add(MyContained element) {
            _elements.add(element);
        }

        public List<MyContained> elements() {
            return _elements;
        }

        public Class<MyContained> getElementType() {
            return MyContained.class;
        }
    }

    class MetaContainer {
        private Container<? extends Contained> container;

        public void setContainer(Container<? extends Contained> container) {
            this.container = container;
        }

        //public void add2(Contained element) {
        //    container.add(container.getElementType().cast(element)); // error
        //}

        public void add(Contained element) {
            _add(container, element);
        }

        public List<? extends Contained> elements() {
            return container.elements();
        }

        private static <T extends Contained> void _add(Container<T> container, Contained element) {
            container.add(container.getElementType().cast(element));
        }
    }

    @Test
    void testGenericContainer() {
        MyContainer2 container = new MyContainer2();
        container.add(new MyContained("Element 1"));
        container.add(new MyContained("Element 2"));

        List<MyContained> elements = container.elements();
        assertThat(elements).hasSize(2);
        assertThat(elements.get(0).toString()).isEqualTo("Element 1");
        assertThat(elements.get(1).toString()).isEqualTo("Element 2");
    }

}
