<%-- 
    Document   : pemohonview
    Created on : Sep 2, 2010, 12:59:41 PM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Maklumat Pemohon</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonList}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama"/>
                    <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Alamat Majikan" >${line.institusiAlamat1}
                        <c:if test="${line.institusiAlamat2 ne null}"> , </c:if>
                        ${line.institusiAlamat2}
                        <c:if test="${line.institusiAlamat3 ne null}"> , </c:if>
                        ${line.institusiAlamat3}
                        <c:if test="${line.institusiAlamat4 ne null}"> , </c:if>
                        ${line.institusiAlamat4}</display:column>
                    <display:column property="institusiPoskod" title="Poskod" />
                    <display:column property="institusiNegeri.nama" title="Negeri" />
                    <%--<display:column title="Alamat" >${line.pihak.suratAlamat1}
                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat2}
                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat3}
                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , </c:if>
                        ${line.pihak.suratAlamat4}</display:column>
                    <display:column property="pihak.suratPoskod" title="Poskod" />
                    <display:column property="pihak.suratNegeri.kod" title="Negeri" />--%>
                    <display:column property="pihak.noTelefon1" title="Nombor Telefon"/>
                   <%-- <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                           <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                        </div>
                    </display:column>--%>
                </display:table>
            </div>
        </fieldset>
    </div>
</s:form>

