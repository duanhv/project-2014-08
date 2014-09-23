package com.spgo.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spgo.business.EmployeeManager;
import com.spgo.common.Constants;
import com.spgo.model.web.EmployeeInfo;

public class WebUserDetailsService implements IWebUserDetailsService, InitializingBean {
	Logger log = Logger.getLogger(WebUserDetailsService.class);

	@Autowired
	private EmployeeManager personService;

	/*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
	public synchronized UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException, DataAccessException {
    	final String LOCATION = "loadUserByUsername(loginId:" + loginId + ")";
    	 log.debug(LOCATION + ":: START");
    		WebUserDetails user = null;
    		EmployeeInfo person = null;
    	try {
    		person = personService.getEmployeeByLoginId(loginId);
    		if (person == null) {        			
                log.info("User not found!!!");
    			throw new UsernameNotFoundException("User not found!!!");
            }
    		user = new AppWebUserDetails(person, Constants.ROLE_MEMBER);
    		
        } catch (UsernameNotFoundException ufe) {
        	//log.error(ufe.getMessage(), ufe);
            throw ufe;
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            throw new UsernameNotFoundException("User not found!!!", e);
        }
        log.debug(LOCATION + ":: END");
        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
    }


	public void reloadUserByUsername(String userName) {
		// TODO Auto-generated method stub
		
	}
}