<%-- 
    Document   : partial_kodbpm2
    Created on : Mar 24, 2010, 11:34:17 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<script type="text/javascript">
    $(document).ready( function(){
        $("#namaDaerah").val($("#kodDaerah").val());

        $("#namaDaerah").change( function() {
            var valueDaerah = $("#namaDaerah").val();
            $("#kodDaerah").val(valueDaerah);
        });
        $("#kodDaerah").blur( function() {
            var valueDaerah = $("#kodDaerah").val();
            $("#namaDaerah").val(valueDaerah);
        });
        $("#kodBPM").blur( function() {
            var valueBPM = $("#kodBPM").val();
            $("#namaBPM").val(valueBPM);
           // $("#bandarPekanMukim").val(valueBPM);
        });
        $("#namaBPM").change( function() {
            var valueBPM = $("#namaBPM").val();
            $("#kodBPM").val(valueBPM);
            //$("#bandarPekanMukim").val(valueBPM);
        });
        $("#namaJenisHakmilik").change( function() {
            var valueJenisHakmilik = $("#namaJenisHakmilik").val();
            $("#kodJenisHakmilik").val(valueJenisHakmilik);
        });
        $("#kodJenisHakmilik").blur( function() {
            var valueJenisHakmilik = $("#kodJenisHakmilik").val();
            $("#namaJenisHakmilik").val(valueJenisHakmilik);
        });

    });
</script>--%>
<s:form partial="true" beanclass="etanah.view.stripes.common.CarianHakmilik">
    <p>
        <label class="Label">Bandar / Pekan / Mukim </label>:
        <%--<s:text name="kodBPM" size="4" id="kodBPM"/> ---%>
        <s:select name="namaBPM" id="namaBPM">
            <s:option value="">-- Sila Pilih --</s:option>
            <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="bandarPekanMukim"/>
        </s:select>

    </p>
</s:form>
