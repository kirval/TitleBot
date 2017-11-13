package tlg.bot.models;

public class Order {
    private Integer id;
    private String orderLink;
    private Integer selectorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderLink() {
        return orderLink;
    }

    public void setOrderLink(String orderLink) {
        this.orderLink = orderLink;
    }

    public Integer getSelectorId() {
        return selectorId;
    }

    public void setSelectorId(Integer selectorId) {
        this.selectorId = selectorId;
    }

    public Order(Integer id, String orderLink, Integer selectorId) {
        this.id = id;
        this.orderLink = orderLink;
        this.selectorId = selectorId;
    }

    public Order(String orderLink, Integer selectorId) {
        this.orderLink = orderLink;
        this.selectorId = selectorId;
    }
}
