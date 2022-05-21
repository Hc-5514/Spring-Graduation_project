package com.graduation.styleguide.service;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.UserInfo;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.dto.UserSignupDto;
import com.graduation.styleguide.dto.UserUpdateDto;
import com.graduation.styleguide.handler.CustomValidationException;
import com.graduation.styleguide.repository.SubscribeRepository;
import com.graduation.styleguide.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserInfo mock_user;

    @Mock
    private PrincipalDetails principalDetails;

    @Mock
    private MultipartFile multipartFile;

    private UserInfo user;

    @BeforeEach
    public void setUp(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user = UserInfo.builder()
                .userID("test")
                .password(encoder.encode("test1"))
                .name("name")
                .auth("ROLE_USER")
                .email("test@test.com")
                .contact("01068105514")
                .address(null)
                .stylelistId(null)
                .build();
    }

    public UserSignupDto createUserSignupDto(){
        return UserSignupDto.builder()
                .userID(user.getUserID())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
    }

    @Test
    public void 회원가입_성공() throws Exception {
        // given
        UserSignupDto userSignupDto = createUserSignupDto();
        given(userRepository.findByUserID(any())).willReturn(null);
        given(userRepository.save(any())).willReturn(user);

        // when
        UserInfo saveUser = userService.save(userSignupDto);

        // then
        assertThat(saveUser).isNotNull();
        assertThat(userSignupDto.getEmail()).isEqualTo(saveUser.getEmail());
        assertThat(userSignupDto.getName()).isEqualTo(saveUser.getName());
    }

    @Test
    public void save_회원가입_실패() throws Exception {
        //given
        UserSignupDto userSignupDto = createUserSignupDto();
        given(userRepository.findByUserID(any())).willReturn(user);

        //when
        assertThrows(CustomValidationException.class, () -> {
            userService.save(userSignupDto);
        });
    }

    public UserUpdateDto createUserUpdateDto() {
        return UserUpdateDto.builder()
                .password(user.getPassword())
                .name("수정한 이름")
                .email(user.getEmail())
                .contact(user.getContact())
                .build();
    }

    @Test
    public void 회원정보수정_성공() throws Exception {
        //given
        UserUpdateDto userUpdateDto = createUserUpdateDto();
        given(userRepository.findById(any())).willReturn(java.util.Optional.ofNullable(user));
        given(principalDetails.getUserInfo()).willReturn(user);
        given(multipartFile.isEmpty()).willReturn(true);

        //when
        userService.update(userUpdateDto, multipartFile, principalDetails);

        //then
        assertThat(userUpdateDto.getName()).isEqualTo(user.getName());
        assertThat(userUpdateDto.getPassword()).isNotEqualTo(user.getPassword());
    }

    @Test
    public void 회원정보수정_실패() throws Exception {
        //given
        UserUpdateDto userUpdateDto = createUserUpdateDto();
        given(userRepository.findById(any())).willReturn(java.util.Optional.ofNullable(null));
        given(principalDetails.getUserInfo()).willReturn(user);

        // when
        assertThrows(CustomValidationException.class, () -> {
            userService.update(userUpdateDto, multipartFile, principalDetails);
        });
    }

    @Test
    public void getUserProfileDto_성공() throws Exception {
        //given


        //when


        //then

    }
}