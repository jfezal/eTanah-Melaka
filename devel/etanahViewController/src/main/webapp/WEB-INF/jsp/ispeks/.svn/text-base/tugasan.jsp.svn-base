<%--    
    Document   : speks
    Created on : Jul 22, 2010, 4:21:36 PM
    Author     : amir.muhaimin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<title>Fail SPEKS</title>
<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">SPEKS</font>
                    </div></td></tr>
        </table><br>
    </div><br>
    <script type="text/javascript">


        function validateUser(pe, id, url2) {
          
            var url = '${pageContext.request.contextPath}/ispeks/tugasan?validate&peringkat=' + pe + '&id=' + id;
            $.ajax({
                type: "GET",
                url: url,
                success: function (data) {
                    if (data == '1') {
                        alert('Anda adalah Penyedia untuk tugasan ini.');
                    } else if (data == '2') {
                        alert('Anda adalah Penyedia atau Penyemak untuk tugasan ini.');
                    } else {
                        window.location.href = '${pageContext.request.contextPath}' + url2;
                    }
                }
            });

        }


        function click_clear() {
            document.formSPEKS.datepicker.value = "";
        }
    </script>
    <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <stripes:messages />
    <stripes:errors />
    <stripes:form beanclass="etanah.view.stripes.ispeks.TugasanIspeksActionBean" id="formSPEKS" name="formSPEKS">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Penyata Pemungut </legend>

                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiTugasanPP}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Id Tugasan" ><a href="#" onclick="return validateUser('${line.kodPeringkat}', '${line.id}', '${line.url}');">${line.id}</a></display:column>
                        <display:column title="Jenis Tugasan"  >${line.urusan}
                            <c:if test="${line.urusan eq 'Penyata Pemungut'}"><p><table class="tablecloth" style="width: 80%">
                                <!--<tr><th>Cara Bayar</th><th>Jumlah</th></tr>-->
                                <tr><td style="width: 30%"><b>Cara Bayar</b></td><td>: ${line.jenisBayar}</td></tr>
                                <tr><td><b>Jumlah</b> </td><td>: RM <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/></td></tr>
                            </table></p></c:if></display:column>
                    <display:column title="Peringkat" property="peringkat" style="text-transform:uppercase;"/>
                </display:table>
                </p>
            </fieldset>
            <fieldset class="aras1">
                <legend>Jornal </legend>

                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiTugasanJR}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Id Tugasan" ><a href="${pageContext.request.contextPath}${line.url}">${line.id}</a></display:column>
                        <display:column title="Jenis Tugasan"  >${line.urusan}
                            <c:if test="${line.urusan eq 'Penyata Pemungut'}"><p><table class="tablecloth" style="width: 80%">
                                <!--<tr><th>Cara Bayar</th><th>Jumlah</th></tr>-->
                                <tr><td style="width: 30%"><b>Cara Bayar</b></td><td>: ${line.jenisBayar}</td></tr>
                                <tr><td><b>Jumlah</b> </td><td>: RM <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/></td></tr>
                            </table></p></c:if></display:column>
                    <display:column title="Peringkat" property="peringkat" style="text-transform:uppercase;"/>
                </display:table>
                </p>
            </fieldset>
            <fieldset class="aras1">
                <legend>Bil </legend>

                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiTugasanBL}"  cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Id Tugasan" ><a href="${pageContext.request.contextPath}${line.url}">${line.id}</a></display:column>
                        <display:column title="Jenis Tugasan"  >${line.urusan}
                            <c:if test="${line.urusan eq 'Penyata Pemungut'}"><p><table class="tablecloth" style="width: 80%">
                                <!--<tr><th>Cara Bayar</th><th>Jumlah</th></tr>-->
                                <tr><td style="width: 30%"><b>Cara Bayar</b></td><td>: ${line.jenisBayar}</td></tr>
                                <tr><td><b>Jumlah</b> </td><td>: RM <fmt:formatNumber value="${line.amaun}" pattern="#,##0.00"/></td></tr>
                            </table></p></c:if></display:column>
                    <display:column title="Peringkat" property="peringkat" style="text-transform:uppercase;"/>
                </display:table>
                </p>
            </fieldset>
            <p>
                <label>&nbsp;</label>
                <stripes:hidden name="idPenyataPemungut"/>
                <stripes:hidden name="idTugasan"/>
                <!-- stripes:button name="reset" value="Isi Semula" class="btn" onclick="click_clear();"/ -->
                <%--<stripes:submit id="mt" name="downloadFile" value="Muatturun" class="btn"/>--%>
            </p>
        </div>
    </stripes:form>
</div>


