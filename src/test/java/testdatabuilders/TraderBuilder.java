package testdatabuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tradingapp.domain.permission.Permission;
import tradingapp.domain.permission.Restriction;
import tradingapp.domain.user.Trader;
import tradingapp.domain.user.UserDetail;

public class TraderBuilder {
    public static String DEFAULT_USERNAME = "trader1";
    public static UserDetail DEFAULT_USERDETAIL = new UserDetail();
    public static List<Permission> NO_PERMISSIONS = Collections.emptyList();
    public static List<Restriction> NO_RESTRICTIONS = Collections.emptyList();

    private String userName = DEFAULT_USERNAME;
    private UserDetail userDetail = DEFAULT_USERDETAIL;
    private List<Permission> permissions = NO_PERMISSIONS;
    private List<Restriction> restrictions = NO_RESTRICTIONS;
    
	private TraderBuilder() { }
	
	public static TraderBuilder aTrader() {
		return new TraderBuilder();
	}
	
	public Trader build() {
		Trader trader = new Trader();
		trader.setUserName(userName);
		trader.setUserDetail(userDetail);
		trader.setPermissions(permissions);
		trader.setRestrictions(restrictions);
		return trader;
	}
	
	public TraderBuilder withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public TraderBuilder with(UserDetail userDetail) {
		this.userDetail = userDetail;
		return this;
	}
	
	public TraderBuilder withPermissions(List<Permission> permissions) {
		this.permissions = new ArrayList<Permission>(permissions);
		return this;
	}
	
	public TraderBuilder with(PermissionBuilder permission) {
		this.permissions = new ArrayList<Permission>(Arrays.asList(permission.build()));
		return this;
	}
	
	public TraderBuilder withNoPermissions() {
		this.permissions = Collections.emptyList();
		return this;
	}

	public TraderBuilder with(RestrictionBuilder restriction) {
		this.restrictions = new ArrayList<Restriction>(Arrays.asList(restriction.build()));
		return this;
	}
	
	public TraderBuilder withRestrictions(List<Restriction> restrictions) {
		this.restrictions = new ArrayList<Restriction>(restrictions);
		return this;
	}
	
	public TraderBuilder but() {
		return new TraderBuilder()
						.withUserName(userName)
						.with(userDetail)
						.withPermissions(permissions)
						.withRestrictions(restrictions);
	}
}
