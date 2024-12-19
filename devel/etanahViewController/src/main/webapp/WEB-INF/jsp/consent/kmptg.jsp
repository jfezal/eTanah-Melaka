<%-- 
    Document   : kmptg
    Created on : Aug 28, 2012, 12:30:47 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function(){
        var val = $('#nilaiTrans').val();
        if(val != ''){
            convert(val, 'nilaiTrans');
        }
    });

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function viewStatus(id){
        window.open("${pageContext.request.contextPath}/consent/semak_status?viewStatus&idPermohonan="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width="+screen.width+",height="+screen.height+",scrollbars=yes,left=0,top=0");
    }


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.KmptgActionBean" id="kmptg">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Utiliti Kertas Makluman PTG</legend>
            <br>
            <c:if test="${fn:length(actionBean.permohonanList) == 0}">
                <font color="red" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila masukkan ID Permohonan yang telah ditolak.</font>
                <p>
                    <label><em>*</em>ID Permohonan :</label>
                    <s:text name="permohonanLama.idPermohonan" size="30" maxlength="20" id="idMohon" onblur="this.value=this.value.toUpperCase();"/>
                </p>
            </c:if>
            <c:if test="${fn:length(actionBean.permohonanList) == 0}">
                <p align="center">
                    <s:submit name="search" value="Cari" class="btn" onclick=""/>
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                </p>
            </c:if>
            <br/>
            <c:if test="${fn:length(actionBean.permohonanList) > 0}">
                <s:hidden name="permohonanLama.idPermohonan"/>
                <font color="red" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sila klik pada ID Permohonan untuk paparan terperinci permohonan dan klik butang Jana Permohonan untuk selesai.</font>
                <div align="center">
                    <display:table name="${actionBean.permohonanList}" class="tablecloth" id="line" style="width:90%" pagesize="20" requestURI="${pageContext.request.contextPath}/consent/semak_status">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="ID Permohonan">
                            <a href="#" title="klik untuk paparan" onclick="viewStatus('${line.idPermohonan}');return false;">${line.idPermohonan}</a>
                        </display:column>
                        <display:column title="Nama Permohonan" property="kodUrusan.nama" style="text-transform:uppercase;"/>
                        <display:column title="Tarikh Permohonan" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        <c:if test="${line.status eq 'TK'}">
                            <display:column title="Status"  value="BATAL"/>
                        </c:if>
                    </display:table>
                </div>
                <br/>
                <p align="center">
                    <s:submit name="save" value="Jana Permohonan" class="btn" onclick=""/>
                    <s:submit name="reset" value="Carian Semula" class="btn" onclick=""/>
                </p>
                <br/>
            </c:if>
        </fieldset>
    </div>
</s:form>