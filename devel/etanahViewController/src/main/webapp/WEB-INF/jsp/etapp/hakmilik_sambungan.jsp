
    
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

<s:form beanclass="etanah.view.etapp.HakmilikSambunganActionBean">
<fieldset class="aras1">
        <legend>
            Maklumat Permohonan
        </legend>
        <p>
            <label for="Id Permohonan">Id Permohonan  :</label>
            ${actionBean.idPermohonan}  
        </p>
    </fieldset>
    <fieldset class="aras1">
        <legend>
            Maklumat Warta
        </legend>
        
    </fieldset>
    <br>
    <fieldset class="aras1">
        <legend>
            Maklumat Hakmilik
        </legend>
         <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="${pageContext.request.contextPath}/pihak_berkepentingan">
                <display:column title="Bil" sortable="true">${line_rowNum}
                </display:column>
                <display:column title="Hakmilik">
                   ${line.hakmilik.idHakmilik}
                </display:column>
               
            </display:table>
        </div>
        <p align="center">
            <br>
            <s:button name="selesai" id="save" value="Selesai" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
        </p>

    </fieldset>

</s:form>
