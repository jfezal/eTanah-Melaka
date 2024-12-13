<%-- 
    Document   : utiliti_report_param_ns
    Created on : May 22, 2013, 10:46:38 PM
    Author     : zabedah.zainal
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    var date = new Date();
    var kodCaw = '${actionBean.cawKod}';
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#caw").val(kodCaw);
    });

    function doSubmitOriginal(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((
        report == 'DISLTSITE_NS.rdf'
              
    ) && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#trh_mula').val()== ""){
            alert("Sila masukkan Tarikh Mula terlebih dahulu.");
            $('#trh_mula').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }
        if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#trh_akhir').val()== ""){
            alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
            $('#trh_akhir').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
            f.submit();
        }

        if((report == 'ENFSTMAJU_MLK.rdf')
            && $('#caw').val()== ""){
            alert("Sila pilih Cawangan terlebih dahulu.");
            $('#caw').focus();
            $.unblockUI();
        }else{
            f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
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

    function doSubmit(f){
        var form = $(f).formSerialize();
        var report = '${actionBean.reportName}';
        if((
        report == 'DISLTSITE_NS.rdf'

    ) && $("#id_mohon").val() == ""){
            alert("Sila masukkan 'ID Permohonan' terlebih dahulu.");
            $("#id_mohon").focus();
            return false;
        }

        if(report == 'ENFSTMAJU_MLK.rdf'){
            if($('#trh_mula').val()== ""){
                alert("Sila masukkan Tarikh Mula terlebih dahulu.");
                $('#trh_mula').focus();
                return false;
            }

            if($('#trh_akhir').val()== ""){
                alert("Sila masukkan Tarikh Akhir terlebih dahulu.");
                $('#trh_akhir').focus();
                return false;
            }

            if($('#caw').val()== ""){
                alert("Sila pilih Cawangan terlebih dahulu.");
                $('#caw').focus();
                return false;
            }
            
        }

        f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
        f.submit();
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.UtilitiReportNSActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <c:if test="${
                  reportname eq 'DISLTSITE_NS.rdf'
                  }">
                <p>
                    <label><em>*</em>ID Permohonan :</label>
                    <s:text id="id_mohon" name="report_p_id_mohon" onblur="this.value = this.value.toUpperCase();"/>
                </p>
            </c:if>
            <c:if test="${reportname eq 'ENFSTMAJU_MLK.rdf'}">
                <p>
                    <label><em>*</em>Tarikh Mula :</label>
                    <s:text id="trh_mula" name="report_p_trh_mula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Tarikh Tamat :</label>
                    <s:text id="trh_akhir" name="report_p_trh_akhir" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p>
                    <label><em>*</em>Cawangan :</label>
                    <s:select id="caw" name="report_p_kod_caw" style="width:145px;" onchange="doFilterKaunter(this.value);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                    </s:select>
                </p>
            </c:if>
            <%--      <c:if test="${reportname eq 'ENFSTMAJU_MLK.rdf'}">
                      <p>
                          <label>Tahun :</label>
                          <s:select id="tahun" name="report_p_tahun" style="width:145px;">
                              <s:option value="">--Sila Pilih--</s:option>
                              <s:options-collection collection="${actionBean.listYear}"/>
                          </s:select>
                      </p>
                  </c:if>--%>

            <br>
            <p align="center">
                <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>

