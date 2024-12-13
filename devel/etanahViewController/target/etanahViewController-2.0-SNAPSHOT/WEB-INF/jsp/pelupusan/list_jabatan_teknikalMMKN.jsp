<%--
    Document   : terima_ulasan_jbtn_teknikal
    Created on : Jul 14, 2010, 2:33:59 PM
    Author     : sitifariza.hanim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function removeSingle (idMesej) {
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            url = "${pageContext.request.contextPath}/pelupusan/jabatan_teknikalMMKNA?deleteSingle&idNotifikasi="+idMesej;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function editJabatanTeknikal(){
    <%--alert(id);--%>
            window.open("${pageContext.request.contextPath}/pelupusan/jabatan_teknikalMMKNA?showEditJabatanTeknikal", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

    function editJabatanTeknikal1(id){
    <%--alert(id);--%>
            window.open("${pageContext.request.contextPath}/pelupusan/jabatan_teknikalMMKNA?showEditJabatanTeknikal&idRujukan="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikalForMMKNActionBean">
    <div class="subtitle">

        <fieldset class="aras1">
            <br>
            <legend>
                Senarai Jabatan Teknikal Dan YB Adun Yang Terlibat
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/jabatan_teknikalMMKNA">
                    <display:column title="Bil." sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama Jabatan">
                        <a href="#" style="color:blue;" onclick="editJabatanTeknikal1('${line.idRujukan}')">${line.namaAgensi}</a>
                    </display:column>
                    <display:column title="Syor">

                        <c:if test="${line.diSokong eq '1'}">
                            Boleh Diluluskan
                        </c:if>
                        <c:if test="${line.diSokong eq '2'}">
                            Tidak Diluluskan
                        </c:if>
                        <c:if test="${line.diSokong eq '3'}">
                            Tiada Halangan
                        </c:if>
                        <c:if test="${line.diSokong eq '4'}">
                            Sesuai
                        </c:if>
                        <c:if test="${line.diSokong eq '5'}">
                            Tidak Sesuai
                        </c:if>
                            <c:if test="${line.diSokong eq '6'}">
                            Sokong
                        </c:if>
                        <c:if test="${line.diSokong eq '7'}">
                           Tidak Disokong
                        </c:if>
                            <c:if test="${line.diSokong eq '8'}">
                            Tidak Terima Ulasan
                        </c:if>

                    </display:column>
                    <display:column title="Hapus">
                <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                         id='rem_${row_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${line.idRujukan}');" />
                </div>
            </display:column>

                </display:table>
                            
                            <p>&nbsp;</p>
                            <p>
                                <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="editJabatanTeknikal()"/>
                            </p>
            </div>
        </fieldset>
    </div>
 <%--   <div class="content" align="center">
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen Yang Telah DiHantar
            </legend>
            <br>
            <display:table class="tablecloth" name="${actionBean.senaraiRujukanDok}" cellpadding="0" cellspacing="0" id="line">
                <display:column title="Bil" sortable="true">${line_rowNum}
                    <s:hidden name="x" class="x${line_rowNum -1}" value="${line.dokumen.idDokumen}"/>
                </display:column>
                <display:column title="Kod Dokumen">
                    ${line.dokumen.kodDokumen.kod}
                </display:column>
                <display:column title="Tajuk / No Rujukan">
                    ${line.dokumen.kodDokumen.nama}
                </display:column>
            </display:table>
        </fieldset>
    </div>--%>

</s:form>