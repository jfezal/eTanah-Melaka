<%-- 
    Document   : popup_carian_dasar
    Created on : Jun 1, 2011, 3:10:55 PM
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
    function popupHakmilik(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah_details",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function pilihDasarNotis(f){
        var len = ${fn:length(actionBean.senaraiDTCpopup)};
        for(var i=1; i<=len; i++){
            if($('#radio'+i).is(':checked')){
                var idDasar = $('#radio'+i).val();
                var serial = $(f).formSerialize();
                var url = "${pageContext.request.contextPath}/hasil/notis?refreshBase&noDasar="+idDasar;
                $.get(url,serial,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshNotis(idDasar);
                    self.close();
                },'html');
            }
        }
    }
    
    function pilihDasarRekod(f){
        var len = ${fn:length(actionBean.senaraiDTCpopup)};
        for(var i=1; i<=len; i++){
            if($('#radio'+i).is(':checked')){
                var idDasar = $('#radio'+i).val();
                var serial = $(f).formSerialize();
                var url = "${pageContext.request.contextPath}/hasil/rekod_penghantaran2?reloadBase&noDasar="+idDasar;
                $.get(url,serial,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshBase(idDasar);
                    self.close();
                },'html');
            }
        }
    }

    function pilihDasarWarta(f){
        var len = ${fn:length(actionBean.senaraiDTCpopup)};
        for(var i=1; i<=len; i++){
            if($('#radio'+i).is(':checked')){
                var idDasar = $('#radio'+i).val();
                var serial = $(f).formSerialize();
                var url = "${pageContext.request.contextPath}/hasil/warta?reloadBaseWarta&idDasar="+idDasar;
                $.get(url,serial,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshBase(idDasar);
                    self.close();
                },'html');
            }
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.hasil.NotisActionBean" id="popup_carian_dasar">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Dasar</legend>
            <p>
                <label>No. Rujukan Dasar :</label>
                <s:text name="pNoDasar"  value="" size="30" maxlength="30" id="pNoDasar" class="pNoDasar" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Daerah :</label>
                <s:select name="pKodCaw" id="pKodCaw" style="width:175px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>ID Hakmilik :</label>
                <s:text name="pIdHakmilik" value="" size="30" maxlength="1000" id="pIdHakmilik" class="pIdHakmilik" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                &nbsp;
            </p>
            <p align="center">
                <s:hidden name="darimana"/>
                <s:submit name="carianDasar" value="Cari" class="btn"/>&nbsp;
                <s:button class="btn" name="reset" value="Isi Semula" onclick="clearText('popup_carian_dasar');"/>&nbsp;
                <s:button name="" value="Tutup" class="btn" onclick="self.close();"/>
            </p>
        </fieldset>
    </div><br>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Maklumat Dasar</legend>
            <p align="center">
                <c:if test="${actionBean.darimana eq 'notis'}">
                    <s:button name="" value="Pilih" class="btn" onclick="pilihDasarNotis(this.form);" /> &nbsp;
                </c:if>
                <c:if test="${actionBean.darimana eq 'rekod'}">
                    <s:button name="" value="Pilih" class="btn" onclick="pilihDasarRekod(this.form);" /> &nbsp;
                </c:if>
                <c:if test="${actionBean.darimana eq 'warta'}">
                    <s:button name="" value="Pilih" class="btn" onclick="pilihDasarWarta(this.form);" /> &nbsp;
                </c:if>
            </p>
            <center>
                <display:table name="${actionBean.senaraiDTCpopup}" class="tablecloth" id="row" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/notis?carianDasar">
                    <display:column><s:radio name="radioKod" id="radio${row_rowNum}" value="${row.idDasar}" class="radio" onmouseover="this.style.cursor='pointer';"/></display:column>
                    <display:column title="Bil.">${row_rowNum}</display:column>
                    <display:column title="No. Rujukan Dasar" property="idDasar"/>
                    <display:column title="Daerah" property="cawangan.name"/>
                    <display:column title="Senarai Hakmilik">
                        <c:if test="${fn:length(row.senaraiHakmilik) eq 0}"><em>Tiada</em></c:if>
                        <c:if test="${fn:length(row.senaraiHakmilik) ne 0}">
                            <c:forEach items="${row.senaraiHakmilik}" var="senarai">
                                <a href="#" onclick="popupHakmilik('${senarai.hakmilik.idHakmilik}');return false;">${senarai.hakmilik.idHakmilik}</a><br>
                            </c:forEach>
                        </c:if>
                    </display:column>
                    <display:column title="Status Semasa">
                        <c:set var="N6A" value=""/>
                        <c:set var="N8A" value=""/>
                        <c:set var="SP" value=""/>
                        <c:set var="NG" value=""/>
                        <c:set var="WR" value=""/>
                        <c:set var="T" value=""/>
                        <c:if test="${fn:length(row.senaraiHakmilik) ne 0}">
                            <c:forEach items="${row.senaraiHakmilik}" var="senaraiHM">
                                <c:if test="${fn:length(senaraiHM.senaraiNotis) eq 0}"><c:set var="T" value="Tiada"/></c:if>
                                <c:if test="${fn:length(senaraiHM.senaraiNotis) ne 0}">
                                    <c:forEach items="${senaraiHM.senaraiNotis}" var="senaraiNotis">
                                        <c:if test="${senaraiNotis.notis.kod eq 'N6A'}"><c:set var="N6A" value="${senaraiNotis.notis.nama}"/></c:if>
                                        <c:if test="${senaraiNotis.notis.kod eq 'N8A'}"><c:set var="N8A" value="${senaraiNotis.notis.nama}"/></c:if>
                                        <c:if test="${senaraiNotis.notis.kod eq 'SP'}"><c:set var="SP" value="${senaraiNotis.notis.nama}"/></c:if>
                                        <c:if test="${senaraiNotis.notis.kod eq 'NG'}"><c:set var="NG" value="${senaraiNotis.notis.nama}"/></c:if>
                                        <c:if test="${senaraiNotis.notis.kod eq 'WR'}"><c:set var="WR" value="${senaraiNotis.notis.nama}"/></c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${SP ne ''}"><c:out value="${SP}"/><br></c:if>
                        <c:if test="${N6A ne ''}"><c:out value="${N6A}"/><br></c:if>
                        <c:if test="${NG ne ''}"><c:out value="${NG}"/><br></c:if>
                        <c:if test="${N8A ne ''}"><c:out value="${N8A}"/><br></c:if>
                        <c:if test="${WR ne ''}"><c:out value="${WR}"/><br></c:if>
                        <c:if test="${T ne ''}"><em><c:out value="${T}"/></em><br></c:if>
                    </display:column>
                    <display:column title="Tarikh Diwujudkan">
                        <fmt:formatDate value="${row.tarikhDasar}" pattern="dd/MM/yyyy"/>
                    </display:column>
                </display:table>
            </center>
        </fieldset>
    </div>
</s:form>