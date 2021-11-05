package de.awtools.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MethodReference {

    private final List<String> strings = new ArrayList<>();

    public void addThis(String string) {
        strings.add(string);
    }

    public void addThat(String string) {
        strings.add(string);
    }

    public List<String> getStrings() {
        return strings;
    }

    public static void calculate(MethodReference mr, Consumer<MethodReference> consumer) {
        consumer.accept(mr);
    }

    public static class MrConsumerThat implements Consumer<MethodReference> {
        @Override
        public void accept(MethodReference t) {
            t.addThat("that");
        }
    }

    public static class MrConsumerThis implements Consumer<MethodReference> {
        @Override
        public void accept(MethodReference t) {
            t.addThis("this");
        }
    }

    public static void main(String[] args) {
        MethodReference mr = new MethodReference();
        MethodReference.calculate(mr, new MrConsumerThis());
        MethodReference.calculate(mr, new MrConsumerThat());

        MethodReference.calculate(mr, (p) -> {
            p.addThis("this");
        });

        MethodReference.calculate(mr, (p) -> {
            p.addThat("that");
        });
    }

}
