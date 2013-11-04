package testdatabuilders;

import tradingapp.domain.company.ClearingFirm;

public class ClearingFirmBuilder {

	private String name = "CLRGFIRM";
	private String code = "CFIRM";
	
	private ClearingFirmBuilder() { }
	
	public static ClearingFirmBuilder aClearingFirm() {
		return new ClearingFirmBuilder();
	}
	
	public ClearingFirmBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public ClearingFirmBuilder withCode(String code) {
		this.code = code;
		return this;
	}
	
	public ClearingFirm build() {
		return new ClearingFirm(name, code);
	}

	public ClearingFirmBuilder but() {
		return new ClearingFirmBuilder()
							.withName(name)
							.withCode(code);
	}
}
