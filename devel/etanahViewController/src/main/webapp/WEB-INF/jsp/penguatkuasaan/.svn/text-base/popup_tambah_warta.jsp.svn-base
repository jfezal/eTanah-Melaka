<%--
    Document   : popup_tambah_warta
    Created on : June 03, 2011, 011:59:00 AM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
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

    function validateForm(){
        if(document.form1.jenisWarta.value=="")
        {
            alert('Sila pilih jenis warta');
            $('#jenisWarta').focus();
            return false;
        }
        return true;
    }
    
    function test(f) {
        $(f).clearForm();
    }

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshWarta();
            self.close();
        },'html');

    }
</script>

<s:form  beanclass="etanah.view.penguatkuasaan.TerimaanWartaActionBean" name="form1">


    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Maklumat Terimaan Warta</legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut.
            </div>&nbsp;

            <p>
                <label><em>*</em>Jenis warta :</label>
                <s:select name="jenisWarta"  style="width:143px;" id="jenisWarta">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodRuj}" label="nama" value="kod" sort="nama" />
                </s:select>
                &nbsp;
            </p>

            <p>
                <label>No.Warta :</label>
                <s:text name="noWarta" maxlength="12" onchange="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Tarikh Warta:</label>
                <s:text name="tarikhWarta"  id="tarikhDitahan" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
            </p>

            <p>
                <label>Tarikh Terima Warta:</label>
                <s:text name="tarikhTerima"  id="tarikhTerima" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
            </p>

            <p>
                <label>Tarikh Disiarkan Warta:</label>
                <s:text name="tarikhSampai"  id="tarikhSampai" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
            </p>

            <p>
                <label>Catatan :</label>
                <s:textarea name="catatan" rows="5" cols="40" onchange="this.value=this.value.toUpperCase();" id="catatan" onkeydown="limitText(this,999);" onkeyup="limitText(this,999);" />&nbsp;
            </p>

        </fieldset>


        <p><label>&nbsp;</label>
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" name="simpan"  onclick="if(validateForm())save(this.name,this.form);" value="Simpan"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>

        <br>
    </div>
</s:form>