<%-- 
    Document   : tuntut_barang
    Created on : Oct 21, 2010, 3:00:20 PM
    Author     : nurshahida.radzi
--%>
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript">
    function refreshPage(){
        var q = $('#form1').serialize();
        var url = document.form1.action + '?refreshPage&';// + event;
        window.location = url+q;

    }
    function muatNaikForm1(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod ;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function removeImej(idDokumen) {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/tuntutan_barang?deleteSelected&idDokumen='+idDokumen;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            refreshPage();
        },'html');
    }
</script>
<table width="100%" bgcolor="yellow">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: yellow;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">PENGUATKUASAAN : Kemasukan Surat Tuntutan</font>
            </div>
        </td>
    </tr>
</table>
<s:form beanclass="etanah.view.penguatkuasaan.TuntutBarangActionBean" name="form1" id="form1" >
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <legend>
                Kemasukan Surat Tuntutan Barang
            </legend>
            <div class="instr-fieldset">
                <font color="red">ARAHAN:</font>Sila Masukkan Id Aduan</div>&nbsp;
            <p>
                <label>ID Aduan :</label>&nbsp;
                <s:text name="idAduan" onkeyup="this.value=this.value.toUpperCase();" value="${actionBean.idAduan}" />
                <s:submit name="searchAduan" value="Cari" class="btn"/>
            </p>
        </fieldset>
    </div>
    <br>
    <c:if test="${view}">
        <fieldset class="aras1">
            <legend>Maklumat Aduan</legend>
            <p>
                <label>Tarikh :</label>&nbsp;
                <fmt:formatDate type="time" pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                &nbsp;
            </p>
            <p>
                <label>Cara Aduan :</label>&nbsp;
                ${actionBean.permohonan.caraPermohonan.nama}&nbsp;
            </p>
            <p>
                <label>Ringkasan Aduan :</label>&nbsp;
                ${actionBean.permohonan.sebab}&nbsp;
            </p>
        </fieldset>
        <br>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Barang Rampasan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/penguatkuasaan/maklumat_barang_tahanan">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Barang yang Dirampas"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                        <display:column title="Kuantiti">${line.kuantiti} ${line.kuantitiUnit}</display:column>
                        <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                        <display:column title="Catatan" property="catatan">${line.catatan}</display:column>
                        <display:column title="Status" property="status.nama">${actionBean.barangRampasanStatus}</display:column>
                        <display:column title="Papar">
                            <c:if test="${line.imej.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${line.imej.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.imej.kodDokumen.nama}"/>
                            </c:if>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
        <br>

        <p>
            <label>Surat Tuntutan :</label>&nbsp;
            <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                 onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',<%--'${line.idDokumen}',--%>
                     '${actionBean.permohonan.idPermohonan}','STB');return false;" height="30" width="30" alt="Muat Naik Surat Tuntutan"
                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
            <c:forEach items="${actionBean.senaraiDokumen}" var="line">
                <c:if test="${line.kodDokumen.kod == 'STB'}">
                    <c:if test="${line.namaFizikal != null}">
                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                             onclick="doViewReport('${line.idDokumen}');" height="30" width="30" alt="papar"
                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.kodDokumen.nama}"/>
                        [${line.tajuk}]
                        <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                             onclick="removeImej('${line.idDokumen}');" height="15" width="15" alt="Hapus"
                             onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${line.kodDokumen.nama}"/>
                    ,</c:if>
                </c:if>
            </c:forEach>

        <p align="right">
            <s:submit class="btn" name="Selesai" value="Selesai"/>
        </p>
    </c:if>
</s:form>