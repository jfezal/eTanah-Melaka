<%-- 
    Document   : pindahan_cara_bayaran
    Created on : Jan 28, 2010, 9:33:01 AM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pindaan Cara Bayaran</font>
            </div>
        </td>
    </tr>
</table>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.PindaanCaraBayaranActionBean" id="cara_bayaran">
<stripes:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kutipan</legend>
            <p class=instr>Sila Masukkan ID Hakmilik atau Nombor Resit untuk membuat carian.</p>

            <p id="hakmilik">
                <label>ID Hakmilik :</label>
                <stripes:text name="hakmilik.idHakmilik"/>
            </p>

            <p id="dokKewangan">
                <label>Nombor Resit :</label>
                <stripes:text name="dokumenKewangan.idDokumenKewangan"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" value="Cari" class="btn"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('cara_bayaran');"/>
            </p>
            <br>
        </fieldset>
    </div>

    <c:if test="${actionBean.visible}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <p class=instr>Sila pilih salah satu daripada hasil carian.</p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiTransaksi}" id="line">
                        <display:column title="Pilih">
                            <div align="center"><stripes:radio name="noResit" value="${line.dokumenKewangan.idDokumenKewangan}" />
                            </div>
                        </display:column>
                        <display:column title="ID Hakmilik">
                            <c:if test="${line.akaunKredit.hakmilik.idHakmilik ne null}">${line.akaunKredit.hakmilik.idHakmilik}</c:if>
                            <c:if test="${line.akaunDebit.hakmilik.idHakmilik ne null}">${line.akaunDebit.hakmilik.idHakmilik}</c:if>
                        </display:column>
                        <display:column property="dokumenKewangan.idDokumenKewangan" title="Nombor Resit" />
                        <display:column property="dokumenKewangan.infoAudit.tarikhMasuk" title="Tarikh Bayar" format="{0,date,dd/MM/yyyy}"/>
                        <display:column style="text-align:right" property="dokumenKewangan.amaunBayaran" title="Amaun (RM)" format="{0,number, 0.00}"/>
                    </display:table>
                </div>
            </fieldset>
        </div>

        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                    <stripes:submit name="Step3" value="Seterusnya" class="btn"/>
                    <stripes:submit name="Step1" value="Kembali" class="btn"/>
                </td>
            </tr>
        </table>
    </c:if>
</stripes:form>