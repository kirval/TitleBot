package tlg.bot.services;

import com.google.inject.Inject;
import tlg.bot.models.RequestHistory;

import java.util.List;

public class HistoryService{

    private AccountService accountService;

    @Inject
    public HistoryService(AccountService accountService) {
        this.accountService = accountService;
    }

    List<RequestHistory> findByUserId(){
        return null;
    }
}
