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

import javax.portlet.PortletRequest;

import java.util.List;

import com.google.common.collect.Lists;

import org.esco.portlet.helpinfo.dao.IHelpInfoResource;
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

    private static final String PREF_HELP_URL = "helpUrl";
    

    @Autowired
    private IHelpInfoResource helpInfoResource;

    @Autowired
    private IHelpUrlBuilder helpUrlBuilder;


    public List<HelpInfo> retrieveHelpInfos(final PortletRequest request) {
        final String helpUrl = request.getPreferences().getValue(PREF_HELP_URL, "false");
      //  request.getPreferences().setValue(arg0, arg1);
        if (log.isDebugEnabled()) {
            log.debug("Preference help url is {}",helpUrl);
        }

        if (helpUrl == null || helpUrl.trim().isEmpty() ) {
            return Lists.newArrayList();
        }

        // case of url is relative
        String rewroteUrl = helpUrlBuilder.transform(request, helpUrl);
        if (rewroteUrl == null || rewroteUrl.trim().isEmpty()) {
            return Lists.newArrayList();
        }

        if (log.isDebugEnabled()) {
            log.debug("After url completion helpUrl is {}",rewroteUrl );
        }

        return helpInfoResource.retrieveInfos(rewroteUrl);
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
        return PREF_HELP_URL;
    }

}
