<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>



<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.SijilPerbadananActionBean">


    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Draf Sijil Perbadanan Pengurusan</legend>
            <br>

<p>
                <label>Nama Perbadanan Pengurusan : </label><s:text name="pengurusanNama" size="120"  id="pengurusanNama" readonly="true"/><em></em>
            </p>
            <p>
                <label>No Syarikat : </label><s:text name="pengurusanNoPengenalan" id="pengurusanNoPengenalan" readonly="true"/><em></em>
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="pengurusanAlamat1" size="100" id="pengurusanAlamat1" readonly="true"/><em></em>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pengurusanAlamat2" size="80" id="pengurusanAlamat2" readonly="true"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pengurusanAlamat3" size="80" id="pengurusanAlamat3" readonly="true"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pengurusanAlamat4" size="80" id="pengurusanAlamat4" readonly="true"/>
            <p>                    <label>Poskod :</label>
                <s:text name="pengurusanPoskod" maxlength="5" id="pengurusanPoskod" onkeyup="validateNumber(this,this.value); " readonly="true"/><em></em>
            </p>

            <p>
                <label>Negeri :</label>
                <s:select name="pengurusanKodNegeri" id="pkodNegeri" value="${actionBean.pengurusanKodNegeri}" disabled="true">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select><em></em>
            </p>
            <p>
                <label>Tarikh Daftar Strata  :</label><s:text name="pengurusanTarikhSijil" readonly="true" id="pengurusanTarikhSijil" formatPattern="dd/MM/yyyy"/><em></em>

            </p>

            <p>
                <label>No Fail:</label><s:text name="pengurusanNoRujukan" id="pengurusanNoRujukan" readonly="true"/><em></em>
            </p>

            <%--<p>
                <label>Tarikh Sijil Dikeluarkan :</label><s:text name="pengurusanTarikhRujukan" readonly="true" id="pengurusanTarikhRujukan" formatPattern="dd/MM/yyyy"/><em></em>
            </p>--%>
            <br>
        </fieldset>
    </div>
    <br>


</s:form>