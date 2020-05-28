package com.ait.service.JDBC;

import com.ait.model.Customer;
import com.ait.service.BaseService;
import com.ait.service.CustomerService;
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
public class CustomerJDBC implements CustomerService {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_CUSTOMERS = "SELECT customer.id, customer.name, customer.address, customer.phone, customer.identity, customer.province_id, customer.isDelete, customer.create_date, province.name AS province FROM customer INNER JOIN province ON province.id = customer.province_id WHERE (customer.isDelete=0 AND province.isdelete=0);";
    private String SELECT_CUSTOMER_BY_ID = "SELECT customer.id, customer.name, customer.address, customer.phone, customer.identity, customer.province_id, customer.isDelete, customer.create_date, province.name AS province FROM customer INNER JOIN province ON province.id = customer.province_id WHERE (customer.isDelete=0 AND province.isdelete=0 AND customer.id = ?);";
    private String SELECT_CUSTOMER_BY_IDENTITY = "SELECT customer.id, customer.name, customer.address, customer.phone, customer.identity, customer.province_id, customer.isDelete, customer.create_date, province.name AS province FROM customer INNER JOIN province ON province.id = customer.province_id WHERE (customer.isDelete=0 AND province.isdelete=0 AND customer.identity = ?);";
    private String INSERT_CUSTOMER = "INSERT INTO customer "+" (name, address,phone,identity,province_id) VALUES "+ "(?,?,?,?,?);";
    private String UPDATE_CUSTOMER = "UPDATE customer SET name=?,address=? ,phone=? ,identity=? ,province_id=? WHERE id=?;";
    private String REMOVE_CUSTOMER = "UPDATE customer SET isDelete = 1 WHERE id=?;";
    private String IDENTITY_LIST = "SELECT customer.identity FROM customer WHERE customer.isDelete =0;";

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
                String province_name = rs.getString("province");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                customers.add(new Customer(id,name,address,phone,identity,province_id,province_name, isDelete,create_date));
            }
        } catch (SQLException e) {
        }
        return customers;


    }

    @Override
    public List identityList() {
        List customers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(IDENTITY_LIST)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String identity = rs.getString("identity");
                customers.add(identity);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    @Override
    public Customer findById(Long id_customer) {
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setLong(1, id_customer);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String identity = rs.getString("identity");
                Long province_id = rs.getLong("province_id");
                String province_name = rs.getString("province");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                customer =(new Customer(id,name,address,phone,identity,province_id,province_name, isDelete,create_date));
            }
        } catch (SQLException e) {
        }
        return customer;

    }

    @Override
    public Customer findByIdentity(String customer_identity) {
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_IDENTITY);) {
            preparedStatement.setString(1, customer_identity);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String identity = rs.getString("identity");
                Long province_id = rs.getLong("province_id");
                String province_name = rs.getString("province");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                customer =(new Customer(id,name,address,phone,identity,province_id,province_name, isDelete,create_date));
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
