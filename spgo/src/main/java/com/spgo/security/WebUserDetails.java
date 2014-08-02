/**
 * Classname :WebUserDetails.java
 *
 * Version information: 1.0
 *
 * Date: Oct 28, 2013 ho.viet.duan create
 *
 */

package com.spgo.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class WebUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Map<String, Serializable> attrs = new HashMap<String, Serializable>();
	protected List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public final boolean addAttr(String key, Serializable value) {
		if(key != null && value != null) {
			attrs.put(key, value);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public final Serializable getAttr(String key) {
		if(key != null) {
			return attrs.get(key);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public final Set<String> getAttrKeys() {
		return attrs.keySet();
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract String getLoginId();

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
     */
    public List<GrantedAuthority> getAuthorities() {
        return roles;
    }
    
    /**
     * Returns {@code true} if the supplied object is a {@code User} instance with the
     * same {@code getUsername()} value.
     * <p>
     * In other words, the objects are equal if they have the same getUsername(), representing the
     * same principal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof WebUserDetails) {
            return getUsername().equals(((WebUserDetails) rhs).getUsername());
        }
        return false;
    }

    /**
     * Returns the hashcode of the {@code getUsername()}.
     */
    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("getUsername(): ").append(this.getUsername()).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.isEnabled()).append("; ");
        sb.append("AccountNonExpired: ").append(this.isAccountNonExpired()).append("; ");
        sb.append("credentialsNonExpired: ").append(this.isCredentialsNonExpired()).append("; ");
        sb.append("AccountNonLocked: ").append(this.isAccountNonLocked()).append("; ");

        if (!getAuthorities().isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : getAuthorities()) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}
