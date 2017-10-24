package tlg.bot.mapper;

import org.apache.ibatis.annotations.*;
import tlg.bot.models.Account;

import java.util.List;


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


    @Select("SELECT * FROM userlist")
    List<Account> findAllAccounts();

    @Select("SELECT telegram_id, first_name, last_name, username " +
            "FROM userlist WHERE id = #{id}")
    Account findById(Integer id);

    @Select("SELECT telegram_id, first_name, last_name, username " +
            "FROM userlist WHERE telegram_id = #{telegramID}")
    Account findByTelegramID(Integer telegramID);

    @Delete("DELETE FROM userlist WHERE id= #{id}")
    void delete(Integer id);

    @Delete("DELETE FROM userlist WHERE telegram_id = #{id}")
    void deleteByTelegramId(Integer id);

    @Update("UPDATE userlist SET first_name=#{first_name}, last_name=#{last_name}, username = #{username}")
    void update(Account account);


}
