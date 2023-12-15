
import com.example.restapi.application.ProductService
import com.example.restapi.domain.Product
import com.example.restapi.dto.AuthenticationRequest
import com.example.restapi.dto.AuthenticationResponse
import com.example.restapi.security.CustomUserDetailsService
import com.example.restapi.security.JwtTokenUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@EnableWebSecurity
class ProductController(
    private val productService: ProductService,
    @Autowired
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val jwtTokenUtil: JwtTokenUtil
) {
    private val logger: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @GetMapping
    fun getAllProducts(@RequestParam startIndex: Int, @RequestParam(defaultValue = "10") pageSize: Int): ResponseEntity<List<Product>> {
        try {
            val products = productService.getAllProducts(startIndex, pageSize)
            // log products received
            logger.info("Received request to get all products with startIndex: {}, pageSize: {}", startIndex, pageSize)

            return ResponseEntity.ok(products)
        } catch (ex: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: String): ResponseEntity<Product?> {
        try {
            val product = productService.getProductById(id)

            // log received product ID
            logger.info("Received request to get product by ID: {}", id)
            return if (product != null) ResponseEntity.ok(product) else ResponseEntity.notFound().build()
        } catch (ex: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping("/search")
    fun searchProducts(@RequestParam searchKey: String): ResponseEntity<List<Product>> {
        try {
            val products = productService.searchProducts(searchKey)

            // log the product search result
            logger.info("Received request to search products with searchKey: {}", searchKey)
        
            return ResponseEntity.ok(products)
        } catch (ex: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    authenticationRequest.username,
                    authenticationRequest.password
                )
            )
        } catch (e: BadCredentialsException) {
            throw Exception("Incorrect username or password", e)
        }

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token: String = jwtTokenUtil.generateToken(userDetails)

        return ResponseEntity.ok(AuthenticationResponse(token))
    }

    // Test Protected Endpoints
    // Test the protected endpoints (/products/**) by including the JWT token in the request header like below
   //  curl -X GET http://localhost:8080/products/1 -H "Authorization: Bearer <your-secret-token>"

}
