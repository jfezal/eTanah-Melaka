<%-- 
    Document   : terima_ulasan_badan_pengawal
    Created on : Oct 20, 2011, 12:00:02 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function validateNumber(elmnt,content) {
             //if it is character, then remove it..
             if (isNaN(content)) {
                 elmnt.value = removeNonNumeric(content);
                 return;
             }
         }
         
  function test(f) {
        $(f).clearForm();
    }
    
    function hidorshow()
{
    if($("#syorJabatan1").val() == "3")
    {
        //$("#ulasanid").hide();
        $("#ulasanid").show();
    }
    else
    {
        $("#ulasanid").show();
    }
}
$(document).ready(function() {
        hidorshow();
  });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.SuratBadanPengawalActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="justify">Surat Badan Pengawal</legend>
            <br>
            <p>
                <label>Jabatan Pengawal Rizab :</label>
                ${actionBean.tanahRizabPermohonan.penjaga}
            </p>
            <p>
                <label>Nama Pegawai Pengawal :</label>
                ${actionBean.tanahRizabPermohonan.namaPenjaga}
            </p>
             <p>
                <label>Tarikh Terima Ulasan :</label>
                <s:text name="mohonRujLuar.tarikhTerima" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
             <p>
                <label>No Rujukan :</label>
                <s:text name="mohonRujLuar.noRujukan" size="30" id="noRujukan" onkeyup="this.value=this.value.toUpperCase();" />
            </p>
            <p >
                <label>Syor Pegawai Pengawal :</label>
                <s:select name="mohonRujLuar.diSokong" id="syorJabatan1" onchange="hidorshow()">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:option value="9">Ada Halangan</s:option>
                        <s:option value="3">Tiada Halangan</s:option>
                        <%--
                        <s:option value="1">Boleh Diluluskan</s:option>
                        <s:option value="2">Tidak Diluluskan</s:option>
                        <s:option value="3">Tiada Halangan</s:option>
                        <s:option value="4">Sesuai</s:option>
                        <s:option value="5">Tidak Sesuai</s:option>
                        <s:option value="6">Sokong</s:option>
                        <s:option value="7">Tidak Disokong</s:option>
                        <s:option value="8">Tidak Terima Ulasan</s:option>
                        --%>
                        </s:select>
            </p>
            <tr>
            <p id="ulasanid">
                <label>Ulasan :</label>
                <s:textarea name="mohonRujLuar.ulasan" rows="15" cols="50" id="ulasan" class="normal_text"/>
            </p>
            </tr>
            <p align="center">
                <s:button name="simpanUlasanPengawal" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                 <s:button name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            </p>
           
    </div>
</s:form>


