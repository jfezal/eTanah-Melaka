<%--
    Document   : lot_index
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>
<script type="text/javascript">
   
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.pelupusan.IndexActionBean">

    <s:messages />
    <s:errors />
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Fahrasat Penyelesaian Tanah
            </legend>
            <br>
            <p>
                <label>Nombor Lot :</label>
                <s:text name="noPT" />
            </p>
            <p>
                <label>Nombor Pelan :</label>
                <s:text name="noSyif" />
            </p>
            <p>
                <label>Nombor Permohonan / Hakmilik Terdahulu :</label>
                <s:text name="noSyif" />
            </p>
            &nbsp;
            <p>
                <label>Pendaftaran Hakmilik :</label>
                <s:text name="noSyif" />
            </p>
            <p>
                <label>Pengeluaran Suratan Hakmilik:</label>
                <s:text name="noSyif" />
            </p>
            <p>
                <label>Jenis / No. Hakmilik yang didaftarkan :</label
                <s:text name="noSyif" />
            </p>
        </fieldset>
    </div>--%>

                 <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Fahrasat Penyelesaian Tanah
            </legend>
            <br/>
            <table border="0">
             <tr>
                <td>Nombor Lot</td>
                <td>:</td>
                <td>  <c:if test="${actionBean.hakmilikBaru.noLot ne null}">
                ${actionBean.hakmilikBaru.noLot}
                </c:if></td>
            </tr>
            <tr>
                <td>Nombor Pelan </td>
                <td>:</td>
                 <td>  <c:if test="${actionBean.hakmilikBaru.noPelan ne null}">
                ${actionBean.hakmilikBaru.noPelan}
                </c:if>
                 </td>
            </tr>
            
            <tr>
                <td>Nombor Permohonan / Hakmilik Terdahulu </td>
                <td>:</td>
                 <td><c:if test="${actionBean.permohonanSebelum.idPermohonan ne null}">
                ${actionBean.permohonanSebelum.idPermohonan}
                     </c:if>
                 </td>
            </tr>
             <tr>

                <td>Tarikh Penerimaan Pelan </td>
                <td>:</td>
                <td>

                </td>
            </tr>
            <tr>

                <td>Pendaftaran Hakmilik </td>
                <td>:</td>
                <td><c:if test="${actionBean.hakmilikBaru.tarikhDaftar ne null}">
                        <s:format value="${actionBean.hakmilikBaru.tarikhDaftar}" formatPattern="dd/MM/yyyy"/>
                     </c:if>
                 
                </td>
            </tr>

            <tr>

                <td>Pengeluaran Suratan Hakmilik</td>
                <td>:</td>
                <td></td>
            </tr>
            <tr>
            <td>Jenis / No. Hakmilik yang didaftarkan/td>
            <td>:</td>
            <td> <c:if test="${actionBean.hakmilikBaru.kodHakmilik.kod ne null}">
               ${actionBean.hakmilikBaru.kodHakmilik.kod} - ${actionBean.hakmilikBaru.kodHakmilik.nama}
                </c:if> /
                  <c:if test="${actionBean.hakmilikBaru.noHakmilik ne null}">
                ${actionBean.hakmilikBaru.noHakmilik}
                </c:if>
            </td>
            </tr>

             </table>
        <br/>
        <br/>

        </fieldset>
    </div>
</s:form>
