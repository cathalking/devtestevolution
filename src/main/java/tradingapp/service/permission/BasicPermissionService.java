package tradingapp.service.permission;

import tradingapp.domain.company.TradingAccount;
import tradingapp.domain.permission.Permission;
import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;

public class BasicPermissionService implements PermissionService {

	public boolean hasViewPermissions(Trader trader, Order order) {
		for (Permission permission : trader.getPermissions()) {
			TradingAccount tradingAccount = permission.getTradingAccount();
			if (permission.getTradingAccount().isActive()
					&& tradingAccount.getTradingFirm().getCode().equals(order.getAccountCode()))
				return true;
		}
		return false;
	}

}
