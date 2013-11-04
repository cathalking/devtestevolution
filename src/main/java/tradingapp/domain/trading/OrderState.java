package tradingapp.domain.trading;

public enum OrderState {

	DONE_FOR_DAY,
	WORKING,
	PARTIALLY_FILLED,
	FILLED,
	CANCELLED,
	REPLACED;
	
}
