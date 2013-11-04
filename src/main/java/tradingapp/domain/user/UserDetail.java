package tradingapp.domain.user;

public class UserDetail {

	private String firstLineAddress;
	private String secondLineAddress;
	private String addressCode;
	private String countryCode;

	public String getFirstLineAddress() {
		return firstLineAddress;
	}
	public void setFirstLineAddress(String firstLineAddress) {
		this.firstLineAddress = firstLineAddress;
	}
	public String getSecondLineAddress() {
		return secondLineAddress;
	}
	public void setSecondLineAddress(String secondLineAddress) {
		this.secondLineAddress = secondLineAddress;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
