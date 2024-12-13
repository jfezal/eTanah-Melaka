<%--
    Document   : nota_daftar_default
    Created on : Dec 22, 2009, 10:05:17 AM
    Author     : mohd.fairul
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Urusaniaga
            </legend>
            <%--<p style="color:red">
                *Isi Yang Berkenaan Sahaja.
            </p>--%>

            <p>
                <label>Nombor Fail / ID Permohonan :</label>
                <s:text name="permohonanRujLuar.noFail"></s:text>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LPBB'
                            or actionBean.permohonan.kodUrusan.kod eq 'LPMB'}">
            <p>
                <label>Sebab Dibatalkan :</label>
                <%--<s:text name="permohonanRujLuar.catatan" ></s:text>--%>
                <s:textarea name="permohonanRujLuar.catatan" rows="5"></s:textarea>
            </p>
            </c:if>
            
        </fieldset>

    </div>
    <br>
    <table style="margin-left: auto; margin-right: auto;">
        <tr>
            <td>&nbsp;</td>
            <td><div >

                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanNofail" value="Simpan"/>


                </div>
            </td>
        </tr>
    </table>

    <br>
</s:form>
