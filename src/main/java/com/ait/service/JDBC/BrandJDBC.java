package com.ait.service.JDBC;

import com.ait.model.Brand;
import com.ait.service.BaseService;
import com.ait.service.DatabaseConnection;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandJDBC implements BaseService<Brand> {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_BRANDS = "SELECT * FROM brand WHERE isDelete=0;";

    @Override
    public List<Brand> findAll() {
        List<Brand> brands = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BRANDS);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Integer isDelete = rs.getInt("isDelete");
                brands.add(new Brand(id, name, isDelete));
            }
        } catch (SQLException e) {
        }
        return brands;


    }
}
