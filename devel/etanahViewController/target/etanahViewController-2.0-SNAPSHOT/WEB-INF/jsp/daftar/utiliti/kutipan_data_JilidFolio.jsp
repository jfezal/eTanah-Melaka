<%-- 
    Document   : kutipan_data_tambahUrusan
    Created on : Oct 7, 2013, 11:55:20 AM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript">
function updateUrusan() {
    // UPDATE URUSAN
    doBlockUI();
//    alert($("#kodSerah").val());
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kutipanData?simpanJilidFolio';
    frm = document.tambahFolioJilid;
    frm.action = url;
    frm.submit();
  }

        
</script>
<style type="text/css">
    input.error { background-color: yellow; }
    #tdLabel {
        color:#003194;
        display:block;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:100px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }
    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        width:40em;
    }
</style>
<!DOCTYPE html>
<div class="a">
    <s:errors />
    <s:messages/>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean" name="tambahFolioJilid" id="tambahFolioJilid">
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <lagend>Maklumat Urusan</lagend><br><br>
                <s:hidden name="idPerserahan" id="idPerserahan" value="${actionBean.hakmilikUrusan.idPerserahan}"/>
                <s:hidden name="idHM" id="idHM" value="${actionBean.hakmilikUrusan.hakmilik.idHakmilik}"/>
                <s:hidden name="kodUrusan" id="kodUrusan" value="${actionBean.hakmilikUrusan.kodUrusan.kod}"/>

                <c:if test="${actionBean.hakmilikUrusan ne null}">
                    <!----------------- Update urusan --------------------------->
                    <div align="center">
                        <table class="tablecloth" width="90%" style="margin-left: auto; margin-right: auto;">
                            <tr><th colspan="2">Butiran Urusan</th></tr>
                            <tr>
                                <td id="tdLabel"> Urusan :&nbsp</td>
                                <td id="tdDisplay" >
                                    ${actionBean.hakmilikUrusan.kodUrusan.kod} - ${actionBean.hakmilikUrusan.kodUrusan.nama}
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel"> Id Urusan :&nbsp</td>
                                <td id="tdDisplay" >
                                    <s:hidden name="hakmilikUrusan.idPerserahan" />
                                    ${actionBean.hakmilikUrusan.idPerserahan}
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel"> Id Hakmilik :&nbsp</td>
                                <td id="tdDisplay" >
                                    <s:hidden name="hakmilikUrusan.hakmilik.idHakmilik" />
                                    ${actionBean.hakmilikUrusan.hakmilik.idHakmilik}
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel">Tarikh Perserahan :&nbsp</td>
                                <td id="tdDisplay" >
                                    <fmt:formatDate type="date" pattern="dd/MM/yyyy hh:mm:ss a" value="${actionBean.hakmilikUrusan.tarikhDaftar}"/>&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel">No Jilid :&nbsp</td>
                                <td id="tdDisplay">
                                    <s:text name="noJilid" id="noJilid" size="30" maxlength="10"
                                            onblur="this.value=this.value.toUpperCase();"
                                            value="${actionBean.folder.noJilid}" />
                                </td>
                            </tr>
                            <tr>
                                <td id="tdLabel">No Folio :&nbsp</td>
                                <td id="tdDisplay">
                                    <s:text name="noFolio" id="noFolio" size="30" maxlength="10"
                                            onblur="this.value=this.value.toUpperCase();"
                                            value="${actionBean.folder.noFolio}" />
                                </td>
                            </tr>
                        </table>
                            <br>
                            <p align="center">
                                <c:if test="${actionBean.hakmilikUrusan ne null}">
                                    <s:submit name="simpanJilidFolio" value="Simpan" class="btn" onclick="updateUrusan();"/>&nbsp;
                                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                                </c:if>
                            </p>
                    </div>
                </c:if>
                <br>
            </fieldset>
        </div>
    </s:form>
</div>