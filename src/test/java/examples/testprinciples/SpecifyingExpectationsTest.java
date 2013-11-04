package examples.testprinciples;

import static examples.assertions.custom.ProjectAssertions.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;

public class SpecifyingExpectationsTest {

	// .containsSequence(order3, order4);

	// .doesNotHaveDuplicates();

	@Test
	public void getPermissions_GivenNewUser_ThenReturnsNoPermissions() {
		// given
		Trader trader = new Trader();
		// when + then
		assertThat(trader.getPermissions()).isEmpty();
	}

	@Test
	public void getPermissions_GivenNewUser_ThenReturnsNoPermissions2() {
		// given
		Trader trader = new Trader();
		// then
		assertTrue(trader.getPermissions().isEmpty()); // Computer dialect
		// .
		assertThat(trader.getPermissions().isEmpty(), is(true)); // .
		// .
		assertThat(trader.getPermissions()).isEmpty(); // .
		// .
		assertThat(trader).hasNoPermissions(); // Human dialect
	}

	@Ignore
	@Test
	public void moreComplexAssertion() {
		// given - inputs + system setup
		// .............................
		Order order1 = null, order2 = null, order3 = null, order4 = null;
		// when
		List<Order> orders = null;// orderSearchService.getOrders(trader,
									// tradingAcct, goldIsinCode);
		// then
		// Using Basic JUnit
		assertEquals(orders.size(), 2);
		Iterator<Order> itr = orders.iterator();
		assertEquals(itr.next(), order3);
		assertEquals(itr.next(), order4);
		// Using Hamcrest
		assertThat(orders, hasItems(order3, order4));
		assertThat(orders.size(), is(2));
		// Using FEST Assertions
		assertThat(orders).containsOnly(order3, order4);
		// Using FEST Assertions (chained assertions)
		assertThat(orders).containsOnly(order3, order4).containsSequence(order3, order4);
	}

	@Test
	public void methodUnderTest_GivenABC_ThenExpectXYZ() {
		// given

		// when

		// then
	}

	@Test
	public void methodUnderTest_GivenInputsABC_AndSystemStateDEF_ThenExpectXYZ2() {
		// given - inputs

		// given - system state

		// when

		// then
	}
}
