package examples.testprinciples;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.fest.assertions.api.Assertions.*;
import static tradingapp.domain.company.AccountStatus.*;
import static examples.assertions.custom.ProjectAssertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.mockito.BDDMockito;

import testhelpers.OrdersDAOInMemory;
import tradingapp.dao.OrdersDAO;
import tradingapp.domain.company.AccountStatus;
import tradingapp.domain.company.ClearingFirm;
import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.company.TradingFirm;
import tradingapp.domain.permission.Permission;
import tradingapp.domain.securities.Future;
import tradingapp.domain.securities.ISIN;
import tradingapp.domain.securities.Product;
import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;
import tradingapp.domain.user.User;
import tradingapp.service.permission.PermissionService;
import tradingapp.service.search.OrderSearchService;
import tradingapp.service.search.OrderSearchServiceImpl;
import tradingapp.service.search.OrderSearchServiceImplV2;

public class SpecifyingSystemStateTest {

	private static final String DEFAULT_ISIN_CODE = "ISIN";
	private static final String DEFAULT_FUTURE_DESC = "DESC";
	
	@Test public void 
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v1() {
		// given - inputs
		String goldIsinCode = "GLD24680";
		String accountCode = "TRDRZ";
		Trader trader = new Trader();
		TradingAccount tradingAcct = new TradingAccount(new TradingFirm("TraderzRUs", accountCode), 
											new ClearingFirm("TooBig2Fail", "2BIG2F"), ACTIVE);
		trader.setPermissions(Arrays.asList(new Permission(tradingAcct)));
		// given - system state
		Future oil3MnthFuture = new Future("OIL.3MNTH", new ISIN("OIL3M0123"));
		Future oil6MnthFuture = new Future("OIL.6MNTH", new ISIN("OIL6M0456"));
		Future goldFuture = new Future("GLD.3MNTH", new ISIN(goldIsinCode));
		String orderId = "ordId";
		Order order1 = new Order(orderId + 1, accountCode, oil3MnthFuture, 1000, 2500);
		Order order2 = new Order(orderId + 2, accountCode, oil6MnthFuture, 1500, 2600);
		Order order3 = new Order(orderId + 3, accountCode, goldFuture, 200, 4300);
		Order order4 = new Order(orderId + 4, accountCode, goldFuture, 150, 4300);
		OrdersDAO orderDAO = new OrdersDAOInMemory(order1, order2, order3, order4);
		OrderSearchService orderSearchService = new OrderSearchServiceImpl(orderDAO);
		// when
		List<Order> orders = orderSearchService.getOrders(trader, accountCode, goldIsinCode);
		// then
		assertThat(orders).containsOnly(order3, order4);
	}
	
	@Test public void // Stubbing system state (Mockito) + using helpers + hiding irrelevant details
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v2() {
		// given - inputs
		String searchAccount = "TRDRZ", searchIsin = "GLD24680";
		Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		// given - system state
		Order order1 = createOrder("TRDRZ", createFuture("GLD24680")),
			  order2 = createOrder("TRDRZ", createFuture("GLD24680"));
		OrdersDAO ordersDAOStub = mock(OrdersDAO.class);
		given(ordersDAOStub.findBy(searchAccount, searchIsin)).willReturn(Arrays.asList(order1, order2));
		OrderSearchService service = createOrderService(ordersDAOStub);
		// when
		List<Order> orders = service.getOrders(trader, searchAccount, searchIsin);
		// then
		assertThat(orders).containsOnly(order1, order2);
	}
	
	@Test public void // Stubbing system state + stubbing inputs (Mockito)
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v3() {
		// given - inputs
		String searchAccount = "TRDRZ", searchIsin = "GLD24680";
		// Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		Trader traderStub = mock(Trader.class);
		Permission permissionStub = mock(Permission.class);
		given(traderStub.getPermissions()).willReturn(Arrays.asList(permissionStub));
		TradingAccount tradingAccountStub = mock(TradingAccount.class);
		given(permissionStub.getTradingAccount()).willReturn(tradingAccountStub);
		given(tradingAccountStub.isActive()).willReturn(Boolean.TRUE);
		given(tradingAccountStub.getTradingFirm()).willReturn(new TradingFirm("", searchAccount));
		// given - system state
		Order order1 = createOrder("TRDRZ", createFuture("GLD24680")),
			  order2 = createOrder("TRDRZ", createFuture("GLD24680"));
		OrdersDAO ordersDAOStub = mock(OrdersDAO.class);
		given(ordersDAOStub.findBy(searchAccount, searchIsin)).willReturn(Arrays.asList(order1, order2));
		OrderSearchService service = createOrderService(ordersDAOStub);
		// when
		List<Order> orders = service.getOrders(traderStub, searchAccount, searchIsin);
		// then
		assertThat(orders).containsOnly(order1, order2);
	}
	
