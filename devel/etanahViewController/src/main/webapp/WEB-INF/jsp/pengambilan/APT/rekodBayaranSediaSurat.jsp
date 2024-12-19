
<%-- 
    Document   : sediaSuratPembayaran
    Created on : Jul 17, 2020, 1:11:06 PM
    Author     : NURBAIZURA
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode !== 46 && charCode > 31
                && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
    
        function remove(val){
            var url = '${pageContext.request.contextPath}/pengambilan/rekod_bayaran_sedia_surat?delete&id='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.sek8.RekodBayaranSediaSuratActionBean">

    <fieldset class="aras1">

        <legend>
            Sedia Surat Pembayaran
        </legend>

        <p>
            <label>Tarikh Terima Bayaran:</label>&nbsp;
            <s:text name="tarikhTerimaBayaran" id="datepicker" class="datepicker"/>&nbsp;<br>
        </p>

        <p>
            <label><font color="red">*</font>No Rujukan :</label>&nbsp;
            <s:text id = "noRujukan" name="noRujukan" />&nbsp;
        </p>

        <p>
            <label><font color="red">*</font>Jumlah Bayaran :</label>&nbsp;
            <s:text name="jumlahBayaran" id="jumlahBayaran" onkeypress="return isNumberKey(event)"/>&nbsp;
        </p>

        <p>
            <label><font color="red">*</font>Nama Penerima :</label>&nbsp;
                    <s:select name="idperpb" id="idperpb">
                        <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listBorangPerPB}" label="borangPerPb.nama" value="id"/>
            </s:select>
        </p>



        <!--        <p>
                    <label><font color="red">*</font>Keterangan Surat Bayaran :</label>&nbsp;
        <s:textarea id="ketSuratBayaran" name="ketSuratBayaran"  rows="10" cols="50"/> 

</p>-->


        <p>
            <label><font color="red">*</font>Jenis Bayaran :</label>&nbsp;
            <s:radio name="carabayar" value="T" checked="true"/>&nbsp;Tunai
            <s:radio name="carabayar" value="C"/>&nbsp;Cek
            <s:radio name="carabayar" value="E"/>&nbsp;EFT
        </p>

    </fieldset>
    <fieldset class="aras1">

        <p align="center">

            <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;&nbsp;

        </p>

    </fieldset>
    <fieldset class="aras1">

        <p align="center">
            <display:table class="tablecloth" name="${actionBean.listAmbilPampasan}" pagesize="30" cellpadding="0" cellspacing="0"
                           requestURI="" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column title="ID Hakmilik"  value="${line.borangPerPb.borangPerHakmilik.hakmilikPermohonan.hakmilik.idHakmilik}"/>
                <display:column title="Nama Penerima"  value="${line.borangPerPb.borangPerPb.nama}"/>
                <display:column title="Amaun Pampasan" >${line.jumTerimaPampasan}</display:column>
                <display:column title="Tarikh Terima" >${line.tarikhDok}</display:column>
                <display:column title="Jenis Bayaran" >${line.kodCaraBayaran.nama}</display:column>
                <display:column title="No Rujukan" >${line.noRujukan}</display:column>
                <display:column title="Hapus">
                <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                         id='rem_${line_rowNum}' onclick="remove('${line.idAmbilPampasan}')">
                </div>
            </display:column>


        </display:table>
    </p> 
    <s:hidden name="idPermohonan"/>
    <center> <s:submit name="hantar" id="save" value="Hantar" class="longbtn"/>   </center> 
</fieldset>



</s:form>