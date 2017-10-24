package tlg.bot.models;

public class Request {
    private Integer id;
    private Integer telegramID;
    private String url;
    private String title;

    public Request(Integer id, Integer telegramID, String url, String title) {
        this.id = id;
        this.telegramID = telegramID;
        this.url = url;
        this.title = title;
    }

    public Request(Integer telegramID, String url, String title) {
        this.telegramID = telegramID;
        this.url = url;
        this.title = title;

    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTelegramID() {
        return this.telegramID;
    }

    public void setTelegramID(Integer telegramID) {
        this.telegramID = telegramID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Url: " + this.url + ", Title: " + this.title;
    }
}
