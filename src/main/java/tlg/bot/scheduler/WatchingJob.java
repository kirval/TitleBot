package tlg.bot.scheduler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import tlg.bot.mapper.*;
import tlg.bot.models.Order;
import tlg.bot.models.Selector;
import tlg.bot.services.ParseService2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WatchingJob implements Job {
    public WatchingJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        AccountMapper accountMapper = new AccountMapperImpl();
        OrderMapper orderMapper = new OrderMapperImpl();
        SelectorMapper selectorMapper = new SelectorMapperImpl();
        ParseService2 parseService2 = new ParseService2();

        List<Selector> selectors = selectorMapper.findAllSelectors();

        for (Selector selector: selectors) {
            long telegramId = selector.getTelegramId();
            String url = selector.getUrl();

            List<Order> orders = orderMapper.findOrdersBySelectorId(selector.getId());
            List<String> orderLinks = new ArrayList<>();
            orders.forEach(a -> orderLinks.add(a.getOrderLink()));

            Elements elements = parseService2.getOrders(url, selector.getElementId());
            List<String> links = new ArrayList<>();
            elements.forEach(a -> links.add(a.attr("abs:href")));
            

            for (String link: links) {
                if(!orderLinks.contains(link)) {
                    Order newOrder = new Order(link, selector.getId());
                    orderMapper.insertOrder(newOrder);
                }
            }
        }
    }
}
