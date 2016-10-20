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
package org.esco.portlet.helpinfo.dao.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import java.util.Set;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * Created by jgribonvald on 27/09/16.
 */
public class EscoHostnameVerifier implements HostnameVerifier, InitializingBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private HostnameVerifier defaultHostnameVerifier;

    private Set<String> trustedDomains;

    public EscoHostnameVerifier() {
        super();
    }

    public boolean verify(String hostname, SSLSession session) {
        log.debug("EscoHostnameVerifier : checking on hostname [" + hostname + "]");
        if (hostname != null && trustedDomains.contains(hostname)) {
            return true;
        }
        return defaultHostnameVerifier.verify(hostname,session);
    }

    public void setTrustedDomains(final Set<String> trustedDomains) {
        this.trustedDomains = trustedDomains;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notEmpty(trustedDomains, "The list of trusted domains isn't initialized !");
        if (defaultHostnameVerifier == null) {
            defaultHostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();
        }
        log.debug("Trusted Domain configured are {}", this.trustedDomains);
    }

}
