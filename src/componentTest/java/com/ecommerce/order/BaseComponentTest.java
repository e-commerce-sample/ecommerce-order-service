package com.ecommerce.order;

import com.ecommerce.order.common.utils.DefaultObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseComponentTest {


    @Autowired
    protected DefaultObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.objectMapper.enable(INDENT_OUTPUT);
    }
}
