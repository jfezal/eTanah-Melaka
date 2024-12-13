<%-- 
    Document   : remisyen_tanah_popup
    Created on : Mar 1, 2011, 11:36:06 AM
    Author     : abu.mansur
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function pilih(f){
        var len = ${fn:length(actionBean.senaraiKumpulanAkaun)};
        for(var i=1; i<=len; i++){
            if($('#radio'+i).is(':checked')){
                var idKump = $('#radio'+i).val();
                var serial = $(f).formSerialize();
                var url = "${pageContext.request.contextPath}/hasil/remisyenTanah?refreshIdKumpulan&idKumpulan="+idKump;
                $.get(url,serial,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshIdKumpulan(idKump);
                    self.close();
                },'html');
            }
        }
    }
</script>


<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.hasil.RemisyenTanahActionBean" id="remisyen_popup">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian ID Kumpulan</legend>
            <p>
                <label>ID Kumpulan :</label>
                <s:text name="idKumpulanP"  value="" size="20" maxlength="12" id="idKumpulanP" class="idKumpulanP" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Daerah :</label>
                <s:select name="kodCawP" id="kodCawP" style="width:175px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Nama Kumpulan :</label>
                <s:text name="namaKumpulanP" value="" size="50" maxlength="1000" id="namaKumpulanP" class="namaKumpulanP" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>
            <p align="center">
                <s:submit name="cariIdKumpulan" value="Cari" class="btn"/>&nbsp;
                <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('remisyen_popup');"/>&nbsp;
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </fieldset>
    </div><br>

    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Maklumat ID Kumpulan</legend>
            <p align="center">
                <s:button name="" value="Pilih" class="btn" onclick="pilih(this.form);" /> &nbsp;
                <%--<s:button name="" value="Tutup" class="btn" onclick="self.close();"/>--%>
            </p>
            <center>
                <display:table name="${actionBean.senaraiKumpulanAkaun}" class="tablecloth" id="row" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/remisyenTanah?cariIdKumpulan">
                    <display:column><s:radio name="radioKod" id="radio${row_rowNum}" value="${row.idTag}" class="radio" onmouseover="this.style.cursor='pointer';"/></display:column>
                    <display:column title="Bil.">${row_rowNum}</display:column>
                    <display:column title="ID Kumpulan" property="idTag"/>
                    <display:column title="Daerah" property="cawangan.name"/>
                    <display:column title="Nama Kumpulan" property="nama"/>
                    <display:column title="Catatan" property="catatan"/>
                    <display:column title="Tarikh Diwujudkan">
                        <fmt:formatDate value="${row.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                    </display:column>
                </display:table>
            </center>
        </fieldset>
    </div>
</s:form>
