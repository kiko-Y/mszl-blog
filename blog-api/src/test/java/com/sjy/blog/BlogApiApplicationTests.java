package com.sjy.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApiApplicationTests {

    @Test
    void contextLoads() {
        A a = new A(1);
        B b = new B(0);
        BeanUtils.copyProperties(b, a);
        System.out.println(a + " " + b);
    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class A {
    int x;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class B {
    Integer x;
}
