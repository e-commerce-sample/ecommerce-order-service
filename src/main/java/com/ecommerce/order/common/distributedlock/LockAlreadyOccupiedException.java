package com.ecommerce.order.common.distributedlock;

import com.ecommerce.order.common.exception.AppException;
import com.ecommerce.order.common.exception.ErrorCode;
import com.google.common.collect.ImmutableMap;

public class LockAlreadyOccupiedException extends AppException {
    public LockAlreadyOccupiedException(String lockKey) {
        super(ErrorCode.LOCK_OCCUPIED, ImmutableMap.of("lockKey", lockKey));
    }
}
