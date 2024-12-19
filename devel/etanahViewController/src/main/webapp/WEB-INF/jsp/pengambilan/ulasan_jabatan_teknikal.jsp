<%-- 
    Document   : ulasan_jabatan_teknikal
    Created on : 13-Jan-2010, 18:54:33
    Author     : nordiyana
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

<s:form beanclass="etanah.view.stripes.pengambilan.UlasanJabatanTeknikalActionBean">
    <s:messages/>
     <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan Jabatan Teknikal</legend>
<%--
            <c:if test="${edit}">
                <p>
                    <label for="Negeri">Jabatan :</label>
                    <s:select name="kodAgensi.kod">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>
                    </s:select>
                </p>
                <p>
                    <label>Ulasan :</label>
                    <s:textarea name="fasaPermohonanUlasan.ulasan" rows="3" cols="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>

            <c:if test="${!edit}">
                <p>
                    <label for="Negeri">Jabatan :</label>
                     ${actionBean.kodAgensi.nama}&nbsp;
                </p>
                <p>
                    <label>Ulasan :</label>
                    ${actionBean.fasaPermohonanUlasan.ulasan}&nbsp;
                </p>
            </c:if>--%>


                <p>
                    <label for="Negeri">Jabatan :</label>
                    <s:select name="kodAgensi.kod">
                        <s:option value="">Sila Pilih</s:option>
                        <%--<s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod"/>--%>
                    </s:select>
                </p>
                <p>
                    <label>Ulasan :</label>
                    <s:textarea name="fasaPermohonanUlasan.ulasan" rows="3" cols="40"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
        </fieldset >
    </div>

</s:form>