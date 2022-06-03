package net.javaguides.qlbanhang.model;

public class Product {
	private Long id;
	private String code;
	private String image;
	private String name;
	private String type;
	private Double price;
	
	public Product() {}
	
	public Product(Long id, String code, String image, String name, String type, Double price) {
		super();
		this.id = id;
		this.code = code;
		this.image = image;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
