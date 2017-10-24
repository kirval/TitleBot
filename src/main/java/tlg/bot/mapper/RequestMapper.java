package tlg.bot.mapper;

import org.apache.ibatis.annotations.*;
import tlg.bot.models.Request;

import java.util.List;

public interface RequestMapper {

    @Results(value = {
            @Result(property = "telegramID",column = "user_telegram_id"),
            @Result(property = "url", column = "url"),
            @Result(property = "title", column = "title")
    })
    @Select("SELECT * FROM request_history WHERE user_telegram_id = #{id}")
    List<Request> selectRequestsByAccountId(Integer id);

    @Select("SELECT * FROM request_history WHERE user_telegram_id = #{id} ORDER BY id  DESC  LIMIT 10")
    List<Request> selectTop10RequestsByAccountId(Integer id);

    @Select("SELECT * FROM request_history")
    List<Request> findAllRequests();

    @Insert("INSERT INTO request_history (user_telegram_id, url, title) VALUES (#{telegramID}, #{url}, #{title});")
    void insertRequest(Request request);

    @Update("UPDATE request_history SET url=#{url}, title=#{title}")
    void update(Request request);

    @Delete("DELETE FROM request_history WHERE id={id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM request_history WHERE user_telegram_id=#{telegram_id}")
    void deleteByTelegramId(Integer telegram_id);
}
