package keepsake.ourmemory.domain.member;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import keepsake.ourmemory.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MemberName memberName;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private PhoneNumber phoneNumber;

    public Member(MemberName memberName,
                  Email email,
                  Password password,
                  PhoneNumber phoneNumber) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
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
        final Member member = (Member) other;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberName=" + memberName +
                ", email=" + email +
                ", password=" + password +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
