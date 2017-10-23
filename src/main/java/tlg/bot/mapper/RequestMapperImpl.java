package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Request;
import tlg.bot.services.MyBatisUtil;

import java.util.List;


public class RequestMapperImpl implements RequestMapper {

    private static SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    private static RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);

    @Override
    public void insertRequest(Request request) {
        requestMapper.insertRequest(request);
        sqlSession.commit();
    }

    @Override
    public List<Request> selectRequestsByAccountId(Integer id) {
        List<Request> requests = requestMapper.selectRequestsByAccountId(id);
        sqlSession.commit();
        return requests;
    }

    @Override
    public List<Request> selectTop10RequestsByAccountId(Integer id) {
        List<Request> requests = requestMapper.selectTop10RequestsByAccountId(id);
        sqlSession.commit();
        return requests;
    }
}
