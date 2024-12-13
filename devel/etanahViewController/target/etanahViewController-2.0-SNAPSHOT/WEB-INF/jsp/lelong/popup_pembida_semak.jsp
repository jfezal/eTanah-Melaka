<%-- 
    Document   : popup_pembida_semak
    Created on : Mar 9, 2011, 5:18:32 PM
    Author     : mdizzat.mashrom
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<s:form beanclass="etanah.view.stripes.lelong.KeputusanBidaanActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle">
        <fieldset class="aras1" id="">
            <legend>
                Maklumat Pembida
            </legend>
            <p>
                <label>Nama : </label>
                ${actionBean.pihak.nama}
            </p>
            <p>
                <label>Jenis Pengenalan : </label>
                <font style="text-transform:uppercase;">${actionBean.pihak.jenisPengenalan.nama}
            </p>
            <p>
                <label>Nombor Pengenalan : </label>
                ${actionBean.pihak.noPengenalan}
            </p>
            <p>
                <label>Alamat : </label>
                ${actionBean.pihak.alamat1}
                <c:if test="${actionBean.pihak.alamat2 ne null}"> , </c:if>
                ${actionBean.pihak.alamat2}
                <c:if test="${actionBean.pihak.alamat3 ne null}"> , </c:if>
                ${actionBean.pihak.alamat3}
                <c:if test="${actionBean.pihak.alamat4 ne null}"> , </c:if>
                ${actionBean.pihak.alamat4}
            </p>

            <p>
                <label>Poskod : </label>
                ${actionBean.pihak.poskod}
            </p>
            <p>
                <label>Negeri : </label>
                ${actionBean.pihak.negeri.nama}
            </p>
            <c:if test="${actionBean.pihak.noTelefon1 ne null}">
                <p>
                    <label> Nombor Telefon Rumah : </label>
                    ${actionBean.pihak.noTelefon1}
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.noTelefonBimbit ne null}">
                <p>
                    <label> Nombor Telefon Bimbit : </label>
                    ${actionBean.pihak.noTelefonBimbit}
                </p>
            </c:if>
            <c:if test="${actionBean.pihak.noTelefon2 ne null}">
                <p>
                    <label> Nombor Telefon Pejabat : </label>
                    ${actionBean.pihak.noTelefon2}
                </p>
            </c:if>
            <br>
            <p>
                <label>Tarikh Lelongan Awam : </label>
                <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy" />&nbsp;
            </p>
            <p>
                <label>Tarikh Akhir Bayaran : </label>
                <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy" />&nbsp; (120 hari dari tarikh lelongan)
            </p>
            <p>
                <label> Harga Rizab (RM) : </label>
                <%--berasingan--%>
                <c:if test="${actionBean.bersameke eq false}">
                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}" /> &nbsp;
                </c:if>
                <%--bersama--%>
                <c:if test="${actionBean.bersameke eq true}">
                    <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.hargaRizab}" /> &nbsp;
                </c:if>
            </p>
            <p>
                <label>Harga Bidaan (RM) : </label>
                <s:format formatPattern="#,##0.00" value="${actionBean.hargaBidaan}" /> &nbsp;
            </p>
            <p>
                <label>Deposit (RM) : </label>
                <s:format formatPattern="#,##0.00" value="${actionBean.deposit}" />
            </p>
            <p>
                <label>Tarikh Terima Bayaran Deposit : </label>
                <fmt:formatDate value="${actionBean.atd.tarikhTerima}" pattern="dd/MM/yyyy" />
            </p>
            <p>
                <label>Cara Bayar : </label> ${actionBean.atd.caraBayaran.nama}
            </p>
            <c:if test="${actionBean.kodBank ne null}">
                <p>
                    <label>Bank : </label>
                    ${actionBean.atd.bank.nama}
                </p>
            </c:if>
            <c:if test="${actionBean.atd.noDokumenBayar ne null}">
                <p>
                    <label>No Rujukan : </label>
                    ${actionBean.atd.noDokumenBayar}
                </p>
            </c:if>
            <c:if test="${actionBean.atd2.jenisDeposit eq 'T'}">
                <p>
                    <label>Deposit Tambahan(RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.atd2.amaun}" /> &nbsp;
                </p>
                <p>
                    <label>No Rujukan Tambahan: </label>
                    ${actionBean.atd2.noDokumenBayar} &nbsp;
                </p>
                <p>
                <label>Cara Bayar : </label> ${actionBean.atd2.caraBayaran.nama}
            </p>
            </c:if>
            <p>
                <label>Baki (RM) : </label>
                <s:format formatPattern="#,##0.00" value="${actionBean.baki}" />
            </p>
            <div class="content" align="center">
                <p>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>