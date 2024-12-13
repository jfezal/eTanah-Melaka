<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : faidzal
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">
    function save111(event, f){


        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div').html(data);
             
        },'html');
        return true;

    }

    function save(event, f, idBadan){

        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/strata/permohonanPNSS?simpanBadanPihak&idBadan='+idBadan;
            $.post(url,
            function(data){
                $('#page_div',opener.document).html(data);
            },'html');
            return true;
        }
    }
    function validateSelect()
    {
        var idBadan = document.getElementById("badan").value;
        if (idBadan =="tiada")
        {
            alert("Tiada ID Badan");
            return false;
        }else
            return true;
    }

    function change(event, f){

        var idBadan = $('#badan').val();
        var url = '${pageContext.request.contextPath}/strata/permohonanPNSS?updateBadan&idBadan='+idBadan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

  
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.strata.PenamatanStrataActionBean">
    <s:messages/>
    <s:errors/> 
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>

            <p>
                <label>Nama :</label>           
                <s:select name="idBadanP" id="badan">
                    <s:option value="tiada" >Pilih</s:option>
                    <s:options-collection collection="${list.senaraiBadanPengurusan}" label="pengurusanNama" value="idBadan" />
                </s:select>
            <p>
                <label>&nbsp;</label><s:submit name="updateBadan" value="Cari" class="btn"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <c:if test="${actionBean.namaPengurusan ne null}">${actionBean.namaPengurusan}</c:if>
                <c:if test="${actionBean.namaPengurusan eq null}">tiada</c:if>

            </p>
            <p>
                <label>Nombor Pengenalan :</label>${actionBean.badanUrus.pengurusanNoPengenalan}
                <c:if test="${actionBean.badanUrus.pengurusanNoPengenalan ne null}">${actionBean.badanUrus.pengurusanNoPengenalan}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanNoPengenalan eq null}">tiada</c:if>
            </p>
            <p>
                <label>Alamat :</label>${actionBean.badanUrus.pengurusanAlamat1}
                <c:if test="${actionBean.badanUrus.pengurusanAlamat1 ne null}">${actionBean.badanUrus.pengurusanAlamat1}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanAlamat1 eq null}">tiada</c:if>
            </p>
            <p>
                <label>&nbsp; </label>
                <c:if test="${actionBean.badanUrus.pengurusanAlamat2 ne null}">${actionBean.badanUrus.pengurusanAlamat2}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanAlamat2 eq null}">tiada</c:if>
            </p>
            <p>
                <label>&nbsp; </label>${actionBean.badanUrus.pengurusanAlamat3}
                <c:if test="${actionBean.badanUrus.pengurusanAlamat3 ne null}">${actionBean.badanUrus.pengurusanAlamat3}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanAlamat3 eq null}">tiada</c:if>
            </p>
            <p>
                <label>&nbsp; </label>${actionBean.badanUrus.pengurusanAlamat4}
                <c:if test="${actionBean.badanUrus.pengurusanAlamat4 ne null}">${actionBean.badanUrus.pengurusanAlamat4}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanAlamat4 eq null}">tiada</c:if>
            </p>
            <p>
                <label>Poskod :</label>${actionBean.badanUrus.pengurusanPoskod}
                <c:if test="${actionBean.badanUrus.pengurusanPoskod ne null}">${actionBean.badanUrus.pengurusanPoskod}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanPoskod eq null}">tiada</c:if>
            </p>
            <p>
                <label>Negeri :</label>${actionBean.badanUrus.pengurusanKodNegeri.nama}
                <c:if test="${actionBean.badanUrus.pengurusanKodNegeri.nama ne null}">${actionBean.badanUrus.pengurusanKodNegeri.nama}</c:if>
                <c:if test="${actionBean.badanUrus.pengurusanKodNegeri.nama eq null}">tiada</c:if>

            </p>


            <p align="center">
                <s:button name="simpanBadanPihak" value="Simpan" class="btn" onclick="save(this.name, this.form, ${actionBean.badanUrus.idBadan});"/></p>
        </fieldset>

    </div>


</s:form>
