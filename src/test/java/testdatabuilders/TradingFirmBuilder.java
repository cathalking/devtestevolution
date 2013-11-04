package testdatabuilders;

import tradingapp.domain.company.TradingFirm;

public class TradingFirmBuilder {

	private String name = "TRDGFIRM";
	private String code = "TFIRM";
	
	private TradingFirmBuilder() { }
	
	public static TradingFirmBuilder aTradingFirm() {
		return new TradingFirmBuilder();
	}
	
	public TradingFirmBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public TradingFirmBuilder withCode(String code) {
		this.code = code;
		return this;
	}
	
	public TradingFirm build() {
		return new TradingFirm(name, code);
	}

	public TradingFirmBuilder but() {
		return new TradingFirmBuilder()
							.withName(name)
							.withCode(code);
	}
}
