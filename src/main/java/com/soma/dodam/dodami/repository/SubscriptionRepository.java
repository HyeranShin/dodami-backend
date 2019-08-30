package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
