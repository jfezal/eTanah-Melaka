<%--
    Document   : rmhsmaklumat_pemohon
    Created on : Dec 16, 2010, 10:42:01 AM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
   
    function addNewPemohon(){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?pemohonPopup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");
    
    }
    function editPemohon(val){
        window.open("${pageContext.request.contextPath}/strata/maklumat_pemohon?editpemohonPopup&idPihak="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }
    
</script>  
<s:form beanclass="etanah.view.strata.RMHSMaklumatPemohonActionBean">
    <s:messages/>
    <s:errors/>


    <%--<!--    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Permohonan Terdahulu </legend>&nbsp;
                <p>
                    <label>Id Permohonan Terdahulu :</label>
                    <s:text name="idPermohonanTerdahulu" id="idPermohonanTerdahulu" size="40" />
                </p>
            </fieldset>&nbsp;
        </div>
        <p>
            <label>&nbsp;</label>
            <s:button name="kembali" id="back" value="Kembali" class="btn"/>
            <s:button name="terus" id="go" value="Terus" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>-->--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Nama :</label>
                ${actionBean.namaPemohon}
                &nbsp;
            </p>
            <%--<p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.jenisPemohon}
                &nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.noPengPemohon}&nbsp;
            </p>--%>
            <p>
                <label>Alamat :</label>
                ${actionBean.alamatPemohon1}  &nbsp;
            </p>
            <c:if test="${actionBean.alamatPemohon2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.alamatPemohon2}  &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.alamatPemohon3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.alamatPemohon3}&nbsp;
                </p>
            </c:if>

            <p>
                <label> Bandar :</label>
                ${actionBean.alamatPemohon4}&nbsp;
            <p>

            <p>
                <label>Poskod :</label>
                ${actionBean.poskodPemohon}&nbsp;
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                ${actionBean.negeriPemohon}&nbsp;
            </p>
            <br>
        </fieldset >
    </div>

</s:form>