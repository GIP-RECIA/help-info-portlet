/*
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
var helpInfoPortlet = helpInfoPortlet || {};


helpInfoPortlet.init = function($, namespace, portletId, openKnownMoreInModal) {


    (function initContainer($, namespace, portletId, openKnownMoreInModal) {
        $(window).bind('load', function () {

            //console.log("namespace : " + namespace);
            

                
                console.log("pose on click " + namespace + '#HelpInfo_' + portletId);
                
                var ancreSelector = namespace + ' > a';
                // event on open help know more url
               
                    $(ancre).on('click', function (e) {
                    	console.log("execute on click");
                        e.preventDefault();
                        $(namespace +  " > .modal").modal('show').find('.modal-body').load($(this).attr('href'));
                    });
                 $(ancreSelector).trigger( "click" );
           
        });

    })($, namespace, portletId, openKnownMoreInModal);

   
};
