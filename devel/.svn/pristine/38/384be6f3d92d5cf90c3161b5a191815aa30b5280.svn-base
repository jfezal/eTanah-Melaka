<%-- 
    Document   : kumpulan_akaun
    Created on : Aug 15, 2013, 12:02:17 PM
    Author     : haqqiem
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        dialogInit('Carian Hakmilik');
        var negeri = "${actionBean.kodNegeri}";
        if (negeri == "melaka") {
            $('#akaun').focus();
        } else {
            $('#hakmilik').focus();
        }
        var l = ${fn:length(actionBean.listAkaun)};
        for(var i=0; i<l; i++){
            $('#idRow'+i).val('');
        }
    });
    function zeroPad(num, count)
    {
        var numZeropad = num + '';
        while (numZeropad.length < count) {
            numZeropad = "0" + numZeropad;
        }
        return numZeropad;
        $("#noLot").val(numZeropad);
    }
    function change() {
        var a = $("#kod").val();
        var b = a.toUpperCase();

        $("#kod").val(b);
    }
    function filterDaerah(kodDaerah, frm) {
        var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaun?penyukatanBPM&daerah=' + kodDaerah;
        frm.action = url;
        frm.submit();

    }
    function filterBPM(kodBPM, frm) {
        var daerah = $('#daerah').val();
        var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaun?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
        frm.action = url;
        frm.submit();
    }
