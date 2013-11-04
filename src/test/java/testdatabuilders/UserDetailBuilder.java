package testdatabuilders;

import tradingapp.domain.user.UserDetail;

public class UserDetailBuilder {

	private String firstLineAddress = "1 Some Place";
	private String secondLineAddress = "This Town";
	private String addressCode = "ZIP1";
	private String countryCode = "US";
	
	private UserDetailBuilder() { }
	
	public static UserDetailBuilder aUserDetail() {
		return new UserDetailBuilder();
	}
	
	public UserDetailBuilder withFirstLineAddress(String firstLineAddress) {
		this.firstLineAddress = firstLineAddress;
		return this;
	}
	
	public UserDetailBuilder withSecondLineAddress(String secondLineAddress) {
		this.secondLineAddress = secondLineAddress;
		return this;
	}

	public UserDetailBuilder withAddressCode(String addressCode) {
		this.addressCode = addressCode;
		return this;
	}
	
	public UserDetailBuilder withCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}
	
	public UserDetail build() {
		UserDetail userDetail = new UserDetail();
		userDetail.setFirstLineAddress(firstLineAddress);
		userDetail.setSecondLineAddress(secondLineAddress);
		userDetail.setAddressCode(addressCode);
		userDetail.setCountryCode(countryCode);
		return userDetail;
	}
	
	public UserDetailBuilder but() {
		return new UserDetailBuilder()
				.withFirstLineAddress(firstLineAddress)
				.withSecondLineAddress(secondLineAddress)
				.withAddressCode(addressCode)
				.withCountryCode(countryCode);
	}
}
