package com.boraji.tutorial.spring.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

   @GetMapping("/")
   public String index(Model model) {

      // Get authenticated user name from SecurityContext
      SecurityContext context = SecurityContextHolder.getContext();
      
      model.addAttribute("message", "You are logged in as " 
                     + context.getAuthentication().getName());
      return "index";
   }
   
   @RequestMapping(value = "/getString",  headers="Accept=*/*", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public Map<String, List<String>> getString() {
	   Map<String, List<String>> map = new HashMap<>();
	   
	   map.put("tes1", Arrays.asList("a","b", "c"));
	   map.put("tes2", Arrays.asList("d","e", "f"));
	   return map;
   }
}
