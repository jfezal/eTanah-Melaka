<%-- 
    Document   : rthsmaklumat_pemohon
    Created on : Nov 1, 2010, 11:01:41 AM
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
        window.open("${pageContext.request.contextPath}/strata/rthsmaklumat_Pemohon?editpemohonPopup&idPihak="+val, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=800,height=300");
    }

</script>
<s:form beanclass="etanah.view.strata.RTHSMaklumatPemohonActionBean">
   <s:messages/>
    <s:errors/>

<!--    <c:if test="${edit1}">
      <div class="subtitle">
        <fieldset class="aras1">
           <legend>Maklumat Permohonan Terdahulu </legend>&nbsp;
            <p>
                <label>Id Permohonan Terdahulu :</label>
                <s:text name="idPermohonanTerdahulu" id="idPermohonanTerdahulu" size="40" />
            </p>
        </fieldset>&nbsp;
    </div>
            <p>
              <label>&nbsp;</label>
              <%--<s:button name="" id="go" value="Tiada Id Pemohonan" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>
              <s:button name="terus" id="go" value="Semak" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
              <s:button name="IsiSemula" id="back" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
    </c:if>&nbsp;
    &nbsp;&nbsp;-->

     <c:if test="${edit2}">
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
           <%--<c:if test="${edit}">
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
            </c:if>--%>
        </fieldset >
    </div>
     </c:if>
</s:form>