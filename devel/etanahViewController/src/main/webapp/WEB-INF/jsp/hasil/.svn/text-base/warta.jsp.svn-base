<%-- 
    Document   : warta
    Created on : Oct 31, 2011, 3:15:20 PM
    Author     : mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function checkNullity(){
        var result = true;
        if($('#dasar').val() == null || $('#dasar').val() == ""){
            alert("Sila isi ID Dasar terlebih dahulu.");
            result = false;
            $('#dasar').focus();
        }
        return result;
    }

function doSubmitWarta(){
    var len = $('.warta_cb').length;
    var param = '';
    var f = document.kump_warta;
    var form = $(f).formSerialize();
    
    for(var i=1;i<=len;i++){
        var cbox = $('#cb_warta_'+i).is(":checked");
        if(cbox){
            param = param+'&idDasarHM='+$('#cb_warta_'+i).val();
        }        
    }

    if(param == ''){
        alert("Tiada Hakmilik yang dipilih untuk disimpan.");
        return;
    }

     var url = '${pageContext.request.contextPath}/hasil/warta?simpan&'+form+param;

     f.action = url;
     f.submit();
}

function janaDokumen(){
    var dasar = $('#dasar').val();
    var warta = $('.warta').val();

    var f = document.kump_warta;
    var form = $(f).formSerialize();

    var report = "";
    if(warta == "NG"){
        report = "HSLDrafWarta_NG6A_NS_dsr.rdf";
    }else if(warta == "N8A"){
        report = "HSLSuratAkuanBerkanunPdsr.rdf";
    }

     window.open("${pageContext.request.contextPath}/reportGeneratorServlet?"+form+"&reportName="+report+"&report_p_id_dasar="+dasar, "eTanah",
                    "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1050,height=700");
}

function popupDasar(){
        var url = '${pageContext.request.contextPath}/hasil/notis?popupCarianDasar&darimana=warta';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=1");
    }

function refreshBase(dasar){
        var q = $('#form1').serialize();
        var url = document.kump_warta.action + '?reloadBaseWarta&idDasar='+dasar;
        window.location = url+q;
    }
</script>
<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Warta Berkumpulan</font>
                </div>
            </td>
        </tr>
    </table>
</div>
<s:form beanclass="etanah.view.stripes.hasil.WartaActionBean" id="warta" name="kump_warta">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <div class="instr-fieldset">
                <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut
            </div>&nbsp;
            <p>
                <label><em>*</em> ID Dasar :</label>
                <s:text id="dasar" name="idDasar" size="30" onblur="this.value = this.value.toUpperCase();"/>
                <img alt='Klik Untuk Cari Dasar' border='0' height="16" width="16" src='${pageContext.request.contextPath}/pub/images/search-icon.png' class='rem'
                     id='carianDasar' onclick='popupDasar();return false;' onmouseover="this.style.cursor='pointer';" title="Carian Dasar.">
            </p>
            <p>
                <label>Jenis Warta :</label>
                <s:select class="warta" name="jenisWarta" style="width:235px;">
                    <%--<s:option value="HHHH">Sila Pilih...</s:option>--%>
                    <s:options-collection collection="${actionBean.kodNotisWarta}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="search" value="Cari" class="btn" onclick="return checkNullity();"/>
                <s:button name="" value="Isi Semula" class="btn" onclick="clearText('warta');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <c:if test="${actionBean.flagSearch}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <c:set var="displayJana" value="false"/>
                    <display:table class="tablecloth" name="${actionBean.senaraiDTCH}" cellpadding="0" cellspacing="0" id="line">                        
                        <display:column title="Bil.">
                            <div align="center">${line_rowNum}.</div>
                        </display:column>
                        <%--<display:column property="idDasarHakmilik" title="id"/>--%>
                        <display:column property="noRujukan" title="No. Fail"/>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="cawangan.name" title="Daerah"/>                        
                        <display:column title="Kumpulan" style="width:40;">

                        <c:set var="kumpulan" value=""/>
                        <c:forEach var="notis" items="${line.senaraiNotis}">
                            <c:if test="${notis.notis.kod eq actionBean.jenisWarta}">
                                <c:if test="${notis.kumpulanWarta eq 'Y'}">
                                    <c:set var="kumpulan" value="checked"/>
                                    <c:set var="displayJana" value="true"/>
                                </c:if>
                            </c:if>
                        </c:forEach>
                            <div align="center">
                                <%--<s:checkbox name="checkbox" id="cb_warta_${line_rowNum}" value="${line.idDasarHakmilik}"/>--%>
                                <input type="checkbox" ${kumpulan} id="cb_warta_${line_rowNum}" value="${line.idDasarHakmilik}" class="warta_cb"/>
                            </div>
                        </display:column>
                    </display:table>
                    <br>
                    <table border="0" width="97%">
                        <tr>
                            <td align="right" width="64%">
                                <c:if test="${actionBean.flagWarta or displayJana}">
                                    <s:button name="" value="Jana Dokumen Warta" class="longbtn" onclick="janaDokumen();"/>
                                </c:if>
                            </td>
                            <td align="left" width="33%">
                                <s:button name="simpan" value="Simpan" class="btn" style="${actionBean.rekod}" onclick="doSubmitWarta();"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
    </c:if>
</s:form>
