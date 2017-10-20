package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Account;
import tlg.bot.services.MyBatisUtil;


public class AccountMapperImpl implements AccountMapper{

    private static SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    private static AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

    @Override
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        sqlSession.commit();
    }
}
