<%--
    Document   : Maklumat_Pembayaran_Pampasan
    Created on : Dec 1, 2011, 10:07:50 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPembayaranPampasanActionBean">

    <s:messages /> 
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>MAKLUMAT PEMBAYARAN PAMPASAN</legend>
             <br>
            <p>
                <label>ID HAKMILIK :</label> ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}
            </p>
             <p>
                <label>NO LOT:</label>  ${actionBean.hakmilikPermohonan.noLot}
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listDisMohonPihak}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pelupusan/maklumat_pembayaran_pampasan">                           

                    <display:column title="BIL">
                        ${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="permohonanPihak.idPihak"/></display:column>                 
                    <display:column property="permohonanPihak.pihak.nama" title="NAMA"/>
                    <display:column property="permohonanPihak.pihak.noPengenalan" title="NO PENGENALAN" />
                    <display:column property="permohonanPihak.nilai" title="NILAI (RM)" />
                    <%--
                    <display:column property="" title="CARA BAYAR" />                    
                    <display:column property="" title="NAMA BANK" />
                    <display:column property="" title="NO CEK" />
                    <display:column property="" title="TARIKH CEK" />
                    --%>
                </display:table>
              </div>
            <p>
                <label>Cara Bayar :</label>${actionBean.caraBayar.kodCaraBayaran.nama}
            </p>
            <p>
                <label>Nama Bank :</label>${actionBean.caraBayar.bank.nama}
            </p>
            <p>
                <label>No Cek :</label>${actionBean.caraBayar.noRujukan}
            </p>
            <p>
                <label>Tarikh Cek :</label><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.caraBayar.tarikhCek}"/>
            </p>
<!--            <p>
                <label>&nbsp;</label>
                <s:button name="simpanMaklumatPembayaranPampasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>    -->
        </fieldset>
    </div>

</s:form>           