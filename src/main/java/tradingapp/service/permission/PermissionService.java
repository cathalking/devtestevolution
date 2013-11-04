package tradingapp.service.permission;

import tradingapp.domain.trading.Order;
import tradingapp.domain.user.Trader;

public interface PermissionService {

	boolean hasViewPermissions(Trader trader, Order order);
}
