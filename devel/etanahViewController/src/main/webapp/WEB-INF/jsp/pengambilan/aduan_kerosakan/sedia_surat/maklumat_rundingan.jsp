<%-- 
    Document   : nota_baru
    Created on : May 17, 2011, 9:16:14 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function removeDok(id) {
        if (confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/notaBaru?delete&idMohonNota=' + id;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.pengambilan.aduan.sedia_surat.MaklumatAduanActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>
                Maklumat Perbincangan
            </legend> 

            <br><br>

            <p>
                <label>Tarikh Perbincangan : </label><s:text name="tarikhPerbincangan" id="datepicker" class="datepicker"/>&nbsp;
            </p>
            <p>
                <label>Masa Perbincangan : </label><s:text name="masaPerbincangan" id="timepicker" class="timepicker"/>&nbsp;
            </p>
            <p>
                <label>Tempat Perbincangan : </label><s:text name="tempatPerbincangan" id="" class=""/>&nbsp;
            </p>

            <br><br>

        </fieldset>
        <br/>
        <label>&nbsp</label>
        <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div');"/>

        <div>
            <fieldset class="aras1">
                <div class="content" align="center">

                </div>

            </fieldset>
        </div>

    </div>
</s:form>
