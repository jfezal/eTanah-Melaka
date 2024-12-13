<%-- 
    Document   : tandatangan_dokumen
    Created on : Jun 27, 2011, 10:12:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready(function() {
    });

    function doViewReport(v) {
//        alert(v);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
           function popupItem(idMohon,idHakmilik,idBorangPerHm) {
//            alert(idMH);

            window.open("${pageContext.request.contextPath}/pengambilan/penerima_borang_f?senaraiF&idPermohonan=" + idMohon +"&idHakmilik="+idHakmilik+"&idBorangPerHm="+idBorangPerHm, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=1200,height=700,scrollbars=yes");
        }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.sek8.PenerimaBorangFActionBean">
    <s:messages/>
    <div align="center"> 
        <fieldset class="aras1">
            <legend>Borang E</legend>
            <display:table class="tablecloth" name="${actionBean.listBorangE}" pagesize="30" cellpadding="0" cellspacing="0"
                           requestURI="" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column title="Id Hakmilik"  property="hm.hakmilik.idHakmilik"></display:column>
                <display:column title="DiTandatangan Oleh" property="tandatangan"></display:column>
                <display:column title="Jumlah Pihak terlibat" property="jumlahPihak">></display:column>
                <display:column title="Papar" >
                    <c:if test="${line.dok.idDokumen ne null}"><div align="center">
                            <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                 onclick="doViewReport('${line.dok.idDokumen}');" height="30" width="30" alt="papar"
                                 onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${line.dok.kodDokumen.nama}"/>
                    </div></c:if>
                </display:column>
                <display:column title="Kemaskini">
                    <s:button name="tambahBorangA" id="save" value="Tambah" class="longbtn" onclick="popupItem('${actionBean.idPermohonan}','${line.hm.hakmilik.idHakmilik}','${line.id}')"/>
                </display:column>

            </display:table>
            <br>

        </fieldset>
    </div>

</s:form>