package tradingapp.domain.securities;

public class ISIN {

	private String isinCode;

	public ISIN(String isinCode) {
		this.isinCode = isinCode;
	}
	
	public String getIsinCode() {
		return isinCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isinCode == null) ? 0 : isinCode.hashCode());
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
		ISIN other = (ISIN) obj;
		if (isinCode == null) {
			if (other.isinCode != null)
				return false;
		} else if (!isinCode.equals(other.isinCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ISIN [isinCode=" + isinCode + "]";
	}

}
