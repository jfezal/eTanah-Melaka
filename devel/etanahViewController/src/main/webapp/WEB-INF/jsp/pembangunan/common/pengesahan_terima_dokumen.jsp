<%--
    Document   : KertasMMK_tangguh
    Created on : DEC 20, 2011, 12:44:34 PM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>


</script>

<s:form beanclass="etanah.view.stripes.pembangunan.common.PenerimaanDokumenActionBean">
    <s:hidden name="kes:textrtasK.kertas.idKertas"/>
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>    

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Sila masukkan maklumat penerimaan</legend>
                <p>
                <label>Nama Penyerah:</label>
                <s:text name="namaPenyerah" size="40"/>
                <p>
                <label>No telefon :</label>
                <s:text name="noTel" size="40" />
            </p>
            <p>
                <label>Tarikh Terima :</label>
                <s:text name="tarikhTerima" size="40" class="datepicker"/>
            </p>
            </p>
                
                    <p align="center">                        
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>                                                
                        <s:button name="selesaiTugasan" id="save" value="Selesai" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                    
                    </p>
        </fieldset>
    </div>
</s:form>