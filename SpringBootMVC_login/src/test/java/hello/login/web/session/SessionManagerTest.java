package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.*;

class SessionManagerTest {

    SessionManger sessionManger = new SessionManger();

    @Test
    @DisplayName("세션의 생성, 쿠키 저장 전달")
    void sessionTest() {
        //생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManger.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //서버에서 세션조회
        Object result = sessionManger.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션 만료
        sessionManger.expire(request);
        Object expired = sessionManger.getSession(request);
        assertThat(expired).isNull();
    }
}
