package tlg.bot.services;

import com.google.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import tlg.bot.mapper.AccountMapper;
import tlg.bot.models.Account;

import java.util.List;


public class AccountService {

    private SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    private AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

    public List<Account> findAll(){
        return accountMapper.findAll();
    }

    public Account findById(Long id){
        return accountMapper.findById(id);
    }

    public void save(Account data){
        accountMapper.insertAccount(data);
        sqlSession.commit();
    }
/*
    void delete(Long id){
        accountMapper.delete(id);
    }*/
}
