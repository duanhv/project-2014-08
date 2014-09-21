/**
 * Classname :AppWebUserDetails.java
 *
 */

package com.spgo.security;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spgo.model.web.EmployeeInfo;

@SuppressWarnings("serial")
public class AppWebUserDetails extends WebUserDetails {
	public interface Keys {
		String USER_ATTR = "USER";
	}
	
	public AppWebUserDetails(EmployeeInfo details, String role) {
        if (details != null) {
            // +++
        	super.addAttr(Keys.USER_ATTR, details);
            
            // +++ add roles
            if (StringUtils.isNotBlank(role)) {
            	if (this.roles == null) {
            		this.roles = new ArrayList<GrantedAuthority>();
            	}
                this.roles.add(new SimpleGrantedAuthority(role));
            }            
        }
    }

    /**
     * 
     * @return
     */
    public EmployeeInfo getUser() {
    	try {
    		return (EmployeeInfo)this.getAttr(Keys.USER_ATTR);
    	} catch (Exception e) {
    		return null;
    	}
    }
    

	public String getPassword() {
		EmployeeInfo details = this.getUser();
		return (details != null ? details.getPassword() : null);
	}

	public String getUsername() {
		EmployeeInfo details = this.getUser();
		return (details != null ? details.getLoginId() : null);
	}

	public boolean isAccountNonExpired() {
		boolean isNonExpired = true;

        return isNonExpired;
	}


	public boolean isAccountNonLocked() {
		
		boolean nonLocked = true;
		
        return nonLocked;
	}


	public boolean isCredentialsNonExpired() {
		 return true;
	}

	public boolean isEnabled() {
		
		boolean enable = true;
		
		return enable;
	}

	@Override
	public String getLoginId() {

		EmployeeInfo details = this.getUser();
		return (details != null ? details.getLoginId() : null);

	}
}
