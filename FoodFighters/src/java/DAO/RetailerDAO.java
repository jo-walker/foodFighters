/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.ProductDTO;
import DTO.RetailerDTO;

/**
 *
 * @author Baasanlkham Gurvantamir 041129783 gurv0003@algonquinlive.com
 */
public interface RetailerDAO {
    void addRetalier(RetailerDTO retailer);
    void addItem(ProductDTO product);
    void getProductsByRetailerID(int retailerID);
}
