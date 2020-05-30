package com.ait.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("chart")
    public String chart(Model model){
        return "admin/chart";
    }

    @GetMapping("")
    public String home(Model model){ return "/user/hometest";}

    @GetMapping("/index")
    public String index(Model model){return "/user/home";}

    @GetMapping("customer")
    public String customer(Model model){return "admin/customer";}

    @GetMapping("vehicle")
    public String vehicle(Model model){return "admin/vehicle";}

    @GetMapping("province")
    public String province(Model model){return "admin/province";}

    @GetMapping("brandColor")
    public String brandColor(Model model){return "admin/brandColor";}

}
