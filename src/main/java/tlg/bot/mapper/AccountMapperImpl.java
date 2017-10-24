package tlg.bot.mapper;

import org.apache.ibatis.session.SqlSession;
import tlg.bot.models.Account;
import tlg.bot.models.Request;
import tlg.bot.services.MyBatisUtil;

import java.util.List;


public class AccountMapperImpl implements AccountMapper{

    private SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    private AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

    @Override
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        sqlSession.commit();
    }

    @Override
    public Account findByTelegramID(Integer telegramID) {
        Account account = accountMapper.findByTelegramID(telegramID);
        sqlSession.commit();
        return account;
    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = accountMapper.findAllAccounts();
        sqlSession.commit();
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        Account account = accountMapper.findById(id);
        sqlSession.commit();
        return account;
    }

    @Override
    public void delete(Integer id) {
        accountMapper.delete(id);
        sqlSession.commit();
    }

    @Override
    public void deleteByTelegramId(Integer id) {
        accountMapper.deleteByTelegramId(id);
        sqlSession.commit();
    }

    @Override
    public void update(Account account) {
        accountMapper.update(account);
        sqlSession.commit();
    }
}
