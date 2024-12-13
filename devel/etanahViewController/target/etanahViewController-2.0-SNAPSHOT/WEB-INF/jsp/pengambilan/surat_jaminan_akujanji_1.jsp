<%-- 
    Document   : jaminan_akujanji
    Created on : Feb 11, 2010, 10:44:25 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
function refreshPageCeroboh(){
        var url = '${pageContext.request.contextPath}/surat_jaminan?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
}
            function popup(idBarang){
               var url = '${pageContext.request.contextPath}/maklumat_barang_tahanan?editBarangRampasanJaminan&idBarang='+idBarang;
                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");


        }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.JaminanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Surat Jaminan Akujanji
            </legend>
            <div class="content" align="center">
                 <c:if test="${edit}">
                <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="Barang yang Dirampas" property="item"></display:column>
                    <display:column title="Kuantiti" property="kuantiti"></display:column>
                    <%--<display:column title="Tarikh Rampasan" property="kuantiti"></display:column>--%>
                    <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                    <display:column title="Catatan" property="catatan"></display:column>
                    <display:column title="Status" property="status.nama" />

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '425'}">

                    <display:column title="Nilai Jaminan" property="amaunJaminan"/>
                    <%--<display:column title="Dituntut Oleh" property="dilepasKepada" />--%>
                    <display:column title="Dituntut Oleh">${line.dilepasKepada}
                        <c:if test="${line.dilepasNoKP ne null}"> ,
                        ${line.dilepasNoKP}
                        </c:if>

                    </display:column>
                    <display:column title="Tarikh Barang Dituntut" property="tarikhDilepas" />
                    </c:if>
                   <display:column title="Kemaskini">
                              <div align="center">
                                  <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                       id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idBarang}')"/>
                              </div>
                  </display:column>

            </display:table>
                <br>
                    <p>
                        <label>Jumlah Wang Jaminan :</label>
                        <s:text name="jum" readonly="true" size="15"/>
                    </p>
            </div> 
        </fieldset>
    </div>
    <div class="content" align="right">
        <table>
            <tr>
                <td align="right">
                    <s:button name="Imbas" value="Simpan" class="btn" />
                </td>
            </tr>
        </table>
        </c:if>
    </div>
</s:form>