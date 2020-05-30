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
import java.util.Date;
import java.util.List;

@Service
public class BrandJDBC implements BaseService<Brand> {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_BRANDS = "SELECT * FROM brand WHERE isDelete =0;";
    private String SELECT_BRAND_BY_ID = "SELECT * FROM brand WHERE (isDelete= 0 AND id=?);";
    private String INSERT_BRAND = "INSERT INTO brand "+" (name) VALUES "+ "(?);";
    private String UPDATE_BRAND = "UPDATE brand SET name=? WHERE id=?;";
    private String REMOVE_BRAND = "UPDATE brand SET isDelete = 1 WHERE id=?; UPDATE vehicle SET isDelete = 1 WHERE vehicle.brand_id = ?";

    @Override
    public List<Brand> findAll() {
        List<Brand> brands = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BRANDS);){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Long id =rs.getLong("id");
                String name = rs.getString("name");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                brands.add(new Brand(id,name,isDelete,create_date));
            }
        } catch (SQLException e) {
        }
        return brands;


    }

    @Override
    public Brand findById(Long id) {
        Brand brand = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRAND_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");

                brand =(new Brand(id, name, isDelete,create_date));
            }
        } catch (SQLException e) {
        }
        return brand;

    }

    @Override
    public void save(Brand brand) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BRAND)) {
            preparedStatement.setString(1, brand.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(Brand brand) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_BRAND)) {
            statement.setString(1, brand.getName());
            statement.setLong(2, brand.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(REMOVE_BRAND)) {
            statement.setLong(1,id);
            statement.setLong(2,id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
