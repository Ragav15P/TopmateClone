package com.Telusko.TopmateApplication.Web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController 
{
	@GetMapping("/api")
    public String testEndPoint()
    {
    	return "The Application is Started";
    }
}
