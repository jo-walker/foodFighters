package DAO;

import DTO.ProductDTO;
import Utilities.DataSource;
import Utilities.DietType;
import Utilities.Exception.ValidationException;
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
        String productQuery = "INSERT INTO Product (productName, price, isVeggie) VALUES (?, ?, ?)";
        String productRetailerQuery = "INSERT INTO ProductRetailer (productID, retailerID, productQuantity, expiryDate, isSurplus) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement productStmt = connection.prepareStatement(productQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                productStmt.setString(1, product.getName());
                productStmt.setDouble(2, product.getPrice());
                productStmt.setBoolean(3, product.isVeggie());
                productStmt.executeUpdate();

                try (ResultSet generatedKeys = productStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int productId = generatedKeys.getInt(1);

                        try (PreparedStatement productRetailerStmt = connection.prepareStatement(productRetailerQuery)) {
                            productRetailerStmt.setInt(1, productId);
                            productRetailerStmt.setInt(2, product.getRetailerID());
                            productRetailerStmt.setInt(3, product.getQuantity());
                            productRetailerStmt.setDate(4, new java.sql.Date(product.getExpiryDate().getTime()));
                            productRetailerStmt.setBoolean(5, product.isSurplus());
                            productRetailerStmt.executeUpdate();
                        }
                    }
                }
            }
            connection.commit();
        }
    }

    @Override
    public ProductDTO getProductByID(int productID) throws SQLException {
        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID WHERE p.productID = ?";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new ProductDTO(
                        resultSet.getInt("productID"),
                        resultSet.getString("productName"),
                        resultSet.getInt("productQuantity"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getBoolean("isSurplus"),
                        resultSet.getInt("retailerID"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isVeggie")
                    );
                }
            }
        }
        return null;
    }


    @Override
    public List<ProductDTO> getProductByName(String name) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID WHERE p.productName LIKE ?";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new ProductDTO(
                        resultSet.getInt("productID"),
                        resultSet.getString("productName"),
                        resultSet.getInt("productQuantity"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getBoolean("isSurplus"),
                        resultSet.getInt("retailerID"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isVeggie")
                    ));
                }
            }
        }
        return products;
    }


    @Override
    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new ProductDTO(
                    resultSet.getInt("productID"),
                    resultSet.getString("productName"),
                    resultSet.getInt("productQuantity"),
                    resultSet.getDate("expiryDate"),
                    resultSet.getBoolean("isSurplus"),
                    resultSet.getInt("retailerID"),
                    resultSet.getDouble("price"),
                    resultSet.getBoolean("isVeggie")
                ));
            }
        }
        return products;
    }


    @Override
    public void updateProduct(ProductDTO product) throws SQLException {
        String productQuery = "UPDATE Product SET productName = ?, price = ?, isVeggie = ? WHERE productID = ?";
        String productRetailerQuery = "UPDATE ProductRetailer SET retailerID = ?, productQuantity = ?, expiryDate = ?, isSurplus = ? WHERE productID = ?";

        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement productStmt = connection.prepareStatement(productQuery)) {
                productStmt.setString(1, product.getName());
                productStmt.setDouble(2, product.getPrice());
                productStmt.setBoolean(3, product.isVeggie());
                productStmt.setInt(4, product.getId());
                productStmt.executeUpdate();

                try (PreparedStatement productRetailerStmt = connection.prepareStatement(productRetailerQuery)) {
                    productRetailerStmt.setInt(1, product.getRetailerID());
                    productRetailerStmt.setInt(2, product.getQuantity());
                    productRetailerStmt.setDate(3, new java.sql.Date(product.getExpiryDate().getTime()));
                    productRetailerStmt.setBoolean(4, product.isSurplus());
                    productRetailerStmt.setInt(5, product.getId());
                    productRetailerStmt.executeUpdate();
                }
            }
            connection.commit();
        }
    }

    @Override
    public void deleteProduct(int productID) throws SQLException {
        String productQuery = "DELETE FROM Product WHERE productID = ?";
        String productRetailerQuery = "DELETE FROM ProductRetailer WHERE productID = ?";

        try (Connection connection = DataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement productRetailerStmt = connection.prepareStatement(productRetailerQuery)) {
                productRetailerStmt.setInt(1, productID);
                productRetailerStmt.executeUpdate();

                try (PreparedStatement productStmt = connection.prepareStatement(productQuery)) {
                    productStmt.setInt(1, productID);
                    productStmt.executeUpdate();
                }
            }
            connection.commit();
        }
    }

    @Override
    public List<ProductDTO> getProductsByRetailerID(int retailerID) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID WHERE pr.retailerID = ?";

        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, retailerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new ProductDTO(
                        resultSet.getInt("productID"),
                        resultSet.getString("productName"),
                        resultSet.getInt("productQuantity"),
                        resultSet.getDate("expiryDate"),
                        resultSet.getBoolean("isSurplus"),
                        resultSet.getInt("retailerID"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isVeggie")
                    ));
                }
            }
        }
        return products;
    }
    @Override
    public void updateProductQuantity(ProductDTO product) throws SQLException {
        String query = "UPDATE ProductRetailer SET productQuantity = ? WHERE productID = ? AND retailerID = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setInt(3, product.getRetailerID());
            preparedStatement.executeUpdate();
        }
    }

    public void validateSurplusProduct(ProductDTO product) throws ValidationException {
        if (product.isSurplus() && product.getQuantity() <= 0) {
            throw new ValidationException("Cannot mark a product with zero or negative quantity as surplus");
        }
    }

    @Override
    public void setSurplus(ProductDTO product) throws SQLException {
        String query = "UPDATE ProductRetailer SET isSurplus = ? WHERE productID = ? AND retailerID = ?";
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, product.isSurplus());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.setInt(3, product.getRetailerID());
            preparedStatement.executeUpdate();
        }
    }
}
    