package pl.dominikasmorag.pojo;

import java.math.BigDecimal;
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
    private long duration;

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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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
                ", duration=" + duration +
                '}';
    }
}
