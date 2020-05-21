package com.ait.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping({"/","chart"})
    public String home(Model model){
        return "chart";
    }

    @GetMapping("hello")
    public String hello(Model model){ return "home";}

    @GetMapping("customer")
    public String customer(Model model){return "admin/customer";}

    @GetMapping("vehicle")
    public String vehicle(Model model){return "admin/vehicle";}

    @GetMapping("province")
    public String province(Model model){return "admin/province";}

    @GetMapping("brandColor")
    public String brandColor(Model model){return "admin/brandColor";}
}
