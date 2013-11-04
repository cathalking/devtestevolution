package testdatabuilders;

import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.permission.Permission;

public class PermissionBuilder {

	private TradingAccount tradingAccount;

	private PermissionBuilder() { }
	
	public static PermissionBuilder aPermission() {
		return new PermissionBuilder();
	}
	
	public PermissionBuilder withTradingAccount(TradingAccount tradingAccount) {
		this.tradingAccount = tradingAccount;
		return this;
	}
	
	public PermissionBuilder with(TradingAccountBuilder tradingAccount) {
		return withTradingAccount(tradingAccount);
	}

	public PermissionBuilder withTradingAccount(TradingAccountBuilder tradingAccount) {
		this.tradingAccount = tradingAccount.build();
		return this;
	}
	
	public Permission build() {
		Permission permission = new Permission(tradingAccount);
		return permission;
	}
	
	public PermissionBuilder but() {
		return new PermissionBuilder()
							.withTradingAccount(tradingAccount);
	}
}
