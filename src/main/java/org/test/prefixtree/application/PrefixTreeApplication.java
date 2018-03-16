package org.test.prefixtree.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.test.prefixtree")
public class PrefixTreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrefixTreeApplication.class, args);
    }
}
