<%-- 
    Document   : kemasukanSijilMC
    Created on : Aug 18, 2016, 4:39:27 PM
    Author     : siti.mudmainnah
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    function doCetak(f) {
        var idHakmilik = $("#idHakmilik").val();
//
        var report = 'STRSijilMCManual_MLK.rdf';
//
//
        window.open("${pageContext.request.contextPath}/reportGeneratorServlet?" + "&reportName=" + report + "&report_p_id_hakmilik="
                + idHakmilik, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");

        $.unblockUI();

    }
    
    function reset1(f) {
        $("#idHakmilik").val('');
        $("#idPermohonan").val('');

    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.strata.utiliti.KemasukanSijilMC">

    <s:messages />
    <s:errors />

    <fieldset class="aras1">
        <legend>Kemasukan Sijil MC (Manual)</legend>

        <p>
            <label><font color="red">*</font>Id Hakmilik :</label>
                <s:text name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();" id="idHakmilik"/>

        </p>
        <p>
            <label>atau</label>
        </p>
        <br />
        <p>
            <label>Id Permohonan :</label>
                <s:text name="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" id="idPermohonan"/>

        </p>
        <p>
            <label>&nbsp;</label><s:submit name="cari" value="Cari" class="btn"/>
        </p>

        <c:if test="${addNew}">
            <p>
                <label><font color="red">*</font>No Sijil :</label>
                    <s:text name="bdnUrus.pengurusanNoSijil" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>No Rujukan Fail :</label>
                    <s:text name="bdnUrus.pengurusanNoRujukan" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Sijil Dikeluarkan :</label>
                    <s:text name="bdnUrus.pengurusanTarikhRujukan" class="datepicker" formatPattern="dd/MM/yyyy" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Tarikh Sijil Diserahkan :</label>
                    <s:text name="bdnUrus.pengurusanTarikhSijil" class="datepicker" formatPattern="dd/MM/yyyy"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nama Penerima Sijil :</label>
                    <s:text name="bdnUrus.nama"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>No Kad Pengenalan :</label>
                    <s:text name="bdnUrus.noPengenalan"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nama Kawasan PBT (Pihak Berkuasa Tempatan) :</label>
                    <s:select name="bdnUrus.alamat4">
                                <s:option value="">Pilih ...</s:option>
                                <s:option value="MBMB">MBMB</s:option>
                                <s:option value="MPHTJ">MPHTJ</s:option>
                                <s:option value="MPJ">MPJ</s:option>
                                <s:option value="MPAG">MPAG</s:option>
                            </s:select>
            </p>
            <label>&nbsp;</label><s:submit name="save" value="Simpan" class="btn"/>
        </c:if>
        <c:if test="${!addNew && actionBean.bdnUrus != null}">
            <br />
            <br />
            <br />
            <p>
                <label><font color="red">*</font>No Sijil :</label>
                    <s:text name="bdnUrus.pengurusanNoSijil" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>No Rujukan Fail :</label>
                    <s:text name="bdnUrus.pengurusanNoRujukan" onkeyup="this.value=this.value.toUpperCase();" size="50"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Rujukan Dikeluarkan :</label>
                    <s:text name="bdnUrus.pengurusanTarikhRujukan" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label>Tarikh Sijil Diserahkan :</label>
                    <s:text name="bdnUrus.pengurusanTarikhSijil" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label>Nama Penerima Sijil :</label>
                    <s:text name="bdnUrus.nama"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>No Kad Pengenalan :</label>
                    <s:text name="bdnUrus.noPengenalan"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nama Kawasan PBT (Pihak Berkuasa Tempatan) :</label>
                    <s:select name="bdnUrus.alamat4">
                                <s:option value="">Pilih ...</s:option>
                                <s:option value="MBMB">MBMB</s:option>
                                <s:option value="MPHTJ">MPHTJ</s:option>
                                <s:option value="MPJ">MPJ</s:option>
                                <s:option value="MPAG">MPAG</s:option>
                            </s:select>
            </p>
            <label>&nbsp;</label>
            <s:submit name="save" value="Simpan" class="btn"/>
            <s:submit name="showForm" value="Kembali" class="btn" onclick="reset1();"/>
            <s:button name="" value="Papar Sijil MC" onclick="doCetak(this.form);" class="btn"/>
        </c:if>
    </fieldset>
</s:form>
