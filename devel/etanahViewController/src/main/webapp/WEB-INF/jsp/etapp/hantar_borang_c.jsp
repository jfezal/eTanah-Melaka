<%-- 
    Document   : carian_hakmilik
    Created on : 15-Oct-2009, 10:22:28
    Author     : md.nurfikri
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {        
        $(".popup").click( function() {            
            
            window.open("hakmilik?hakmilikDetail&idHakmilik="+$("#hakmilik").val(), "eTanah",
            "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
        });

        $("#add").click( function(){
            frm = this.form;
            //TODO :
            var len = $(".daerah1").length;
                    
            for(var i=1; i<=len; i++){
                if($('#chkbox'+i).is(':checked')){
                    var id = $(".idhakmilik"+i).text();
                    var caw = $(".cawangan"+i).text();
                    var daerah = $(".daerah"+i).text();
                    var lot = $(".lot"+i).text();
                    var luas = $(".luas"+i).text();
                    var noLitho = $(".noLitho"+i).text();
                    self.opener.addRows(id, caw, daerah, lot, luas, noLitho);
                }
            }
            self.close();
        });
    });
    
        function muatNaikForm(folderId, dokumenId, idPermohonan, kodDokumen) {
//           alert (kodDokumen);
        var left = (screen.width / 2) - (1000 / 2);
        var top = (screen.height / 2) - (150 / 2);
        var url = '${pageContext.request.contextPath}/pengambilan/common/rekodKpsnMMKActionBean?muatNaikForm&folderId=' + folderId + '&dokumenId='
                + dokumenId + '&idPermohonan=' + idPermohonan + '&kodDokumen=' + kodDokumen;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }
</script>
<div class="subtitle">
    <s:form beanclass="etanah.view.etapp.HantarBorangCActionBean">
       <fieldset class="aras1">
            <legend>
                Maklumat Permohonan
            </legend>
            <p>
                <label for="Id Permohonan">ID Permohonan :</label>
                ${actionBean.idPermohonan}
            </p>
            
        </fieldset>
 
    <br>
  
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen Di Hantar
            </legend>

            <div align="center">
                 <fieldset class="aras1">
                    <br/> 
                    <s:hidden name="folderId" value="${folderId}"/>
                    <s:hidden name="dokumenId" value="${dokumenId}"/>
                    <s:hidden name="idPermohonan" value="${idPermohonan}"/>       
                    <s:hidden name="prm" value="${prm}"/>
                    <br/>
                    <c:set var="rowNum" value="0"/>

                    <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%"
                                   requestURI="${pageContext.request.contextPath}/dokumen/folder">
                        <display:column title="Bil">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" group="1">
                            <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                        </display:column>

                        <display:column title="Tajuk /<br/> No Rujukan" property="dokumen.kodDokumen.nama" />
                         <display:column title="Tindakan">
                            <p align="center">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                     onclick="muatNaikForm('${row.folder.folderId}', '${row.dokumen.idDokumen}'
                                                     , '${actionBean.permohonan.idPermohonan}', '${row.dokumen.kodDokumen.kod}');
                                             return false;" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor = 'pointer';" title="Muat Naik untuk Dokumen ${row.dokumen.kodDokumen.nama}"/> <b>|</b>
                                <c:if test="${row.dokumen.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor = 'pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                    <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                    </c:if>
                                </c:if>
                            </p>
                        </display:column>     
                    </display:table>

                    <br>      
                    <p align="center">
                        <s:hidden name="permohonan.idPermohonan" id="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    </p>
                    <br/>
                </fieldset>
             </div>    
            <p align="center">
            <br>
            <s:submit name="selesai" id="save" value="Selesai" class="btn" />&nbsp;
        </p>
        </fieldset>
    </s:form>
</div>
