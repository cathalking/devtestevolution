package testhelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tradingapp.dao.OrdersDAO;
import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.trading.Order;

public class OrdersDAOInMemory implements OrdersDAO {

	private List<Order> orders;

	public OrdersDAOInMemory(Order... orders) {
		this.orders = Arrays.asList(orders);
	}

	@Override
	public List<Order> findBy(String accountCode) {
		List<Order> matchingOrders = new ArrayList<Order>();
		for(Order order: orders) {
			if (accountCode.equals(order.getAccountCode())) {
				matchingOrders.add(order);
			}
		}
		return matchingOrders;
	}
	
	public List<Order> findBy(String accountCode, String isinCode) {
		List<Order> matchingOrders = new ArrayList<Order>();
		for(Order order: orders) {
			if (accountCode.equals(order.getAccountCode())
					&& isinCode.equals(order.getProduct().getIsinCode())) {
				matchingOrders.add(order);
			}
		}
		return matchingOrders;
	}

}
