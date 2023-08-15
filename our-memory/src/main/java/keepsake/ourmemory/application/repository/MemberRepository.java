package keepsake.ourmemory.application.repository;

import keepsake.ourmemory.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
