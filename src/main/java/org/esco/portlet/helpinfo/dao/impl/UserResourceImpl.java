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

import javax.portlet.PortletRequest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.esco.portlet.helpinfo.dao.IUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserResourceImpl implements IUserResource/*, InitializingBean*/{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    private String currentEtabCodeInfoKey;

    private IUserResource mockedUserResource;

    /*public String getCurrentEtabCode(final PortletRequest request) {
        String escoUaiCourant = null;

        final List<String> uaiCourant = this.getUserInfo(request, this.currentEtabCodeInfoKey);

        if (uaiCourant.size() == 1) {
            // Monovalued attribute
            escoUaiCourant = uaiCourant.iterator().next().toLowerCase();
        }

        if (!StringUtils.hasText(escoUaiCourant)) {
            escoUaiCourant = null;
            log.warn("Unable to retrieve {} attribute in Portal UserInfo !", this.currentEtabCodeInfoKey);
        }

        return escoUaiCourant;
    }*/

    /**
     * Retrieve the user info attribute from portlet context, or the Mocked user info
     *
     * @param request the portlet request
     * @param attributeName the attribute to retrieve
     * @return the user info attribute values
     */
    @SuppressWarnings("unchecked")
    public List<String> getUserInfo(final PortletRequest request, final String attributeName) {
        Map<String, List<String>> userInfo =
                (Map<String, List<String>>) request.getAttribute("org.jasig.portlet.USER_INFO_MULTIVALUED");

        List<String> attributeValues = null;

        if (userInfo != null) {
            attributeValues = userInfo.get(attributeName);
        } else if ("true".equals(System.getProperty("help-info-portlet.testEnv"))) {
            attributeValues = this.mockedUserResource.getUserInfo(null, attributeName);
        } else {
            log.error("Unable to retrieve Portal UserInfo !");
            //throw new IllegalStateException("Unable to retrieve Portal UserInfo !");
        }

        if (attributeValues == null) {
            attributeValues = Collections.EMPTY_LIST;
        }

        return attributeValues;
    }

//    public void afterPropertiesSet() throws Exception {
//        Assert.hasText(this.currentEtabCodeInfoKey, "No property currentEtabCodeInfoKey is set !");
//    }

//    public String getCurrentEtabCodeInfoKey() {
//        return currentEtabCodeInfoKey;
//    }
//
//    public void setCurrentEtabCodeInfoKey(String currentEtabCodeInfoKey) {
//        this.currentEtabCodeInfoKey = currentEtabCodeInfoKey;
//    }

    public IUserResource getMockedUserResource() {
        return mockedUserResource;
    }

    public void setMockedUserResource(IUserResource mockedUserResource) {
        this.mockedUserResource = mockedUserResource;
    }

}
