package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Request;
import tlg.bot.services.MyBatisUtil;


public class RequestMapperImpl implements RequestMapper {

    private static SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    private static RequestMapper requestMapper = sqlSession.getMapper(RequestMapper.class);

    @Override
    public void insertRequest(Request request) {
        requestMapper.insertRequest(request);
        sqlSession.commit();
    }
}
