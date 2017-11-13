package tlg.bot.mapper;

import org.apache.ibatis.annotations.*;
import tlg.bot.models.Order;

import java.util.List;

public interface OrderMapper {

    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "orderLink", column = "order_link"),
            @Result(property = "selectorId", column = "selector_id")
    })

    @Select("SELECT * FROM Orders WHERE id = #{id}")
    Order getOrderById(Integer id);

    @Select("SELECT * FROM Orders WHERE selector_id = #{id}")
    List<Order> findOrdersBySelectorId(Integer id);

    @Select("SELECT * FROM Orders")
    List<Order> findAllOrders();

    @Insert("INSERT INTO Orders (order_link, selector_id) VALUES (#{orderLink}, #{selectorId});")
    void insertOrder(Order order);

    @Update("UPDATE Orders SET order_link=#{orderLink}")
    void update(Order order);

    @Delete("DELETE FROM Orders WHERE id={id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM Orders WHERE selector_id=#{selectorId}")
    void deleteBySelectorId(Integer selectorId);
}
