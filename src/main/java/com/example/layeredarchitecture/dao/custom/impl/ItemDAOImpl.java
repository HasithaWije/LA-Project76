package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Item";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<ItemDTO> items = new ArrayList<>();

        while (rst.next()) {
            String code = rst.getString("code");
            String desc = rst.getString("description");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            int qty = rst.getInt("qtyOnHand");

            items.add(new ItemDTO(code,desc,unitPrice,qty));
        }
        return items;
    }
    // ------------------------------------------------ For OrderController -----------------------------------------------

    @Override
    public ItemDTO get(String newItemCode) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Item WHERE code=?";
        ResultSet rst = CrudUtil.execute(sql,newItemCode);
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean delete( String code) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Item WHERE code=?";
        return CrudUtil.execute(sql,code);
    }

    @Override
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql, itemDTO.getCode(), itemDTO.getDescription(),
                itemDTO.getUnitPrice(), itemDTO.getQtyOnHand());
    }

    @Override
    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?";
        return CrudUtil.execute(sql, itemDTO.getDescription(), itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand(), itemDTO.getCode());
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {

        String sql = "SELECT code FROM Item WHERE code=?";
        ResultSet rst = CrudUtil.execute(sql,code);
        return rst.next();
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {

        String sql = "SELECT code FROM Item ORDER BY code DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
}
