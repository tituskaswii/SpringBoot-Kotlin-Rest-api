package com.example.restapi.application

import com.example.restapi.domain.Product
import org.springframework.stereotype.Service

@Service
interface ProductService {
    fun getAllProducts(startIndex: Int, pageSize: Int): List<Product>
    fun getProductById(id: String): Product?
    fun searchProducts(searchKey: String): List<Product>
}
