package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {

        String sql = "SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;";
        ResultSet rst = CrudUtil.execute(sql);

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean exist(String orderId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT oid FROM `Orders` WHERE oid=?";
        ResultSet rst = CrudUtil.execute(sql, orderId);
        return rst.next();

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDTO get(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)";
        return CrudUtil.execute(sql, orderDTO.getOrderId(), Date.valueOf(orderDTO.getOrderDate()),
                orderDTO.getCustomerId());
    }

    @Override
    public boolean update(OrderDTO customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }
}
