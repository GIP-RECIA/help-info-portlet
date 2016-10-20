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
package org.esco.portlet.helpinfo;

import org.esco.portlet.helpinfo.dao.impl.MockUserResourceImpl;
import org.esco.portlet.helpinfo.service.bean.IHelpUrlBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jgribonvald on 14/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class HelpUrlBuilderTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IHelpUrlBuilder helpUrlBuilder;

    @Test
    public void testUrlComposeAbsoluteWithoutSheme() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(0);
        request.setContextPath("/portal");
        request.setScheme("https");
        request.addParameter("param1", "value");

        final String url = helpUrlBuilder.transform(request, "//edu.internal.fr/portal/test/guest");
        Assert.assertEquals(url,"https://edu.internal.fr/portal/test/guest");
    }

    @Test
    public void testUrlComposeAbsolute() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(8443);
        request.setContextPath("/portal");
        request.setScheme("https");
        request.addParameter("param1", "value");

        final String url = helpUrlBuilder.transform(request, "https://other.external.fr/portal/test/{ESCOUAICourant}");
        Assert.assertEquals(url,"https://other.external.fr/portal/test/" + MockUserResourceImpl.getESCOUAICourant().get(0));
    }

    @Test
    public void testUrlComposeRelativeWithoutCTXWithPort() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(8443);
        request.setContextPath("/portal");
        request.setScheme("http");
        request.addParameter("param1", "value");
        //request.setPreferences();

        final String url = helpUrlBuilder.transform(request, "test/{ESCOUAICourant}");
        Assert.assertEquals(url,"http://edu.internal.fr:8443/portal/test/" + MockUserResourceImpl.getESCOUAICourant().get(0));
    }

    @Test
    public void testUrlComposeRelativeWithoutCTXWithoutPort() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(0);
        request.setContextPath("/portal");
        request.setScheme("http");
        request.addParameter("param1", "value");

        final String url = helpUrlBuilder.transform(request, "test/{ESCOUAICourant}");
        Assert.assertEquals(url,"http://edu.internal.fr/portal/test/" + MockUserResourceImpl.getESCOUAICourant().get(0));
    }


    @Test
    public void testUrlComposeRelativeOnHTTPWithoutCtx() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(80);
        request.setContextPath("/portal");
        request.setScheme("https");
        request.addParameter("param1", "value");

        final String url = helpUrlBuilder.transform(request, "test/guest");
        Assert.assertEquals(url,"https://edu.internal.fr/portal/test/guest");
    }

    @Test
    public void testUrlComposeRelativeOnHTTPSWithoutCtx() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(443);
        request.setContextPath("/portal");
        request.setScheme("https");
        request.addParameter("param1", "value");

        final String url = helpUrlBuilder.transform(request, "test/guest");
        Assert.assertEquals(url,"https://edu.internal.fr/portal/test/guest");
    }

    @Test
    public void testUrlComposeRelativeOnHTTPSOnCurrentCtx() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(443);
        request.setContextPath("/portal");
        request.setScheme("https");
        request.addParameter("param1", "value");

        final String url = helpUrlBuilder.transform(request, "/ctxpath/test/guest");
        Assert.assertEquals(url,"https://edu.internal.fr/ctxpath/test/guest");
    }

}
