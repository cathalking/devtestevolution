package tradingapp.domain.user;

import java.util.List;

import tradingapp.domain.permission.Permission;

public interface User {

	public String getUserName();

	public List<Permission> getPermissions();

}