/**
 * Copyright © 2016 ESUP-Portail (https://www.esup-portail.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esco.portlet.helpinfo.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.esco.portlet.helpinfo.dao.IHelpInfoResource;
import org.esco.portlet.helpinfo.dao.IUserResource;
import org.esco.portlet.helpinfo.model.HelpInfo;
import org.esco.portlet.helpinfo.service.IHelpInfoService;
import org.esco.portlet.helpinfo.service.bean.IHelpUrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jgribonvald on 14/09/16.
 */
@Service
public class HelpInfoServiceImpl implements IHelpInfoService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String PREF_HELP = "helpNoReadMore";
    private static final String PREF_YEPS = "helpYepsNoReadMore";
    
    private static final String PREF_HELP_URL = "helpUrl";
    private static final String PREF_YEPS_URL = "yepsUrl";
    
    private static final String PREF_YEPS_PREFIX_FILTER = "yepsHelpFilter_";

    @Autowired
    private IHelpInfoResource helpInfoResource;

    @Autowired
    private IHelpUrlBuilder helpUrlBuilder;

    @Autowired
    private IUserResource userResource;

    @Override
    public void  noReadMore(final PortletRequest request, boolean hide, boolean yeps) throws ReadOnlyException {
    	PortletPreferences pp = request.getPreferences();
    	
    	String pref = yeps ? PREF_YEPS : PREF_HELP;
    	
    	if (hide) {
    		pp.setValue(pref, "true");
    	} else {
    		pp.setValue(pref, "false");
    	}
    	
    	try {
			pp.store();
			log.debug(" PortletPreferences store ok ");
		} catch (ValidatorException | IOException e) {
			log.error(" PortletPreferences not store", e.getMessage());
		}
    }
    
    private boolean showYepsInfo(final PortletRequest request) {
    	
    	PortletPreferences pp = request.getPreferences();
    	Map<String, String[]> prefMap = pp.getMap();
    	
    	boolean ok = false;
    	for (String prefName : prefMap.keySet()) {
    		
    		if (prefName.startsWith(PREF_YEPS_PREFIX_FILTER)) {
    			
    			String attrName = prefName.substring(PREF_YEPS_PREFIX_FILTER.length());
    			String[] okValues = prefMap.get(prefName);
    			
    			ok = false;
    			
    			List<String> userValues = userResource.getUserInfo(request, attrName);
    			log.debug("showYepsInfo: attrName ={}", attrName);
    			if (userValues != null && okValues != null) {
    				
		    			for (String userVal : userValues) {
		    				
		    				for (String okVal : okValues) {
		    					if (okVal.equals(userVal)) {
		    						ok = true;
		    						break;
		    					}
		    				}
		    				if (ok) {
		    					break;
		    				}
		    			}
	    		}
    			if (! ok) {
    				return false;
    			}
    			
    		}
		}
    	log.debug("showYepsInfo = {}", ok);
    	return ok;
    }
    
    public HelpInfo retrieveHelpInfos(final PortletRequest request) {
    	PortletPreferences pp = request.getPreferences();
    	
    	String yepsNoRead; 
    	String helpNoRead = pp.getValue(PREF_HELP, "false");
    	
    	boolean showYeps = showYepsInfo(request);
    	
       
        if (showYeps) {
        	yepsNoRead = pp.getValue(PREF_YEPS, "false");
        } else {
        	yepsNoRead = "true";
        }
        
        
        
      //  request.getPreferences().setValue(arg0, arg1);
        if (log.isDebugEnabled()) {
            log.debug("Preference help helpNoRead is {}",helpNoRead);
            log.debug("Preference help yepsNoRead is {}", yepsNoRead);
        }

        HelpInfo info = new HelpInfo();
        info.setAlreadyRead("true".equals(helpNoRead));
        info.setYepsAlreadyRead(!showYeps || "true".equals(yepsNoRead));
        
        info.setHelpUrl(pp.getValue(PREF_HELP_URL, "/aide/AideENT/indexAideENT.html"));
        info.setYepsUrl(pp.getValue(PREF_YEPS_URL, "/aide/AideYeps/indexAideYeps.html"));
        return info;
    }

    public IHelpInfoResource getHelpInfoResource() {
        return helpInfoResource;
    }

    public void setHelpInfoResource(final IHelpInfoResource helpInfoResource) {
        this.helpInfoResource = helpInfoResource;
    }

    public IHelpUrlBuilder getHelpUrlBuilder() {
        return helpUrlBuilder;
    }

    public void setHelpUrlBuilder(final IHelpUrlBuilder helpUrlBuilder) {
        this.helpUrlBuilder = helpUrlBuilder;
    }

    public static String getPrefHelpUrl() {
        return PREF_HELP;
    }

	public IUserResource getUserResource() {
		return userResource;
	}

	public void setUserResource(IUserResource userResource) {
		this.userResource = userResource;
	}

}
