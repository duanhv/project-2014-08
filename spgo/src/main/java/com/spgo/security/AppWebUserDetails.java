/**
 * Classname :AppWebUserDetails.java
 *
 */

package com.spgo.security;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spgo.model.web.PersonInfo;

@SuppressWarnings("serial")
public class AppWebUserDetails extends WebUserDetails {
	public interface Keys {
		String USER_ATTR = "USER";
	}
	
	public AppWebUserDetails(PersonInfo details, String role) {
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
    public PersonInfo getUser() {
    	try {
    		return (PersonInfo)this.getAttr(Keys.USER_ATTR);
    	} catch (Exception e) {
    		return null;
    	}
    }
    
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		PersonInfo details = this.getUser();
		return (details != null ? details.getPassword() : null);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		PersonInfo details = this.getUser();
		return (details != null ? details.getLoginId() : null);
	}

	/* 
	 * Indicates whether the user's account has expired. An expired account
     * cannot be authenticated. true if the user's account is valid (ie
     * non-expired), false if no longer valid (ie expired)
     * 
     * (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		boolean isNonExpired = true;

        return isNonExpired;
	}

	/* 
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated. true if the user is not locked, false otherwise
     * 
     * (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		
		boolean nonLocked = true;
		
        return nonLocked;
	}

	/* 
	 * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication. true if the user's credentials are
     * valid (ie non-expired), false if no longer valid (ie expired)
     * 
     * (non-Javadoc)
     * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		 return true;
	}

	/* 
	 * Indicates whether the user is enabled or disabled. A disabled user cannot
     * be authenticated. true if the user is enabled, false otherwise
     * 
     * (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		
		boolean enable = true;
		
		return enable;
	}

	@Override
	public String getLoginId() {

		PersonInfo details = this.getUser();
		return (details != null ? details.getLoginId() : null);

	}
}
