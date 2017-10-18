package tlg.bot.models;

import lombok.Data;

@Data
public class Account {
    Integer id;

    Boolean is_bot;

    String first_name;

    String last_name;

    String username;

    String language_name;
}
