<%-- 
    Document   : rekod_penghantaran_pihak
    Created on : 22 Julai 2010, 5:23:50 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function closed(){
        self.opener.refreshRekod($("#dasar").val(), $("#status").val());
        self.close();
    }

    function validate(){
        var count = ${fn:length(actionBean.senaraiNotis)};
        for(var i = 0; i <= count-1; i++){
            if($("#kodStatus"+i).val() == "-"){
                alert('Sila pilih " Status Penyampaian " terlebih dahulu.');
                $("#kodStatus"+i).focus();
                return false;
            }
            if($("#kodHantar"+i).val() == "-"){
                alert('Sila pilih " Cara Penghantaran " terlebih dahulu.');
                $("#kodHantar"+i).focus();
                return false;
            }
            if($("#tarikhHantar"+i).val() == "-" || $("#tarikhHantar"+i).val() == ""){
                alert('Sila masukkan " Tarikh Hantar " terlebih dahulu.');
                $("#tarikhHantar"+i).focus();
                return false;
            }
            if($("#tarikhTerima"+i).val() == "-" || $("#tarikhTerima"+i).val() == ""){
                $("#tarikhTerima"+i).val("-");
            }
            if($("#catatan"+i).val() == ""){
                $("#catatan"+i).val("-");
            }
        }
        return true;
    }

    function changeOnFokus(id){
        if($("#"+id).val() == "-"){
            $("#"+id).val("");
        }
    }

    function dateValidation(id,value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function muatNaikForm(notis) {
        var f = document.rekodHantar;
        var urlTmp = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?simpanRefresh';
        f.action = urlTmp;
        f.submit();

        var idDasar = $("#dasar").val();
        var idhakmilik = $("#hakmilik").val();
        var kodStatus = $("#status").val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?popupUpload&idNotis='+notis+'&noDasar='+idDasar+'&idHakmilik='+idhakmilik+'&kodStatus='+kodStatus;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(idNotis) {
        var f = document.rekodHantar;
        var urlTmp = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?simpanRefresh';
        f.action = urlTmp;
        f.submit();
        <%--$.get(urlTmp,
        function(data){
            if(data == '0'){
                alert("Terdapat masalah teknikal.");
            }else{--%>
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var hakmilik = '${actionBean.hakmilik.idHakmilik}';
                var dasar = '${actionBean.noDasar}';
                var status = '${actionBean.kodStatus}';
                var url = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?popupScan&idNotis=' + idNotis+'&idHakmilik='+hakmilik+'&noDasar='+dasar+'&kodStatus='+status;
                window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
            <%--}
        });--%>
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function doReloadPagePihak(hakmilik,dasar,status){
        var f = document.rekodHantar;
        var url = "${pageContext.request.contextPath}/hasil/rekod_penghantaran2?rekodHantar&idHakmilik="+hakmilik+'&noDasar='+dasar+'&kodStatus='+status;
        f.action = url;
        f.submit();
    }

    <%--function reloadUpload(idDasar, idhakmilik, kodStatus){
        var url = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?reloadPopupUpload&noDasar='+idDasar+'&kodStatus='+kodStatus+'&idHakmilik='+idhakmilik;
        var url = '${pageContext.request.contextPath}/hasil/rekod_penghantaran2?rekodHantar&noDasar='+idDasar+'&kodStatus='+kodStatus+'&idHakmilik='+idhakmilik;
        $.get(url,
        function(data){
            $('#rekod_hantar2').html(data);
        }, 'html');
    }--%>
</script>
<div id="rekod_kemasukan">
<s:form id="rekod_hantar2" beanclass="etanah.view.stripes.hasil.RekodPenghantaran2ActionBean" name="rekodHantar">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Hakmilik </legend>
            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label>No. Akaun :</label>
                    ${actionBean.hakmilik.akaunCukai.noAkaun}&nbsp;
                </p>
            </c:if>
            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}&nbsp;
                <s:hidden id="dasar" name="noDasar"/>
                <s:hidden id="hakmilik" name="idHakmilik"/>
                <s:hidden id="status" name="kodStatus"/>
            </p>
        </fieldset>
        <br>
    </div>
    <div class="subtitle displaytag">
        <fieldset class="aras1">
            <legend>Kemasukan Rekod Penghantaran</legend>
            <p class=instr>
                *<em>Petunjuk :</em>
            </p>
            <p class=instr>
                <em>H:</em> Tarikh Hantar
                <em>T:</em> Tarikh Terima
            </p>
                <font size="2" color="black"></font>
            <p>
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                     width="20" height="20" /> - <font size="1" color="black">Papar Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             width="20" height="20" /> - <font size="1" color="black">Muat Naik Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                             width="20" height="20" /> - <font size="1" color="black">Imbas Dokumen</font>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiNotis}" cellpadding="0" cellspacing="0" requestURI="/hasil/rekod_penghantaran2?rekodHantar" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="pihak.nama" title="Nama Pemilik" style="width:180px;"/>
                    <display:column title="Status Penyampaian">
                        <em>*</em>
                        <s:select name="statusSampai[${line_rowNum-1}]" id="kodStatus${line_rowNum-1}">
                            <s:option value="-">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod" sort="nama"/>
                        </s:select>
                    </display:column>
                    <display:column title="Cara Penghantaran" style="width:180px;">
                        <em>*</em>
                        <s:select name="caraHantar[${line_rowNum-1}]" id="kodHantar${line_rowNum-1}">
                            <s:option value="-">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </display:column>
                    <display:column title="Tarikh " style="width:170px;">
                        <p><em>*</em>H :<s:text id="tarikhHantar${line_rowNum-1}" class="datepicker" name="tarikhHantar[${line_rowNum-1}]" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id,this.value)" style="width:100px;"/></p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;T :<s:text id="tarikhTerima${line_rowNum-1}" class="datepicker" name="tarikhTerima[${line_rowNum-1}]" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id,this.value)" style="width:100px;"/></p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    </display:column>
                    <display:column  title="Catatan">
                        <s:textarea id="catatan${line_rowNum-1}" name="catatan[${line_rowNum-1}]" rows="5" cols="25" onblur="this.value=this.value.toUpperCase();" onfocus="changeOnFokus(this.id);"/>
                    </display:column>
                    <display:column title="Tindakan">
                        <p align="center">
                            <%--<c:if test="${line.dokumenBukti == null}">--%>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/> /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                            <%--</c:if>--%>
                            <c:if test="${line.dokumenBukti != null}">
                                /<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.dokumenBukti.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                    </display:column>
                </display:table>
            </div>
            <p align="right">
                <s:submit name="simpan" id="simpan" value="Simpan" class="btn" onclick="return validate();"/>&nbsp;
                <s:button name="" id="close" value="Tutup" class="btn" onclick="closed();"/>&nbsp;
            </p>

        </fieldset >
</div>
</s:form>
</div>
