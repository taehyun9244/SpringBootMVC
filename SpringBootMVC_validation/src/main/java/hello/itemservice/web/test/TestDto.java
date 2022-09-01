package hello.itemservice.web.test;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class TestDto {

    @Pattern(regexp = "")
    @NotNull(message = "이름은 필수입니다")
    private String username;

    @Pattern(regexp = "")
    @NotNull(message = "비밀번호 필수입니다")
    private String password;

    @Pattern(regexp = "")
    @NotNull(message = "주소 필수입니다")
    private String address;

    @Pattern(regexp = "")
    @NotNull(message = "전화번호 필수입니다")
    private String phoneNumber;

    @Email
    @NotNull(message = "이메일일 필수니다")
    private String email;
}
