package de.awtools.string;

import java.util.Objects;

public class Nickname implements CharSequence {

    private final String value;

    private Nickname(String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public int length() {
        return value.length();
    }

    @Override
    public char charAt(int index) {
        return value.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return value.subSequence(start, end);
    }

    @Override
    public String toString() {
        return value;
    }
}
