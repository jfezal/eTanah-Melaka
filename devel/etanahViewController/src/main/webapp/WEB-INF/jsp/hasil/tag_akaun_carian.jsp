<%-- 
    Document   : tag_akaun_carian
    Created on : Mar 25, 2011, 10:46:21 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
            $('#hakmilik').hide();
            $('#kumpulan').hide();
            $('#akaun').hide();
            changeJenisCarian($('#carian').val());
    });

    function changeJenisCarian(f){
        if (f == 'KMP'){
            $('#kumpulan').show();
            $('#hakmilik').hide();
            $('#akaun').hide();
            $('#ak').val("");
            $('#hm').val("")
        }
        else if (f == 'HMK'){
            $('#hakmilik').show();
            $('#kumpulan').hide();
            $('#akaun').hide();
            $('#kump').val("");
            $('#ak').val("");
        }
        else if (f == 'AKN'){
            $('#akaun').show();
            $('#hakmilik').hide();
            $('#kumpulan').hide();
            $('#kump').val("");
            $('#hm').val("");
        }
        else{
            $('#hakmilik').hide();
            $('#kumpulan').hide();
            $('#akaun').hide();
            $('#kump').val("");
            $('#ak').val("");
            $('#hm').val("");
        }
    }

    function kumpulanBaru(f){
        f.action = '${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG_baru';
        f.submit();
    }

    function paparDetail(idKumpulan){
        var f = document.kumpulan;
        var form = $(f).formSerialize();
        var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG_baru?'+form+'&tagAkaun.idTag='+idKumpulan;
        f.action = url;
        f.submit();
    }

    function hapusKumpulanAkaun(idKumpulan){
        if(confirm("Anda pasti untuk hapuskan ID Kumpulan :"+idKumpulan+" ?")){
            var f = document.kumpulan;
            var form = $(f).formSerialize();
            var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG?hapusKumpulan&'+form+'&idKumpulanAkaun='+idKumpulan;
            f.action = url;
            f.submit();
        }
    }

    function hapusKumpulanAhli(idHakmilik, idAhli){
        if(confirm("Anda pasti untuk hapuskan ID Hakmilik :"+idHakmilik+" ?")){
            var f = document.kumpulan;
            var form = $(f).formSerialize();
            var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaunTAG?hapusAhli&'+form+'&idAhli='+idAhli;
            f.action = url;
            f.submit();
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div id="cr">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr>
                <td width="100%" height="20" >
                    <div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Carian Kumpulan</font>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <s:form name="kumpulan" beanclass="etanah.view.stripes.hasil.KumpulanAkaunTAGActionBean" id="carian_kumpulan">
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>Jenis Carian :</label>
                <s:select name="carian" onchange="changeJenisCarian(this.value)" id="carian">
                    <s:option value="0">Sila Pilih...</s:option>
                    <s:option value="KMP">ID Kumpulan</s:option>
                    <s:option value="HMK">ID Hakmilik</s:option>
                    <c:if test="${actionBean.negeri eq 'melaka'}">
                        <s:option value="AKN">Nombor Akaun</s:option>
                    </c:if>
                </s:select>
            </p>
            <div id="kumpulan">
                <p>
                    <label>ID Kumpulan :</label>
                    <s:text name="idKumpulan" onkeyup="this.value=this.value.toUpperCase();" id="kump"/>
                </p>
                <p>
                    <label>Nama Kumpulan :</label>
                    <s:text name="namaKumpulan" onkeyup="this.value=this.value.toUpperCase();" id="kump"/>
                </p>
                <p>
                    <label>Daerah :</label>
                    <s:select name="cawangan" id="kump">
                        <s:option label="Sila Pilih..." value="" />
                        <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod" sort="name"/>
                    </s:select>
                </p>
            </div>
            <p id="hakmilik">
                <label>ID Hakmilik :</label>
                <s:text name="idHakmilik" onkeyup="this.value=this.value.toUpperCase();" id="hm"/>
            </p>
            <p id="akaun">
                <label>Nombor Akaun :</label>
                <s:text name="noAkaun" onkeyup="this.value=this.value.toUpperCase();" id="ak"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:submit name="carianKumpulan" value="Cari" class="btn"/>
                <s:button name="" value="Isi Semula" class="btn" onclick="clearText('carian_kumpulan');"/>
            </p>
            <br>
        </fieldset>
        </div>
        <br>
        <c:if test="${actionBean.showPanelGroup}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <p align="center">
                       <s:submit name="" value="Tambah Kumpulan" class="longbtn" onclick="kumpulanBaru(this.form);" /> &nbsp;
                    </p>
                    <div align="left">
                       <font size="2" color="black">Petunjuk :</font>
                        <p>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 width="30" height="30" /> - <font size="2" color="black">Papar</font>
                            &nbsp;<b>|</b>&nbsp;
                            <img src="${pageContext.request.contextPath}/images/not_ok.gif"/> - <font size="2" color="black">Hapus</font>
                            &nbsp;<b>|</b>&nbsp;
                            <img src="${pageContext.request.contextPath}/images/lock.gif"/> - <font size="2" color="black">Tidak Dibenarkan Hapus</font>
                        </p>
                    </div>
                    <display:table class="tablecloth" name="${actionBean.senaraiKumpulan}" pagesize="10" cellpadding="0" cellspacing="0" id="line" requestURI="/hasil/kumpulan_akaunTAG?carianKumpulan" excludedParams="hapusKumpulan">
                        <display:column title="Bil.">
                            <div align="center">${line_rowNum}.</div>
                        </display:column>
                        <display:column property="idTag" title="ID Kumpulan"/>
                        <display:column property="nama" title="Nama Kumpulan"/>
                        <display:column property="cawangan.name" title="Daerah"/>
                        <display:column property="catatan" title="Catatan"/>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column property="infoAudit.dimasukOleh.nama" title="Didaftar Oleh"/>
                        <display:column title="Bil. Hakmilik" style="text-align:center">
                            <c:set value="${fn:length(line.senaraiAhli)}" var="count"/>
                            <c:out value="${count}"/> Hakmilik
                        </display:column>
                        <%--<display:column title="Tindakan">
                            <s:button name="papar" class="btn" value="Papar" onclick="paparDetail('${line.idTag}',this.form)"/>
                        </display:column>--%>
                        <display:column title="Tindakan" style="width:90px;">
                            <div align="center">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="paparDetail('${line.idTag}')" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar kumpulan :${line.nama}"/>
                            <c:if test="${actionBean.peng.kodCawangan.kod eq line.cawangan.kod}">
                                &nbsp;<b>|</b>&nbsp;
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="hapusKumpulanAkaun('${line.idTag}');"
                                    onmouseover="this.style.cursor='pointer';" title="Hapus kumpulan :${line.nama}"/>
                            </c:if>
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.showPanelSingle}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <p align="center">
                       <s:button name="createKod" value="Tambah Kumpulan" class="longbtn" onclick="kumpulanBaru(this.form);" /> &nbsp;
                    </p>
                    <div align="left">
                       <font size="2" color="black">Petunjuk :</font>
                        <p>
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 width="30" height="30" /> - <font size="2" color="black">Papar</font>
                            &nbsp;<b>|</b>&nbsp;
                            <img src="${pageContext.request.contextPath}/images/not_ok.gif"/> - <font size="2" color="black">Hapus</font>
                            &nbsp;<b>|</b>&nbsp;
                            <img src="${pageContext.request.contextPath}/images/lock.gif"/> - <font size="2" color="black">Tidak Dibenarkan Hapus</font>
                        </p>
                    </div>
                    <display:table class="tablecloth" name="${actionBean.senaraiAhli}" id="line">
                        <display:column title="Bil.">
                            <div align="center">${line_rowNum}.</div>
                        </display:column>
                        <c:if test="${actionBean.negeri eq 'melaka'}">
                            <display:column property="akaun.noAkaun" title="No. Akaun"/>
                        </c:if>
                        <display:column property="akaun.hakmilik.idHakmilik" title="ID Hakmilik"/>
                        <display:column property="tagAkaun.idTag" title="ID Kumpulan"/>
                        <display:column property="tagAkaun.nama" title="Nama Kumpulan"/>
                        <display:column property="cawangan.name" title="Daerah"/>
                        <%--<display:column property="catatan" title="Catatan"/>--%>
                        <display:column property="infoAudit.tarikhMasuk" title="Tarikh Daftar" format="{0,date,dd/MM/yyyy hh:mm:ss a}"/>
                        <display:column title="Tindakan">
                            <div align="center">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="paparDetail('${line.tagAkaun.idTag}')" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar kumpulan"/>
                            <c:choose>
                                <c:when test="${actionBean.peng.kodCawangan.kod eq line.cawangan.kod}">
                                    &nbsp;<b>|</b>&nbsp;
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="hapusKumpulanAhli('${line.akaun.hakmilik.idHakmilik}','${line.idAhli}');"
                                     onmouseover="this.style.cursor='pointer';" title="Hapus Hakmilik :${line.akaun.hakmilik.idHakmilik}"/>
                                </c:when>
                                <c:otherwise>
                                    &nbsp;<b>|</b>&nbsp;
                                    <img alt='Tidak Dibenarkan Hapus' src='${pageContext.request.contextPath}/images/lock.gif' />
                                </c:otherwise>
                            </c:choose>
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    </s:form>
</div>
