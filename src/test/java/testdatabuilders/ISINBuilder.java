package testdatabuilders;

import tradingapp.domain.securities.ISIN;

public class ISINBuilder {

	private String isinCode = "ISIN12345";
	
	private ISINBuilder() { }
	
	public static ISINBuilder anISIN() {
		return new ISINBuilder();
	}
	
	public static ISINBuilder aISIN() {
		return anISIN();
	}

	public ISINBuilder withIsinCode(String isinCode) {
		this.isinCode = isinCode;
		return this;
	}
	
	public ISIN build() {
		ISIN isin = new ISIN(isinCode);
		return isin;
	}
	
	public ISINBuilder but() {
		return new ISINBuilder()
						.withIsinCode(isinCode);
	}
}
