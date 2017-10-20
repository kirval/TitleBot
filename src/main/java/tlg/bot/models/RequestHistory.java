package tlg.bot.models;

import lombok.Data;

@Data
public class RequestHistory {
    private Integer telegram_id;
    private String url;
    private String title;

    public RequestHistory(Integer telegram_id, String url, String title) {
        this.telegram_id = telegram_id;
        this.url = url;
        this.title = title;
    }
}
