<%-- 
    Document   : kemasukan_ulasan_jabatan_teknikal
    Created on : Apr 14, 2010, 6:10:08 PM
    Author     : nursyazwani
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

<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>

<script type="text/javascript">
    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();
    });

    function saveUlasan(event, f){
        if(validate()){

        }
        else{
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageUlasanJabatanTeknikal();
                self.close();
            },'html');
        }
    }
    function validate(){
        if($("#jabatan").val() == ""){
            alert('Sila pilih " Jabatan Teknikal " terlebih dahulu.');
            $("#jabatan").focus();
            return true;
        }
        if($("#ulasan").val() == ""){
            alert('Sila masukkan " Ulasan " terlebih dahulu.');
            $("#ulasan").focus();
            return true;
        }
    }

</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.pembangunan.UlasanJabatanTeknikalActionBean">
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Kemasukan Ulasan Jabatan Teknikal</legend>
                        <p><label>Jabatan :</label>
                            <s:select name="jabatanTeknikal" style="width:290px" id="jabatan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="Jabatan Kebajikan Masyarakat">Jabatan Kebajikan Masyarakat</s:option>
                                <s:option value="Jabatan Kerja Raya">Jabatan Kerja Raya</s:option>
                                <s:option value="Jabatan Pengairan dan Saliran">Jabatan Pengairan dan Saliran</s:option>
                                <s:option value="Jabatan Perancang Bandar dan Desa">Jabatan Perancang Bandar dan Desa</s:option>
                                <s:option value="Jabatan Tenaga Kerja">Jabatan Tenaga Kerja</s:option>
                                <%--<s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>--%>
                            </s:select>
                         </p>
                         <p><label>Ulasan :</label>
                             <s:textarea name="ulasan" rows="3" cols="40" id="ulasan"/>
                         </p>
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpan" id="simpan" value="Simpan" class="btn" onclick="saveUlasan(this.name, this.form);"/>
                            <label>&nbsp;</label>
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </p>
            </fieldset>
        </div>
    </s:form>
