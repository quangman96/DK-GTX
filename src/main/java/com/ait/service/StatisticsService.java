package com.ait.service;

import com.ait.model.Statistics;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StatisticsService {
    List<Statistics> statisticsByMonth();
    List<Statistics> statisticsByBrand();
    List<Statistics> statisticsByProvince();
}
