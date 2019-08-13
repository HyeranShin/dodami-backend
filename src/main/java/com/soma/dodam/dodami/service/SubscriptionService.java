package com.soma.dodam.dodami.service;

import com.soma.dodam.dodami.domain.Subscription;
import com.soma.dodam.dodami.domain.User;
import com.soma.dodam.dodami.dto.response.SubscriptionResDto;
import com.soma.dodam.dodami.exception.InvalidValueException;
import com.soma.dodam.dodami.exception.NotExistException;
import com.soma.dodam.dodami.repository.SubscriptionRepository;
import com.soma.dodam.dodami.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public List<SubscriptionResDto> getSubscriptionInfo() {
        List<SubscriptionResDto> subscriptionList = subscriptionRepository.findAll()
                .stream()
                .map(subscription -> new SubscriptionResDto(subscription))
                .collect(Collectors.toList());

        return subscriptionList;
    }

    public void purchaseSubscription(Long idx, Integer subscriptionIdx) {
        subscriptionRepository.findById(subscriptionIdx)
                .orElseThrow(() -> new NotExistException("idx", "존재하지 않는 구독권 번호입니다."));
        User user = userRepository.findById(idx)
                .orElseThrow(() -> new NotExistException("token", "존재하지 않는 유저입니다."));
        userRepository.save(user.updateSubscription(subscriptionIdx));
    }

    public SubscriptionResDto getSpecificSubscriptionInfo(Integer idx) {
        Subscription subscription = subscriptionRepository.findById(idx)
                .orElseThrow(() -> new NotExistException("idx", "존재하지 않는 구독권 번호입니다."));
        return new SubscriptionResDto(subscription);
    }

    public void cancelSubscription(User user) {
        if(user.getSubscriptionIdx() == 0) {
            throw new InvalidValueException("subscriptionIdx", "현재 구독중인 구독권이 없습니다.");
        }
        subscriptionRepository.findById(user.getSubscriptionIdx())
                .orElseThrow(() -> new NotExistException("subscriptionIdx", "존재하지 않는 구독권 번호입니다."));
        userRepository.save(user.updateSubscription(0));
    }
}
