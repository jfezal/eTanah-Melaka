l<%-- 
    Document   : notis_bukti_penyampaian_view
    Created on : Jul 12, 2011, 11:29:12 AM
    Author     : sitifariza.hanim
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
<script type="text/javascript">

    function refreshPageNotis(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?reload';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }

    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?notisPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function muatNaikForm(notis) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?popupUpload&idNotis='+notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        // window.location.reload();
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function scan(notis) {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?popupScan&idNotis='+notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

    function popup(idNotis){
        //alert(idBarang)
        var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?viewNotisDetail&idNotis='+idNotis;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
    }

    function removeNotis(idNotis){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?deleteSingle&idNotis='+idNotis;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function editNotis(idNotis){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_bukti_penyampaian?editNotisPopup&idNotis='+idNotis;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

</script>

<s:form beanclass="etanah.view.penguatkuasaan.NotisBuktiPenyampaianActionBean" id="folder_div">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle" id="myDiv">
        <fieldset class="aras1">
            <legend>
                Maklumat Penghantaran Notis/Borang
            </legend><br>

            <p class=instr>
                *<em>Petunjuk :</em>
            </p>
            <p class=instr>
                <em>H:</em> Tarikh Hantar
                <em>T:</em> Tarikh Terima
                <em>P:</em> Tarikh Tampal
            </p>
            <font size="2" color="black"></font>
            <p>
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                     width="20" height="20" /> - <font size="1" color="black">Papar Dokumen</font>&nbsp;&nbsp;&nbsp;
                <%-- <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                      width="20" height="20" /> - <font size="1" color="black">Muat Naik Dokumen</font>&nbsp;&nbsp;&nbsp;
                 <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                      width="20" height="20" /> - <font size="1" color="black">Imbas Dokumen</font>--%>
            </p><br>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/penguatkuasaan/notis_penyampaian" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Jenis Notis" class="rowCount"><a class="popup" onclick="popup(${line.idNotis})">${line.kodNotis.nama}</a></display:column>
                    <display:column title="Nama Penghantar Notis">
                        ${line.penghantarNotis.nama}<br>
                        No.K/P:(${line.penghantarNotis.noPengenalan})
                    </display:column>
                    <display:column title="Nama Penerima Notis">
                        ${line.penerimaNotis}
                    </display:column>
                    <display:column title="Status Penyampaian" property="kodStatusTerima.nama" class="${line_rowNum}"/>
                    <display:column title="Tarikh" class="${line_rowNum}">
                        H :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhHantar}"/> <br>
                        T :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTerima}"/> <br>
                        P :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTampal}"/>
                    </display:column>
                    <display:column title="Papar">
                        <p align="center">
                            <%-- <c:if test="${line.buktiPenerimaan.namaFizikal == null}">
                                 <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                      onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                 /
                                 <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                      onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                             </c:if>--%>
                            <c:if test="${line.buktiPenerimaan.namaFizikal != null}">
                                <%--<img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>--%>
                                
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                    </display:column>

                    <%-- <display:column title="Hapus">
                         <div align="center">
                             <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                  id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNotis('${line.idNotis}');"/>
                         </div>
                     </display:column>

                    <display:column title="Kemaskini">
                        <div align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editNotis('${line.idNotis}')"/>
                        </div>
                    </display:column>--%>
                </display:table>
                <%-- <table>
                     <tr>
                         <td align="right">
                             <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                         </td>
                     </tr>
                 </table>--%>
            </div>

            <br>
        </fieldset>
    </div>
</s:form>