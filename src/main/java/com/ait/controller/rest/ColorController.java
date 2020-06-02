package com.ait.controller.rest;

import com.ait.model.Color;
import com.ait.service.JDBC.ColorJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ColorController {

    @Autowired
    ColorJDBC colorService;

    @GetMapping("/colors")
    public List<Color> colorList() {
        return colorService.findAll();
    }

    @GetMapping("/colors/{id}")
    public Color findColorById(@PathVariable Long id) {
        return colorService.findById(id);
    }

    @PostMapping("/colors")
    public Color addNewColor(@RequestBody Color color) {
        colorService.save(color);
        return color;
    }

    @PutMapping("/colors/{id}")
    public Color updateColor(@PathVariable Long id, @RequestBody Color color) {
        Color color1 = colorService.findById(id);
        color1.setName(color.getName());
        color1.setId(id);
        colorService.update(color1);
        return color;
    }

    @PutMapping("/colors/delete/{id}")
    public void removeColor(@PathVariable Long id) {
        colorService.remove(id);
    }
}

