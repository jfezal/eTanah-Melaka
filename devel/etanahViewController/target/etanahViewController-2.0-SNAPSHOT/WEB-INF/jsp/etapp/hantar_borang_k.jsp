<%-- 
    Document   : carian_hakmilik
    Created on : 15-Oct-2009, 10:22:28
    Author     : md.nurfikri
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //filterKodSeksyen();
        //filterKodGunaTanah();
//        $(".datepicker").datepicker({dateFormat: 'dd/MM/yyyy'});

//        if (idHakmilik != 'null') {
//            //alert('masuk idHakmilik != null');
//            p(idHakmilik);

        }
    });


</script>
    <s:form beanclass="etanah.view.etapp.HantarBorangKActionBean">
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
                Senarai Hakmilik
            </legend>

            <div align="center">
                 <display:table class="tablecloth" name="${actionBean.listHakmilikEndos}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="hakmilik" id="line">
                    <%--<display:column> <s:checkbox name="chkbox" id="chkbox${line_rowNum}" value="${line.idHakmilik}"/></display:column>--%>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup idhakmilik${line_rowNum}" />
                    <display:column property="tarikhDaftar" title="Tarikh Daftar" class="cawangan${line_rowNum}"/>
                    <display:column property="idPerserahan" title="Id Perserahan" class="lot${line_rowNum}"/>
                    <display:column property="masaDaftar" title="Masa Daftar" class="luas${line_rowNum}"/>
                </display:table>
                
             </div>    
            <p align="center">
            <br>
            <s:hidden name="idPermohonan"/>
            
        </p>
        </fieldset>
        <fieldset class="aras1">
            <legend>
                Senarai Dokumen Di Hantar
            </legend>
Sila muat naik pada TAB Dokumen : Dokumen yang diperlukan ialah Sijil Carian Rasmi, Surat Iringan Endorsan Borang K

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
