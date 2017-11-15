package tlg.bot.models;

public class Selector {
    private Integer id;
    private String elementId;
    private Long telegramId;
    private String url;

    public Selector(Long telegramId, String url) {
        this.telegramId = telegramId;
        this.url = url;
    }

    public Selector(Integer id, Long telegramId, String url) {
        this.id = id;
        this.telegramId = telegramId;
        this.url = url;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
