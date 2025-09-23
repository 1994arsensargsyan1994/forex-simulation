package org.arsen.forex.api.facade;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractForexServiceFacadeTest {

    @SuppressWarnings("unused")
    protected abstract void verifyNoMoreMockInteractions();

    protected static Long randomId() {
        return RandomUtils.nextLong();
    }

    protected static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}