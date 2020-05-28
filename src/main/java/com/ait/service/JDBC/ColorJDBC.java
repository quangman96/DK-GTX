package com.ait.service.JDBC;

import com.ait.model.Color;
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
public class ColorJDBC implements BaseService<Color> {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_COLORS = "SELECT * FROM color WHERE isDelete= 0;";
    private String SELECT_COLOR_BY_ID = "SELECT * FROM color WHERE (isDelete=0 AND id=?);";
    private String INSERT_COLOR = "INSERT INTO color "+" (name) VALUES "+ "(?);";
    private String UPDATE_COLOR = "UPDATE color SET name=? WHERE id=?;";
    private String REMOVE_COLOR = "UPDATE color SET isDelete = 1 WHERE id=?;";

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COLORS);){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Long id =rs.getLong("id");
                String name = rs.getString("name");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                colors.add(new Color(id,name,isDelete,create_date));
            }
        } catch (SQLException e) {
        }
        return colors;


    }

    @Override
    public Color findById(Long id) {
        Color color = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COLOR_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");

                color =(new Color(id, name, isDelete,create_date));
            }
        } catch (SQLException e) {
        }
        return color;

    }


    @Override
    public void save(Color color) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COLOR)) {
            preparedStatement.setString(1, color.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(Color color) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_COLOR)) {
            statement.setString(1, color.getName());
            statement.setLong(2, color.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(REMOVE_COLOR)) {
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

