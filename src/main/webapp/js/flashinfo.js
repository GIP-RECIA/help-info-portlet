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
            if ($(namespace + ' #myCarousel_' + portletId + " .item").length > 0) {
                var defaults      = {
                    selector:          namespace + ' #myCarousel_' + portletId + " .item img"   ,
                    parent:               namespace + ' #myCarousel_' + portletId + " .item",
                    exclude:              [ 'rgb(0,0,0)', 'rgb(255,255,255)' ],
                    normalizeTextColor:   false,
                    normalizedTextColors:  {
                        light:      "#fff",
                        dark:       "#000"
                    },
                    lumaClasses:  {
                        light:      "ab-light",
                        dark:       "ab-dark"
                    }
                };
                $.adaptiveBackground.run(defaults);

                // évènement de fin de recherche de couleur dominante d'une image
                $(namespace + ' #myCarousel_' + portletId + " .item img").on('ab-color-found', function(ev,payload) {
                    //console.log(payload.color);   // The dominant color in the image.
                    //console.log(payload.palette); // The color palette found in the image.
                    //console.log(ev);   // The jQuery.Event object
                    colorCarouselElements($, namespace, portletId);
                });

                 // évènement de slide du carousel terminé
                $(namespace + ' #myCarousel_' + portletId).on('slid.bs.carousel', function (e) {
                    colorCarouselElements($, namespace, portletId);
                });

                // event on open help know more url
                if (openKnownMoreInModal) {
                    $(namespace + ' #myCarousel_' + portletId + ' .carousel-caption>a').on('click', function (e) {
                        e.preventDefault();
                        $(namespace + ' #helpInfoModal' + portletId).modal('show').find('.modal-body').load($(this).attr('href'));
                    });
                }
            } else {
                console.log("HelpInfo doesn't have element to show !")
            }
        });

    })($, namespace, portletId, openKnownMoreInModal);

    function colorCarouselElements($, namespace, portletId) {
        var selector = $(namespace + ' #myCarousel_' + portletId + ' .item.active');
        if (selector && selector.length > 0) {
            if(selector.hasClass("ab-dark")) {
                $(namespace + ' #myCarousel_' + portletId).removeClass("ab-light").addClass("ab-dark");
            } else {
                $(namespace + ' #myCarousel_' + portletId).removeClass("ab-dark").addClass("ab-light");
            }
        }
    }
};
