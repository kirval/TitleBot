package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Account;
import tlg.bot.models.Request;
import tlg.bot.services.MyBatisUtil;

import java.util.List;


public class AccountMapperImpl implements AccountMapper{

    private static SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    private static AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

    @Override
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        sqlSession.commit();
    }

    @Override
    public List<Request> selectRequestsByAccountId(Integer id) {
        List<Request> requests = accountMapper.selectRequestsByAccountId(id);
        sqlSession.commit();
        return requests;
    }


    @Override
    public List<Request> selectTop10RequestsByAccountId(Integer id) {
        List<Request> requests = accountMapper.selectTop10RequestsByAccountId(id);
        sqlSession.commit();
        return requests;
    }
}
