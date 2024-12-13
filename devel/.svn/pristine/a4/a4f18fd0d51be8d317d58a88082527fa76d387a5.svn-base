<%-- 
    Document   : hasil_laporan_param
    Created on : May 18, 2010, 5:02:51 PM
    Author     : zadhirul.farihim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    <%--function doSubmit(f){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });

        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';

            var url = form.replace(/&/g, "%26");
            window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
            $.unblockUI();
        }
    }--%>



    function doSubmit(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((
        report == ''
            ) && $("#id_mohon").val() == ""){
                    alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
                    $("#id_mohon").focus();
                }else{
                    var url = form.replace(/&/g, "%26");
                    window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
                    <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;--%>
                    f.submit();
                }

    if((report == 'stat_pembida_MLK.rdf')
        && $("#id_mohon").val() == "" || $("#id_hakmilik").val() == ""){
        alert("Sila masukkan 'ID Permohonan' atau ID Hakmilik' terlebih dahulu.");
        $('#id_mohon' || '#id_hakmilik' ).focus();
        $.unblockUI();
    }else{
        var url = form.replace(/&/g, "%26");
        window.open("${pageContext.request.contextPath}/common/pdf_viewer?pdf_url="+url, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
        <%--f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;--%>
        f.submit();
    }
          
        }



    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
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

    function validateYearLength(value){
        var plength = value.length;
        if(plength != 4){
            alert('"Tahun" yang dimasukkan salah.');
            $('#tahun').val("");
            $('#tahun').focus();
        }
    }
</script>



<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
    
        <td width="100%" height="20" ><div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"> Ringkasan Senarai Kutipan Agensi</font>
                </div></td>
<s:form beanclass="etanah.view.laporan.HasilLaporanKutipanAgensi">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>

                <p>
                    <label>Kod Agensi :</label>
                    <s:select name="report_p_kod_agensi">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodAgensiKutipan}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label>Tarikh Tamat :</label>
                    <s:text id="trh_tamat" name="report_p_trh_tamat" class="datepicker" formatPattern="dd/MM/yyyy"
                            onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>


            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
            </p>

        </fieldset >
    </div>

</s:form>
