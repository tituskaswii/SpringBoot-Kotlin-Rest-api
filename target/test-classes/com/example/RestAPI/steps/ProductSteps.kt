/*
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.junit.Assert.assertEquals

@Component
class ProductSteps @Autowired constructor(
    private val restTemplate: TestRestTemplate,
    private val productTestContext: ProductTestContext
) {

    @Given("^the startIndex is (\\d+)$")
    fun givenTheStartIndexIs(startIndex: Int) {
        productTestContext.startIndex = startIndex
    }

    @Given("^the product ID is \"([^\"]*)\"$")
    fun givenTheProductIdIs(productId: String) {
        productTestContext.productId = productId
    }

    @Given("^the search key is \"([^\"]*)\"$")
    fun givenTheSearchKeyIs(searchKey: String) {
        productTestContext.searchKey = searchKey
    }

    @When("^the client requests GET /products$")
    fun whenTheClientRequestsGetProducts() {
        productTestContext.response = restTemplate.getForEntity("/products?startIndex={startIndex}", Array<Product>::class.java, productTestContext.startIndex)
    }

    @When("^the client requests GET /products/([^/]+)$")
    fun whenTheClientRequestsGetProductById(id: String) {
        productTestContext.response = restTemplate.getForEntity("/products/{id}", Product::class.java, id)
    }

    @When("^the client requests POST /products/search with searchKey \"([^\"]*)\"$")
    fun whenTheClientRequestsPostProductSearch(searchKey: String) {
        val requestBody = mapOf("searchKey" to searchKey)
        productTestContext.response = restTemplate.postForEntity("/products/search", requestBody, Array<Product>::class.java)
    }

    @Then("^the response status code should be (\\d+)$")
    fun thenTheResponseStatusCodeShouldBe(expectedStatusCode: Int) {
        assertEquals(expectedStatusCode, productTestContext.response?.statusCodeValue)
    }

    @Then("^the response should contain products$")
    fun thenTheResponseShouldContainProducts() {
        val products = productTestContext.response?.body
        assertEquals(false, products?.isEmpty())
    }

    @Then("^the response should contain product details$")
    fun thenTheResponseShouldContainProductDetails() {
        val product = productTestContext.response?.body
        assertEquals(true, product != null)
    }

    @Then("^the response should contain products matching the search key$")
    fun thenTheResponseShouldContainProductsMatchingTheSearchKey() {
        val products = productTestContext.response?.body
        val searchKey = productTestContext.searchKey
        assertEquals(false, products?.all { it.title.contains(searchKey) || it.description.contains(searchKey) || it.brand.contains(searchKey) }?.isEmpty())
    }
}
*/
