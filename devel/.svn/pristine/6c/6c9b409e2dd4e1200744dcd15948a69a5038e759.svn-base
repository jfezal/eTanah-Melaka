<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : Sreenivasa Reddy Munagal
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
<s:form beanclass="etanah.view.strata.RBHSMaklumatPemohonActionBean">
    <s:messages/>
    <s:errors/>

    <!--    <c:if test="${readOnly}">
          <div class="subtitle">
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
        <s:button name="terus" id="go" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        <s:button name="kembali" id="back" value="Isi Semula" onclick="doSubmit(this.form, this.name, 'page_div')" class="btn"/>
     </p>
    </c:if>&nbsp;-->
    &nbsp;&nbsp;
    <%--<c:if test="${edit2}">--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>
            <p>
                <label>Nama :</label>
                ${actionBean.pemohon.pihak.nama}&nbsp;
            </p>
            <c:if test="${actionBean.pemohon.pihak.jenisPengenalan ne null}">
                <p>
                    <label>Jenis Pengenalan :</label>
                    ${actionBean.pemohon.pihak.jenisPengenalan.nama}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.pemohon.pihak.noPengenalan ne null}">
                <p>
                    <label>Nombor Pengenalan :</label>
                    ${actionBean.pemohon.pihak.noPengenalan}&nbsp;
                </p>
            </c:if>
            <p>
                <label>Alamat :</label>
                ${actionBean.pemohon.pihak.alamat1}&nbsp;
            </p>
            <c:if test="${actionBean.pemohon.pihak.alamat2 ne null}">
            <p>
                <label> &nbsp;</label>
                ${actionBean.pemohon.pihak.alamat2}&nbsp;
            </p>
            </c:if>
            <c:if test="${actionBean.pemohon.pihak.alamat3 ne null}">
            <p>
                <label> &nbsp;</label>
                ${actionBean.pemohon.pihak.alamat3}&nbsp;
            </p>
            </c:if>
            <c:if test="${actionBean.pemohon.pihak.alamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pemohon.pihak.alamat4}&nbsp;
                <p>
                </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.pemohon.pihak.poskod}&nbsp;
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                ${actionBean.pemohon.pihak.negeri.nama}&nbsp;
            </p>

        </fieldset >
    </div>
    <%--</c:if>--%>
</s:form>