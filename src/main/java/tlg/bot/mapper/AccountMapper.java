package tlg.bot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tlg.bot.models.Account;


public interface AccountMapper {
    @Results(value = {
            @Result(property = "telegramID",column = "telegram_id"),
            @Result(property = "first_name", column = "first_name"),
            @Result(property = "last_name", column = "last_name"),
            @Result(property = "username", column = "username")
    })

    @Insert("INSERT INTO userlist (telegram_id, first_name, last_name, username) " +
            "VALUES (#{telegramID}, #{first_name}, #{last_name}, #{username});")
    void insertAccount(Account account);

    @Select("SELECT telegram_id, first_name, last_name, username " +
            "FROM userlist WHERE telegram_id = #{telegramID}")
    Account findByTelegramID(Integer telegramID);

}
