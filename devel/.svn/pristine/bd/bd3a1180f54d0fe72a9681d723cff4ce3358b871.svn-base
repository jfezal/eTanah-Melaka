<%-- 
    Document   : lokasiPopupSave
    Created on : Apr 29, 2010, 3:41:01 PM
    Author     : aminah.abdmutalib
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html><head>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript">
    function validateForm(){

        if($('#bandarP').val() == ''){
            alert("Sila pilih Bandar/Pekan/Mukim");
            return false;
        }
        if($('#pkod').val() == ''){
            alert("Sila pilih jenis tanah");
            return false;
        }
        return true;
        self.opener.refreshPage();
        self.close();
    }
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPage();
            self.close();
        },'html');
       
    }
     

 </script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.penguatkuasaan.SenaraiAduanActionBean">
 <s:hidden name="idPermohonan" value="actionBean.idPermohonan" />
    <s:messages/>
    <div class="instr" align="center">
            <s:errors/>
    </div>
    <div class="subtitle">
<fieldset class="aras1">
            <legend>Maklumat Lokasi Aduan</legend>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bandarP">
                    <<s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod" sort="nama" />
                    </s:select>
                &nbsp;
            </p>
            <p>
            <label>Jenis Tanah :</label>
                <s:select name="pemilikan.kod" id="pkod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>
            <p>
                <label>Nombor Lot :</label>
                <s:text name="noLot" /> &nbsp;
            </p>
            <p>
                <label>Lokasi :</label>
                <s:textarea name="lokasi" rows="5" cols="50"/>&nbsp;
            </p>
            <p align="right">
                <s:button class="btn" value="Simpan" name="lokasiSave" onclick="if(validateForm())save(this.name,this.form);" />
                <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.opener.refreshPage();self.close();" />
                         </p>
                <br>
        </fieldset>
    </div>
</s:form>
