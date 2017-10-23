package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Account;
import tlg.bot.models.Request;
import tlg.bot.services.MyBatisUtil;

import java.util.List;


public class AccountMapperImpl implements AccountMapper{

    @Override
    public void insertAccount(Account account) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

        accountMapper.insertAccount(account);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Account findByTelegramID(Integer telegramID) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

        Account account = accountMapper.findByTelegramID(telegramID);

        sqlSession.close();
        return account;
    }
}
