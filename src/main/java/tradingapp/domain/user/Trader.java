package tradingapp.domain.user;

import java.util.ArrayList;
import java.util.List;

import tradingapp.domain.permission.Permission;
import tradingapp.domain.permission.Restriction;

public class Trader implements User {

	private String userName;
	private List<Permission> permissions = new ArrayList<Permission>();
	private UserDetail userDetail;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = new ArrayList<Permission>(permissions);
	}

	private List<Restriction> restrictions;
	
	public List<Restriction> getRestrictions() {
		return restrictions;
	}
	
	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = new ArrayList<Restriction>(restrictions);
	}
}
