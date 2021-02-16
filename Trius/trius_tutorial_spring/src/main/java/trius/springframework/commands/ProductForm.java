package trius.springframework.commands;


import java.math.BigDecimal;

public class ProductForm {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() { return stock; }

    public void setStock(BigDecimal stock) { this.stock = stock; }
}
