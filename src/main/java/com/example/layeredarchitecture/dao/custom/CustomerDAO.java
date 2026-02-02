package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;
    public void saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;
    public void updateCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException;
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public String generateId() throws SQLException, ClassNotFoundException;
    }
