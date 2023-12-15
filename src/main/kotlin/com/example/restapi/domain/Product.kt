package com.example.restapi.domain

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val brand: String,
    val thumbnail: String
) {
    init {
        require(id.isNotBlank()) { "Product ID must not be blank" }
        require(title.isNotBlank()) { "Product title must not be blank" }
        require(description.isNotBlank()) { "Product description must not be blank" }
        require(price >= 0) { "Product price must be non-negative" }
        require(brand.isNotBlank()) { "Product brand must not be blank" }
        require(thumbnail.isNotBlank()) { "Product thumbnail must not be blank" }
    }
}