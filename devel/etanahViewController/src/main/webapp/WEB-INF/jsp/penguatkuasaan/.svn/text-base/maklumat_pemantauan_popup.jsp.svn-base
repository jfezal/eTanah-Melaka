<%-- 
    Document   : maklumat_pemantauan
    Created on : Feb 22, 2010, 9:43:55 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
 $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function test(f) {
        $(f).clearForm();
    }
    function validateForm(){
if($('#tkhMula').val() == ''){
        alert("Sila Isi Tarikh Pemantauan");
        return false;
    }
    if($('#jam').val() == '0'){
        alert("Sila Isi Masa ");
        return false;
    }
     if($('#minit').val() == '0'){
        alert("Sila Isi Masa ");
        return false;
    }
     if($('#ampm').val() == '0'){
        alert("Sila Isi Masa ");
        return false;
    }
    if($('#catat').val() == ''){
        alert("Sila Isi Laporan Pemantauan");
        return false;
    }
    self.opener.refreshPagePantau1();
    self.close();
}
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPemantauanActionBean">

           <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Pemantauan
            </legend>
            <div class="content">
            <p>
                        <label><font color="red">*</font>Tarikh :</label>
                        <s:text name="tarikhPemantauan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhMula"/>
                        <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                    </p>
                    <p>
                        <label><font color="red">*</font>Masa :</label>
                    <s:select name="jam" style="width:61px" id="jam">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>:
                    <s:select name="minit" style="width:61px" id="minit">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>:
                    <s:select name="ampm" style="width:80px" id="ampm">
                        <s:option value="0">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select>
                </p>
                    <p>
                        <label><font color="red">*</font>Laporan Pemantauan :</label>
                         <s:textarea name="catatan" rows="5" cols="50" id="catat" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
            </div>
        </fieldset>
                    <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:submit name="simpanPopup" id="simpan" value="Simpan" class="btn" onclick="return validateForm();"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
    </div>
    
</s:form>