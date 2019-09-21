package kr.devdogs.swot.user.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserHomeController {

    // test
    @RequestMapping(value="/", method= RequestMethod.GET)
    public JSONObject test(@RequestParam("id") String id){
        JSONObject JSON = new JSONObject();
        JSON.put("test", id);
        return JSON;
    }

}
