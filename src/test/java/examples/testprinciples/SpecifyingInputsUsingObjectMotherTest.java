package examples.testprinciples;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.fest.assertions.api.Assertions.*;
import static testhelpers.ObjectMother.*;
import static tradingapp.domain.company.AccountStatus.*;
import static examples.assertions.custom.ProjectAssertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

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
import tradingapp.service.search.OrderSearchService;
import tradingapp.service.search.OrderSearchServiceImpl;

public class SpecifyingInputsUsingObjectMotherTest {
	
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
	
	@Test public void // Using helpers
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v2() {
		// given - inputs
		String accountCode = "TRDRZ";
		String goldIsinCode = "GLD24680";
		TradingAccount tradingAcct = createTradingAccount(accountCode);
		Trader trader = createTraderWithPermissionsFor(tradingAcct);
		// given - system state
		Future oil3MnthFuture = createFuture("OIL.3MNTH", "OIL3M0123");
		Future oil6MnthFuture = createFuture("OIL.6MNTH", "OIL6M0456");
		Future goldFuture = createFuture("GLD.3MNTH", goldIsinCode);
		Order order1 = createOrder(accountCode, oil3MnthFuture, qty(1000), price(2500));
		Order order2 = createOrder(accountCode, oil6MnthFuture, qty(1500), price(2600));
		Order order3 = createOrder(accountCode, goldFuture, qty(200), price(4300));
		Order order4 = createOrder(accountCode, goldFuture, qty(150), price(4300));
		OrderSearchService orderSearchService = createOrderService(
													createOrdersDAO(order1, order2, order3, order4));
		// when
		List<Order> orders = orderSearchService.getOrders(trader, accountCode, goldIsinCode);
		// then
		assertThat(orders).containsOnly(order3, order4);
	}
	
	@Test public void // Using helpers + hiding irrelevant details
	getOrders_GivenTraderWithAccountPerms_SearchByAccountAndIsin_ThenReturnsMatchingOrders_v3() {
		// given - inputs
		String searchAccount = "TRDRZ", searchIsin = "GLD24680";
		Trader trader = createTraderWithPermissionsFor(createTradingAccount("TRDRZ"));
		// given - system state
		Order order1 = createOrder("TRDRZ", dummyFuture()),
			  order2 = createOrder("TRDRZ", dummyFuture()),
			  order3 = createOrder("TRDRZ", createFuture("GLD24680")),
			  order4 = createOrder("TRDRZ", createFuture("GLD24680"));
		OrderSearchService service = createOrderService(createOrdersDAO(order1, order2, order3, order4));
		// when
		List<Order> orders = service.getOrders(trader, searchAccount, searchIsin);
		// then
		assertThat(orders).containsOnly(order3, order4);
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

	

}
