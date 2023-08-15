package keepsake.ourmemory.config;

import keepsake.ourmemory.application.repository.MemberRepository;
import keepsake.ourmemory.domain.member.Email;
import keepsake.ourmemory.domain.member.Member;
import keepsake.ourmemory.domain.member.MemberName;
import keepsake.ourmemory.domain.member.Password;
import keepsake.ourmemory.domain.member.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile({"dev", "prod"})
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final InitService initService;

    @Override
    public void run(final String... args) {
        initService.init();
    }

    @Component
    @RequiredArgsConstructor
    private static class InitService {
        private final MemberRepository memberRepository;

        @Transactional
        public void init() {
            Member member = new Member(
                    new MemberName("아이크"),
                    new Email("a@a.com"),
                    new Password("아이크짱"),
                    new PhoneNumber("01012341234")
            );
            memberRepository.save(member);
        }
    }
}
