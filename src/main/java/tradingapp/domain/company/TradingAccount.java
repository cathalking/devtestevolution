package tradingapp.domain.company;

public class TradingAccount {

	private TradingFirm tradingFirm;
	private ClearingFirm clearingFirm;
	private AccountStatus status;
	
	public TradingAccount(TradingFirm tradingFirm, ClearingFirm clearingFirm, AccountStatus status) {
		this.tradingFirm = tradingFirm;
		this.clearingFirm = clearingFirm;
		this.status = status;
	}

	public TradingFirm getTradingFirm() {
		return tradingFirm;
	}

	public ClearingFirm getClearingFirm() {
		return clearingFirm;
	}
	
	public boolean isActive() {
		return status.equals(AccountStatus.ACTIVE);
	}

	@Override
	public String toString() {
		return "TradingAccount [tradingFirm=" + tradingFirm + ", clearingFirm=" + clearingFirm + ", status=" + status
				+ "]";
	}
	
}
