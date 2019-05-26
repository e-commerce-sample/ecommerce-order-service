package com.ecommerce.common.distributedlock;

import com.ecommerce.common.exception.AppException;

import static com.ecommerce.common.exception.CommonErrorCode.LOCK_OCCUPIED;
import static com.google.common.collect.ImmutableMap.of;

public class LockAlreadyOccupiedException extends AppException {
    public LockAlreadyOccupiedException(String lockKey) {
        super(LOCK_OCCUPIED, of("lockKey", lockKey));
    }
}
