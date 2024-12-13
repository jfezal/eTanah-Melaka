<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-right:0.1em;
        text-align:right;
        width:28em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        width:28em;
    }
</style>

<script type="text/javascript">
    $(document).ready(function(){
        $('form').submit(function(){
            $('#loading-img').show();
        });
    });
</script>

<s:form beanclass="etanah.view.stripes.AquireActionBean">
    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend><br>
            <div align="center">
                <table border="0" align="center">

                    <tr><td id="tdLabel">Urusan :&nbsp;</td>
                        <td id="tdDisplay"> ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                        </td>
                    </tr>
                    <tr><td id="tdLabel">ID Permohonan :&nbsp;</td>
                        <td id="tdDisplay">
                            ${actionBean.permohonan.idPermohonan}&nbsp;
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Tarikh Masuk :&nbsp;</td>
                        <td id="tdDisplay">
                            ${actionBean.tarikhMasuk}&nbsp;
                        </td>
                    </tr>
                </table>
            </div><br>
<%--            <p>
                <label for="Permohonan">Urusan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <p>
                <label for="Tarikh Masuk">Tarikh Masuk :</label>
                ${actionBean.tarikhMasuk}&nbsp;
            </p>--%>
            <br/>
            <fieldset class="aras1">
                <legend align="center">Senarai Hakmilik Terlibat</legend>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTerlibat}"
                                   cellpadding="0" cellspacing="0" id="line1"
                                   requestURI="/aquireActionBean"
                                   style="width:50%" pagesize="10">
                        <display:column title="Bil">${line1_rowNum}</display:column>
                        <display:column title="Id Hakmilik" property="idHakmilik"/>
                        <display:column title="Mukim" property="bpm"/>
                    </display:table>
                </p>
            </fieldset>
            <br/>
            <%-- <p>
                 <label for="ID Hakmilik">ID Hakmilik :</label>
                 ${actionBean.idHakmilik}&nbsp;
                 &nbsp;
                 <c:forTokens items="${actionBean.idHakmilik}" delims="," varStatus="line" var="items">
                     ${items}
                     <c:if test="${line.count mod 2 == 0}">
                     <br/>
                     </p>
                     <p><label>&nbsp;</label>&nbsp;
                     </c:if>
                     <c:if test="${line.last ne true}">,</c:if>
                 </c:forTokens>
             </p>--%>

            <p>
                <label>&nbsp;</label>
                <s:submit name="terus" id="terus" value="Ambil Tugasan" class="longbtn"/>
                <%--<s:submit name="kembali" id="kembali" value="Kembali" class="btn"/>--%>
                <s:button name="kembali" id="kembali" value="Kembali" onclick="javascript:history.back(1);" class="btn"/>&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" style="display:none" id="loading-img"/>
            </p>
        </fieldset >
    </div>
</s:form>