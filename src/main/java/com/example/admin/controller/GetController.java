package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path="/getMethod")
    public String getRequest(){

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name="password") String pwd){
        String password = "123";

        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id+password;
    }

    // localhost:8080/api/getMultiParameter?account=abc&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){

        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "","email" : "", "page" : 0 }
        return searchParam;
    }
}
