package com.sontaypham.persistence.repository;

import com.sontaypham.repository.HiDomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public class HiInfrastructureRepositoryImpl implements HiDomainRepository {
    @Override
    public String sayHi(String who) {
        return "Hi infrastructure " + who;
    }
}
