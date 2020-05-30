package com.ait.service.JDBC;

import com.ait.model.Statistics;
import com.ait.service.DatabaseConnection;
import com.ait.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsJDBC implements StatisticsService {
    Connection connection = DatabaseConnection.getConnection();

    private String STATISTICS_BY_BRAND = "SELECT brand.name AS brand, COUNT(brand_id) AS amount FROM vehicle INNER JOIN brand ON brand.id = vehicle.brand_id WHERE(brand.isDelete =0 AND vehicle.isDelete =0) GROUP BY brand.name ORDER BY brand.name DESC limit 6;";

    private String STATISTICS_BY_MONTH = "SELECT to_char(date_trunc('month', create_date),'yyyy-MM') year_month, COUNT (EXTRACT(MONTH FROM create_date)) AS amount FROM customer WHERE isDelete = 0 GROUP BY year_month ORDER BY year_month;";

    private String STATISTICS_BY_PROVINCE = "SELECT province.name AS province, COUNT(province_id) AS amount FROM customer INNER JOIN province ON province.id = customer.province_id WHERE (customer.isDelete =0 AND province.isDelete =0) GROUP BY province.name;";

    @Override
    public List<Statistics> statisticsByMonth() {
        List<Statistics> vehicles = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(STATISTICS_BY_MONTH)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String year_month = rs.getString("year_month");
                Long amount = rs.getLong("amount");
                vehicles.add(new Statistics(year_month,amount));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicles;
    }

    @Override
    public List<Statistics> statisticsByBrand() {
        List<Statistics> vehicles = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(STATISTICS_BY_BRAND)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String brand = rs.getString("brand");
                Long amount = rs.getLong("amount");
                vehicles.add(new Statistics(brand,amount));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vehicles;
    }

        @Override
    public List<Statistics> statisticsByProvince() {
        List<Statistics> customers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(STATISTICS_BY_PROVINCE)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String province = rs.getString("province");
                Long amount = rs.getLong("amount");
                customers.add(new Statistics(province,amount));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
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
