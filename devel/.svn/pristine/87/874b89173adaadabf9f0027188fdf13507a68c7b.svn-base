<%-- 
    Document   : kemasKini_profile
    Created on : Jul 4, 2011, 6:17:24 PM
    Author     : amir.muhaimin
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function hideShowId(f){
        if((f.elements['jabatan'].value == '16')||(f.elements['jabatan'].value == '17')){
            if (browserType == "gecko" )
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else if (browserType == "ie")
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else
                document.poppedLayer =
                eval('document.layers["hideshow"]');
            document.poppedLayer.style.visibility = "visible";
        }
        else{
            if (browserType == "gecko" )
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else if (browserType == "ie")
                document.poppedLayer =
                eval('document.getElementById("hideshow")');
            else
                document.poppedLayer =
                eval('document.layers["hideshow"]');
            document.poppedLayer.style.visibility = "hidden";
            
        } 
    }
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.uam.KemasKiniProfilBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Id Pengguna</legend>
            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
                    ${actionBean.pguna.idPengguna}
            </p>
            <br>
        </fieldset>
        <fieldset class="aras1">
            <legend>Maklumat Asas Pengguna</legend>
            <p>
                <label><font color="red">*</font>Nama :</label>
                    ${actionBean.pguna.nama}
            </p>
            <p>
                <label><font color="red">*</font>Kad Pengenalan :</label>
                    ${actionBean.pguna.noPengenalan}
            </p>
            <p>
                <c:if test="${!actionBean.melaka}">
                    <label><font color="red">*</font>Cawangan :</label>
                    </c:if>
                    <c:if test="${actionBean.melaka}">
                    <label><font color="red">*</font>Jabatan :</label>
                    </c:if>
                    ${actionBean.namaCaw}
            </p>
            <p>
                <c:if test="${!actionBean.melaka}">
                    <label><font color="red">*</font>Jabatan :</label>
                    </c:if>
                    <c:if test="${actionBean.melaka}">
                    <label><font color="red">*</font>Unit :</label>
                    </c:if>
                    ${actionBean.namaJabatan}
            </p>
            <p>
                <label><font color="red">*</font>Jawatan :</label>
                    ${actionBean.namaJawatan}
            </p>
            <%--<c:if test="${actionBean.kaunter}">
                <p>
                    <label><font color="red">*</font>Id Kaunter :</label>
                        ${actionBean.pguna.idKaunter}
                </p>
            </c:if>--%>
            <p>
                <label><font color="red">*</font>Alamat :</label>
                    ${actionBean.pguna.alamat1}
            </p>
            <c:if test="${actionBean.pguna.alamat2 ne null}">
                <p><label>&nbsp;</label>
                    ${actionBean.pguna.alamat2}
                </p>
            </c:if>
            <c:if test="${actionBean.pguna.alamat3 ne null}">
                <p><label>&nbsp;</label>
                    ${actionBean.pguna.alamat3}
                </p>
            </c:if>
            <p>
                <label><font color="red">*</font>Poskod :</label>
                    ${actionBean.pguna.poskod}
            </p>
            <p>
                <label><font color="red">*</font>Negeri :</label>
                    ${actionBean.namaNegeri}
            </p>

            <!--            <p>
                            <label>Peranan Utama :</label>
            ${actionBean.namaPeranan}
        </p>
        <br>-->
            <p>
                <label><font color="red">*</font>No Telefon :</label>
                    ${actionBean.pguna.noTelefon}
            </p>
            <c:if test="${actionBean.pguna.noTelefonBimbit ne null}">
                <p>
                    <label>No Telefon Bimbit :</label>
                    ${actionBean.pguna.noTelefonBimbit}
                </p>
            </c:if>
            <p>
                <label><font color="red">*</font>Email :</label>
                    ${actionBean.pguna.email}
            </p>
            <p>
                <label><font color="red">*</font>Penyelia / Ketua Unit:</label>
                    ${actionBean.pguna.penyelia.idPengguna}
            </p>
            <br>
        </fieldset>
        <br />
        <p>
            <label>&nbsp;</label>

            <s:submit name="kembali" value="Kembali" class="btn"/>
        </p>
        <br><br>
    </div>
</s:form>