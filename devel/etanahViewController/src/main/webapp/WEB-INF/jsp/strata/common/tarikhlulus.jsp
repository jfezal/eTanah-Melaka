<%--
    Document   : badanPengurusan.jsp
    Created on : Apr 14, 2011, 12:24:04 AM
    Author     : mohdfaidzal
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<%--<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>--%>
<script type="text/javascript">
    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate < today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("Sila Pilih Semula");
        }
    }
    function validate(){
        var tarikhkelulusan = document.getElementById("tarikhkelulusan").value;


        if (tarikhkelulusan == "")
        {
            alert('Sila Masukkan Tarikh Kelulusan');
            document.getElementById("tarikhkelulusan").focus();
            return false;
        }
        else{
            return true;
                
        }

    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" beanclass="etanah.view.strata.HalhalLainActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tarikh Yang Diluluskan</legend>
            <c:if test="${!DT}">
            <p><label>Tarikh</label> <s:text name="tarikhLulus" class="datepicker" id="tarikhkelulusan" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/></p>
            <label>&nbsp;</label><s:button class="btn" name="simpanTarikh" value="Simpan" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}"/>
            </c:if>            
            <c:if test="${DT}">
                <br/> 
                <p class=instr><em>*</em><font color="red">Arahan: Maaf. Permohonan ini telah ditolak. Tarikh kelulusan tidak sah.</font></p>
                <p><label>Tarikh</label> <s:text name="tariktolak" readonly="true" formatPattern="dd/MM/yyyy"/></p>                
            </c:if>
        </fieldset>
    </div>
</s:form>
