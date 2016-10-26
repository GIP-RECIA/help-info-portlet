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

import java.util.List;

import org.esco.portlet.helpinfo.model.HelpInfo;
import org.esco.portlet.helpinfo.service.IHelpInfoService;
import org.esco.portlet.helpinfo.service.impl.HelpInfoServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockPortletPreferences;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Created by jgribonvald on 14/09/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class HelpInfoServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IHelpInfoService helpInfoService;

    @Test
    public void test1() throws Exception {
        MockRenderRequest request = new MockRenderRequest();
        request.setServerName("edu.internal.fr");
        request.setServerPort(8443);
        request.setContextPath("/portal");
        request.setScheme("http");
        request.addParameter("param1", "value");

        MockPortletPreferences prefs = new MockPortletPreferences();
        prefs.setValue(HelpInfoServiceImpl.getPrefHelpUrl(), "/test/{ESCOUAICourant}");
        request.setPreferences(prefs);

        HelpInfo fil = helpInfoService.retrieveHelpInfos(request);
        Assert.notNull(fil);
      
        //Assert.assertEquals(url,"http://edu.internal.fr:8443/portal/test/" + MockUserResourceImpl.getESCOUAICourant().get(0));
    }
}
