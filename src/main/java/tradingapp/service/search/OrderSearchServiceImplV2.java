package tradingapp.service.search;

import java.util.ArrayList;
import java.util.List;

import tradingapp.dao.OrdersDAO;
import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.permission.Permission;
import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;
import tradingapp.domain.user.User;
import tradingapp.service.permission.PermissionService;

public class OrderSearchServiceImplV2 implements OrderSearchService {
	
	private OrdersDAO ordersDAO;
	private PermissionService permissionService;

	public OrderSearchServiceImplV2(OrdersDAO orderDAO, PermissionService permissionService) {
		this.ordersDAO = orderDAO;
		this.permissionService = permissionService;
	}

	@Override
	public List<Order> getOrders(Trader trader, String accountCode, String isinCode) {
		List<Order> matchingOrders = new ArrayList<Order>();
		for (Order order : ordersDAO.findBy(accountCode, isinCode)) {
			if (permissionService.hasViewPermissions(trader, order)) {
				matchingOrders.add(order);
			}
		}
		return matchingOrders;
	}


	@Override
	public List<Order> getOrders(Trader trader, String accountCode) {
		List<Order> matchingOrders = new ArrayList<Order>();
		for (Order order : ordersDAO.findBy(accountCode)) {
			if (permissionService.hasViewPermissions(trader, order)) {
				matchingOrders.add(order);
			}
		}
		return matchingOrders;
	}

}
