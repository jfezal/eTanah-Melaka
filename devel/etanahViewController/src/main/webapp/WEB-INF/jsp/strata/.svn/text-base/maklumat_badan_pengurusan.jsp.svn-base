<%-- 
    Document   : maklumat_badan_pengurusan
    Created on : Nov 29, 2012, 5:32:41 PM
    Author     : Afham
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.strata.PertanyaanHakmilikStrataActionBean">
    <div id="bayar">

        <div class="subtitle">

            <fieldset class="aras1">
                <legend>Maklumat Badan Pengurusan</legend>

                <p><label>Nama :</label>
                    ${actionBean.badanUrus.pihak.nama}
                </p>
                <p><label>Alamat :</label>
                    <c:if test="${actionBean.badanUrus.pihak.alamat1 ne null}">
                    ${actionBean.badanUrus.pihak.alamat1}
                    </c:if>
                    <c:if test="${actionBean.badanUrus.pihak.alamat1 eq null}">
                    -
                    </c:if>
                </p>
                <c:if test="${actionBean.badanUrus.pihak.alamat2 ne null}">
                <p><label>&nbsp;</label>
                    ${actionBean.badanUrus.pihak.alamat2}
                </p>
                </c:if>
                <c:if test="${actionBean.badanUrus.pihak.alamat3 ne null}">
                <p><label>&nbsp;</label>
                    ${actionBean.badanUrus.pihak.alamat3}
                </p>
                </c:if>
                <c:if test="${actionBean.badanUrus.pihak.alamat4 ne null}">
                <p><label>&nbsp;</label>
                    ${actionBean.badanUrus.pihak.alamat4}
                </p>
                </c:if>
                 <p><label>Poskod :</label>
                     <c:if test="${actionBean.badanUrus.pihak.poskod ne null}">
                    ${actionBean.badanUrus.pihak.poskod}
                    </c:if>
                     <c:if test="${actionBean.badanUrus.pihak.poskod eq null}">
                    -
                    </c:if>
                </p>
                 <p><label>Negeri :</label>
                    <c:if test="${actionBean.badanUrus.pihak.negeri ne null}">
                    ${actionBean.badanUrus.pihak.negeri.nama}
                    </c:if>
                     <c:if test="${actionBean.badanUrus.pihak.negeri eq null}">
                    -
                    </c:if>
                </p>
                 <p><label>No. Telefon :</label>
                    <c:if test="${actionBean.badanUrus.pihak.noTelefon1 ne null}">
                    ${actionBean.badanUrus.pihak.noTelefon1}
                    </c:if>
                     <c:if test="${actionBean.badanUrus.pihak.noTelefon1 eq null}">
                    -
                    </c:if>
                </p>
                <br>
            </fieldset>
        </div> 
    </div>
</s:form>