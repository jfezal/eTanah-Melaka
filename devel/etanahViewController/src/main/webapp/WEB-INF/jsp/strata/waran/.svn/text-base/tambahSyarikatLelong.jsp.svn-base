<%-- 
    Document   : tambahSyarikatLelong.jsp
    Created on : 14 April 2011
    Author     : faidzal
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>


<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function pilih(event, f){
        var s = document.form1.idSytlelong.value;
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
            //   refreshPage();
        },'html');
    }

    function save(event, f){   
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            //alert(data);
            $('#searchpelelong').html("");
            $('#searchpelelong1').html(data);
        },'html');
        //alert("--");
        //refreshPage;

    }
    function validatela(){
        var len = $('.nama').length;
        for(var i=1; i<=len; i++){
            var ckd = $('#idSytlelong'+i).is(":checked");

            if(ckd == false && i == len){
                alert("Sila Pilih Jurulelong");
                return false;
            }
            if(ckd == true){
                return true;
            }
        }
    }
</script>
<div id="searchpelelong">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form name="form1" id="form1" beanclass="etanah.view.strata.PelelongActionBean">

    <s:messages/>
    <s:errors/>
    
        <fieldset class="aras1">
            <legend>Cari Syarikat Jurulelong</legend>

            <p>
                <label title="Nama" >Nama :</label><s:text name="nama" id="nama"/>&nbsp; <font color="red">atau</font>
            </p>
            <p>
                <%--<label title="IdSyarikat" >Id Syarikat :</label><s:text name="idSyt"/>--%>
                <label title="IdSyarikat" >No Syarikat :</label><s:text name="idSyt" id="idSyt"/>
            </p>

            <p >
                <label>&nbsp;</label> <s:button name="cariPelelong" value="Cari" class="btn" onclick="save(this.name, this.form);"/>
            </p>
            <br>

        </fieldset>
        
        <fieldset class="aras1">
            <legend>Cari Syarikat Jurulelong</legend>
            <c:if test="${fn:length(actionBean.listSytJuruLelong) > 0}">
                <display:table class="tablecloth" name="${actionBean.listSytJuruLelong}" id="line">
                    <display:column> <s:radio id="idSytlelong${line_rowNum}" name="idSytlelong" value="${line.idSytJlb}"/></display:column>
                    <display:column title="Bil" >${line_rowNum}</display:column>
                    <display:column title="Nama" property="nama" class="nama" />
                    <display:column title="No Syarikat" property="noPengenalan"/>
                    <display:column title="Alamat">${line.alamat1} ${line.alamat2} ${line.alamat3} ${line.alamat4} </display:column>
                    <display:column title="Poskod" property="poskod"/>
                    <display:column title="Negeri" property="negeri.nama"/>
                    <display:column title="No Telefon" property="noTelefon1"/>
                    <display:column title="No Telefon" property="noTelefon2"/>
                </display:table>
                <br>
                <label>&nbsp;</label> <s:button name="pilihSytJuruLelong" value="Simpan" class="btn" onclick="if (validatela()){pilih(this.name, this.form);}"/>
                <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            </c:if>
        </fieldset>
</s:form>
</div>
<div id="searchpelelong1"></div>

