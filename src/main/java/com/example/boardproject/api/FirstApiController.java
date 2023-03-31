package com.example.boardproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Rest Controller란 Rest Api용 컨트롤러이다 그리고 JSON형태를 반환해준다.

public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }

}
