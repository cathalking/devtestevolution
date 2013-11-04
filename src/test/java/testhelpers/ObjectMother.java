package testhelpers;

import static tradingapp.domain.company.AccountStatus.ACTIVE;

import java.util.Arrays;

import tradingapp.dao.OrdersDAO;
import tradingapp.domain.company.AccountStatus;
import tradingapp.domain.company.ClearingFirm;
import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.company.TradingFirm;
import tradingapp.domain.permission.Permission;
import tradingapp.domain.securities.Future;
import tradingapp.domain.securities.ISIN;
import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;
import tradingapp.service.search.OrderSearchService;
import tradingapp.service.search.OrderSearchServiceImpl;

public class ObjectMother {

	public static OrdersDAOInMemory createOrdersDAO(Order order1, Order order2, Order order3, Order order4) {
		return new OrdersDAOInMemory(order1, order2, order3, order4);
	}

	public static OrderSearchService createOrderService(OrdersDAO ordersDAO) {
		OrderSearchService orderSearchService = new OrderSearchServiceImpl(ordersDAO);
		return orderSearchService;
	}
	
	private OrderSearchService prepareOrderService(Order order1, Order order2, Order order3, Order order4) {
		OrdersDAO orderDAO = createOrdersDAO(order1, order2, order3, order4);
		OrderSearchService orderSearchService = new OrderSearchServiceImpl(orderDAO);
		return orderSearchService;
	}

	public static Order createOrder(String accountCode, Future dummyFuture) {
		return createOrder(accountCode, dummyFuture, qty(0), price(0));
	}

	public static  Order createOrder(String accountCode, Future future, int qty, int price) {
		return new Order(nextOrderId(), accountCode, future, qty, price);
	}
	
	private Order createOrder(String orderId, TradingAccount tradingAcct, Future future, int qty, int price) {
		return new Order(orderId, tradingAcct.getTradingFirm().getCode(), future, qty, price);
	}

	public static  Future createFuture(String desc, String isinCode) {
		return new Future(desc, new ISIN(isinCode));
	}
	
	public static  Future createFuture(String isinCode) {
		return createFuture(DEFAULT_FUTURE_DESC, isinCode);
	}

	public static  Future dummyFuture() {
		return createFuture(DEFAULT_FUTURE_DESC, DEFAULT_ISIN_CODE);
	}

	public static TradingAccount createTradingAccount(String accountCode) {
		return createTradingAccount(ACTIVE, accountCode);
	}

	public static  TradingAccount createTradingAccount(AccountStatus status, String tradingFirmCode) {
		TradingAccount tradingAcct = new TradingAccount(
				new TradingFirm(null, tradingFirmCode), new ClearingFirm(null, null), status);
		return tradingAcct;
	}
	
	public static  Trader createTraderWithPermissionsFor(TradingAccount tradingAccount) {
		Trader trader = new Trader();
		trader.setPermissions(Arrays.asList(new Permission(tradingAccount)));
		return trader;
	}
	
	private static final String DEFAULT_ISIN_CODE = "ISIN";
	private static final String DEFAULT_FUTURE_DESC = "DESC";
	
	public static int price(int price) {
		return price;
	}

	public static int qty(int qty) {
		return qty;
	}
	
	private static int nextOrderId = 0;
	
	private static String nextOrderId() {
		return "ord:" + (++nextOrderId);
	}

	private static Future DUMMY_FUTURE = new Future(DEFAULT_FUTURE_DESC, new ISIN(DEFAULT_ISIN_CODE));
}
