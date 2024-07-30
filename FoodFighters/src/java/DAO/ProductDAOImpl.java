package DAO;

import DTO.ProductDTO;
import Utilities.DataSource;
import Utilities.DietType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    

    public ProductDAOImpl() {
       
    }

    @Override
    public void addProduct(ProductDTO product) throws SQLException {
        String query = "INSERT INTO Product (name, quantity, expiryDate, surplus, retailerID, price, dietType) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDate(3, new java.sql.Date(product.getExpiryDate().getTime()));
            preparedStatement.setBoolean(4, product.isSurplus());
            preparedStatement.setInt(5, product.getRetailerID());
            preparedStatement.setInt(6, product.getPrice());
            preparedStatement.setString(7, product.getDietType().getValue());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ProductDTO getProductByID(int productID) throws SQLException {
        String query = "SELECT * FROM Product WHERE id = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new ProductDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getBoolean("surplus"),
                        resultSet.getInt("retailerID"),
                        resultSet.getInt("price"),
                        DietType.fromValue(resultSet.getString("dietType"))
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<ProductDTO> getProductByName(String name) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE name LIKE ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new ProductDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getBoolean("surplus"),
                        resultSet.getInt("retailerID"),
                        resultSet.getInt("price"),
                        DietType.fromValue(resultSet.getString("dietType"))
                    ));
                }
            }
        }
        return products;
    }

    @Override
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new ProductDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("quantity"),
                    resultSet.getDate("expiryDate"),
                    resultSet.getBoolean("surplus"),
                    resultSet.getInt("retailerID"),
                    resultSet.getInt("price"),
                    DietType.fromValue(resultSet.getString("dietType"))
                ));
            }
        }
        return products;
    }

    @Override
    public void updateProduct(ProductDTO product) throws SQLException {
        String query = "UPDATE Product SET name = ?, quantity = ?, expiryDate = ?, surplus = ?, retailerID = ?, price = ?, dietType = ? WHERE id = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setDate(3, new java.sql.Date(product.getExpiryDate().getTime()));
            preparedStatement.setBoolean(4, product.isSurplus());
            preparedStatement.setInt(5, product.getRetailerID());
            preparedStatement.setInt(6, product.getPrice());
            preparedStatement.setString(7, product.getDietType().getValue());
            preparedStatement.setInt(8, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int productID) throws SQLException {
        String query = "DELETE FROM Product WHERE id = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productID);
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public List<ProductDTO> getProductsByRetailerID(int retailerID) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT p.id, p.name, p.quantity, p.expiryDate, p.surplus, pr.retailerID, p.price, p.dietType " +
                       "FROM Product p " +
                       "JOIN ProductRetailer pr ON p.id = pr.productID " +
                       "WHERE pr.retailerID = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, retailerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new ProductDTO(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getBoolean("surplus"),
                        resultSet.getInt("retailerID"),
                        resultSet.getInt("price"),
                        DietType.fromValue(resultSet.getString("dietType"))
                    ));
                }
            }
        }
        return products;
    }
}
    