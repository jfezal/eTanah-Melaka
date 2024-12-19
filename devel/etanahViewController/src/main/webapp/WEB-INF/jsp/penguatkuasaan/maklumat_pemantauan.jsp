<%-- 
    Document   : maklumat_pemantauan
    Created on : Feb 22, 2010, 9:43:55 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_pemantauan?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");        
    }
    function removeSingle(idPemantauan)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pemantauan?deleteSingle&idPemantauan='
                +idPemantauan;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPagePantau1();
    }
    function refreshPagePantau1(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pemantauan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function popup(h){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pemantauan?popupedit&idPemantauan='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function muatNaikForm1(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload?muatNaikForm1&folderId=' + folderId
            + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }
    function refreshPage(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pemantauan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function removeImej(idImej) {
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_pemantauan?deleteSelected&idImej='+idImej;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPemantauanActionBean">
     <s:messages />
  <s:errors />
    <c:if test="${actionBean.errorMsg==false}">
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Pemantauan
            </legend>
            <c:if test="${edit}">
                <div class="instr-fieldset">
                    Klik butang tambah untuk masukkan maklumat pemantauan
                </div>
                <div class="content" align="center">
                    <%--name="${actionBean.senaraiAduanPemantauan}" --%>
                    <display:table class="tablecloth" name="${actionBean.senaraiAduanPemantauan}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh Dan Masa Pemantauan" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${line.tarikhPemantauan}"/></display:column>
                        <display:column title="Laporan Pemantauan" property="catatan"></display:column>
                        <%--<display:column title="Muat Naik">--%>
                            <%--<c:if test="${line.dokumen.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${line.permohonan.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${line.permohonan.kodDokumen.nama}"/> /
                            </c:if>--%>
                            <%--<img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                 onclick="muatNaikForm1('${line.permohonan.folderDokumen.folderId}',
                                     '${line.permohonan.idPermohonan}','LPM');return false;" height="15" width="15" alt="Muat Naik"
                                 onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>--%>
                        <%--</display:column>--%>
                         <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idPemantauan}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiAduanPemantauan[line_rowNum-1].idPemantauan}');" />
                            </div>
                        </display:column>
                    </display:table>

               <%-- <p>
                    <b>Lampiran:</b>
                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                         onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                             '${actionBean.permohonan.idPermohonan}','LPM');return false;" height="30" width="30" alt="Muat Naik"
                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                <p align="center">
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LPM'}">
                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                               ${count})
                              
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                ${senarai.dokumen.kodDokumen.nama} ${count} /
                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>

                                <c:set value="${count+1}" var="count"/>
                            </c:if>
                        </c:if>

                    </c:forEach>
                </p>--%>
                <%--</p>--%>

                    <br>
                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>

                </div>
            </c:if>
            <c:if test="${view}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiAduanPemantauan}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Tarikh Dan Masa Pemantauan" style="${bcolor}"><fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${line.tarikhPemantauan}"/></display:column>
                        <display:column title="Laporan Pemantauan" property="catatan"></display:column>
                    </display:table>
 <%--                   <p>
                    <b>Lampiran:</b>
                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                         onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                             '${actionBean.permohonan.idPermohonan}','LPM');return false;" height="30" width="30" alt="Muat Naik"
                         onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                <p align="center">
                    <c:set value="1" var="count"/>
                    <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                        <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LPM'}">
                            <c:if test="${senarai.dokumen.namaFizikal != null}">
                               ${count})

                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                     onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                     onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                ${senarai.dokumen.kodDokumen.nama} ${count} 
                                <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                     onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                     onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen ${senarai.dokumen.kodDokumen.nama}"/>

                                <c:set value="${count+1}" var="count"/>
                            </c:if>
                        </c:if>

                    </c:forEach>
                </p>--%>
                    <br>
                </div>
            </c:if>
        </fieldset>
    </div>
    </c:if>
</s:form>