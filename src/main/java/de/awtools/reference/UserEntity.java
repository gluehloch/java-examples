package de.awtools.reference;

public class UserEntity {

    private final Long id;
    private final TaxId taxId;
    private final String name;
    private final String surname;

    public UserEntity(Long id, TaxId taxId, String name, String surname) {
        this.id = id;
        this.taxId = taxId;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public TaxId getTaxId() {
        return taxId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public static class UserReference implements Reference<TaxId, UserEntity> {

        private final TaxId taxId;

        public UserReference(TaxId taxId) {
            this.taxId = taxId;
        }
        
        public TaxId getTaxId() {
            return taxId;
        }

        @Override
        public UserEntity getEntity() {
            return null;
        }

        @Override
        public TaxId getReference() {
            return null;
        }
    }

}
