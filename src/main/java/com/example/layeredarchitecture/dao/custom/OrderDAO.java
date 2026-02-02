package com.example.layeredarchitecture.dao.custom;

import java.sql.*;
import java.time.LocalDate;

public interface OrderDAO {
    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public boolean exitsOrder(String orderId) throws SQLException, ClassNotFoundException;

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
}
