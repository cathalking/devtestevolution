package tradingapp.service.search;

import java.util.ArrayList;
import java.util.List;

import tradingapp.dao.OrdersDAO;
import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.permission.Permission;
import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;
import tradingapp.domain.user.User;

public class OrderSearchServiceImpl implements OrderSearchService {
	
	private OrdersDAO ordersDAO;

	public OrderSearchServiceImpl(OrdersDAO orderDAO) {
		this.ordersDAO = orderDAO;
	}

	@Override
	public List<Order> getOrders(Trader trader, String accountCode, String isinCode) {
		List<Order> matchingOrders = new ArrayList<Order>();
		for (Order order : ordersDAO.findBy(accountCode, isinCode)) {
			if (traderHasAccountPermissions(trader, order)) {
				matchingOrders.add(order);
			}
		}
		return matchingOrders;
	}

	private boolean traderHasAccountPermissions(Trader trader, Order order) {
		for (Permission permission : trader.getPermissions()) {
			TradingAccount tradingAccount = permission.getTradingAccount();
			if (permission.getTradingAccount().isActive() &&
					tradingAccount.getTradingFirm().getCode().equals(order.getAccountCode()))
				return true;
		}
		return false;
	}

	@Override
	public List<Order> getOrders(Trader trader, String accountCode) {
		List<Order> matchingOrders = new ArrayList<Order>();
		for (Order order : ordersDAO.findBy(accountCode)) {
			if (traderHasAccountPermissions(trader, order)) {
				matchingOrders.add(order);
			}
		}
		return matchingOrders;
	}

}
