<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.1em;
        text-align:right;
        width:38.6em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:50em;
    }
</style>

<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.stripes.consent.KeputusanPermohonanActionBean">
        <s:messages/>
        <s:errors/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>

                <table border="0">
                    <tr> <td>&nbsp;</td></tr>
                    <tr><td id="tdLabel">Permohonan :&nbsp;</td>
                        <td id="tdDisplay"><font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
                        </td>
                    </tr>
                    <tr><td id="tdLabel">ID Permohonan :&nbsp;</td>
                        <td id="tdDisplay">
                            ${actionBean.permohonan.idPermohonan}&nbsp;
                        </td>
                    </tr>

                    <c:if test="${actionBean.permohonan.keputusan.nama ne null}">
                        <tr><td id="tdLabel">Keputusan :&nbsp;</td>
                            <td id="tdDisplay">
                                ${actionBean.permohonan.keputusan.nama}&nbsp;
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${noSijil && actionBean.permohonan.keputusan.kod eq 'L'}">
                        <tr><td id="tdLabel">Nombor Sijil :&nbsp;</td>
                            <td id="tdDisplay">
                                ${actionBean.permohonanRujukanLuar.noRujukan}&nbsp;
                            </td>
                        </tr>
                    </c:if>
                </table>
                <br/>
            </fieldset>
        </div>
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Hakmilik</legend>
                <p align="center">
                    <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) <= 20}">
                        <display:table style="width:30%;" class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" cellpadding="0" cellspacing="0"
                                       id="lineHakmilik">
                            <display:column title="Bil" sortable="true" style="width:40;text-align: center">${lineHakmilik_rowNum}</display:column>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="text-align: center"/>
                        </display:table>
                    </c:if>
                    <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 20}">
                        <display:table style="width:30%;" class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}" cellpadding="0" cellspacing="0"
                                       id="lineHakmilik" requestURI="${pageContext.request.contextPath}/consent/keputusan_permohonan" pagesize="20">
                            <display:column title="Bil" sortable="true" style="width:40;text-align: center">${lineHakmilik_rowNum}</display:column>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="text-align: center"/>
                        </display:table>
                    </c:if>
                </p>
                <br/>
            </fieldset>
        </div>
    </s:form>
</div>