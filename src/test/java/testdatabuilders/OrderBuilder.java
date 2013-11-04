package testdatabuilders;

import tradingapp.domain.securities.Product;
import tradingapp.domain.trading.Order;
import tradingapp.domain.trading.OrderState;
import static testdatabuilders.FutureBuilder.*;

public class OrderBuilder {

	private String orderId = "123";
	private String accountCode = "ABC";
	private Product product = aFuture().build();
	private int qty = 100;
	private int price = 2000;
	private OrderState state = OrderState.WORKING;
	
	private OrderBuilder() { }
	
	public static OrderBuilder anOrder() {
		return new OrderBuilder();
	}
	
	public static OrderBuilder aOrder() {
		return anOrder();
	}
	
	public OrderBuilder withOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}
	
	public OrderBuilder withAccountCode(String accountCode) {
		this.accountCode = accountCode;
		return this;
	}
	
	public OrderBuilder with(Product product) {
		return withProduct(product);
	}

	public OrderBuilder withProduct(Product product) {
		this.product = product;
		return this;
	}
	
	public OrderBuilder withQty(int qty) {
		this.qty = qty;
		return this;
	}
	
	public OrderBuilder withPrice(int price) {
		this.price = price;
		return this;
	}
	
	public OrderBuilder withOrderState(OrderState state) {
		this.state = state;
		return this;
	}
	
	public Order build() {
		Order order = new Order(orderId, accountCode, product, qty, price);
		return order;
	}
	
	public OrderBuilder but() {
		return new OrderBuilder()
				.withOrderId(orderId)
				.withAccountCode(accountCode)
				.withProduct(product)
				.withQty(qty)
				.withPrice(price)
				.withOrderState(state);
	}
}
