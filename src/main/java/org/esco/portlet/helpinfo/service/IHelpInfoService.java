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
package org.esco.portlet.helpinfo.service;

import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;

import org.esco.portlet.helpinfo.model.HelpInfo;

/**
 * Created by jgribonvald on 14/09/16.
 */
public interface IHelpInfoService {

    HelpInfo retrieveHelpInfos(final PortletRequest request);

	void noReadMore(PortletRequest request, boolean hide) throws ReadOnlyException;

}
