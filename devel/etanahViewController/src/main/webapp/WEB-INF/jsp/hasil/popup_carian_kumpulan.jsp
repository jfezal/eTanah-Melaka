<%-- 
    Document   : popup_carian_kumpulan
    Created on : Oct 11, 2011, 2:44:45 PM
    Author     : haqqiem
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
    function popupHakmilik(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah_details",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function pilihKumpulan(){
        var len = ${fn:length(actionBean.senaraiKumpulan)};
        for(var i=1; i<=len; i++){
            if($('#radio'+i).is(':checked')){
                var idKumpulan = $('#radio'+i).val();
                var d = $('#daerah').val();
                self.opener.refreshKump(idKumpulan, d);
                <%--self.opener.refreshKump(idKumpulan);--%>
                self.close();
            }
        }
    }

    function getValueDaerah(id){
        $("#daerah").val(id);}
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.hasil.CarianKumpulanActionBean" id="popup_carian_kumpulan">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Kumpulan</legend>

            <p>
                <label>ID Kumpulan :</label>
                <s:text name="idKump"  value="" size="30" maxlength="30" id="idKumpulan" class="idKumpulan" onchange="this.value = this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Nama Kumpulan :</label>
                <s:text name="namaKump" value="" size="30" maxlength="1000" id="namaKumpulan" class="namaKumpulan" onchange="this.value = this.value.toUpperCase();"/>
            </p>

            <p>
                <label><em>*</em>Pilih Daerah :</label>
                <s:select name="daerah" id="daerah" onchange="checkingDatabase(this.value)">
                    <s:option label="Pilih Daerah ..." value="" />
                    <c:forEach items="${actionBean.senaraiDaerah}" var="i" >
                        <s:option value="${i.kod}">${i.nama}</s:option>
                    </c:forEach>
                </s:select>
            </p>
            
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>
            <p align="center">
                <s:submit name="search" value="Cari" class="btn"/>&nbsp;
                <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('popup_carian_kumpulan');"/>&nbsp;
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
            <s:text name="" id="daerah" readonly="true" style="display:none;"/>
        </fieldset>
    </div><br>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Maklumat Kumpulan</legend>
            <p align="center">
                    <s:button name="" value="Pilih" class="btn" onclick="pilihKumpulan();" /> &nbsp;
            </p>
            <center>
                <display:table name="${actionBean.senaraiKumpulan}" class="tablecloth" id="row" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/carian_kumpulan">
                    <display:column><s:radio name="radioKod" id="radio${row_rowNum}" value="${row.idKumpulan}" onclick="getValueDaerah('${row.cawangan.daerah.kod}')" class="radio" onmouseover="this.style.cursor='pointer';"/></display:column>
                    <display:column title="Bil.">
                            <div align="center">${row_rowNum}.</div>
                        </display:column>
                        <display:column property="idKumpulan" title="ID Kumpulan"/>
                        <display:column property="cawangan.daerah.nama" title="Daerah"/>
                        <display:column property="catatan" title="Nama Kumpulan"/>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Masuk" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column title="Bil. Hakmilik" style="text-align:center">
                            <c:set value="${fn:length(row.senaraiAkaun)}" var="count"/>
                            <c:out value="${count}"/> Hakmilik
                        </display:column>
                </display:table>
            </center>
        </fieldset>
    </div>
</s:form>