package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.CommonDAO;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Customer";
        ResultSet rst = CrudUtil.execute(sql);

        ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        while (rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            customers.add(new CustomerDTO(id, name, address));
        }

        return customers;
    }

    // ------------------------------------------------ For OrderController -----------------------------------------------

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE id=?";
        ResultSet rst = CrudUtil.execute(sql, id);
        rst.next();
        CustomerDTO customerDTO = new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
        return customerDTO;
    }

    // ---------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Customer (id,name, address) VALUES (?,?,?)";
        return  CrudUtil.execute(sql, customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE Customer SET name=?, address=? WHERE id=?";
        return CrudUtil.execute(sql, customerDTO.getName(), customerDTO.getAddress(), customerDTO.getId());
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT id FROM Customer WHERE id=?";
        ResultSet rst = CrudUtil.execute(sql, id);
        return rst.next();
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM Customer WHERE id=?";
        return CrudUtil.execute(sql, id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {

        String sql = "SELECT id FROM Customer ORDER BY id DESC LIMIT 1";
        ResultSet rst = CrudUtil.execute(sql);
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
}
