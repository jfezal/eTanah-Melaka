<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
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
<s:form beanclass="etanah.view.strata.RTHSMaklumatViewPemohonActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Nama :</label>
                ${actionBean.pihak.nama}&nbsp;
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <p>
                <label>Alamat :</label>
                ${actionBean.pihak.alamat1}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat2}&nbsp;
            </p>

            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat3}&nbsp;
            </p>
            <c:if test="${actionBean.pihak.alamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pihak.alamat4}&nbsp;
                <p>
                </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.pihak.poskod}&nbsp;
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                ${actionBean.pihak.negeri.nama}&nbsp;
            </p>
           <c:if test="${edit}">
                <c:if test="${actionBean.pihak eq null}">
                    <p>
                        <br>
                        <label>&nbsp;</label>
                        <s:button class="btn" value="Cari" name="new" id="new" onclick="addNewPemohon();"/>&nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.pihak ne null}">
                    <p>
                        <br>
                        <label>&nbsp;</label>
                        <s:button class="btn" value="Kemaskini" name="new" id="new" onclick="editPemohon('${actionBean.pihak.idPihak}');"/>&nbsp;
                    </p>
                </c:if>
            </c:if>
        </fieldset >
    </div>
</s:form>