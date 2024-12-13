<%--
    Document   : Maklumat_Penerimaan_Pembayaran_Pampasan
    Created on : Dec 1, 2011, 10:07:50 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPenerimaanBayaranPampasanActionBean">

    <s:messages /> 
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>MAKLUMAT PENERIMAAN BAYARAN PAMPASAN</legend>

            <br/><br/>
            <table border="0" align="left"> <tr><td>

                        <table border="0" align="left"><tr><td><b><font color="blue"> ID HAKMILIK :</font></b>
                                        ${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}

                                </td></tr> 

                            <tr><td></td></tr>

                            <tr><td><b><font color="blue">NO LOT:</font></b>
                                        ${actionBean.hakmilikPermohonan.noLot}
                                </td></tr></td></tr></table>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.permohonanPihak}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="/pelupusan/maklumat_penerimaan_pembayaran_pampasan">    

                    <display:column title="BIL">
                        
                        ${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="permohonanPihak.idPihak"/>
                        
                    </display:column>
                    <display:column property="pihak.nama" title="NAMA"/>
                    <display:column property="pihak.noPengenalan" title="NO PENGENALAN" />
                    <display:column title="STATUS TERIMA">
                        
                        <s:radio name="checkmate" id="checkmate" value="YA"/> Ya &nbsp;
                        <s:radio name="checkmate" id="checkmate" value="TIDAK"/> Tidak
                    
                    </display:column>               
                    <display:column title="TARIKH TERIMA"> 
                        
                        <s:text formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhTerima}" class="datepicker" name="date"/>
                        
                    
                    </display:column>                
                </display:table>
            </div  > </td></tr>      
            </td></tr>
            </table>        
        </fieldset>
    </div

    <td align="center">
        <s:button name="simpanPenerimaanPembayaranPampasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
    </td>

</s:form>                  