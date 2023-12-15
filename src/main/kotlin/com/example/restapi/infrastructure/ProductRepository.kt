package com.example.restapi.infrastructure

import com.example.restapi.domain.Product
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Repository
class ProductRepository(private val restTemplate: RestTemplate, @Value("\${api.provider.url}") private val apiProviderUrl: String)  {

     fun getAllProducts(startIndex: Int, pageSize: Int): List<Product> {
        val uri = UriComponentsBuilder.fromUriString(apiProviderUrl)
            .queryParam("startIndex", startIndex)
            .queryParam("pageSize", pageSize)
            .build().toUriString()

        val response = restTemplate.getForObject(uri, Array<Product>::class.java)
        return response?.toList() ?: emptyList()
    }

     fun getProductById(id: String): Product? {
        val uri = UriComponentsBuilder.fromUriString("$apiProviderUrl/$id").build().toUriString()

        return restTemplate.getForObject(uri, Product::class.java)
    }

     fun searchProducts(searchKey: String): List<Product> {
        val uri = UriComponentsBuilder.fromUriString(apiProviderUrl)
            .queryParam("searchKey", searchKey)
            .build().toUriString()

        val response = restTemplate.getForObject(uri, Array<Product>::class.java)
        return response?.toList() ?: emptyList()
    }
}
