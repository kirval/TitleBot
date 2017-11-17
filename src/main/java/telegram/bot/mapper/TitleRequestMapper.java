package telegram.bot.mapper;

import org.apache.ibatis.annotations.*;
import telegram.bot.domain.TitleRequest;

import java.util.List;

@Mapper
public interface TitleRequestMapper {

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "telegramId",column = "user_telegram_id"),
            @Result(property = "url", column = "url"),
            @Result(property = "title", column = "title")
    })
    @Select("SELECT * FROM request_history WHERE user_telegram_id = #{telegramId}")
    List<TitleRequest> selectRequestsByTelegramId(Integer telegramId);

    @Select("SELECT * FROM request_history WHERE user_telegram_id = #{telegramId} ORDER BY id  DESC  LIMIT 10")
    List<TitleRequest> selectLast10RequestsByTelegramId(Integer telegramId);

    @Select("SELECT * FROM request_history")
    List<TitleRequest> findAllRequests();

    @Insert("INSERT INTO request_history (user_telegram_id, url, title) VALUES (#{telegramId}, #{url}, #{title});")
    void insertRequest(TitleRequest request);

    @Update("UPDATE request_history SET url=#{url}, title=#{title}")
    void update(TitleRequest request);

    @Delete("DELETE FROM request_history WHERE id={id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM request_history WHERE user_telegram_id=#{telegramId}")
    void deleteByTelegramId(Integer telegramId);
}
