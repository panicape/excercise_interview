package es.panicape.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.panicape.model.Offers;
import es.panicape.model.ProductDto;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class StoreController.
 */
@RestController
@RequestMapping("/store_handler")
public class StoreController {


	/**
	 * Pay basket.
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/basket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> payBasket() {
		List<ProductDto> products = new ArrayList<ProductDto>();
		List<Offers> offerList = new ArrayList<Offers>();
		
		Double totalPrice = 0.0;
		
		ProductDto Pear = new ProductDto();
		Pear.setName("Pear");
		Pear.setPrice(0.75);
		Pear.setQuantity(3);
		products.add(Pear);
		
		totalPrice = Pear.getPrice()*Pear.getQuantity();

		ProductDto orange = new ProductDto();
		orange.setName("Orange");
		orange.setPrice(1.0);
		orange.setQuantity(25);
		products.add(orange);
		
		totalPrice = totalPrice+orange.getPrice()*orange.getQuantity();

		ProductDto apple = new ProductDto();
		apple.setName("Apple");
		apple.setPrice(0.9);
		apple.setQuantity(12);
		products.add(apple);
		
		totalPrice = totalPrice + apple.getPrice()*apple.getQuantity();


		Offers offer1 = new Offers();
		offer1.setName("offer2");
		offer1.setProductToPay("Apple");
		offer1.setQuantityToPay(2);
		offer1.setProductFree("Apple");
		offer1.setQuantityFree(1);
		offerList.add(offer1);

		Offers offer2 = new Offers();
		offer2.setName("offer2");
		offer2.setProductToPay("Pear");
		offer2.setQuantityToPay(2);
		offer2.setProductFree("Orange");
		offer2.setQuantityFree(1);
		offerList.add(offer2);

		Offers offer3 = new Offers();
		offer3.setName("offer3");
		offer3.setProductToPay("Pear");
		offer3.setQuantityToPay(4);
		offer3.setDiscount(1);
		offerList.add(offer3);
		
		ProductDto productfree = null ;
		for (Offers offers : offerList) {
			for (int i = 0; i < products.size(); i++) {
				if (productfree != null && productfree.getName().equals(products.get(i).getName())) {
					products.get(i).setQuantity(products.get(i).getQuantity()+productfree.getQuantity());
					productfree = null;
				}
				if (offers.getProductToPay().equals(products.get(i).getName())) {
					
					if (offers.getProductFree() != null) {
						
						Integer totalfree = products.get(i).getQuantity()/offers.getQuantityToPay();
						if (offers.getProductFree().equals(products.get(i).getName())) {
							products.get(i).setQuantity(products.get(i).getQuantity()+totalfree);
						} else {
							productfree = new ProductDto();
							productfree.setName(offers.getProductFree());
							productfree.setQuantity(products.get(i).getQuantity()+totalfree);
						}
						System.out.println(offers.getName() + " applied");
					}
					
					if (offers.getDiscount() != null) {
						Integer discount = products.get(i).getQuantity()/offers.getQuantityToPay();
						if (discount != 0) {
							System.out.println(offers.getName() + " applied");
						}
					}
				}
			}
		}
		
		System.out.println ("Total price=" + totalPrice);
		
		System.out.println("Total Product List=" + products.size());
		for (ProductDto productDto: products) {
			System.out.println("Name="+productDto.getName() + ", quantity="+productDto.getQuantity());
		}
		
		
		return ResponseEntity.ok(products);
	}

	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	@ApiResponses({
		@ApiResponse(code = 200, message = "Productos encontrados"),
		@ApiResponse(code = 500, message = "Error de plataforma"),
		@ApiResponse(code = 401, message = "Productos no encontrados")
	})
	@RequestMapping(value = "/products_all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getProducts() {
		List<ProductDto> products = new ArrayList<ProductDto>();

		ProductDto Pear = new ProductDto();
		Pear.setName("Pear");
		Pear.setPrice(0.75);
		Pear.setQuantity(3);
		products.add(Pear);

		ProductDto orange = new ProductDto();
		orange.setName("Orange");
		orange.setPrice(1.0);
		orange.setQuantity(25);
		products.add(orange);

		ProductDto apple = new ProductDto();
		apple.setName("apple");
		apple.setPrice(0.9);
		apple.setQuantity(12);
		products.add(apple);

		return ResponseEntity.ok(products);
	}

	/**
	 * Gets the offers.
	 *
	 * @return the offers
	 */
	@RequestMapping(value = "/offers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Offers>> getOffers() {
		List<Offers> offerList = new ArrayList<Offers>();
		Offers offer1 = new Offers();
		offer1.setProductToPay("Apple");
		offer1.setQuantityToPay(2);
		offer1.setProductFree("Apple");
		offer1.setQuantityFree(1);
		offerList.add(offer1);

		Offers offer2 = new Offers();
		offer2.setProductToPay("Pear");
		offer2.setQuantityToPay(2);
		offer2.setProductFree("Orange");
		offer2.setQuantityFree(1);
		offerList.add(offer2);

		Offers offer3 = new Offers();
		offer3.setProductToPay("Pear");
		offer3.setQuantityToPay(4);
		offer3.setDiscount(1);
		offerList.add(offer3);

		return ResponseEntity.ok(offerList);
	}

}
