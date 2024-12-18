
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
  <script>
 var isNS = (navigator.appName == "Netscape") ? 1 : 0;
  if(navigator.appName == "Netscape") document.captureEvents(Event.MOUSEDOWN||Event.MOUSEUP);
  function mischandler(){
   return false;
 }
  function mousehandler(e){
 	var myevent = (isNS) ? e : event;
 	var eventbutton = (isNS) ? myevent.which : myevent.button;
    if((eventbutton==2)||(eventbutton==3)) return false;
 }
 document.oncontextmenu = mischandler;
 document.onmousedown = mousehandler;
 document.onmouseup = mousehandler;
  </script>
<s:form beanclass="etanah.view.strata.SemakLaporanActionBean">
   <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
         <legend><font size="2" color="Red">${actionBean.arahan}</font></legend>
         <p align="center"><br>
    <%--<c:if test="${jana}">--%>
    <iframe width="1000" height="1200" src="${actionBean.iframeURL}#navpanes=0 &toolbar=0&messages=0"  scrolling=no></iframe>
      <%--</c:if>--%>
    </p>&nbsp;
<%--    <embed toolbar="0" src="${actionBean.iframeURL}">--%>

        </fieldset>
</div>
            &nbsp;
    &nbsp;&nbsp;
</s:form>