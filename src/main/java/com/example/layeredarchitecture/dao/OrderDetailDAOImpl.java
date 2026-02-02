package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.OrderDetailsDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean saveOrderDetails(OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, orderDetail.getoId(), orderDetail.getItemCode(), orderDetail.getUnitPrice(), orderDetail.getQty());
    }
}
