package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Selector;
import tlg.bot.services.MyBatisUtil;

import java.util.List;

public class SelectorMapperImpl implements SelectorMapper{

    @Override
    public Selector getSelectorById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        Selector selector = mapper.getSelectorById(id);
        sqlSession.close();
        return selector;
    }

    @Override
    public List<Selector> findSelectorsByAccountId(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        List<Selector> selectors = mapper.findSelectorsByAccountId(id);
        sqlSession.close();
        return selectors;
    }

    @Override
    public List<Selector> findAllSelectors() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        List<Selector> selectors = mapper.findAllSelectors();
        sqlSession.close();
        return selectors;
    }

    @Override
    public void insertSelector(Selector selector) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Selector selector) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        mapper.update(selector);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByTelegramId(Integer telegram_id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        SelectorMapper mapper = sqlSession.getMapper(SelectorMapper.class);
        mapper.deleteByTelegramId(telegram_id);
        sqlSession.commit();
        sqlSession.close();
    }
}
