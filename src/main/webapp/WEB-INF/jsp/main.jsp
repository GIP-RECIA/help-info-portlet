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

<portlet:actionURL var="hidePermanentlyAction" name="hidePermanently" />
<portlet:actionURL var="hideAction" name="hide" />


<div id="helpInfoPortlet_${n}" class="helpInfoPortlet">
	
		<c:if test="${! helpinfos.alreadyRead}" var="testvar" >
		avec modal
			<a class="helpInfoOpenModal" href="/aide/AideENT/indexAideENT.html" target="_blank" ></a>
				
	
		<div 	class="modal fade" 
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
		<form:form method="POST" action="${hidePermanentlyAction}"  modelAttribute="helpinfos">
						<button type="submit" class="btn btn-default" >
							<spring:message code="portlet.modal.definitClose" />
						</button>
		</form:form>			
		<form:form method="POST" action="${hideAction}"  >
						<button type="submit" class="btn btn-default" > 
						<!-- 		data-dismiss="modal" -->
								<spring:message code="portlet.modal.close" />
						</button>
		</form:form>			
					</div>
				</div>
			</div>
		</div>
	</c:if> 
	
	<portlet:actionURL var="showAction" name="show" />
	<c:if test="${helpinfos.alreadyRead }"  >
		sans modal
		<div>
		
		<form:form method="POST" action="${showAction}"  >
						<button type="submit" class="btn btn-default" data-dismiss="modal">show</button>
		</form:form>	
		</div>
	</c:if>
</div>

<%@include file="/WEB-INF/jsp/scripts.jsp" %>
