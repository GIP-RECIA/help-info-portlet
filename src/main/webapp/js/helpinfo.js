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


	helpInfoPortlet.cacher = function(urlHelp, urlYeps) {
		
		url = urlHelp; //valeur par defaut 
		
		if (helpInfoPortlet.help == helpInfoPortlet.courant) {
			helpInfoPortlet.help = false;
		} else {
			if (helpInfoPortlet.yeps == helpInfoPortlet.courant) {
				url = urlYeps;
				helpInfoPortlet.yeps = false;
			}
		}
		
		$.ajax({
		    url: url,
		    type: 'GET',
		    datatype:'json',
		    success: function(){
		    	//rien a faire
		    	}
		    });	
		if (!helpInfoPortlet.montrer()) {
			$('.helpInfoPortlet div.modal').modal('hide');
		};
	};
	
	helpInfoPortlet.montrer = function(){
		   if (helpInfoPortlet.yeps) {
        	   $(helpInfoPortlet.yeps).trigger( "click" );
        	   helpInfoPortlet.courant = helpInfoPortlet.yeps;
        	   return 1;
           } 
		   if (helpInfoPortlet.help) {
            	   $(helpInfoPortlet.help).trigger( "click" );
            	   helpInfoPortlet.courant = helpInfoPortlet.help;
            	   return 1;
           }
           
		   return 0;
	};
	
    (function initContainer($, namespace, portletId, openKnownMoreInModal) {
        $(window).bind('load', function () {
           
                var ancreSelector = namespace + ' a.helpInfoOpenModal';
                // event on open help know more url
               var ancre =  $(ancreSelector);               
               	$(ancre).on('click', function (e) {
                 
                        e.preventDefault();
                        $(namespace +  " > .modal").modal('show').find('.modal-body').load($(this).attr('href'));
                    });
               	$(ancre).each(function(){
               		if ($(this).hasClass('yeps')) {
               			helpInfoPortlet.yeps = $(this);
               		} else {
               			helpInfoPortlet.help = $(this);
               		}
               	}
               	);
               	
               	helpInfoPortlet.montrer();
           
        });

    })($, namespace, portletId, openKnownMoreInModal);   
};



