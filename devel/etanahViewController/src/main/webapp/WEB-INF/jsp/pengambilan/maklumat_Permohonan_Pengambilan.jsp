<%--
    Document   : maklumat_Permohonan_Pengambilan
    Created on : Feb 01, 2012, 1:24:38 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pengambilan.MaklumatPermohonanPengambilanActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="Permohonan">Permohonan :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.kodUrusan.nama}</font>&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
        </fieldset>
    </div>

    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BTADT' && !edit}">
        <br/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Terdahulu</legend>
                <p>
                    <label>Nama Permohonan :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.kodUrusan.nama}</font>&nbsp;
                </p>
            </fieldset>
        </div>
    </c:if>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label>Nama :</label>
                <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahNama}</font>&nbsp;
            </p>
            <p>
                <label>Jenis Penyerah :</label>
                <font style="text-transform:uppercase;">
                    <c:if test="${actionBean.permohonan.kodPenyerah.nama ne null}"> ${actionBean.permohonan.kodPenyerah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.permohonan.kodPenyerah.nama eq null}"> Tiada Data </c:if>
                </font>
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat1 ne null}">
                <p>
                    <label>Alamat :</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat1}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat2}</font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;"> ${actionBean.permohonan.penyerahAlamat3} </font> &nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label> &nbsp;</label>
                    <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahAlamat4}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahEmail ne null}">
                <p>
                    <label>Email :</label>
                    <font style="text-transform: lowercase">${actionBean.permohonan.penyerahEmail}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahEmail eq null}">
                <p>
                    <label>Email :</label>TIADA DATA
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahNoTelefon1 ne null}">
                <p>
                    <label>No. Telefon :</label>
                    <font style="text-transform: lowercase">${actionBean.permohonan.penyerahNoTelefon1}</font>&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahNoTelefon1 eq null}">
                <p>
                    <label>No. Telefon :</label>TIADA DATA
                </p>
            </c:if>
            <p>
                <label>Poskod :</label>
                ${actionBean.permohonan.penyerahPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                <font style="text-transform:uppercase;">${actionBean.permohonan.penyerahNegeri.nama} </font>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>
