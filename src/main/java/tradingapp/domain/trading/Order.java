package tradingapp.domain.trading;

import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.securities.Product;

public class Order {

	private String orderId;
	private String accountCode;
	private Product product;
	private int qty;
	private int price;
	private OrderState state = OrderState.WORKING;

	public Order(String orderId, String accountCode, Product product, int qty, int price) {
		this.orderId = orderId;
		this.accountCode = accountCode;
		this.product = product;
		this.qty = qty;
		this.price = price;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public String getAccountCode() {
		return accountCode;
	}
	public Product getProduct() {
		return product;
	}
	public int getQty() {
		return qty;
	}
	public int getPrice() {
		return price;
	}
	public OrderState getState() {
		return state;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", accountCode=" + accountCode + ", product=" + product + ", qty="
				+ qty + ", price=" + price + ", state=" + state + "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + price;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + qty;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((accountCode == null) ? 0 : accountCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (price != other.price)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (qty != other.qty)
			return false;
		if (state != other.state)
			return false;
		if (accountCode == null) {
			if (other.accountCode != null)
				return false;
		} else if (!accountCode.equals(other.accountCode))
			return false;
		return true;
	}

}