	@Test public void // Stubbing system state + stubbing input (Mockito w/ Deep Stubs)
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v3_1() {
		// given - inputs
		String searchAccount = "TRDRZ", searchIsin = "GLD24680";
		// Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		Trader traderStub = mock(Trader.class);
		Permission permissionStub = mock(Permission.class, RETURNS_DEEP_STUBS);
		given(traderStub.getPermissions()).willReturn(Arrays.asList(permissionStub));
		given(permissionStub.getTradingAccount().isActive()).willReturn(Boolean.TRUE);
		given(permissionStub.getTradingAccount().getTradingFirm().getCode()).willReturn(searchAccount);
		// given - system state
		Order order1 = createOrder("TRDRZ", createFuture("GLD24680")),
			  order2 = createOrder("TRDRZ", createFuture("GLD24680"));
		OrdersDAO ordersDAOStub = mock(OrdersDAO.class);
		given(ordersDAOStub.findBy(searchAccount, searchIsin)).willReturn(Arrays.asList(order1, order2));
		OrderSearchService service = createOrderService(ordersDAOStub);
		// when
		List<Order> orders = service.getOrders(traderStub, searchAccount, searchIsin);
		// then
		assertThat(orders).containsOnly(order1, order2);
	}
	
	@Test public void // Using a Mock to test interactions + Stubbing system state (Mockito)
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v4_1() {
		// given - inputs
		String searchAccount = "TRDRZ", searchIsin = "GLD24680";
		Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		// given - system state
		Order order1 = createOrder("TRDRZ", createFuture("GLD24680")),
			  order2 = createOrder("TRDRZ", createFuture("GLD24680"));
		OrdersDAO ordersDAOStub = mock(OrdersDAO.class);
		given(ordersDAOStub.findBy(searchAccount, searchIsin)).willReturn(Arrays.asList(order1, order2));
		PermissionService permissionServiceMock = mock(PermissionService.class);
		OrderSearchService service = createOrderServiceV2(ordersDAOStub, permissionServiceMock);
		// when
		List<Order> orders = service.getOrders(trader, searchAccount, searchIsin);
		// then
		verify(permissionServiceMock).hasViewPermissions(trader, order1);
		verify(permissionServiceMock).hasViewPermissions(trader, order2);
	}
	
	@Test public void // Using a Mock to test interactions + Stubbing system state (Mockito)
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v4_2() {
		// given - inputs
		String searchAccount = "TRDRZ", searchIsin = "GLD24680";
		Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		// given - system state
		Order order1 = createOrder("TRDRZ", createFuture("GLD24680")),
			  order2 = createOrder("TRDRZ", createFuture("GLD24680"));
		OrdersDAO ordersDAOStub = mock(OrdersDAO.class);
		given(ordersDAOStub.findBy(searchAccount, searchIsin)).willReturn(Arrays.asList(order1, order2));
		PermissionService permissionServiceMock = mock(PermissionService.class);
		given(permissionServiceMock.hasViewPermissions(
				BDDMockito.any(Trader.class), BDDMockito.any(Order.class))).willReturn(Boolean.TRUE);
		OrderSearchService service = createOrderServiceV2(ordersDAOStub, permissionServiceMock);
		// when
		List<Order> orders = service.getOrders(trader, searchAccount, searchIsin);
		// then
		verify(permissionServiceMock).hasViewPermissions(trader, order1);
		verify(permissionServiceMock).hasViewPermissions(trader, order2);
		assertThat(orders).containsOnly(order1, order2);
	}
	
	@Test public void // Using helpers + hiding irrelevant details - Showing variation of test
	// that again shows only relevent detail jumping out
	getOrders_GivenTraderWithAccountPerms_SearchByAccount_ThenReturnsMatchingOrders_v3() {
		// given - inputs
		String searchAccount = "TRDRZ";
		Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		// given - system state
		Order order1 = createOrder("TRDRZ", dummyFuture());
		Order order2 = createOrder("TRDRZ", dummyFuture());
		Order order3 = createOrder("TRDRZ", dummyFuture());
		Order order4 = createOrder("TRDRZ", dummyFuture());
		OrderSearchService service = createOrderService( createOrdersDAO(order1, order2, order3, order4));
		// when
		List<Order> orders = 
				service.getOrders(trader, searchAccount);
		// then
		assertThat(orders).containsOnly(order1, order2, order3, order4);
	}

//	Order order1 = createOrder(tradingAcct, DUMMY_FUTURE, qty(1000), price(2500));
	// Could use statics for the dummy values. But the IDE makes them pop out. 
	// If the most important info pops out that can be very useful


