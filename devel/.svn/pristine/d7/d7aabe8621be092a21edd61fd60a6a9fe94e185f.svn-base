<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

    });
    
    function viewUrusan(idPermohonan, noRujukan) {
      window.open("${pageContext.request.contextPath}/daftar/maklumat_surat?popupSurat&idPembetulan=" + noRujukan, "eTanah",
              "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    
    function hapusUrusan(idPermohonan, noRujukan) {
      window.open("${pageContext.request.contextPath}/daftar/maklumat_surat?delete&idPembetulan=" + noRujukan, "eTanah",
              "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil" />
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.MaklumatSuratActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Surat
            </legend>
            <br/>
            <!--<p><font color="black" size="2"><b>*Sila isi yang diperlukan sahaja.</b></font></p>-->
            <c:if test="${actionBean.senaraiDokumen ne null}">
                <display:table class="tablecloth" name="${actionBean.senaraiDokumen}"  cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/daftar/maklumat_surat" pagesize="10">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="kodDokumen.nama" title="Nama Surat" sortable="true"/>
                    <display:column property="noRujukan" title="ID Surat" sortable="true"/>
                    <display:column property="permohonan.idPermohonan" title="ID Perserahan" sortable="true"/>

                    <display:column title="Baiki">
                        <div align="center">
                            <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewUrusan('${line.permohonan.idPermohonan}','${line.noRujukan}');
                                                                 return false;" onmouseover="this.style.cursor = 'pointer';">
                            </p>
                        </div>
                    </display:column>
                    <%--<display:column title="Hapus">--%>
                        <!--<div align="center">-->
<!--                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem${line_rowNum}' onclick="hapusUrusan('${line.permohonan.idPermohonan}','${line.noRujukan}')"
                                 onmouseover="this.style.cursor = 'pointer';" >-->
                        <!--</div>-->
                    <%--</display:column>--%>
                </display:table>
            </c:if>
        </fieldset>
    </div>
</s:form>