package testdatabuilders;

import tradingapp.domain.company.AccountStatus;
import tradingapp.domain.company.ClearingFirm;
import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.company.TradingFirm;
import static testdatabuilders.ClearingFirmBuilder.*;
import static testdatabuilders.TradingFirmBuilder.*;

public class TradingAccountBuilder {

	private TradingFirm tradingFirm = aTradingFirm().build();
	private ClearingFirm clearingFirm = aClearingFirm().build();
	private AccountStatus status = AccountStatus.ACTIVE;
	
	private TradingAccountBuilder() { }
	
	public static TradingAccountBuilder aTradingAccount() {
		return new TradingAccountBuilder();
	}
	
	public TradingAccountBuilder withTradingFirm(TradingFirm tradingFirm) {
		this.tradingFirm = tradingFirm;
		return this;
	}
	
	public TradingAccountBuilder with(TradingFirmBuilder tradingFirm) {
		return withTradingFirm(tradingFirm);
	}

	public TradingAccountBuilder withTradingFirm(TradingFirmBuilder tradingFirm) {
		this.tradingFirm = tradingFirm.build();
		return this;
	}
	
	public TradingAccountBuilder withClearingFirm(ClearingFirm clearingFirm) {
		this.clearingFirm = clearingFirm;
		return this;
	}
	
	public TradingAccountBuilder isInActive() {
		this.status = AccountStatus.INACTIVE;
		return this;
	}

	public TradingAccountBuilder withAccountStatus(AccountStatus status) {
		this.status = status;
		return this;
	}
	
	public TradingAccount build() {
		return new TradingAccount(tradingFirm, clearingFirm, status);
	}
	
	public TradingAccountBuilder but() {
		return new TradingAccountBuilder()
					.withTradingFirm(tradingFirm)
					.withClearingFirm(clearingFirm)
					.withAccountStatus(status);
	}
}
