
    <%-- 
    Document   : rekodTerimaCek
    Created on : Jul 17, 2020, 1:20:05 PM
    Author     : NURBAIZURA
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

<s:form beanclass="etanah.view.etapp.SijilUkurActionBean">
        <fieldset class="aras1">
           <legend>
                Maklumat Permohonan
            </legend>
            <p>
                <label for="Id Permohonan">ID Permohonan :</label>
                ${actionBean.idPermohonan}
            </p>
             <p>
                <label for="No Permohonan Ukur">No Permohonan Ukur :</label>
                ${actionBean.noPU}
            </p>
             <p>
                <label for="Id Permohonan">Tarikh Permohonan Ukur :</label>
                ${actionBean.tarikhPU}
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
                            
                    </display:table>

                    <br>      
                   
                    <br/>
                </fieldset>
             </div>    
            <p align="center">
            <br>
            <s:submit name="selesai" id="save" value="Selesai" class="btn" />&nbsp;
        </p>
        </fieldset>
    </s:form>
