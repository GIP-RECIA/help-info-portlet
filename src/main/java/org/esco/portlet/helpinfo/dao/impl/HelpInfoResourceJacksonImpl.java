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
package org.esco.portlet.helpinfo.dao.impl;

import java.util.List;

import com.google.common.collect.Lists;

import org.esco.portlet.helpinfo.dao.IHelpInfoResource;
import org.esco.portlet.helpinfo.model.HelpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jgribonvald on 13/09/16.
 */
public class HelpInfoResourceJacksonImpl implements IHelpInfoResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(cacheNames = "helpinfos", key = "#helpUrl")
    public List<HelpInfo> retrieveInfos(String helpUrl) {
        return this.getServiceInfos(helpUrl);
    }

    private List<HelpInfo> getServiceInfos(String url) {
        if (log.isDebugEnabled()) {
            log.debug("Requesting help Infos on URL {}", url );
        }

        List<HelpInfo> flL;

        try {
            flL = Lists.newArrayList(restTemplate.getForObject(url, HelpInfo[].class));
        } catch (RestClientException ex) {
            log.warn("Error getting helpInfoList from url '{}'", url, ex);
            return Lists.newArrayList();
        } catch (HttpMessageNotReadableException ex) {
            log.warn("Error getting helpInfoList from url '{}' the object doesn't map HelpInfo Object properties", url, ex);
            return Lists.newArrayList();
        }

        return flL;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
