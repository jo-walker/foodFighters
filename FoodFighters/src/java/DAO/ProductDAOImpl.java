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
        
        Connection con = null;
        PreparedStatement preparedStatement = null;
        
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID WHERE pr.retailerID = ?";

        try {
            
            con = DataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
            
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
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Keeps track of the sorting logic for price
     */
    private static boolean priceSortedASC = true;
    
    @Override
    public List<ProductDTO> getProductsByRetailerIDSortedByPrice(int retailerID) throws SQLException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        List<ProductDTO> products = new ArrayList<>();

        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID WHERE pr.retailerID = ? " +
                       "ORDER BY p.price " + (priceSortedASC ? "ASC" : "DESC");

        try {
            con = DataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        }

        // INVERT THE LOGIC
        priceSortedASC = !priceSortedASC;

        return products;
    }

    /**
     * Keeps track of the sorting logic for Expiry Date
     */
    private static boolean expiryDateSortedASC = true;
    
    @Override
    public List<ProductDTO> getProductsByRetailerIDSortedByExpiryDate(int retailerID) throws SQLException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        List<ProductDTO> products = new ArrayList<>();

        String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                       "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID WHERE pr.retailerID = ? " +
                       "ORDER BY pr.expiryDate " + (expiryDateSortedASC ? "ASC" : "DESC");

        try {
            con = DataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (con != null) {
                con.close();
            }
        }

        // INVERT THE LOGIC
        expiryDateSortedASC = !expiryDateSortedASC;

        return products;
    }

        
    @Override
    public List<ProductDTO> getProductsByVegStatus(int isVeg) throws SQLException {
    List<ProductDTO> products = new ArrayList<>();
    
    // SQL query with conditional WHERE clause based on isVeg
    String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                   "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID";

    if (isVeg == 1) {
        query += " WHERE p.isVeggie = ?";
    }

    try (Connection connection = DataSource.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        
        // Set the parameter only if filtering for vegetarian items
        if (isVeg == 1) {
            preparedStatement.setBoolean(1, true);
        }
        
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
public List<ProductDTO> getSurplusProducts() throws SQLException {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    List<ProductDTO> products = new ArrayList<>();

    String query = "SELECT p.productID, p.productName, p.price, p.isVeggie, pr.retailerID, pr.productQuantity, pr.expiryDate, pr.isSurplus " +
                   "FROM Product p JOIN ProductRetailer pr ON p.productID = pr.productID " +
                   "WHERE pr.isSurplus = true";

    try {
        con = DataSource.getConnection();
        preparedStatement = con.prepareStatement(query);

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
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return products;
}

    @Override
      public void updateProductQuantity(int productId, int quantity, int retailerID) throws SQLException {
        String query = "UPDATE ProductRetailer SET productQuantity = productQuantity - ? WHERE productID = ? AND retailerID = ?";
        try (Connection con = DataSource.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, retailerID);
            preparedStatement.executeUpdate();
        }
    }  
}
