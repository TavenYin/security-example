package com.github.taven.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {
    @GetMapping("403")
    public Object _403() {
        Map result = new HashMap<>();
        result.put("code", 403);
        result.put("mes", "access denied");
        return result;
    }

}
