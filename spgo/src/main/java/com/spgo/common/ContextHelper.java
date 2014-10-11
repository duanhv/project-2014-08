/**
 * Classname :ContextHelper.java
 */
package com.spgo.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spgo.security.WebUserDetails;


public abstract class ContextHelper {

	static final Logger log = Logger.getLogger(ContextHelper.class);

    /**
     * 
     * @return
     */
    public static String getLoginId() {
        try {
            
        	if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
        		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        	}
            
        } catch (Exception e) {
            log.debug("getLoginId() - " + e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * check current user is annonymous or not
     * 
     * @return
     */
    public static boolean isAnonymous() {
        AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx != null) {
            Authentication auth = ctx.getAuthentication();
            return resolver.isAnonymous(auth);
        }
        return true;
    }

    public static String getFileNameFromEmail(String email) {
    	String fileName = email;
    	
    	if (StringUtils.isNotBlank(fileName)) {
    		fileName = fileName.replace("@", "_");
    		fileName = fileName.replace(".", "_");
    	}
    	
    	return fileName;
    }
    
    public static void main(String[] args) {
    	//System.out.println(getFileNameFromEmail("test@gmail.com"));
    	
    	String oriFileName = "filemane_thoe.jsp";
    	String extFile     = oriFileName.substring(oriFileName.indexOf(".") , oriFileName.length());
    	System.out.println(extFile);
    }
}