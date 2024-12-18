<%-- 
    Document   : penukaran_seksyen
    Created on : Jul 12, 2010, 8:56:25 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
function validateForm(){
    if  ($('#kodUrusan').val ==""){
        return false
    }
     return true;
}

</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatSeksyenActionBean">

    <s:messages/>
    <s:errors/>
    <div class="subtitle">       
        <fieldset class="aras1">
            <legend>Malumat Penukaran Seksyen</legend>

            <p>
                <label>Peruntukan Seksyen :</label>
                <s:select name="kodUrusan.kod"  id="kodUrusan" value="${actionBean.permohonan.rujukanUndang2}"  style="width:500px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${actionBean.senaraiUrusan}" label="nama" value="kod" sort="kod" />
                </s:select>&nbsp;
            </p>
            <p>
                <label>Ulasan :</label>
                <s:textarea name="permohonan.catatan" rows="5" cols="50"/>&nbsp;
            </p>
        </fieldset>
            <p align="right">
                <s:submit class="btn" name="changeUrusan" value="Simpan" onclick="return validateForm();"/>
             </p>
    </div>
</s:form>