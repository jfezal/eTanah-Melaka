<%-- 
    Document   : tandatangan_dokumen
    Created on : Jun 27, 2011, 10:12:58 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready( function() {
    });

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pengambilan.share.common.TandatanganBorangActionBean">
    <s:messages/>
    <div align="center"> 
        <fieldset class="aras1">
            <legend>Tandatangan Dokumen Manual </legend>
            <span style="text-align: left">${actionBean.namaDokumen}</span>
            <s:hidden name="kodNotis"/>
             <s:hidden name="kodNotis1"/>
            <p>Ditandatangan Oleh :
                <s:select name="tandatangan" id="namapguna">
                                               <s:option label="Sila Pilih" value="" />
                                                <!--penggunaList                          -->
                                               
                        <s:option value="">-- Sila Pilih --</s:option>
                    <s:options-collection collection="${actionBean.listPenggunaPeranan}" value="pengguna.idPengguna" label="pengguna.nama" />
                                           </s:select>
                </p>
                <div id="buttontandatangan" align="center">
                    <s:hidden name="kodDokumen"/>
                <s:button name="simpanTandatangan" class="longbtn" value="Simpan Tandatangan" onclick="doSubmit(this.form,this.name,'page_div')"/>
            </div>
            <br>

        </fieldset>
    </div>

</s:form>