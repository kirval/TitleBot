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

    @Override
    public List<Account> findAllAccounts() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accounts = accountMapper.findAllAccounts();
        sqlSession.close();
        return accounts;
    }

    @Override
    public Account findById(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.findById(id);
        sqlSession.close();
        return account;
    }

    @Override
    public void delete(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.delete(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByTelegramId(Integer id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.deleteByTelegramId(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Account account) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        accountMapper.update(account);
        sqlSession.commit();
        sqlSession.close();
    }
}
