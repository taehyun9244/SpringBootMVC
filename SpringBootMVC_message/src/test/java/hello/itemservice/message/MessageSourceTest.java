package hello.itemservice.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource msg;

    @Test
    void helloMessage(){
        String result = msg.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy( ()-> msg.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMeassage(){
        String result = msg.getMessage("no_code", null, "기본메세지", null);
        assertThat(result).isEqualTo("기본메세지");
    }

    @Test
    @DisplayName("이름 치환")
    void argumentMessage(){
        String result = msg.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    @DisplayName("국제화")
    void defaultLang(){
        assertThat(msg.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(msg.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    @DisplayName("영어 국제화")
    void enLang(){
        assertThat(msg.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

    @Test
    @DisplayName("일본어 국제화")
    void jpLang(){
        assertThat(msg.getMessage("hello", null, Locale.JAPAN)).isEqualTo("こんにちは");
    }
}
