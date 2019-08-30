package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.response.ProfileResDto;
import com.soma.dodam.dodami.dto.request.SignInReqDto;
import com.soma.dodam.dodami.dto.request.SignUpReqDto;
import com.soma.dodam.dodami.dto.response.SubscriptionResDto;
import com.soma.dodam.dodami.exception.AlreadyExistException;
import com.soma.dodam.dodami.exception.InvalidValueException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.exception.NotMatchException;
import com.soma.dodam.dodami.repository.SubscriptionRepository;
import com.soma.dodam.dodami.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PasswordEncoder passwordEncoder;

    private final static String EMAIL_REGEX;
    private final static String PASSWORD_REGEX;
    private final static String NAME_REGEX;
    private final static String PHONE_REGEX;

    static {
        EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        PASSWORD_REGEX = "^[a-zA-Z0-9]{8,20}$";
        NAME_REGEX = "^[가-힣]{2,}$";
        PHONE_REGEX = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
    }

    @Transactional
    public User signUp(SignUpReqDto signUpReqDto) {
        checkRequired(signUpReqDto);
        checkValidity(signUpReqDto);
        return userRepository.save(signUpReqDto.toUser(passwordEncoder.encode(signUpReqDto.getPassword())));
    }

    @Transactional
    public User signIn(SignInReqDto signInReqDto) {
        checkRequired(signInReqDto);
        User user = userRepository.findById(signInReqDto.getId())
                .orElseThrow(() -> new NotExistException("id", "존재하지 않는 이메일 입니다."));
        if(!matchPassword(user.getPassword(), signInReqDto.getPassword())) {
            throw new NotMatchException("password", "비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    @Transactional
    public void modifyProfileUrl(Long idx, String profileUrl) {
        User user = userRepository.findById(idx)
                .orElseThrow(() -> new NotExistException("token", "존재하지 않는 유저입니다."));
        userRepository.save(user.updateProfileUrl(profileUrl));
    }

    public ProfileResDto getProfile(User user) {
        SubscriptionResDto subscriptionResDto = null;
        if(user.getSubscriptionIdx() != 0) {
            subscriptionResDto = new SubscriptionResDto(subscriptionRepository.findById(user.getSubscriptionIdx()).orElseThrow(
                    () -> new NotExistException("subscriptionId", "존재하지 않는 구독권 번호입니다.")
            ));
        }
        return ProfileResDto.builder()
                .name(user.getName())
                .profileUrl(user.getProfileUrl())
                .subscriptionResDto(subscriptionResDto)
                .build();
    }

    @Transactional
    public void withdraw(Long idx) {
        userRepository.deleteById(idx);
    }

//    @Transactional
//    public void modifyUserInfo(Long idx, ModUserInfoReqDto modUserInfoReqDto) {
//        User user = userRepository.findById(idx)
//                .orElseThrow(() -> new NotExistException("idx", "존재하지 않는 유저입니다."));
//        if(!checkEmptyOrNull(modUserInfoReqDto.getPhone()) && !checkEmptyOrNull(modUserInfoReqDto.getPassword())) {
//            throw new InvalidValueException("phone, password", "변경할 값이 없습니다.");
//        }
//        if(checkEmptyOrNull(modUserInfoReqDto.getPhone())) {
//            if(user.getPhone().equals(modUserInfoReqDto.getPhone())) {
//                throw new InvalidValueException("phone", "기존의 휴대폰 번호와 일치합니다.");
//            }
//            isValidPhone(modUserInfoReqDto.getPhone());
//            userRepository.save(user.updatePhone(modUserInfoReqDto.getPhone()));
//        }
//        if(checkEmptyOrNull(modUserInfoReqDto.getPassword())) {
//            if(matchPassword(user.getPassword(), modUserInfoReqDto.getPassword())) {
//                throw new InvalidValueException("password", "기존의 비밀번호와 일치합니다.");
//            }
//            isValidPassword(modUserInfoReqDto.getPassword());
//            userRepository.save(user.updatePassword(passwordEncoder.encode(modUserInfoReqDto.getPassword())));
//        }

//    }

    private void checkRequired(SignUpReqDto signUpReqDto) {
        if(signUpReqDto.getId() == null) {
            throw new NotExistException("email", "이메일을 입력해주세요.");
        }
        if(signUpReqDto.getName() == null) {
            throw new NotExistException("name", "성함을 입력해주세요.");
        }
        if(signUpReqDto.getPassword() == null) {
            throw new NotExistException("password", "비밀번호를 입력해주세요.");
        }
    }

    private void checkRequired(SignInReqDto signInReqDto) {
        if(signInReqDto.getId() == null) {
            throw new NotExistException("email", "이메일을 입력해주세요.");
        }
        if(signInReqDto.getPassword() == null) {
            throw new NotExistException("password", "비밀번호를 입력해주세요.");
        }
    }

    private void checkValidity(SignUpReqDto signUpReqDto) {
        isValidId(signUpReqDto.getId());
        isExistingId(signUpReqDto.getId());
        isValidPassword(signUpReqDto.getPassword());
//        isEqualConfigPassword(signUpReqDto.getPassword(), signUpReqDto.getConfigPassword());
        isValidName(signUpReqDto.getName());
        if(signUpReqDto.getPhone() != null) {
            isValidPhone(signUpReqDto.getPhone());
            isExistingPhone(signUpReqDto.getPhone());
        }
    }

    private Boolean isValidId(String id) {
        if(!id.matches(EMAIL_REGEX)) {
            throw new InvalidValueException("email", "이메일의 형식이 잘못되었습니다.");
        }
        return Boolean.TRUE;
    }

    private Boolean isValidPassword(String password) {
        if(!password.matches(PASSWORD_REGEX)) {
            throw new InvalidValueException("password", "비밀번호는 8~20자의 영문 대소문자 또는 숫자로 이루어져야 합니다.");
        }
        return Boolean.TRUE;
    }

    private Boolean isValidName(String name) {
        if(!name.matches(NAME_REGEX)) {
            throw new InvalidValueException("name", "이름은 2글자 이상의 한글로 이루어져야 합니다.");
        }
        return Boolean.TRUE;
    }

    private Boolean isValidPhone(String phone) {
        if(!phone.matches(PHONE_REGEX)) {
            throw new InvalidValueException("phone", "휴대폰 번호의 형식이 잘못되었습니다.");
        }
        return Boolean.TRUE;
    }

    private Boolean isExistingId(String id) {
        if(userRepository.findById(id).isPresent()) {
            throw new AlreadyExistException("id", "이미 사용중인 이메일 입니다.");
        }
        return Boolean.TRUE;
    }

    private Boolean isExistingPhone(String phone) {
        if(userRepository.findByPhone(phone).isPresent()) {
            throw new AlreadyExistException("phone", "이미 사용중인 휴대폰 번호 입니다.");
        }
        return Boolean.TRUE;
    }
//    private Boolean isEqualConfigPassword(String password, String configPassword) {
//        if(!password.equals(configPassword)) {
//            throw new NotMatchException("password", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//        }
//        return Boolean.TRUE;

//    }

    private boolean matchPassword(String EncodedPassword, String signInPassword) {
        return passwordEncoder.matches(signInPassword, EncodedPassword);
    }

//    private boolean checkEmptyOrNull(String value) {
//        if(value == null) {
//            return false;
//        }
//        return !value.isEmpty();
//    }
}
