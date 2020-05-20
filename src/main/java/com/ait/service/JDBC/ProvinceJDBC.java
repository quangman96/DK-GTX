package com.ait.service.JDBC;

import com.ait.model.Province;
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
public class ProvinceJDBC implements BaseService<Province> {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_PROVINCES = "SELECT * FROM province WHERE isDelete=0;";
    private String SELECT_PROVINCE_BY_ID = "SELECT * FROM province WHERE id=?;";
    private String INSERT_PROVINCE = "INSERT INTO province "+" (name,code) VALUES "+ "(?,?);";
    private String UPDATE_PROVINCE = "UPDATE province SET name=?,code=? WHERE id=?;";
    private String REMOVE_PROVINCE = "UPDATE province SET isDelete = 1 WHERE id=?;";



    @Override
    public List<Province> findAll() {
        List<Province> provinces = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCES);){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Long id =rs.getLong("id");
                String name = rs.getString("name");
                Integer code = rs.getInt("code");
                Integer isDelete = rs.getInt("isDelete");
                provinces.add(new Province(id,name,code,isDelete));
            }
        } catch (SQLException e) {
        }
        return provinces;


    }

    @Override
    public Province findById(Long id) {
        Province province = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer code = rs.getInt("code");
                Integer isDelete = rs.getInt("isDelete");

                province =(new Province(id, name,code, isDelete));
            }
        } catch (SQLException e) {
        }
        return province;

    }

    @Override
    public void save(Province province) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROVINCE)) {
            preparedStatement.setString(1, province.getName());
            preparedStatement.setInt(2,province.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(Province province) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PROVINCE)) {
            statement.setString(1, province.getName());
            statement.setInt(2,province.getCode());
            statement.setLong(3, province.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(REMOVE_PROVINCE)) {
            statement.setLong(1,id);
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



