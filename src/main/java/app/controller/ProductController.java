package app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.model.CurrentPrice;
import app.model.Product;
import app.model.ProductInfo;
import app.repository.ProductPriceRepository;


@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductPriceRepository productPriceRepository;
  
  @RequestMapping(method = RequestMethod.GET, value="/{productId}")
  public Product getProductDetails(@PathVariable("productId") Long productId){
	  Product product = new Product();
	  try{
	  RestTemplate restTemplate = new RestTemplate();
	  CurrentPrice response = productPriceRepository.findCurrentPriceByProductId(productId);
	  ResponseEntity<String> resp = restTemplate.getForEntity("https://api.target.com/products/v3/13860428?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz", String.class);
	  ProductInfo productInfo = new ProductInfo();;
	  ObjectMapper mapper = new ObjectMapper();
	  JsonNode root = mapper.readTree(resp.getBody());
	  productInfo.setName(root.findValues("general_description").get(0).asText());
	  productInfo.setId(response.getProductId());
	  product.setInfo(productInfo);
	  product.setCurrentPrice(response);
	  }catch(Exception e){
		  System.out.println(e.getLocalizedMessage());
	  }
    return product;
  }
  
  @RequestMapping(method = RequestMethod.PUT, value="/{productId}")
  public String editProduct(@PathVariable("productId") Long productId, 
      @RequestBody Product product){
   CurrentPrice currentPrice = new CurrentPrice();
   currentPrice.setCurrency_code(product.getCurrentPrice().getCurrency_code());
   currentPrice.setId(product.getCurrentPrice().getId());
   currentPrice.setProductId(product.getCurrentPrice().getProductId());
   currentPrice.setValue(product.getCurrentPrice().getValue());
   
   productPriceRepository.save(currentPrice);
    String response = "Product Price updated successfully";
    return response;
  }

}
