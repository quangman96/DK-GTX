package com.ait.controller.rest;

import com.ait.model.Statistics;
import com.ait.service.JDBC.StatisticsJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticsController {
    @Autowired
    StatisticsJDBC statisticsService;

    @GetMapping("/statistics/province")
    public List<Statistics> statisticsByProvince() { return statisticsService.statisticsByProvince();}

    @GetMapping("/statistics/brand")
    public List<Statistics> statisticsByBrand() {return statisticsService.statisticsByBrand();}

    @GetMapping("/statistics/month")
    public List<Statistics> statisticsByMonth() {return statisticsService.statisticsByMonth();}
}
