/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.ProductDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andrea Visani 041104651 visa0004@algonquinlive.com
 */
public interface ProductDAO {
    
    public List<ProductDTO> getAllProducts();
    public ArrayList<ProductDTO> getProductByName(String name);
    public ProductDTO getProductByID(Integer productID);
    public void addProduct(ProductDTO product);
    public void updateProduct(ProductDTO product);
    public void deleteProduct(ProductDTO product);
    
}
