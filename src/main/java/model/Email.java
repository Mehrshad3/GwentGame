package model;

import java.util.Objects;

public record Email(String email) {
    @Override
    public String toString() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof String email1) return this.email.equals(email1);
        if (!(o instanceof Email email1)) return false;
        return Objects.equals(email, email1.email);
    }
}