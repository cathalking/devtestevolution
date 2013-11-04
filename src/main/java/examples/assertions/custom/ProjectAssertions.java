package examples.assertions.custom;

import org.fest.assertions.api.Assertions;

import tradingapp.domain.user.Trader;
import examples.customassertions.TraderAssert;

public class ProjectAssertions extends Assertions {
	
	public static TraderAssert assertThat(Trader trader) {
		return new TraderAssert(trader);
	}

}
