<%-- 
    Document   : pihak_berkepentingan_multiple
    Created on : Jul 19, 2010, 1:37:37 PM
    Author     : muhammad.amin
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">

    function doGetContent(id,value) {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var url = '${pageContext.request.contextPath}/consent/pihak_consent?doGetPartialPage&idHakmilik=' + id +'&valuePass='+value;
        $.ajax({
            url : url,
            dataType : 'html',
            success : function(data) {$('#div_content').html(data);$.unblockUI();}
        });
    }
</script>
<div class="subtitle displaytag">
    <s:messages/>
    <s:errors/>
    <s:form beanclass="etanah.view.stripes.consent.PihakConsentActionBean">
        <fieldset class="aras1">
            <legend>Senarai Hakmilik Permohonan</legend>
            <br/>
            <c:if test="${edit}">
                <div align="center"><font color="red" size="2">Sila pilih hakmilik untuk membuat kemasukan. Hanya tuan tanah yang sama akan dipaparkan.</font></div>
            </c:if>

            <c:if test="${display}">
                <div align="center"><font color="red" size="2">Sila pilih hakmilik untuk paparan. Hanya tuan tanah yang sama akan dipaparkan.</font></div>
            </c:if>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.p.senaraiHakmilik}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/consent/pihak_consent">
                    <c:if test="${edit}">
                        <display:column>
                            <input type="radio" id="" name="rb" onclick="doGetContent('${line.hakmilik.idHakmilik}','edit');"
                                   class="checkbox"/>
                        </display:column>
                    </c:if>
                    <c:if test="${display}">
                        <display:column>
                            <input type="radio" id="" name="rb" onclick="doGetContent('${line.hakmilik.idHakmilik}','display');"
                                   class="checkbox"/>
                        </display:column>
                    </c:if>

                    <display:column title="Hakmilik" property="hakmilik.idHakmilik"/>
                    <display:column title="Daerah" property="hakmilik.daerah.nama"/>
                    <display:column title="Bandar / Pekan / Mukim" property="hakmilik.bandarPekanMukim.nama"/>
                </display:table>
            </div>
        </fieldset>
        <br/>
        <div id="div_content" class="content" align="center"/>
    </s:form>
</div>
