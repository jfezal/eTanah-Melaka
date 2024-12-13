<%-- 
    Document   : borang_7C_TSPSS
    Created on : Aug 18, 2011, 2:22:57 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
        text-align:left;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
    }
</style>

<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatTSPSSActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penyediaan Borang 7C
            </legend>
            <div class="content" align="left">
                <p><b><font color="blue">Pengubahan Kategori Penggunaan Tanah :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.hakmilikPermohonan.hakmilik.kategoriTanah.kod ne null}">${line.noPlot}</c:if></display:column>
                    <display:column title="Daripada" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.kategoriTanah.kod ne null}">${line.hakmilikPermohonan.hakmilik.kategoriTanah.nama}</c:if></display:column>
                    <display:column title="Kepada" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.kategoriTanah.kod ne null}">${line.kategoriTanah.nama}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pengenaan Kategori Penggunaan Tanah :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.hakmilikPermohonan.hakmilik.kategoriTanah.kod eq null}">${line.noPlot}</c:if></display:column>
                    <display:column title="Jenis Kategori" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.kategoriTanah.kod eq null}">${line.kategoriTanah.nama}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pembatalan Syarat Nyata :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.kodSyaratNyata.kod eq '00000000' || line.kodSyaratNyata.kod eq '00000001' || line.kodSyaratNyata.kod eq '00000002' || line.kodSyaratNyata.kod eq '00000003'}">${line.noPlot}</c:if></display:column>
                    <display:column title="Syarat Nyata" style="vertical-align:baseline"><c:if test="${line.kodSyaratNyata.kod eq '00000000' || line.kodSyaratNyata.kod eq '00000001' || line.kodSyaratNyata.kod eq '00000002' || line.kodSyaratNyata.kod eq '00000003'}">${line.kodSyaratNyata.syarat}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pembatalan Ungkapan "Padi", "Getah", "Kampung", dsb :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.ungkapanBatal ne null}">${line.noPlot}</c:if></display:column>
                    <display:column title="Batal Ungkapan" style="vertical-align:baseline"><c:if test="${line.ungkapanBatal ne null}">${line.ungkapanBatal}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pindaan Syarat Nyata :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000000' && line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000001' && line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000002' && line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000003'}">${line.noPlot}</c:if></display:column>
                    <display:column title="Syarat Nyata" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000000' && line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000001' && line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000002' && line.hakmilikPermohonan.hakmilik.syaratNyata.kod ne '00000003'}">${line.kodSyaratNyata.syarat}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pengenaan Syarat Nyata Baru :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000000' || line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000001' || line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000002' || line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000003'}">${line.noPlot}</c:if></display:column>
                    <display:column title="Syarat Nyata" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000000' || line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000001' || line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000002' || line.hakmilikPermohonan.hakmilik.syaratNyata.kod eq '00000003'}">${line.kodSyaratNyata.syarat}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pembatalan Sekatan Kepentingan :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.kodSekatanKepentingan.kod eq '00000000' || line.kodSekatanKepentingan.kod eq '00000001' || line.kodSekatanKepentingan.kod eq '00000002' || line.kodSekatanKepentingan.kod eq '00000003'}">${line.noPlot}</c:if></display:column>
                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline"><c:if test="${line.kodSekatanKepentingan.kod eq '00000000' || line.kodSekatanKepentingan.kod eq '00000001' || line.kodSekatanKepentingan.kod eq '00000002' || line.kodSekatanKepentingan.kod eq '00000003'}">${line.kodSekatanKepentingan.sekatan}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pindaan Sekatan Kepentingan :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne'00000000' && line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000001' && line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000002' && line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000003'}">${line.noPlot}</c:if></display:column>
                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000000' && line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000001' && line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000002' && line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod ne '00000003'}">${line.kodSekatanKepentingan.sekatan}</c:if></display:column>
                </display:table>
                <br>
                <p><b><font color="blue">Pengenaan Sekatan Kepentingan Baru :</font></b></p>
                <display:table class="tablecloth" name="${actionBean.senaraiPlotPelan}" cellpadding="0" cellspacing="0"
                               requestURI="/pembangunan/melaka/maklumat_TSPSS" id="line">
                    <display:column title="No. Plot" style="vertical-align:baseline;width:10%"><c:if test="${line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '00000000' || line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '00000001' || line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '000000002' || line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '00000003'}">${line.noPlot}</c:if></display:column>
                    <display:column title="Sekatan Kepentingan" style="vertical-align:baseline"><c:if test="${line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '00000000' || line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '00000001' || line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '000000002' || line.hakmilikPermohonan.hakmilik.sekatanKepentingan.kod eq '00000003'}">${line.kodSekatanKepentingan.sekatan}</c:if></display:column>
                </display:table>
            </div>
        </fieldset>
    </div>
</s:form>
