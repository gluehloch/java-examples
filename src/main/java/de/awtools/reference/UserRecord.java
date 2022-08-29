package de.awtools.reference;

import java.util.Objects;

public record UserRecord(Long id, TaxId taxId, String name, String surname) implements Reference<TaxId , UserRecord> {

    public String toMessage() {
        return String.format("UserRecord=[%s, %s]", name, surname);
    }

    @Override
    public UserRecord getEntity() {
        return this;
    }

    @Override
    public TaxId getReference() {
        return this.taxId();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserRecord)) return false;
        UserRecord ur = (UserRecord) obj;
        return ur.taxId().equals(this.taxId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(taxId, name, surname);
    }

    @Override
    public String toString() {
        return this.toMessage();
    }

}
