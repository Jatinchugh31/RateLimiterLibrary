package com.ratelimiter.model;

import lombok.Data;

@Data
public class LimiterKey {
    private final String userId;
    private final String apiId;
    private final KeyScope keyScope;

    public String getKey() {
        if (keyScope == KeyScope.API) {
            return apiId;
        }
        if (keyScope == KeyScope.USER_API) {
            return userId;
        }
        return userId + apiId;
    }
}
