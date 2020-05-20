package com.ait.service.JDBC;

import com.ait.model.Customer;
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
public class CustomerJDBC implements BaseService<Customer> {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer WHERE isDelete=0;";
    private String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id=?;";
    private String INSERT_CUSTOMER = "INSERT INTO customer "+" (name, address,phone,identity,province_id) VALUES "+ "(?,?,?,?,?);";
    private String UPDATE_CUSTOMER = "UPDATE customer SET name=?,address=? ,phone=? ,identity=? ,province_id=? WHERE id=?;";
    private String REMOVE_CUSTOMER = "UPDATE customer SET isDelete = 1 WHERE id=?;";

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Long id =rs.getLong("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String identity = rs.getString("identity");
                Long province_id = rs.getLong("province_id");
//                Date current_day = rs.getDate("current_day");
                Integer isDelete = rs.getInt("isDelete");
                customers.add(new Customer(id,name,address,phone,identity,province_id, isDelete));
            }
        } catch (SQLException e) {
        }
        return customers;


    }

    @Override
    public Customer findById(Long id) {
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String identity = rs.getString("identity");
                Long province_id = rs.getLong("province_id");
//                Date current_day = rs.getDate("current_day");
                Integer isDelete = rs.getInt("isDelete");
                customer =(new Customer(id,name,address,phone,identity,province_id, isDelete));
            }
        } catch (SQLException e) {
        }
        return customer;

    }

    @Override
    public void save(Customer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2,customer.getAddress());
            preparedStatement.setString(3,customer.getPhone());
            preparedStatement.setString(4,customer.getIdentity());
            preparedStatement.setLong(5,customer.getProvince_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            statement.setString(1, customer.getName());
            statement.setString(2,customer.getAddress());
            statement.setString(3,customer.getPhone());
            statement.setString(4,customer.getIdentity());
            statement.setLong(5,customer.getProvince_id());
            statement.setLong(6, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(REMOVE_CUSTOMER)) {
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
