<%-- 
    Document   : maklumat_waris
    Created on : Jan 6, 2012, 12:30:30 PM
    Author     : Srinivas
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function removeSingle (idMesej) {
        form = document.form1;
        var answer = confirm("adakah anda pasti untuk Hapus?");
        if (answer) {
            form.action = "${pageContext.request.contextPath}/pelupusan/maklumatWaris?deleteSingle&idNotifikasi="+idMesej;
            form.submit();
        }
    }

    function editJabatanTeknikal(){
    <%--alert(id);--%>
            window.open("${pageContext.request.contextPath}/pelupusan/maklumatWaris?showForm1", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }

    function editJabatanTeknikal1(id){
    <%--alert(id);--%>
            window.open("${pageContext.request.contextPath}/pelupusan/maklumatWaris?showForm1&idPermohonanPihak="+id, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.maklumatWarisActionBean">
    <div class="subtitle">

        <fieldset class="aras1">
            <br>
            <legend>
                Waris
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.mohonPihakList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumatWaris">
                    <display:column title="No." sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama Waris">
                        <a href="#" style="color:blue;" onclick="editJabatanTeknikal1('${line.idPermohonanPihak}')">${line.pihak.nama}</a>
                    </display:column>
                    <display:column title="Jenis Waris" property="jenis.nama">
                    </display:column>
                    <%--<display:column title="Jenis Waris" >
                         <c:if test="${line.pihak.kodJantina eq '1'}">
                            Waris Lelaki
                        </c:if>
                        <c:if test="${line.pihak.kodJantina eq '2'}">
                            Waris Perempuan
                        </c:if>
                        <c:if test="${line.pihak.kodJantina eq '0'}">
                            Waris Kadim
                        </c:if>
                    </display:column>--%>
                    <display:column title="Umur" property="umur" />
                    
                    <display:column title="Alamat">
                    ${line.pihak.alamat1}<br>${line.pihak.alamat2}<br>${line.pihak.alamat3}
                    </display:column>

                 <%--   <display:column title="Hapus">
                <div align="center">
                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                         id='rem_${row_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${line.idRujukan}');" />
                </div>
            </display:column>--%>

                </display:table>

                            <p>&nbsp;</p>
                            <p>
                                <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="editJabatanTeknikal()"/>
                            </p>
            </div>
        </fieldset>
    </div>

</s:form>
