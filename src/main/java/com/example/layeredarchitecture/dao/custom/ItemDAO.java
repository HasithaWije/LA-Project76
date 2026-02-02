package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;

    public ItemDTO getItem(String newItemCode) throws SQLException, ClassNotFoundException;

    public void deleteItem( String code) throws SQLException, ClassNotFoundException;

    public void addItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws SQLException, ClassNotFoundException;

    public boolean updateItem(String code, String description, BigDecimal unitPrice, int qtyOnHand) throws SQLException, ClassNotFoundException;

    public boolean existItem(String code) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;
}
