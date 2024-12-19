<%-- 
    Document   : remisyen_tanah
    Created on : Mar 1, 2011, 10:28:26 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function cetakBilCukaiBatch(f, idKumpulan){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var negeri = "${actionBean.negeri}";
        <%--var f = this.form();--%>
        var form = $(f).formSerialize();
        var report = "";
        if(negeri == "melaka")
            report = "HSLBilCukaiBatch_MLK.rdf";
        if(negeri == "sembilan")
            report = "HSLBilCukaiBatch_NS.rdf";
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kump="+idKumpulan, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        $.unblockUI();
    }
    
      function cetakBilCukaiBatchSTR(f, idKumpulan){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var negeri = "${actionBean.negeri}";
        <%--var f = this.form();--%>
        var form = $(f).formSerialize();
        var report = "";
        if(negeri == "melaka")
            report = "HSLBilCukaiBatchSTR_MLK.rdf";
        if(negeri == "sembilan")
            report = "HSLBilCukaiBatch_NS.rdf";
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_kump="+idKumpulan, "eTanah",
        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        $.unblockUI();
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Cetak Bil Cukai Tanah Berkumpulan</font>
            </div>
        </td>
    </tr>
</table>
<s:form name="rt1" beanclass="etanah.view.stripes.hasil.CetakBilBatchActionBean" id="bil_tanah">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>ID Kumpulan :</label>
                <s:text name="idKumpulan" value="" size="30" maxlength="12" id="idKumpulan" class="idKumpulan" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Nama Kumpulan :</label>
                <s:text name="namaKumpulan" value="" size="30" maxlength="28" id="namaKumpulan" class="namaKumpulan" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Daerah Daftar :</label>
                <s:select name="kodCaw" id="kodCaw" style="width:175px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>
                </s:select>
            </p>
            <%--<p>
                <label>Catatan :</label>
                <s:textarea name="catatan" id="catatan" class="catatan" cols="40" rows="4" onchange="this.value = this.value.toUpperCase();"/>
           </p>--%>
           <p>
               <label>&nbsp;</label>
               &nbsp;
           </p>
           <p align="center">
               <s:submit name="search" value="Cari" class="btn"/>
               <s:button name="reset" value="Isi Semula" class="btn" onclick="clearText('bil_tanah');"/>
           </p>
        </fieldset>
    </div>
    <c:if test="${actionBean.show}">
        <div class="content" align="center">
           <fieldset class="aras1">
               <legend>Maklumat Kumpulan</legend>
               <div align="left">
                   <font size="2" color="black">Petunjuk :</font>
                    <p>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                             width="20" height="20" /> - <font size="2" color="black">Cetak Bil Cukai Tanah</font>
                    </p>
               </div>
                <br>
                <display:table class="tablecloth" name="${actionBean.senaraiKumpulanAkaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/cetak_bil">
                    <display:column title="Bil.">
                        <div align="center">${line_rowNum}.</div>
                    </display:column>
                    <display:column property="idTag" title="ID Kumpulan"/>
                    <display:column property="nama" title="Nama Kumpulan"/>
                    <display:column property="cawangan.name" title="Daerah"/>
                    <display:column property="catatan" title="Catatan"/>
                    <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                    <display:column title="Bil. Hakmilik" style="text-align:center">
                        <c:set value="${fn:length(line.senaraiAhli)}" var="count"/>
                        <c:out value="${count}"/> Hakmilik
                    </display:column>
                    <display:column title="Cetak HM Induk" style="width:90px;">
                        <div align="center">
                            <c:if test="${count gt 0}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                 onclick="cetakBilCukaiBatch(this.form, '${line.idTag}')" height="30" width="30" alt="cetak"
                                 onmouseover="this.style.cursor='pointer';" title="Cetak Bil Cukai Tanah Bagi ID Kumpulan :${line.idTag}"/>
                            </c:if>
                        </div>
                    </display:column>
                        <display:column title="Cetak HM Strata" style="width:90px;">
                        <div align="center">
                            <c:if test="${count gt 0}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                 onclick="cetakBilCukaiBatchSTR(this.form, '${line.idTag}')" height="30" width="30" alt="cetak"
                                 onmouseover="this.style.cursor='pointer';" title="Cetak Bil Cukai Tanah Bagi ID Kumpulan :${line.idTag}"/>
                            </c:if>
                        </div>
                    </display:column>
                </display:table>
           </fieldset>
        </div>
    </c:if>
</s:form>
