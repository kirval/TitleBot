package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Order;
import tlg.bot.services.MyBatisUtil;

import java.util.List;

public class OrderMapperImpl implements OrderMapper{
    @Override
    public Order getOrderById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order order = mapper.getOrderById(id);
        sqlSession.close();
        return order;
    }

    @Override
    public List<Order> findOrdersBySelectorId(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = mapper.findOrdersBySelectorId(id);
        sqlSession.close();
        return orders;
    }

    @Override
    public List<Order> findAllOrders() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orders = mapper.findAllOrders();
        sqlSession.close();
        return orders;
    }

    @Override
    public void insertOrder(Order order) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        mapper.insertOrder(order);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Order order) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        mapper.update(order);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteBySelectorId(Integer selectorId) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        mapper.deleteBySelectorId(selectorId);
        sqlSession.commit();
        sqlSession.close();
    }
}
