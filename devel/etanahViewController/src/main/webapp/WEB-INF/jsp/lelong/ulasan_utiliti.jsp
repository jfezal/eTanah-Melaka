<%-- 
    Document   : ulasan_utiliti
    Created on : Oct 19, 2011, 4:13:42 PM
    Author     : mdizzat
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function simpan(f){
        var queryString = $(f).formSerialize()
        var url = "${pageContext.request.contextPath}/lelong/pembatalan_permohonan?simpan&"+queryString;
        $.post(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }


</script>
<s:form beanclass="etanah.view.stripes.lelong.PembatalanPermohonanUtiliti">
    <br>
        <fieldset class="aras1">
            <legend>
                Ulasan
            </legend><br>
            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
            <p>
                <label> Keputusan : </label>Batal Permohonan
            </p>
            <p>
                <label> Ulasan :</label>
                <s:textarea name="fasaPermohonan.ulasan" id="ulasan" rows="15" cols="80" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <br>
            <p align="center">
                <s:submit name="simpan" value="Simpan" class="btn"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </p>
            <br>
        </fieldset>
</s:form>