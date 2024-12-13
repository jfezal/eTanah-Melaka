<%-- 
    Document   : ringkasan_laporan_polis
    Created on : 22-Mar-2010, 11:08:10
    Author     : nurshahida.radzi
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    
    function test(f) {
        $(f).clearForm();
    }

    function refreshPageCeroboh(){
        var url = '${pageContext.request.contextPath}/ringkasan_laporan_polis?refreshpage';
        $.get(url,function(data){$('#page_div').html(data);},'html');
    }

    function validateForm(){

        if($('#noReport').val()=="")
        {
            alert("Sila Isi Nombor Report");
            return false;
        }

        if($('#datepicker').val()=="")
        {
            alert('Sila isikan Tarikh Laporan terlebih dahulu');
            return false;
        }
        if($('#jam').val()=="")
        {
            alert('Sila isikan jam laporan terlebih dahulu');
            return false;
        }
        if($('#minit').val()=="")
        {
            alert('Sila isikan minit laporan terlebih dahulu');
            return false;
        }
        if($('#ampm').val()=="")
        {
            alert('Sila pilih pagi atau petang pada bahagian masa laporan');
            return false;
        }
        if($('#lokasi').val()=="")
        {
            alert('Sila masukkan Lokasi Balai Polis');
            return false;
        }

        return true;
    }
    
    function muatNaikForm1(folderId, idPermohonan, dokumenKod, idRujukan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function removeImej(idImej,idRujukan) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/ringkasan_laporan_polis?deleteSelected&idImej='+idImej+'&idRujukan='+idRujukan;
            $.get(url,
            function(data){
                $("#uploadDiv").replaceWith($('#uploadDiv', $(data)));
            },'html');
        }
    }

    function muatNaikForm(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }


    function refreshPagePolis(){
        //alert("yuhu");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/ringkasan_laporan_polis?refreshpage';
        $.get(url,
        function(data){
            $("#uploadDiv").replaceWith($('#uploadDiv', $(data)));
        },'html');
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.RingkasanLaporanPolisActionBean" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                        Ringkasan Laporan Polis/SPRM
                    </c:when>
                    <c:otherwise>
                        Ringkasan Laporan Polis
                    </c:otherwise>
                </c:choose>                
            </legend>
            <div class="content" >
                <c:if test="${actionBean.kodNegeri eq '05'}">
                    <p>
                        <label><em>*</em>Agensi Terlibat :</label>
                        <s:select name="kodAgensiDiPilih">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiAgensi}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                </c:if>
                <p>
                    <label><em>*</em>Nombor <i>Report</i> :</label>
                    <s:text name="permohonanRujukanLuar.noRujukan" id="noReport" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label><em>*</em>Tarikh Laporan:</label>
                    <s:text name="tarikhRujukan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="datepicker" />&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>

                <p>
                    <label><em>*</em>Masa Laporan:</label>
                    <s:select name="jam" id="jam">
                        <s:option value=""> Jam </s:option>
                        <c:forEach var="jam" begin="1" end="12">
                            <s:option value="${jam}">${jam}</s:option>
                        </c:forEach>
                    </s:select>
                    <s:select name="minit" id="minit">
                        <s:option value=""> Minit </s:option>
                        <c:forEach var="minit" begin="00" end="59" >
                            <c:choose>
                                <c:when test="${minit > 9}"><s:option value="${minit}">${minit}</s:option></c:when>
                                <c:otherwise><s:option value="0${minit}">0${minit}</s:option></c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </s:select>
                    <s:select name="ampm" id="ampm" style="width:80px">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">PAGI</s:option>
                        <s:option value="PM">PETANG</s:option>
                    </s:select>
                </p>

                <p>
                    <c:choose>
                        <c:when test="${actionBean.kodNegeri eq '05'}">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq '429'}">
                                    <label>Lokasi Balai Polis / Cawangan SPRM :</label>
                                </c:when>
                                <c:otherwise>
                                    <label><em>*</em>Lokasi Balai Polis :</label>
                                </c:otherwise>
                            </c:choose> 
                        </c:when>
                        <c:otherwise>
                            <label>Lokasi Balai Polis :</label>
                        </c:otherwise>
                    </c:choose>
                    <s:text name="permohonanRujukanLuar.lokasiAgensi" id="lokasi" size="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>


                <c:if test="${actionBean.permohonanRujukanLuar ne null}">
                    <p>
                        <label>Lampiran :</label>
                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                 '${actionBean.permohonan.idPermohonan}','LP');return false;" height="30" width="30" alt="Muat Naik"
                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                    </p>
                    <div id="uploadDiv" align="center">
                        <table>
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <tr>
                                    <td>
                                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LP'}">
                                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                                                <br>
                                                -
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                                <font size="2px;">${senarai.dokumen.tajuk}&nbsp;</font>/
                                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                                     onclick="removeImej('${senarai.dokumen.idDokumen}','${actionBean.permohonanRujukanLuar.idRujukan}');" height="15" width="15" alt="Hapus"
                                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${actionBean.permohonanRujukanLuar.dokumen.kodDokumen.nama}"/>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <c:set value="${count+1}" var="count"/>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>



            </div>
        </fieldset>
        <p align="right">
            <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
        </p>
    </div>
</s:form>
