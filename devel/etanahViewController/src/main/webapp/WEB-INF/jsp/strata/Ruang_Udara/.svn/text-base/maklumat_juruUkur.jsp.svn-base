<%-- 
    Document   : maklumat_juruUkur
    Created on : Jul 1, 2010, 11:25:51 AM
    Author     : User
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.strata.MaklumatJuruUkurActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Juruukur
                </legend>
                <p>
                    <label>Nama :</label> <s:text name="mohonJUBL.jurukur.nama"size="40"/>
                    &nbsp;<br/>
                </p>
                <p>
                    <label>Jenis Pengenalan :</label> <s:text name="test"/>
                    &nbsp;
                </p>
                <p>
                    <label>Nombor Pengenalan :</label> <s:text name="mohonJUBL.jurukur.noPengenalan"/>
                    &nbsp;
                </p>
                <p>
                    <label for="alamat">Alamat :</label>
                    <s:text name="mohonJUBL.jurukur.alamat1" id="suratAlamat1" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="mohonJUBL.jurukur.alamat2" id="suratAlamat2" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="mohonJUBL.jurukur.alamat3" id="suratAlamat3" size="40"/>
                </p>
                <p>
                    <label> &nbsp;</label>
                    <s:text name="mohonJUBL.jurukur.alamat4" id="suratAlamat4" size="40"/>
                </p>
                <p>
                    <label>Poskod :</label>
                    <s:text name="mohonJUBL.jurukur.poskod" id="suratPoskod" size="40" maxlength="5"/>
                </p>

                <p>
                    <label for="Negeri">Negeri :</label>
                    <s:select name="kodNegeri.kod" id="suratNegeri">
                        <s:option value="0">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>&nbsp;<label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                </p>

            </fieldset >
        </div>
    </div>
</s:form>