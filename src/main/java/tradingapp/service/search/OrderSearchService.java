package tradingapp.service.search;

import java.util.List;

import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;

public interface OrderSearchService {

	public List<Order> getOrders(Trader trader, String accountCode, String isinCode);

	public List<Order> getOrders(Trader trader, String searchAccountCode);

}