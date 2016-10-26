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
package org.esco.portlet.helpinfo.mvc.portlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.esco.portlet.helpinfo.model.HelpInfo;
import org.esco.portlet.helpinfo.service.IHelpInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Main portlet view.
 */
@Controller
@Scope("session")
@RequestMapping("VIEW")
public class MainController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IHelpInfoService helpInfoService;

   
    private HelpInfo helpInfoSession;
    
    @RenderMapping 
    public ModelAndView showMainView(final RenderRequest request, final RenderResponse response) {
        final String viewName = "main";        
        final ModelAndView mav = new ModelAndView(viewName);
        
        if(log.isDebugEnabled()) {
            log.debug("Using view name " + viewName + " for main view");
        }
        
        HelpInfo helpInfo = helpInfoSession ;
        if (helpInfo == null) {
        	log.debug("new helpInfo");
        	helpInfo = this.helpInfoService.retrieveHelpInfos(request);
        	helpInfoSession = helpInfo;
        }
   //    helpInfo.setAlreadyRead(true);
        mav.addObject("helpinfos", helpInfo);

        if(log.isDebugEnabled()) {
            log.debug("Rendering main view");
        }
       
        return mav;
    }
 
    @ActionMapping("hidePermanently" )
    public void hidePermanently(  final ActionRequest request
			, final ActionResponse response) throws IOException {
    	log.debug("hidePermanently OK");
    	hide(request, response);
    	
    	 try {
         	this.helpInfoService.noReadMore(request, true);
         } catch (ReadOnlyException e) {
         	log.error("hidePermanently error " + e.getMessage());
         }
    }
    
    @ActionMapping("hide" )
    public void hide(  final ActionRequest request
			, final ActionResponse response) throws IOException {
    	log.debug("hide OK");
    	
    	Enumeration<String> names = request.getAttributeNames();
    	
    	while (names.hasMoreElements()) {
    		String name =  names.nextElement();
    		
    		log.debug("propertie  :" + name + " : " + request.getAttribute(name));
    	}
    	helpInfoSession.setAlreadyRead(true);
    	 
    }
    @ActionMapping("show" )
    public void show(  final ActionRequest request
			, final ActionResponse response) throws IOException {
    	log.debug("show OK");
    	
    	Enumeration<String> names = request.getAttributeNames();
    	
    	while (names.hasMoreElements()) {
    		String name =  names.nextElement();
    		
    		log.debug("propertie  :" + name + " : " + request.getAttribute(name));
    	}
    	helpInfoSession.setAlreadyRead(false);
    	
    	try {
         	this.helpInfoService.noReadMore(request, false);
         } catch (ReadOnlyException e) {
         	log.error("show  error " + e.getMessage());
         }
    	 
    }
    
  //  @ActionMapping
    public void doAction() {
        // no-op action mapping to prevent accidental calls to this URL from
        // crashing the portlet
    	log.debug("action mapping");
    	
    }

    public IHelpInfoService getHelpInfoService() {
        return helpInfoService;
    }

    public void setHelpInfoService(IHelpInfoService helpInfoService) {
        this.helpInfoService = helpInfoService;
    }

}
