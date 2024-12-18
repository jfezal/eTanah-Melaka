<%-- 
    Document   : tambah_permohonan
    Created on : Apr 4, 2013, 10:10:30 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    
    $(document).ready( function(){
    <c:if test="${actionBean.save}">
            self.close();
            opener.refreshPermohonanKelompok($('#idKelompok').val());
    </c:if>
        });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>

<s:form beanclass="etanah.view.stripes.pelupusan.CarianPermohonanBerkelompokActionBean" name ="tambahPermohonan" id ="tambahPermohonan">
    <s:messages/>
    <s:errors/>
    <s:hidden name="idKelompok" id="idKelompok"/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p><label><font color="red">*</font>ID Permohonan :</label>
                        <s:text id="idPermohonan" name="idPermohonan"   value="${actionBean.idPermohonan}"></s:text>
                </p>
                <p align="center"><s:submit name="carianPermohonan" value="Cari" class="btn"/>&nbsp;
                    <s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('tambahPermohonan')" />
                    <s:button name = "tutup" class="btn" value ="Tutup" onclick= "self.close();" />
                </p>

            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">  
            <div class="content">
                <fieldset class="aras1">
                    <legend>
                        Status Permohonan
                    </legend>
                    <table class="tablecloth" style="font-size: small">
                        <tr>
                            <td>
                                <label>ID Permohonan :</label>
                            </td>
                            <td>
                                ${actionBean.permohonan.idPermohonan}
                                <s:hidden name="idMohon" id="idMohon" value="${actionBean.permohonan.idPermohonan}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Tujuan Permohonan :</label>
                            </td>
                            <td>
                                ${actionBean.tujuan}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Aliran Terkini :</label>
                            </td>
                            <td>
                                ${actionBean.idAliranTerkini}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Status :</label>
                            </td>
                            <td>
                                ${actionBean.status}
                            </td>
                        </tr>
                        <c:if test="${actionBean.simpan}"> 
                            <tr align="center">
                                <td colspan="2">
                                    <s:submit name="simpanPermohonan" value="Simpan Permohonan" class="longbtn"/>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </fieldset>
            </div>
        </c:if>
    </div>                     
</s:form>

