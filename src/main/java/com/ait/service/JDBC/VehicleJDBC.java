package com.ait.service.JDBC;

import com.ait.model.Customer;
import com.ait.model.Vehicle;
import com.ait.service.BaseService;
import com.ait.service.DatabaseConnection;
import com.ait.service.VehicleService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleJDBC implements VehicleService {
    Connection connection = DatabaseConnection.getConnection();

    private String SELECT_ALL_VEHICLES = "SELECT vehicle.id, vehicle.vehicle_name, vehicle.engine_num, vehicle.chassis_num, vehicle.customer_id, vehicle.brand_id, vehicle.color_id, vehicle.isDelete, vehicle.create_date, " +
        "customer.name AS customer, brand.name AS brand, color.name AS color FROM vehicle " +
        "INNER JOIN customer ON customer.id = vehicle.customer_id INNER JOIN brand ON brand.id = vehicle.brand_id " +
        "INNER JOIN color ON color.id = vehicle.color_id WHERE (vehicle.isDelete= 0 AND customer.isDelete=0 " +
        "AND brand.isDelete = 0 AND color.isDelete =0);";

    private String SELECT_VEHICLE_BY_ID  = "SELECT vehicle.id, vehicle.vehicle_name, vehicle.engine_num, vehicle.chassis_num, vehicle.customer_id, vehicle.brand_id, vehicle.color_id, vehicle.isDelete, vehicle.create_date, " +
            "customer.name AS customer, brand.name AS brand, color.name AS color FROM vehicle " +
            "INNER JOIN customer ON customer.id = vehicle.customer_id INNER JOIN brand ON brand.id = vehicle.brand_id " +
            "INNER JOIN color ON color.id = vehicle.color_id WHERE (vehicle.isDelete= 0 AND customer.isDelete=0 " +
            "AND brand.isDelete = 0 AND color.isDelete =0 AND vehicle.id=?);";

    private String SELECT_VEHICLE_BY_ENGINE_OR_CHASSIS_NUMBER = "SELECT vehicle.id, vehicle.vehicle_name, vehicle.engine_num, vehicle.chassis_num, vehicle.customer_id, vehicle.brand_id, vehicle.color_id, vehicle.isDelete, vehicle.create_date, " +
            "customer.name AS customer, brand.name AS brand, color.name AS color FROM vehicle " +
            "INNER JOIN customer ON customer.id = vehicle.customer_id INNER JOIN brand ON brand.id = vehicle.brand_id " +
            "INNER JOIN color ON color.id = vehicle.color_id WHERE (vehicle.isDelete = 0 AND (vehicle.engine_num = ? OR vehicle.chassis_num = ?))";

    private String INSERT_VEHICLE = "INSERT INTO vehicle "+" (vehicle_name,engine_num,chassis_num,customer_id,brand_id,color_id)" +
            " VALUES "+ "(?,?,?,?,?,?);";

    private String UPDATE_VEHICLE = "UPDATE vehicle SET vehicle_name=?, engine_num=?, chassis_num=?, customer_id=?, brand_id=?," +
            " color_id=? WHERE id=?;";

    private String REMOVE_VEHICLE = "UPDATE vehicle SET isDelete = 1 WHERE id=?;";



    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEHICLES);){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Long id =rs.getLong("id");
                String vehicle_name = rs.getString("vehicle_name");
                String engine_num = rs.getString("engine_num");
                String chassis_num = rs.getString("chassis_num");
                Long customer_id = rs.getLong("customer_id");
                String customer = rs.getString("customer");
                Long brand_id = rs.getLong("brand_id");
                String brand = rs.getString("brand");
                Long color_id = rs.getLong("color_id");
                String color = rs.getString("color");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                vehicles.add(new Vehicle(id,vehicle_name,engine_num,chassis_num,isDelete,customer_id,customer,brand_id,brand,color_id,color,create_date));
            }
        } catch (SQLException e) {
        }
        return vehicles;


    }

    @Override
    public Vehicle findById(Long vehicle_id) {
        Vehicle vehicle = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEHICLE_BY_ID);) {
            preparedStatement.setLong(1, vehicle_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id =rs.getLong("id");
                String vehicle_name = rs.getString("vehicle_name");
                String engine_num = rs.getString("engine_num");
                String chassis_num = rs.getString("chassis_num");
                Long customer_id = rs.getLong("customer_id");
                String customer = rs.getString("customer");
                Long brand_id = rs.getLong("brand_id");
                String brand = rs.getString("brand");
                Long color_id = rs.getLong("color_id");
                String color = rs.getString("color");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                vehicle = (new Vehicle(id,vehicle_name,engine_num,chassis_num,isDelete,customer_id,customer,brand_id,brand,color_id,color,create_date));
            }
        } catch (SQLException e) {
        }
        return vehicle;

    }

    @Override
    public void save(Vehicle vehicle) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEHICLE)) {
            preparedStatement.setString(1,vehicle.getVehicle_name());
            preparedStatement.setString(2,vehicle.getEngine_num());
            preparedStatement.setString(3,vehicle.getChassis_num());
            preparedStatement.setLong(4,vehicle.getCustomer_id());
            preparedStatement.setLong(5,vehicle.getBrand_id());
            preparedStatement.setLong(6,vehicle.getColor_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE)) {
            statement.setString(1,vehicle.getVehicle_name());
            statement.setString(2, vehicle.getEngine_num());
            statement.setString(3,vehicle.getChassis_num());
            statement.setLong(4,vehicle.getCustomer_id());
            statement.setLong(5,vehicle.getBrand_id());
            statement.setLong(6,vehicle.getColor_id());
            statement.setLong(7, vehicle.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(REMOVE_VEHICLE)) {
            statement.setLong(1,id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Vehicle findByEngineOrChassisNumber(String engine, String chassis) {
     Vehicle vehicle = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEHICLE_BY_ENGINE_OR_CHASSIS_NUMBER);) {
            preparedStatement.setString(1, engine);
            preparedStatement.setString(2,chassis);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Long id =rs.getLong("id");
                String vehicle_name = rs.getString("vehicle_name");
                String engine_num = rs.getString("engine_num");
                String chassis_num = rs.getString("chassis_num");
                Long customer_id = rs.getLong("customer_id");
                String customer = rs.getString("customer");
                Long brand_id = rs.getLong("brand_id");
                String brand = rs.getString("brand");
                Long color_id = rs.getLong("color_id");
                String color = rs.getString("color");
                Integer isDelete = rs.getInt("isDelete");
                Date create_date = rs.getDate("create_date");
                vehicle = (new Vehicle(id,vehicle_name,engine_num,chassis_num,isDelete,customer_id,customer,brand_id,brand,color_id,color,create_date));
            }
        } catch (SQLException e) {
        }
        return vehicle;
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


