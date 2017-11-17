package telegram.bot.mapper;

import org.apache.ibatis.annotations.*;
import telegram.bot.domain.Account;

import java.util.List;

@Mapper
public interface AccountMapper {
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "telegramId",column = "telegram_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "username", column = "username")
    })

    @Insert("INSERT INTO userlist (telegram_id, first_name, last_name, username) " +
            "VALUES (#{telegramId}, #{firstName}, #{lastName}, #{username});")
    void insertAccount(Account account);


    @Select("SELECT * FROM userlist")
    List<Account> findAllAccounts();

    @Select("SELECT id, telegram_id, first_name, last_name, username " +
            "FROM userlist WHERE id = #{id}")
    Account findById(Long id);

    @Select("SELECT telegram_id, first_name, last_name, username " +
            "FROM userlist WHERE telegram_id = #{telegramId}")
    Account findByTelegramID(Integer telegramId);

    @Delete("DELETE FROM userlist WHERE id= #{id}")
    void delete(Integer id);

    @Delete("DELETE FROM userlist WHERE telegram_id = #{id}")
    void deleteByTelegramId(Integer telegramId);

    @Update("UPDATE userlist SET first_name=#{firstName}, last_name=#{lastName}, username = #{username}")
    void update(Account account);


}
