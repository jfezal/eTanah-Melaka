<%-- 
    Document   : ulasan_jbtn_teknikal
    Created on : Jul 14, 2010, 11:22:13 AM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function savePemohon(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Penyediaan Laporan
            </legend>
           <%-- <p class="instr">Klik pada pautan dibawah untuk mendapatkan maklumat tanah berkaitan.</p>--%>
            <p>
                <label>Jabatan :</label>
                <%--${actionBean.HakmilikUrusan.agensiRujukan.nama}&nbsp;--%>
            </p><label>&nbsp;</label>
            <p>
                <label>Tarikh Penyediaan :</label>
                <%--${actionBean.hakmilikPermohonan.catatan}&nbsp;--%>
            </p><label>&nbsp;</label>
            <p>
                <label>Nama Penyedia :</label>
                <s:text name="nama_penyelia" id="nama_penyelia" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nombor Rujukan :</label>
                <s:text name="noRujukan" id="" size="40" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <legend>
                Ulasan Jabatan Teknikal
            </legend>

            <p>
                <label>Syor :</label>
                <s:select name="syor1">
                    <s:option>Sila Pilih</s:option>
                    <s:option>Tiada Halangan</s:option>
                    <s:option>Tidak Menyokong</s:option>
                </s:select>

            </p>
            <p>
                <label>Ulasan :</label>
                <s:textarea name="ulasan1" id=""  cols="55" rows="10"  />
            </p>
            
        </fieldset>
    </div>
            <p>
                <label>&nbsp;</label>
                <s:reset name="reset" value="Isi Semula" class="btn"/>
                <s:button name="simpanUlasanJbtnTeknikal" id="simpan" value="Simpan" class="btn" onclick="savePemohon(this.name, this.form);"/>
                <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

</s:form>

