package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDAOImpl {

    public boolean saveOrderDetails(OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        stm.setString(1,orderDetail.getoId());
        stm.setString(2, orderDetail.getItemCode());
        stm.setBigDecimal(3, orderDetail.getUnitPrice());
        stm.setInt(4, orderDetail.getQty());

        return stm.executeUpdate() >0;
    }
}