	@Test public void // Using helpers
	getOrders_GivenTraderWithInactiveAcctPerms_SearchByAccountAndIsin_ThenReturnsNoOrders() {
		// given - inputs
		String accountCode = "TRDRZ";
		String goldIsinCode = "COF24680";
		TradingAccount inactiveTradingAcct = createTradingAccount(INACTIVE, accountCode);
		Trader trader = createTraderWithPermissionsFor(inactiveTradingAcct);
		// given - system state
		Future oil3MnthFuture = createFuture("OIL.3MNTH", "OIL3M0123");
		Future oil6MnthFuture = createFuture("OIL.6MNTH", "OIL6M0456");
		Future goldFuture = createFuture("GLD.3MNTH", goldIsinCode);
		Order order1 = createOrder(accountCode, oil3MnthFuture, 1000, 2500);
		Order order2 = createOrder(accountCode, oil6MnthFuture, 1500, 2600);
		Order order3 = createOrder(accountCode, goldFuture, 200, 4300);
		Order order4 = createOrder(accountCode, goldFuture, 150, 4300);
		OrderSearchService orderSearchService = createOrderService(
													createOrdersDAO(order1, order2, order3, order4));
		// when
		List<Order> orders = 
				orderSearchService.getOrders(trader, inactiveTradingAcct.getTradingFirm().getCode(), goldIsinCode);
		// then
		assertThat(orders).isEmpty();
	}
	
	private int price(int price) {
		return price;
	}

	private int qty(int qty) {
		return qty;
	}

	private OrdersDAOInMemory createOrdersDAO(Order order1, Order order2, Order order3, Order order4) {
		return new OrdersDAOInMemory(order1, order2, order3, order4);
	}

	private OrderSearchService createOrderService(OrdersDAO ordersDAO) {
		OrderSearchService orderSearchService = new OrderSearchServiceImpl(ordersDAO);
		return orderSearchService;
	}
	

	private OrderSearchService createOrderServiceV2(OrdersDAO ordersDAO, PermissionService permissionService) {
		OrderSearchService orderSearchService = new OrderSearchServiceImplV2(ordersDAO, permissionService);
		return orderSearchService;
	}
	
	
	private OrderSearchService prepareOrderService(Order order1, Order order2, Order order3, Order order4) {
		OrdersDAO orderDAO = createOrdersDAO(order1, order2, order3, order4);
		OrderSearchService orderSearchService = new OrderSearchServiceImpl(orderDAO);
		return orderSearchService;
	}

	private int nextOrderId = 0;

	private String nextOrderId() {
		return "ord:" + (++nextOrderId);
	}

	private Order createOrder(String accountCode, Future dummyFuture) {
		return createOrder(accountCode, dummyFuture, qty(0), price(0));
	}

	private Order createOrder(String accountCode, Future future, int qty, int price) {
		return new Order(nextOrderId(), accountCode, future, qty, price);
	}
	
	private Order createOrder(String orderId, TradingAccount tradingAcct, Future future, int qty, int price) {
		return new Order(orderId, tradingAcct.getTradingFirm().getCode(), future, qty, price);
	}

	private Future createFuture(String desc, String isinCode) {
		return new Future(desc, new ISIN(isinCode));
	}
	
	private Future createFuture(String isinCode) {
		return createFuture(DEFAULT_FUTURE_DESC, isinCode);
	}

	private Future dummyFuture() {
		return createFuture(DEFAULT_FUTURE_DESC, DEFAULT_ISIN_CODE);
	}
	
	private static Future DUMMY_FUTURE = new Future(DEFAULT_FUTURE_DESC, new ISIN(DEFAULT_ISIN_CODE));

	private TradingAccount createTradingAccount(String accountCode) {
		return createTradingAccount(ACTIVE, accountCode);
	}

	private TradingAccount createTradingAccount(AccountStatus status, String tradingFirmCode) {
		TradingAccount tradingAcct = new TradingAccount(
				new TradingFirm(null, tradingFirmCode), new ClearingFirm(null, null), status);
		return tradingAcct;
	}
	
	private Trader createTraderWithPermissionsFor(TradingAccount tradingAccount) {
		Trader trader = new Trader();
		trader.setPermissions(Arrays.asList(new Permission(tradingAccount)));
		return trader;
	}

}
