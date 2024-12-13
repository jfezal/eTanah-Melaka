<%-- 
    Document   : NotisBorangBSek4
    Created on : 11-May-2011, 15:40:08
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function (data) {
                    $('#page_div', opener.document).html(data);

                    setTimeout(function () {
                        self.close();
                    }, 100);
                }, 'html');
    }

    function validate() {

        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++) {

            var nama = document.getElementById('namela' + i);
            var kod = document.getElementById('kodPenyampaian' + i);
            var kod2 = document.getElementById('kodPenghantaran' + i);
            var tarikh = document.getElementById('tarikhDihantar' + i);
            var tarikh2 = document.getElementById('tarikhTerima' + i);
            var catat = document.getElementById('catatanTerima' + i);
            if (nama.value == "") {
                alert("Sila Masukkan Nama Penghantar Notis");
                $('#namela' + i).focus();
                return false;
            }
            if (kod.value == "") {
                alert("Sila Pilih Status Penyampaian");
                $('#kodPenyampaian' + i).focus();
                return false;
            }
            if (kod2.value == "") {
                alert("Sila Pilih Cara Penghantaran");
                $('#kodPenghantaran' + i).focus();
                return false;
            }
            if (tarikh.value == "") {
                alert("Sila Pilih Tarikh Hantar");
                $('#tarikhDihantar' + i).focus();
                return false;
            }
            if (tarikh2.value == "") {
                alert("Sila Pilih Tarikh Terima");
                $('#tarikhTerima' + i).focus();
                return false;
            }
            if (catat.value == "") {
                alert("Sila Masukkan Catatan");
                $('#catatanTerima' + i).focus();
                return false;
            }
        }
        return true;
    }


    function muatNaikForm(notis) {
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/pengambilan/notis_borangB?popupUpload&idNotis=' + notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
   function detailPermohonan(v) {
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/pengambilan/senarai_permohonan?popupPermohonanDetail&iidPermohonan=' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600, left=" + left + ",top=" + top);
    }   
    function senaraiHakmilik(v) {
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height ) - (150 / 2);
        var url = '${pageContext.request.contextPath}/pengambilan/senarai_permohonan?popupPermohonanDetail&iidPermohonan=' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function addPenghantarNotis() {
        window.open("${pageContext.request.contextPath}/pengambilan/notis_borangB?popupPenghantarNotis", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function scan(notis) {
        var url = '${pageContext.request.contextPath}/pengambilan/notis_borangB?popupScan&idNotis=' + notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }
    function ajaxLink(link, update) {
        $.get(link, function (data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }


</script>

<s:form beanclass="etanah.view.pengambilan.utiliti.SenaraiPermohonanPengambilanActionBean" id="folder_div">

    <s:messages/>
    <s:errors/>&nbsp;

    <div  id="hakmilik_details">
        <fieldset class="aras1">

            <legend>
                Carian Permohonan
            </legend>
            <p><label>Id Permohonan</label> <s:text name="idMohon" size="40"/> </p>

            <p><label>&nbsp;</label>  <s:submit class="longbtn" value="cari" name="cari"/>&nbsp;
            </p>
            <br>



        </fieldset> 
        <fieldset class="aras1">
            <legend>
                Maklumat Permohonan Pengambilan
            </legend><br/>
            <div align="center">
<c:set var="row_num" value="${actionBean.__pg_start}"/>
 <div class="content" align="center"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.senaraiPermohonanPengambilan}"
                               id="line" pagesize="10" style="width:95%"
                               requestURI="${pageContext.request.contextPath}//pengambilan/senarai_permohonan?showForm"
                               requestURIcontext="true"
                               sort="external" size="${actionBean.__pg_total_records}" partialList="true">                    
                    <c:set var="row_num" value="${row_num+1}"/>              
                    <display:column title="Bil" class="bil${line_rowNum}">                        
                        ${row_num}
                    </display:column>
                    <display:column title="Permohonan Pengambilan"> 
                        <table class="tablecloth">
                                    <tr style="width: 100%">
                                        <th>Urusan</th> <th width="100">Id Permohonan</th><th width="310">Senarai Hakmilik Permohonan</th>
                                        <th width="100">Tarikh Permohonan</th><th>Tarikh Kelulusan Sek 4</th><th>Tarikh Warta Sek 4</th><th>Tarikh Tamat Tempoh Warta Sek 4</th><th>Status</th>
                                    </tr>
                                    <tr style="width: 100%">
                                        <th>SEKSYEN 4</th>
                                        <td><a href="#" onClick="detailPermohonan('${line.pengambilanSek4Form.idPermohonan}')">${line.pengambilanSek4Form.idPermohonan}</a></td>
                                        <td>${line.pengambilanSek4Form.senaraiHakmilik}
                                          <c:if test="${line.pengambilanSek4Form.urlhakmilik}">
                                                <a href="#" onClick="senaraiHakmilik('${line.pengambilanSek4Form.idPermohonan}')">...terperinci</a>
                                            </c:if>
                                        </td>
                                        <td>${line.pengambilanSek4Form.tarikhPermohonan}</td>
                                        <td>${line.pengambilanSek4Form.tarikhKelulusanSek4}</td>
                                        <td>${line.pengambilanSek4Form.tarikhWartaSek4}</td>
                                        <td>${line.pengambilanSek4Form.tarikhTamatTempohSek4}</td>
                                        <td></td>
                                    </tr>
                                </table>
                                        <c:if test="${line.recordSek8}">
                                            <table class="tablecloth">
                                    <tr style="width: 100%">
                                        <th>Urusan</th><th width="100">Id Permohonan</th><th width="310">Senarai Hakmilik Permohonan</th><th width="100">Tarikh Permohonan</th><th>Tarikh Borang D</th><th>Tarikh Borang H</th><th>Tarikh Borang K</th><th>Jumlah Hakmlik Sambungan</th><th>Status</th>
                                    </tr>
                                    <tr style="width: 100%">
                                       <th>SEKSYEN 8</th> 
                                        <td><a href="#" onClick="detailPermohonan('${line.pengambilanSek4Form.idPermohonan}')">${line.pengambilanSek8Form.idPermohonan}</a></td>
                                        <td>${line.pengambilanSek8Form.senaraiHakmilik}
                                        <c:if test="${line.pengambilanSek8Form.urlhakmilik}">
                                                <a href="" onClick="senaraiHakmilik('${line.pengambilanSek4Form.idPermohonan}')">...terperinci</a>
                                            </c:if></td>
                                        <td>${line.pengambilanSek8Form.tarikhPermohonan}</td>
                                        <td>${line.pengambilanSek8Form.tarikhBorangD}</td>
                                        <td>${line.pengambilanSek8Form.tarikhBorangH}</td>
                                        <td>${line.pengambilanSek8Form.tarikhBorangK}</td>
                                        <td>${line.pengambilanSek8Form.jumlahHakmilikSamb} ${line.pengambilanSek8Form.jumlahHakmilikSelesai}</td>
                                        <td></td>
                                    </tr>
                                </table>
                                        </c:if>
                    </display:column>
                </display:table>

<!--                <table class="tablecloth">
                    <tr style="width: 100%">
                        <th>Bil</th><th>Permohonan Pengambilan</th>
                    </tr>
                     <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.senaraiPermohonanPengambilan}" var="line">
                        <tr>
                            <td>${count}</td>
                            <td>
                                <table class="tablecloth">
                                    <tr style="width: 100%">
                                        <th>Urusan</th> <th width="100">Id Permohonan</th><th width="310">Senarai Hakmilik Permohonan</th>
                                        <th width="100">Tarikh Permohonan</th><th>Tarikh Kelulusan Sek 4</th><th>Tarikh Warta Sek 4</th><th>Tarikh Tamat Tempoh Warta Sek 4</th><th>Status</th>
                                    </tr>
                                    <tr style="width: 100%">
                                        <th>SEKSYEN 4</th>
                                        <td><a href="#" onClick="detailPermohonan('${line.pengambilanSek4Form.idPermohonan}')">${line.pengambilanSek4Form.idPermohonan}</a></td>
                                        <td>${line.pengambilanSek4Form.senaraiHakmilik}
                                          <c:if test="${line.pengambilanSek4Form.urlhakmilik}">
                                                <a href="#" onClick="senaraiHakmilik('${line.pengambilanSek4Form.idPermohonan}')">...terperinci</a>
                                            </c:if>
                                        </td>
                                        <td>${line.pengambilanSek4Form.tarikhPermohonan}</td>
                                        <td>${line.pengambilanSek4Form.tarikhKelulusanSek4}</td>
                                        <td>${line.pengambilanSek4Form.tarikhWartaSek4}</td>
                                        <td>${line.pengambilanSek4Form.tarikhTamatTempohSek4}</td>
                                        <td></td>
                                    </tr>
                                </table>
                                        <c:if test="${line.recordSek8}">
                                            <table class="tablecloth">
                                    <tr style="width: 100%">
                                        <th>Urusan</th><th width="100">Id Permohonan</th><th width="310">Senarai Hakmilik Permohonan</th><th width="100">Tarikh Permohonan</th><th>Tarikh Borang D</th><th>Tarikh Borang H</th><th>Tarikh Borang K</th><th>Jumlah Hakmlik Sambungan</th><th>Status</th>
                                    </tr>
                                    <tr style="width: 100%">
                                       <th>SEKSYEN 8</th> 
                                        <td><a href="#" onClick="detailPermohonan('${line.pengambilanSek4Form.idPermohonan}')">${line.pengambilanSek8Form.idPermohonan}</a></td>
                                        <td>${line.pengambilanSek8Form.senaraiHakmilik}
                                        <c:if test="${line.pengambilanSek8Form.urlhakmilik}">
                                                <a href="" onClick="senaraiHakmilik('${line.pengambilanSek4Form.idPermohonan}')">...terperinci</a>
                                            </c:if></td>
                                        <td>${line.pengambilanSek8Form.tarikhPermohonan}</td>
                                        <td>${line.pengambilanSek8Form.tarikhBorangD}</td>
                                        <td>${line.pengambilanSek8Form.tarikhBorangH}</td>
                                        <td>${line.pengambilanSek8Form.tarikhBorangK}</td>
                                        <td>${line.pengambilanSek8Form.jumlahHakmilikSamb} ${line.pengambilanSek8Form.jumlahHakmilikSelesai}</td>
                                        <td></td>
                                    </tr>
                                </table>
                                        </c:if>
                            </td>
                        </tr>
                         <c:set value="${count +1}" var="count"/>
                    </c:forEach>
                </table>-->
            </div><br /><br />
        </fieldset>

        <!--        <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Penghantaran Borang B
                        </legend>
        
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
        
        
                        </div>
                            <p align="right">
        <s:button class="btn"  name="simpan" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
    </p>
<br>
</fieldset>
</div>-->


    </div>
</s:form>
