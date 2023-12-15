package com.example.restapi.infrastructure

import com.example.restapi.domain.Product
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.map.IMap
import org.springframework.stereotype.Component

@Component
class ProductCache(private val hazelcastInstance: HazelcastInstance) {

    private val cache: IMap<String, List<Product>> =
        hazelcastInstance.getMap("productCache")

    fun cacheProducts(products: List<Product>) {
        cache.set("allProducts", products)
    }

    fun getCachedProducts(startIndex: Int, pageSize: Int): List<Product> {
        val allProducts = cache.get("allProducts") ?: emptyList()
        val endIndex = minOf(startIndex + pageSize, allProducts.size)
        return if (startIndex < endIndex) allProducts.subList(startIndex, endIndex) else emptyList()
    }
}
