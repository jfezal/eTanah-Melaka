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
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //filterKodSeksyen();
        //filterKodGunaTanah();
//        $(".datepicker").datepicker({dateFormat: 'dd/MM/yyyy'});

//        if (idHakmilik != 'null') {
//            //alert('masuk idHakmilik != null');
//            p(idHakmilik);

        }
    });


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.RekodTerimaCekActionBean">

    <fieldset class="aras1">

        <legend>
            Rekod Terima Cek
        </legend>


        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listAmbilPampasan}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                <display:column title="Bil" sortable="true">${line_rowNum}
                </display:column>
                <display:column title="Nama Penerima Bayaran">
                    <c:if test="${line.borangPerPb.borangPerPb == null}">${line.borangPerPb.nama}</c:if>
                    <c:if test="${line.borangPerPb.borangPerPb != null}">${line.borangPerPb.borangPerPb.nama}</c:if>
                </display:column>
                <display:column  title="Jumlah Pampasan (RM)">RM ${line.jumTerimaPampasan}</display:column>
                <display:column property="noRujukan" title="No.Cek/No Rujukan" />
                <display:column  title="Nama"><input type="text" name="namaAmbil" value="${line.nama}" /></display:column>
                <display:column  title="No Pengenalan ${line.noPengenalan}"> <input type="text" name="nokp" value="${line.noPengenalan}"/></display:column>

                <display:column title="tarikh ${line.trhTerimaCek}">
                    <input type="text" name="tarikhAmbil" value="${line.trhTerimaCek}" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    <input type="hidden" name="idAmbil" value="${line.idAmbilPampasan}"/>
                </display:column>
                <input type="hidden" name="idAmbil" value="${line.idAmbilPampasan}"/>
                <%--</c:if>--%>
            </display:table>
        </div>
        <p align="center">
            <br>
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
             <s:submit name="hantar" id="save" value="Hantar" class="longbtn"/>
        </p>

    </fieldset>

</s:form>