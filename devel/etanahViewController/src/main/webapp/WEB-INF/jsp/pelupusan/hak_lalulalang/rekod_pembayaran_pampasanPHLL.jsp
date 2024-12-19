<%-- 
    Document   : rekod_pembayaran_pampasanPHLL
    Created on : Oct 10, 2012, 10:33:51 PM
    Author     : Navin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.RekodPembayaranPampasanPHLLActionBean">

    <s:messages /> 
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rekod Pembayaran Pampasan</legend>
             <br>
            <p>
                <label>Id Hakmilik :</label> ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}
            </p>
             <p>
                <label>No Lot :</label>  ${actionBean.hakmilikPermohonan.noLot}
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listDisMohonPihak}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="/pelupusan/rekod_pembayaran_pampasanPHLL">                           

                    <display:column title="BIL">
                        ${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="permohonanPihak.idPihak"/></display:column>                 
                    <display:column property="permohonanPihak.pihak.nama" title="NAMA"/>
                    <display:column property="permohonanPihak.pihak.noPengenalan" title="NO PENGENALAN" />
                    <display:column property="permohonanPihak.nilai" title="NILAI (RM)" />
                </display:table>
              </div>
            <p>
                <label>Cara Bayar :</label>
                <s:select name="kodCaraBayaran.kod" style="width:300px;" id="kodCaraBayaran" >
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:option value="CT">Cek Tempatan</s:option>
                    <s:option value="CL">Cek Luar</s:option>
                    <s:option value="CB">Cek Bank Negara</s:option>
                    <s:option value="DB">Bank Draf</s:option>
                </s:select>
            </p>
            <p>
                <label>Nama Bank :</label>
                <s:select name="kodBank.kod" style="width:300px;" id="kodBank" >
                    <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                </s:select>
            </p>
            <p>
                <label>No. Cek :</label>
                <s:text name="noDok" size="50" id="nodok"/>
            </p>
            <p>
                <label>Tarikh Terima Cek :</label>
                <s:text name="tarikhDok" class="datepicker" id="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <br/>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanMaklumatPembayaranPampasan" id="save" value="Rekod" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>   
        </fieldset>
    </div>

</s:form>           
