package es.panicape.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class Offers.
 */
@Getter
@Setter
public class Offers {
	
	@ApiModelProperty(value = "Nombre de la oferta", required = true, example = "Pera")
	private String name;

	/** The product to pay. */
	@ApiModelProperty(value = "Nombre del producto a pagar", required = true, example = "Pera")
	private String productToPay;
	
	/** The quantity to pay. */
	@ApiModelProperty(value = "Cantidad del producto a pagar", required = true, example = "3")
	private Integer quantityToPay;
	
	/** The product free. */
	@ApiModelProperty(value = "Nombre del producto que hay gratis", required = false, example = "Manzana")
	private String productFree;
	
	/** The quantity free. */
	@ApiModelProperty(value = "Cantidad del producto que hay gratis", required = false, example = "2")
	private Integer quantityFree;
	
	/** The discount. */
	@ApiModelProperty(value = "Descuento final que se hace sobre la compra (en euros)", required = false, example = "1")
	private Integer discount;
	
}
