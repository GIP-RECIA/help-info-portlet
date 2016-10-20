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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import org.esco.portlet.helpinfo.dao.IUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockUserResourceImpl implements IUserResource {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final static List<String> ESCOUAICourant = Lists.newArrayList("0450822X");
    private final static List<String> displayName = Lists.newArrayList("guest");

    private static final Map<String, List<String>> userInfoMap = ImmutableMap.of(
            "ESCOUAICourant", ESCOUAICourant,
            "displayName", displayName
    );

    /**
     * Retrieve the user info attribute from portlet context, or the Mocked user info
     *
     * @param request the portlet request
     * @param attributeName the attribute to retrieve
     * @return the user info attribute values
     */
    @SuppressWarnings("unchecked")
    public List<String> getUserInfo(final PortletRequest request, final String attributeName) {
        Map<String, List<String>> userInfo = userInfoMap;

        List<String> attributeValues = null;

        if (userInfo != null) {
            attributeValues = userInfo.get(attributeName);
        } else {
            log.error("Unable to retrieve Portal UserInfo !");
            //throw new IllegalStateException("Unable to retrieve Portal UserInfo !");
        }

        if (attributeValues == null) {
            attributeValues = Collections.EMPTY_LIST;
        }

        return attributeValues;
    }

    public static List<String> getESCOUAICourant() {
        return ESCOUAICourant;
    }

    public static Map<String, List<String>> getUserInfoMap() {
        return userInfoMap;
    }
}
