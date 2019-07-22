package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.SignInReqDto;
import com.soma.dodam.dodami.dto.SignUpReqDto;
import com.soma.dodam.dodami.exception.AlreadyExistException;
import com.soma.dodam.dodami.exception.InvalidValueException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.exception.NotMatchException;
import com.soma.dodam.dodami.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;

    private final static String ID_REGEX = "^[a-z]+[a-z0-9]{3,11}$";
    private final static String PASSWORD_REGEX = "^[a-zA-Z0-9]{8,20}$";
    private final static String NAME_REGEX = "^[가-힣]{2,}$";
    private final static String PHONE_REGEX = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User signUp(SignUpReqDto signUpReqDto) {
        checkValidity(signUpReqDto);
        return userRepository.save(signUpReqDto.toUser());
    }

    @Transactional
    public User signIn(SignInReqDto signInReqDto) {
        User user = userRepository.findById(signInReqDto.getId())
                .orElseThrow(() -> new NotExistException("id", "존재하지 않는 아이디 입니다."));
        user.matchPasswordBy(signInReqDto);
        return user;
    }

    public void checkValidity(SignUpReqDto signUpReqDto) {
        isValidId(signUpReqDto.getId());
        isExistingId(signUpReqDto.getId());
        isValidPassword(signUpReqDto.getPassword());
        isEqualConfigPassword(signUpReqDto.getPassword(), signUpReqDto.getConfigPassword());
        isValidName(signUpReqDto.getName());
        isValidPhone(signUpReqDto.getPhone());
        isExistingPhone(signUpReqDto.getPhone());
    }

    public Boolean isValidId(String id) {
        if(!id.matches(ID_REGEX)) {
            throw new InvalidValueException("id", "아이디는 영문 소문자로 시작하는 4~12자의 영문 소문자 또는 숫자로 이루어져야 합니다.");
        }
        return Boolean.FALSE;
    }

    public Boolean isValidPassword(String password) {
        if(!password.matches(PASSWORD_REGEX)) {
            throw new InvalidValueException("password", "비밀번호는 8~20자의 영문 대소문자 또는 숫자로 이루어져야 합니다.");
        }
        return Boolean.FALSE;
    }

    public Boolean isValidName(String name) {
        if(!name.matches(NAME_REGEX)) {
            throw new InvalidValueException("name", "이름은 2글자 이상의 한글로 이루어져야 합니다.");
        }
        return Boolean.FALSE;
    }

    public Boolean isValidPhone(String phone) {
        if(!phone.matches(PHONE_REGEX)) {
            throw new InvalidValueException("phone", "휴대폰 번호의 형식이 잘못되었습니다.");
        }
        return Boolean.FALSE;
    }

    public Boolean isExistingId(String id) {
        if(userRepository.findById(id).isPresent()) {
            throw new AlreadyExistException("id", "이미 사용중인 아이디 입니다.");
        }
        return Boolean.FALSE;
    }

    public Boolean isExistingPhone(String phone) {
        if(userRepository.findByPhone(phone).isPresent()) {
            throw new AlreadyExistException("phone", "이미 사용중인 휴대폰 번호 입니다.");
        }
        return Boolean.FALSE;
    }

    public boolean isEqualConfigPassword(String password, String configPassword) {
        if(!password.equals(configPassword)) {
            throw new NotMatchException("password", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }
        return Boolean.FALSE;
    }
}