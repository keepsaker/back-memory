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
public class PhoneNumber {
    @Column(nullable = false)
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final PhoneNumber that = (PhoneNumber) other;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
