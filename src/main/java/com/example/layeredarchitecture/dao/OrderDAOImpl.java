package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {

        String sql = "SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;";
        ResultSet rst = CrudUtil.execute(sql);

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean exitsOrder(String orderId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT oid FROM `Orders` WHERE oid=?";
        ResultSet rst = CrudUtil.execute(sql, orderId);
        return rst.next();

    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)";
        return CrudUtil.execute(sql, orderDTO.getOrderId(), Date.valueOf(orderDTO.getOrderDate()),
                orderDTO.getCustomerId());
    }
}
