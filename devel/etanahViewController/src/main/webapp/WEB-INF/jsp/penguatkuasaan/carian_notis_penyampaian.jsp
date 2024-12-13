<%--
    Document   : carian_notis_penyampaian
    Created on :Sept 8, 2011, 12:23:22 PM
    Author     : ctzainal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/scripts/datepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript"src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>



<script language="javascript" type="text/javascript">
    function tambahBaru(idPermohonan){
        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idMohon').focus();
            return false;

        }else{
            window.open("${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?notisPopup&idPermohonan="+idPermohonan, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=500");
        }
    }

    function refreshPageNotis(idPermohonan){
        //alert("refreshPageNotis");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?reload&idPermohonan='+idPermohonan;
        $.get(url,
        function(data){
            $("#notisPenyampaianDiv").replaceWith($('#notisPenyampaianDiv', $(data)));
        },'html');
    }

    function refreshImejDiv(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?refreshpage';
        $.get(url,
        function(data){
            //$("#ImejDiv").replaceWith($('#ImejDiv', $(data)));
            $("#notisPenyampaianDiv").replaceWith($('#notisPenyampaianDiv', $(data)));
            //$('#page_div').html(data);
        },'html');
    }
    function removeNotis(idNotis){
        var idPermohonan = $('#idPermohonanCarian').val();
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?deleteSingle&idNotis='+idNotis;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPageNotis(idPermohonan);
            },'html');
        }
    }

    function editNotis(idNotis,idPermohonan){
    <%--alert(idPermohonan);--%>
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/6)-(150/6);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?editNotisPopup&idNotis='+idNotis+'&idPermohonan='+idPermohonan;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);
        }

        function muatNaikForm(notis) {
            var idPermohonan = $('#idPermohonanCarian').val();
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?popupUpload&idNotis='+notis+'&idPermohonan='+idPermohonan;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
            // window.location.reload();
        }

        function scan(notis) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_notis_penyampaian?popupScan&idNotis='+notis;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
        }


        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiNotisPenyampaianActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <div id="notisPenyampaianDiv">


            <fieldset class="aras1">

                <legend>Carian notis penyampaian</legend>
                <br>
                <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                    <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                    <s:submit name="searchNotisPenyampaian" value="Cari" class="btn"/>
                </p>

                <legend>Notis Penyampaian</legend>

                <p>
                    <label>Id Permohonan :</label>
                    ${actionBean.idPermohonan} &nbsp;
                    <%--<s:text name="idPermohonan" value="${actionBean.notis.permohonan.idPermohonan}" disabled="true" id="idPermohonanCarian"/> &nbsp;--%>
                    <s:hidden name="idPermohonan" id="idPermohonanCarian"/>
                </p>

                <div class="content" align="center">
                    <div id="ImejDiv">
                        <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/penguatkuasaan/notis_penyampaian" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Jenis Notis" class="rowCount"><a class="popup" onclick="popup(${line.idNotis})">${line.kodNotis.nama}</a></display:column>
                            <display:column title="Nama Penghantar Notis">
                                ${line.penghantarNotis.nama}<br>
                                No.K/P:(${line.penghantarNotis.noPengenalan})
                            </display:column>
                            <display:column title="Status Penyampaian" property="kodStatusTerima.nama" class="${line_rowNum}"/>
                            <display:column title="Tarikh" class="${line_rowNum}">
                                H :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhHantar}"/> <br>
                                T :   <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTerima}"/>
                            </display:column>
                            <display:column title="Tindakan">
                                <p align="center">
                                    <c:if test="${line.buktiPenerimaan.namaFizikal == null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                    </c:if>
                                    <c:if test="${line.buktiPenerimaan.namaFizikal != null}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </c:if>
                                </p>
                            </display:column>

                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeNotis('${line.idNotis}');"/>
                                </div>
                            </display:column>

                            <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editNotis('${line.idNotis}','${actionBean.notis.permohonan.idPermohonan}')"/>
                                </div>
                            </display:column>
                        </display:table>
                    </div>
                    <br/>

                    <table width="70%">
                        <tr><td align="center">
                                <br/>
                                <c:choose>
                                    <c:when test="${actionBean.idPermohonanNotExist eq true}">
                                        <s:button class="btn" value="Tambah" name="new" id="new" disabled="true" onclick="tambahBaru('${actionBean.idPermohonan}');"/>
                                    </c:when>
                                    <c:otherwise>
                                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru('${actionBean.idPermohonan}');"/>
                                    </c:otherwise>
                                </c:choose>

                    </table>

                </div>
            </fieldset>
        </div>
    </div>

</s:form>

