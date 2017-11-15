package tlg.bot.mapper;

import org.apache.ibatis.annotations.*;
import tlg.bot.models.Selector;

import java.util.List;

public interface SelectorMapper {

    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "elementId", column = "element_id"),
            @Result(property = "telegramId", column = "telegramId"),
            @Result(property = "url", column = "url")
    })
    @Select("SELECT * FROM Selector WHERE id = #{id}")
    Selector getSelectorById(Integer id);

    @Select("SELECT * FROM Selector WHERE telegramId = #{telegramId}")
    List<Selector> findSelectorsByAccountId(Integer id);

    @Select("SELECT * FROM Selector")
    List<Selector> findAllSelectors();

    @Insert("INSERT INTO Selector (telegram_id, elementId, url) VALUES (#{telegramId}, #{elementId}, #{url});")
    void insertSelector(Selector selector);

    @Update("UPDATE Selector SET url=#{url}")
    void update(Selector selector);

    @Delete("DELETE FROM Selector WHERE id={id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM Selector WHERE user_telegram_id=#{telegram_id}")
    void deleteByTelegramId(Integer telegram_id);
}
