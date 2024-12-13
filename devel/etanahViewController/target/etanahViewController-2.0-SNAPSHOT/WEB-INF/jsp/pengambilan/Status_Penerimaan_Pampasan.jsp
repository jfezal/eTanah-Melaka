<%-- 
    Document   : Status_Penerimaan_Pampasan
    Created on : Oct 13, 2010, 2:31:09 PM
    Author     : Rajesh Reddy
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<script type="text/javascript">

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.StatusPenerimaanPampasanActionBean">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">Status Penerimaan Pampasan</font></font>
            </div></td></tr>
        </table>
    </div><br />
    <s:messages/>
    <s:errors/>

    <fieldset class="aras1"><br/>
            <legend align="left">
                <b>Maklumat Hakmilik Permohonan</b>
            </legend>
            <p align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/status_penerimaan_pampasan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                        <s:link beanclass="etanah.view.stripes.pengambilan.StatusPenerimaanPampasanActionBean"
                            event="hakmilikDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>${line.hakmilik.idHakmilik}
                        </s:link>
                    </display:column>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </p>
    </fieldset>
    
    <br/>
    <br/>
    <c:if test="${actionBean.hakmilik ne null}">
        <%--<div  align="center">--%>
            <fieldset class="aras1">
                <legend align="left">
                    <b>Status Penerimaan Pampasan</b>
                </legend>
                <br/>
                
                <table align="center">
                    <tr>
                        <td><label for="idHakmilik">Id Hakmilik :</label></td>
                        <td>${actionBean.hakmilik.idHakmilik}</td>
                    </tr>
                    <tr>
                        <td><label for="noLot">No Lot/No :</label></td>
                        <td>${actionBean.hakmilik.noLot}</td>
                    </tr>
                    <tr>
                        <td><label for="daerah">Daerah :</label></td>
                        <td>${actionBean.hakmilik.daerah.nama}</td>
                    </tr>
                    <tr>
                        <td><label for="bandarPekanMukim">Bandar/Pekan/Mukim :</label></td>
                        <td>${actionBean.hakmilik.bandarPekanMukim.nama}</td>
                    </tr>
                </table>
                    <br/><br/>
                    <div align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPihak}" cellpadding="0" cellspacing="0"
                                       requestURI="/pengambilan/status_penerimaan_pampasan" id="line1">
                            <display:column title="No" sortable="true">${line1_rowNum}</display:column>
                            <display:column property="pihak.nama" title="Orang Berkepentingan" />
                            <display:column property="pihak.noPengenalan" title="No KP" />
                            <display:column title="Status" >
                                <s:select name="statusMohonPihak[${line1_rowNum-1}]" id="statusMohonPihak[${line1_rowNum-1}]">
                                    <s:option value="0">Sila Pilih</s:option>
                                    <s:options-collection collection="${actionBean.senaraiKodStatusMohonPihak}" label="nama" value="kod"/>
                                </s:select>
                            </display:column>
                        </display:table>
                    </div>
                    <br/><br/>
            </fieldset>
        <%--</div>--%>
        <div align="center">
            <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </div>
    </c:if>
</div>
</s:form>