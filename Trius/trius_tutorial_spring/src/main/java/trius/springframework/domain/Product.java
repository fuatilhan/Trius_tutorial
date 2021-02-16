package trius.springframework.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Document
public class Product {
    @Id
    @NotNull
    private ObjectId _id;
    @NotNull(message = "Product name is required.")
    @Pattern(regexp="^[a-zA-Z0-9]{1,255}",message="at most 255 characters")
    private String name;
    @NotNull(message = "Product description is required.")
    @Pattern(regexp="^[a-zA-Z0-9]{1,255}",message="at most 255 characters")
    private String description;
    @NotNull(message = "Product price is required.")
    @Max(value=99999, message="can't be higher than 99,999")
    private BigDecimal price;
    @NotNull(message = "Product must be in stock.")
    @Max(value=99999, message="can't be higher than 99,999")
    private BigDecimal stock;


    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
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
