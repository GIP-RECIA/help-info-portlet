<%--

    Copyright © 2016 ESUP-Portail (https://www.esup-portail.org/)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<jsp:directive.include file="/WEB-INF/jsp/include.jsp" />

<rs:aggregatedResources path="skin.xml" />

<script type="text/javascript">
	// Bootstrap javascript fails if included multiple times on a page.
	// uPortal Bootstrap best practice: include bootstrap if and only if it is not present and save it to
	// portlets object. Bootstrap functions could be manually invoked via portlets.bootstrapjQuery variable.
	// All portlets using Bootstrap Javascript must use this approach.  Portlet's jQuery should be included
	// prior to this code block.
	var portlets = portlets || {};
	// If bootstrap is not present at uPortal jQuery nor a community bootstrap, dynamically load it.
	<c:choose>
		<c:when test="${usePortalJsLibs}">
		portlets.bootstrapjQuery;
		</c:when>
		<c:otherwise>
		portlets.bootstrapjQuery || document.write('<script src="/help-info-portlet/rs/bootstrap/3.3.5/bootstrap.min.js"><\/script>');
		</c:otherwise>
	</c:choose>

</script>

<div id="helpInfoPortlet_${n}" class="helpInfoPortlet">
<%--   
	<c:if test="${fn:length(helpinfos) gt 0}">
		<div id="myCarousel_${n}" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<c:if test="${fn:length(helpinfos) gt 1}">
				<ol class="carousel-indicators">
					<c:forEach var="entry" items="${helpinfos}" varStatus="loop">
						<c:set var="isactive" scope="page" ><c:if test="${loop.first}">active</c:if></c:set>
						<li data-target="#myCarousel_${n}" data-slide-to="${loop.index}"
							class="${isactive}"></li>
					</c:forEach>
				</ol>
			</c:if>
			<div class="carousel-inner" role="listbox">
				<c:forEach var="entry" items="${helpinfos}" varStatus="loop">
					<!-- Wrapper for slides first entry active-->
					<c:set var="isactive" scope="page" ><c:if test="${loop.first}">active</c:if></c:set>
					<div class="item ${isactive}">
						<img class='carousel-image${loop.index}' src="${entry.imgLink}"
							 alt="${(not empty entry.imgAlt) ? entry.imgAlt : entry.title}">
						<div class="carousel-caption">
							<h3 class="carousel-text${loop.index}">${entry.title}</h3>
							<p class="carousel-text${loop.index}">${entry.text}</p>
							<a href="${entry.knowMoreLink}" target="_blank"> <span class="knowMoreText"><spring:message
									code="portlet.knowmore" /></span>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- Left and right controls -->
			<c:if test="${fn:length(helpinfos) gt 1}">
				<a class="left carousel-control" href="#myCarousel_${n}" role="button"
				   data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
											aria-hidden="true"></span> <span class="sr-only"><spring:message code="portlet.previous" /></span>
				</a> <a class="right carousel-control" href="#myCarousel_${n}" role="button"
						data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only"><spring:message code="portlet.next" /></span>
			</a>
			</c:if>
		</div>
--%>
		<div> 
		debut test
		<a data-toggle="modal" 
		data-target="#helpInfoModal${n}" 
		href="file:///home/legay/BOOTSTRAP/charteENT.html" target="_blank" > tester</a>
							
		<div 	class="modal fade" 
				id="helpInfoModal${n}" 
				tabindex="-1" 
				role="dialog" 
				aria-labelledby="HelpInfoModalLabel" 
				aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"><spring:message code="portlet.modal.title" /></h4>
					</div>
					<div class="modal-body"><div class="te"></div></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="portlet.modal.close" /></button>
					</div>
				</div>
			</div>
		</div>
		fin test
		</div>
<%-- 	</c:if> --%>
</div>

<%@include file="/WEB-INF/jsp/scripts.jsp" %>