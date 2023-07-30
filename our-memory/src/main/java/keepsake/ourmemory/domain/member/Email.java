package keepsake.ourmemory.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    private static final String DELIMITER = "@";

    @Column(nullable = false)
    private String email;

    public Email(String email) {
        validateEmail(email);
        this.email = email;
    }

    private void validateEmail(String email) {
        if (!email.contains(DELIMITER)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Email email1 = (Email) other;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                '}';
    }
}