</script>
<script type="text/javascript">
    function popup(id) {
        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?infoPembayar&idPegang=" + id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function refresh1(v) {
        var url = '${pageContext.request.contextPath}/common/carian_hakmilik?papar&idHakmilik=' + v;
        $.get(url,
                function(data) {
                    $('#daerah').html(data);
                }, 'html');
    }

    function doSubmit(e, f, g, h) {
        var q = $(f).formSerialize();
        f.action = f.action + '?' + e + '&idHakmilik=' + g + '&noAkaun=' + h + '&popup&' + q;
        f.submit();
    }

    function completeId(id) {
        var l = id.length;
        if (l > 0) {
            var lengthId = 8;
            var i = "";
            for (var x = 0; x < (lengthId - l); x++) {
                i = i + '0';
            }
            var noHakmilik = i + id;
            $("#noHakmilik").val(noHakmilik);
        }
    }
    
    function cubaTest(f,rownum){
        var c = document.getElementById("kandungan" + rownum);
        if(c.checked){
            $("#idRow"+rownum).val(f);
        }else{$("#idRow"+rownum).val('');};
    }
    
    function updateField(){
        var m = document.getElementById("akaunid");
        var l = ${fn:length(actionBean.listAkaun)};
        
        for (var i = 0; i < l; i++){
            var d = document.getElementById("idRow"+i);
            if(d.value != ''){
                if(m.value == ''){m.value = d.value;}
                else{m.value = m.value+','+d.value;}
            }
        }
        $("#akaunid").val(m.value);
        if(m.value == ''){
            alert('Sila pilih Akaun.');
            return false;
        }else{
            return true;}        
    }
    
    function selectAll(a){
        var l = ${fn:length(actionBean.listAkaun)};
        for (var i = 0; i < l; i++){
            var c = document.getElementById("kandungan" + i);
            var hm = document.getElementById("idhm" + i);
            if (c == null) break;
            c.checked = a.checked;
            cubaTest(hm.value,i);
        }
    }

    function removeAkaun(id) {
        if (confirm("Adakah pasti untuk hapus Nombor Akaun " + id + " ?")) {
            var NoAkaun = $("#noAkaun").val();
            var IdHakmilik = $("#idHakmilik").val();
            var Daerah = $("#daerah").val();
            var BandarPekanMukim = $("#bandarPekanMukim").val();
            var Seksyen = $("#seksyen").val();
            var Status = $("#kodStatus").val();
            var KodHakmilik = $("#kod").val();
            var NoHakmilik = $("#noHakmilik").val();
            var KodLot = $("#kodLot").val();
            var NoLot = $("#noLot").val();
            var Pembayar = $("#pembayar").val();
            var Pemilik = $("#pemilik").val();
            var KategoriTanah = $("#kategoriTanah").val();
            var AmaunDari = $("#amaunDari").val();
            var DariTahun = $("#dariTahun").val();
            var HinggaTahun = $("#hinggaTahun").val();
            var url = '${pageContext.request.contextPath}/hasil/kumpulan_akaun?delete&idHakmilikRemove=' + id;
            url += '&noAkaun=' + NoAkaun;
//            url += '&idHakmilik=' + IdHakmilik;
            url += '&daerah=' + Daerah;
            url += '&bandarPekanMukim=' + BandarPekanMukim;
            url += '&seksyen=' + Seksyen;
            url += '&kodStatus=' + Status;
            url += '&kod=' + KodHakmilik;
            url += '&noHakmilik=' + NoHakmilik;
            url += '&kodLot=' + KodLot;
            url += '&noLot=' + NoLot;
            url += '&pembayar=' + Pembayar;
            url += '&pemilik=' + Pemilik;
            url += '&kategoriTanah=' + KategoriTanah;
            url += '&amaunDari=' + AmaunDari;
            url += '&dariTahun=' + DariTahun;
            url += '&hinggaTahun=' + HinggaTahun;
            $.get(url,
                    function(data) {
                        $('#refreshPage').html(data);
                    }, 'html');
        }
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<div id="refreshPage">
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <c:if test="${actionBean.flagKumpulan eq 'tag'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kumpulan TAG Akaun</font>
                    </c:if>
                    <c:if test="${actionBean.flagKumpulan eq 'kumpulan'}">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pengurusan Kumpulan Hakmilik</font>
                    </c:if>
                </div>
            </td>
        </tr>
    </table></div>
    <s:form beanclass="etanah.view.stripes.hasil.KumpulanAkaunActionBean" id='kump_hakmilik'>
            <s:errors/>
            <s:messages/>
            <c:if test="${actionBean.flagKumpulan eq 'tag'}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Kumpulan</legend>
                        <s:hidden name="tagAkaun.idTag" id="tagId"/>
                        <p>
                            <label>ID Kumpulan :</label>
                            ${actionBean.tagAkaun.idTag}&nbsp;
                        </p>
                        <p>
                            <label>Nama Kumpulan :</label>
                            ${actionBean.tagAkaun.nama}&nbsp;
                        </p>
                        <p>
                            <label>Catatan :</label>
                            ${actionBean.tagAkaun.catatan}&nbsp;
                        </p>
                    </fieldset>
                </div>
            </c:if>
            <c:if test="${actionBean.flagKumpulan eq 'kumpulan'}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>Maklumat Kumpulan</legend>
                        <s:hidden name="kumpulanAkaun.idKumpulan" id="idKump"/>
                        <p>
                            <label>ID Kumpulan :</label>
                            ${actionBean.kumpulanAkaun.idKumpulan}&nbsp;
                        </p>
                        <p>
                            <label>Nama Kumpulan :</label>
                            ${actionBean.kumpulanAkaun.catatan}&nbsp;
                        </p>
                        <p>
                            <label>Catatan :</label>
                            ${actionBean.kumpulanAkaun.cawangan.name}&nbsp;
                        </p>
                    </fieldset>
                </div>
            </c:if>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Carian Akaun</legend>
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <p>
                        <label> No Akaun :</label>
                        <s:text id="akaun" name="noAkaun" maxlength="20" size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                </c:if>

                <p>
                    <label >ID Hakmilik :</label>
                    <s:text id="hakmilik" name="idHakmilik"  size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label>  Daerah :</label>
                    <s:select name="daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value,this.form);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                            <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>

                <p>
                    <label>  Bandar/Pekan/Mukim :</label>
                    <s:select name="bandarPekanMukim" id="bandarPekanMukim" style="width:210px;" onchange="filterBPM(this.value,this.form);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <c:forEach items="${actionBean.senaraiBPM}" var="i" >
                            <s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>

                <p>
                    <label>  Seksyen :</label>
                    <s:select name="seksyen" id="seksyen" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <c:forEach items="${actionBean.senaraiKodSeksyen}" var="i" >
                            <s:option value="${i.kod}">${i.nama}</s:option>
                        </c:forEach>
                    </s:select>
                </p>

                <p>
                    <label>  Status Hakmilik :</label>
                    <s:select name="kodStatusHakmilik" id='kodStatus' style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStatusHakmilik}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>

                <p>
                    <label >Jenis Hakmilik :</label>
                    <s:text name="kodHakmilik" id="kod" size="31" onkeyup="javaScript:change();" />
                </p>

                <p>
                    <label >No Hakmilik :</label>
                    <s:text name="noHakmilik"  size="31" id="noHakmilik" maxlength="8" /><%-- onblur="completeId(this.value)"/>--%>
                </p>

                <p>
                    <label >Kod Lot :</label>
                    <s:select name="lot" id="kodLot" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>

                <p>
                    <label >No Lot/PT :</label>
                    <s:text name="noLot" id="noLot"  maxlength="15" size="31"/><%-- onblur="zeroPad(this.value,7);"/>--%>
                </p>

                <p>
                    <label> Nama Pembayar :</label>
                    <s:text name="namaPembayar" id="pembayar"  maxlength="50" size="31" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>

                <p>
                    <label> Nama Pemilik :</label>
                    <s:text name="namaPemilik" id="pemilik" maxlength="50" size="31" onkeyup="this.value=this.value.toUpperCase();" />
                </p>

                <p>
                    <label>Kategori Tanah :</label>
                    <s:select id="kategoriTanah" name="kategoriTanah" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>

                <p>
                    <label>Amaun Patut Kutip(RM) :</label>
                    Dari <s:text id="amaunDari" name="amaunDari"  size="8" maxlength="10" onkeyup="validateMoney(this,this.value);"/> <%--Hingga <s:text name="amaunHingga" id="" size="8" maxlength="10" onkeyup="validateMoney(this,this.value);"/>--%>
                </p>

                <p>
                    <label> Tahun Tunggakan :</label>
                    Dari <s:text id="dariTahun" name="dariTahun" size="8" maxlength="4" onkeyup="validateNumber(this,this.value);" onchange="return validateYearLn(this.id);"/> Hingga <s:text name="hinggaTahun" id="hinggaTahun" size="8" maxlength="4" onkeyup="validateNumber(this,this.value);" onchange="return validateYearLn(this.id);"/> <%--Tahun--%>
                </p><br>

                <div align="right">
                    <s:submit name="search" value="Cari" class="btn" />
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('kump_hakmilik');" />
                </div>
            </fieldset>
        </div>
        <c:if test="${actionBean.flag}">
            <br>
            <s:text name="strAkaun" id="akaunid" style="display:none;"/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Senarai Akaun</legend>

                    <c:set var="row_num" value="${actionBean.__pg_start}"/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listAkaun}"
                            pagesize="10" cellpadding="0" cellspacing="0"
                            requestURI="kumpulan_akaun" id="line"
                            sort="external" size="${actionBean.__pg_total_records}" partialList="true">
                            <c:set var="row_num" value="${row_num+1}"/>

                            <display:column title="Bil" sortable="true" > <div align="center">${row_num}</div></display:column>
                            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                                <display:column property="noAkaun" title="No Akaun"  />
                            </c:if>
                            <display:column title="ID Hakmilik"  >${line.hakmilik.idHakmilik}
                                <s:text name="listAkaun[${line_rowNum - 1}].noAkaun" style="display:none"  id="idhm${line_rowNum - 1}" />
                            </display:column>
                            <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"  />
                            <display:column property="hakmilik.daerah.nama" title="Daerah"  />
                            <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah" />
                            <display:column title="Jenis">
                                <div align="center">${line.hakmilik.kodHakmilik.kod}</div>
                            </display:column>
                            <display:column property="pemegang.nama" title="Nama Pembayar"  />
                            <display:column title="Nama Pemilik Tanah (No Pengenalan)" >
                                <ol>                               
                                    <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="a">
                                        <c:if test="${(senarai.jenis.kod eq 'PM') and senarai.aktif eq 'Y'}">
                                            <li>
                                                <c:out value="${senarai.pihak.nama}" />  <c:if test="${senarai.pihak.noPengenalan ne null}">(<c:out value="${senarai.pihak.noPengenalan}" />)</c:if>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ol>
                            </display:column>
                            <display:column property="baki" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, #,###,##0.00}" style="width:100;text-align:right;"/>
                            <display:column property="hakmilik.kodStatusHakmilik.nama" title="Status Hakmilik"  style="width:100;text-align:right"/>
                            <c:set value="0" var="bilTahun"/>
                            <c:forEach items="${line.senaraiTransaksiDebit}" var="transaksi">
                                <c:if test="${bilTahun eq 0}">
                                    <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                                </c:if>
                                <c:if test="${bilTahun > 0}">
                                    <c:set value="${transaksi.untukTahun}" var="maxTahun"/>
                                </c:if>
                                <c:if test="${(transaksi.kodTransaksi.kod eq '61402' or transaksi.kodTransaksi.kod eq '76152') and transaksi.untukTahun ne actionBean.currYear}">
                                    <c:set value="${bilTahun + 1}" var="bilTahun"/>
                                </c:if>
                            </c:forEach>
                            <display:column title="Tahun Tunggakan" style="text-align:right" >
                                <c:out value="${minTahun}"/><c:if test="${bilTahun > 1}"> - <c:out value="${maxTahun}"/></c:if>
                            </display:column>
                            <%--display:column title="Hapus" style="text-align:center">
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif' onclick="removeAkaun('${line.hakmilik.idHakmilik}');" />
                            </display:column--%>
                            <display:column title="Pilih <br><input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />" style="text-align:center">
                                <s:checkbox name="" onclick="cubaTest('${line.noAkaun}','${line_rowNum - 1}')" id="kandungan${line_rowNum - 1}"/>
                                <s:text name="" value="${line.noAkaun}" id="idRow${line_rowNum - 1}" style="display:none;"/>
                            </display:column>
                        </display:table>
                    </div>
                    <div align="right"><s:checkbox name="pilihSemua"/><em><font size="2" color="black"><b>Pilih Semua Akaun.</b></font></em> &nbsp;&nbsp;<br></div>
                    <div align="right"><s:submit name="Step6" value="Kemaskini Senarai" class="longBtn" onclick="return updateField();"/>&nbsp;&nbsp;&nbsp;</div>
                    <br>
                </fieldset>
            </div>

            <div align="center"><table style="width:99.2%" bgcolor="green">
                <tr>
                    <td align="right">
                        <c:if test="${actionBean.flagKumpulan eq 'tag'}">
                            <s:submit name="Step2" value="Simpan" class="btn"/>&nbsp;
                            <s:submit name="Step3" value="Menu Utama" class="btn"/>&nbsp;
                        </c:if>
                        <c:if test="${actionBean.flagKumpulan eq 'kumpulan'}">
                            <s:submit name="Step4" value="Simpan" class="btn"/>&nbsp;
                            <s:submit name="Step5" value="Menu Utama" class="btn"/>&nbsp;
                        </c:if>
                    </td>
                </tr>
            </table></div>
        </c:if>
    </s:form>
</div>
