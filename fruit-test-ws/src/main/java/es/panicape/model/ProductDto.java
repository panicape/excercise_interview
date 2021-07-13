package es.panicape.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Instantiates a new product dto.
 */
@Data	
public class ProductDto {
	
	/** The name. */
	@ApiModelProperty(value = "Nombre del producto", required = true, example = "Pera")
	private String name;
	
	/** The price. */
	@ApiModelProperty(value = "Precio del producto (en euros)", required = true, example = "0.79")
	private Double price;
	
	/** The quantity. */
	@ApiModelProperty(value = "Cantidad del producto", required = true, example = "1")
	private Integer quantity;
	
}
