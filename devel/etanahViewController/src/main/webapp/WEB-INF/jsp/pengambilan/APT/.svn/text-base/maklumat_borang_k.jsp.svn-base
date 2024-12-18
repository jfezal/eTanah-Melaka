<%-- 
    Document   : rekodTerimaCek
    Created on : Jul 17, 2020, 1:20:05 PM
    Author     : NURBAIZURA
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
        //filterKodSeksyen();
        //filterKodGunaTanah();
//        $(".datepicker").datepicker({dateFormat: 'dd/MM/yyyy'});

//        if (idHakmilik != 'null') {
//            //alert('masuk idHakmilik != null');
//            p(idHakmilik);

        }
    });


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.sek8.MaklumatBorangKActionBean">

    <fieldset class="aras1">

        <legend>
            Rekod Terima Cek
        </legend>


        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listAmbilHakmilik}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pengambilan/sedia_borangK">
                <display:column title="Bil" sortable="true">${line_rowNum}
                </display:column>
                <display:column  title="Id Hakmilik">${line.hakmilikPermohonan.hakmilik.idHakmilik}</display:column>
                <display:column property="luasAmbil" title="Luas Ambil" />
                <display:column property="kodUnitLuas.kod" title="Luas Ambil" />
                <display:column  title="Kategori">
                    <s:select name="flagAmbilSebahagian2" value="${line.flagAmbilSbgn}" id="flagAmbilSebahagian">
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:option value="Y">Sebahagian</s:option>
                    <s:option value="T">Keseluruhan</s:option>
                </s:select>
                </display:column>
                    <display:column  title="Jumlah Hakmilik Baru">
                        <s:hidden name="idAmbilHakmilik2">${line.idAh}</s:hidden>
                        <s:text name="jumHakmilikBaru2">${line.jumlahHakmilik}</s:text></display:column>
                
   
            </display:table>
        </div>
        <p align="center">
            <br>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
        </p>

    </fieldset>

</s:form>