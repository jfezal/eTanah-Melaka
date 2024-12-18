<%--
    Document   : view_kompaun_oks
    Created on : July 22, 2011, 6:44:22 PM
    Author     : sitifariza.hanim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">

    function viewSyorKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/oks_maklumat_kompaun?viewSyorKompaunDetail&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function viewMuktamadKompaun(id,oks){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/oks_maklumat_kompaun?viewMuktamadKompaun&idKompaun='+id+'&oks='+oks;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.OKSMaklumatKompaunActionBean">
    <s:messages />
    <s:errors />
    <c:if test="${syorKompaun}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Syor Kompaun
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            ${line.noRujukan}
                        </display:column>
                        <display:column title="Isu Kepada">
                            ${line.isuKepada}
                        </display:column>
                        <display:column title="No.KP">${line.noPengenalan}</display:column>
                        <display:column title="Syor Kompaun (RM)" format="{0,number, 0.00}">
                            <fmt:formatNumber pattern="0.00" value="${line.orangKenaSyak.amaunKompaunSyor}"/>&nbsp;
                        </display:column>
                    </display:table>
                    <br>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${muktamadKompaun}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Muktamad Kompaun
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            ${line.noRujukan}
                        </display:column>
                        <display:column title="Isu Kepada">
                            ${line.isuKepada}
                        </display:column>
                        <display:column title="No.KP">${line.noPengenalan}</display:column>
                        <display:column title="Syor Kompaun (RM)" format="{0,number, 0.00}">
                            <fmt:formatNumber pattern="0.00" value="${line.orangKenaSyak.amaunKompaunSyor}"/>&nbsp;
                        </display:column>
                        <display:column title="Muktamad Kompaun (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari" />

                    </display:table>
                    <br>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>
