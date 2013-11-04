package tradingapp.domain.permission;

import tradingapp.domain.company.TradingAccount;

public class Permission {

	private TradingAccount tradingAccount;
	
	public Permission(TradingAccount tradingAccount) {
		this.tradingAccount = tradingAccount;
	}

	public TradingAccount getTradingAccount() {
		return tradingAccount;
	}

}
