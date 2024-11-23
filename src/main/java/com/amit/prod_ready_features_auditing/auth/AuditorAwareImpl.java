package com.amit.prod_ready_features_auditing.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor(){
//         get security context
//        get authentication
//        get the principle
//        get the username

        return Optional.of("AMIT LAL");
    }
}
