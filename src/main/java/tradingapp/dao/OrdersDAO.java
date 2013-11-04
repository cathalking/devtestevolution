package tradingapp.dao;

import java.util.List;

import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.trading.Order;

public interface OrdersDAO {

	public List<Order> findBy(String accountCode);
	public List<Order> findBy(String accountCode, String isinCode);
}
