package pl.dominikasmorag.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Result {
    private int id;
    private String location;
    private String description;
    private float squareFootage;
    private BigDecimal price;
    private String link;
    private String imgUrl;
    private Date postingDate;
    private Timestamp timestamp;

    public Result (int id, String location, String description, float squareFootage, BigDecimal price, String link, String imgUrl, Date postingDate, Timestamp timestamp) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.squareFootage = squareFootage;
        this.price = price;
        this.link = link;
        this.imgUrl = imgUrl;
        this.postingDate = postingDate;
        this.timestamp = timestamp;
    }

    public Result (String location, String description, float squareFootage, BigDecimal price, String link, String imgUrl, Date postingDate, Timestamp timestamp) {
        this.location = location;
        this.description = description;
        this.squareFootage = squareFootage;
        this.price = price;
        this.link = link;
        this.imgUrl = imgUrl;
        this.postingDate = postingDate;
        this.timestamp = timestamp;
    }

    public Result() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(float squareFootage) {
        this.squareFootage = squareFootage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", squareFootage=" + squareFootage +
                ", price=" + price +
                ", link='" + link + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", postingDate=" + postingDate +
                ", timestamp=" + timestamp +
                '}';
    }
}
