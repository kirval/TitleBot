package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Request;
import tlg.bot.services.MyBatisUtil;

import java.util.List;


public class RequestMapperImpl implements RequestMapper {

    @Override
    public void insertRequest(Request request) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        requestMapper.insertRequest(request);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Request> selectRequestsByAccountId(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        List<Request> requests = requestMapper.selectRequestsByAccountId(id);
        sqlSession.close();
        return requests;
    }

    @Override
    public List<Request> selectTop10RequestsByAccountId(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        List<Request> requests = requestMapper.selectTop10RequestsByAccountId(id);
        sqlSession.close();
        return requests;
    }

    @Override
    public List<Request> findAllRequests() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        List<Request> requests = requestMapper.findAllRequests();
        sqlSession.close();
        return requests;
    }

    @Override
    public void update(Request request) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        requestMapper.update(request);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        requestMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByTelegramId(Integer telegram_id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);
        requestMapper.deleteByTelegramId(telegram_id);
        sqlSession.commit();
        sqlSession.close();
    }
}
