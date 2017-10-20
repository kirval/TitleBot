package tlg.bot.mapper;

import tlg.bot.models.Account;

import java.util.ArrayList;
import java.util.List;

public interface AccountMapper {
    void insertAccount(Account account);
    List<Account> findAll();
    Account findById(long id);
    Account save(Account data);
    void delete(long id);
}
