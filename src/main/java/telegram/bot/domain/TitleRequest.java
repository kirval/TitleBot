package telegram.bot.domain;

public class TitleRequest {
    private Integer id;
    private Integer telegramId;
    private String url;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(Integer telegramId) {
        this.telegramId = telegramId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TitleRequest(Integer telegramId, String url, String title) {
        this.telegramId = telegramId;
        this.url = url;
        this.title = title;
    }

    public TitleRequest(Integer id, Integer telegramId, String url, String title) {
        this.id = id;
        this.telegramId = telegramId;
        this.url = url;
        this.title = title;
    }

    @Override
    public String toString() {
        return title + "\n" + url;
    }
}
