package tradingapp.domain.securities;


public class Future implements Product {

	@Override
	public String toString() {
		return "Future [desc=" + desc + ", isin=" + isin + "]";
	}

	private String desc;
	private ISIN isin;

	public Future(String desc, ISIN isin) {
		this.desc = desc;
		this.isin = isin;
	}

	@Override
	public String getIsinCode() {
		return isin.getIsinCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((isin == null) ? 0 : isin.hashCode());
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
		Future other = (Future) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (isin == null) {
			if (other.isin != null)
				return false;
		} else if (!isin.equals(other.isin))
			return false;
		return true;
	}


}
