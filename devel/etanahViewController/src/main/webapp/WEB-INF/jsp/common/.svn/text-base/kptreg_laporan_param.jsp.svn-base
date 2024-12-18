<%-- 
    Document   : kptreg_laporan_param
    Created on : May 18, 2010, 5:02:51 PM
    Author     : wan.fairul
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
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function doSubmit(f){
        var form = $(f).formSerialize();
        f.action = "${pageContext.request.contextPath}/reportGeneratorServlet?"+form;
        f.submit();
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

<s:form beanclass="etanah.view.laporan.KptregLaporanActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Parameter</legend>
            <c:set value="${actionBean.reportName}" var="reportname"/>

             <c:if test="${reportname eq 'MIS05_2.rdf' or reportname eq 'MIS08_2.rdf'
                                or reportname eq 'MIS11_2.rdf'}">
                         <p>
                            <label>Negeri :</label>
                            <s:select name="report_p_kod_negeri" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                  </c:if>
                          
                  <c:if test="${reportname eq 'MIS05_1.rdf' or reportname eq 'MIS08_1.rdf'
                                or reportname eq 'MIS11_1.rdf'}">
                        <p>
                            <label>Daerah :</label>
                            <s:select name="report_p_kod_daerah" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                  </c:if>
                  <c:if test="${reportname eq 'MIS04_3.rdf' 
                                 or reportname eq 'MIS05_3.rdf'
                                or reportname eq 'MIS10_3.rdf'
                                or reportname eq 'MIS11_3.rdf'
                                or reportname eq 'MIS12_3.rdf'
                                or reportname eq 'MIS15_3.rdf'}">
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
                  </c:if>
                  <c:if test="${reportname eq 'MIS04_2.rdf' or reportname eq 'MIS07_2.rdf'
                                or reportname eq 'MIS10_2.rdf'
                                or reportname eq 'MIS12_2.rdf'
                                or reportname eq 'MIS13_2.rdf'
                                or reportname eq 'MIS15_2.rdf'
                                or reportname eq 'MIS16_2.rdf'
                                or reportname eq 'MIS22_2.rdf'}">
                        <p>
                            <label>Negeri :</label>
                            <s:select name="report_p_kod_negeri" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
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
                  </c:if>       

                  <c:if test="${reportname eq 'MIS04_1.rdf' or reportname eq 'MIS07_1.rdf'
                                or reportname eq 'MIS10_1.rdf'
                                or reportname eq 'MIS12_1.rdf'
                                or reportname eq 'MIS13_1.rdf'
                                or reportname eq 'MIS15_1.rdf'
                                or reportname eq 'MIS16_1.rdf'
                                or reportname eq 'MIS22_1.rdf'}">
                        <p>
                            <label>Daerah :</label>
                            <s:select name="report_p_kod_daerah" style="width:145px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod"/>
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
                  </c:if>


                  <br>
                  <p align="center">
                      <s:button name="" id="simpan" value="Papar" class="btn" onclick="doSubmit(this.form);"/>&nbsp;
                      <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                      <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
                  </p>

            </fieldset >
        </div>

</s:form>
