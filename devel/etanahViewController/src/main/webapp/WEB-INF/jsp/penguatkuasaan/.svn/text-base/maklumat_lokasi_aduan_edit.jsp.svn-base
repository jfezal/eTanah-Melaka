<%-- 
    Document   : borang_lokasi_aduan
    Created on : Jan 18, 2010, 4:09:02 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>

<script type="text/javascript">

    function validateForm(){

        if($('#bandarP').val() == ''){
            alert("Sila pilih Bandar/Pekan/Mukim");
            return false;
        }
        if($('#pkod').val() == ''){
            alert("Sila pilih jenis tanah");
            return false;
        }
        self.opener.refreshPageCeroboh();
        self.close();
    }


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLokasiActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Lokasi Kejadian
            </legend>
            <div class="content">
                <p>
                    <label>ID</label>${actionBean.idAduanLokasi}&nbsp;
                </p>
                <p>
                    <label>Tempat Aduan :</label>
                    ${actionBean.permohonan.cawangan.name}&nbsp;
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <s:select name="bandarPekanMukim.kod" value="${actionBean.aduanLokasi.bandarPekanMukim.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p>
                    <label>Jenis Tanah :</label>
                    <s:select name="pemilikan.kod" value="${actionBean.aduanLokasi.pemilikan.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodPemilikan}" label="nama" value="kod" sort="nama" />
                    </s:select>
                    &nbsp;
                </p>
                <p>
                    <label>Nombor Lot :</label>
                    <s:text name="aduanLokasi.noLot"/> &nbsp;
                </p>
                <p>
                    <label>Lokasi :</label>
                    <s:textarea name="aduanLokasi.lokasi" rows="5" cols="50"/>&nbsp;
                </p>

                <br>
                <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idAduanLokasi" value="${actionBean.aduanLokasi.idAduanLokasi}"/>
                    <s:submit name="simpanSingle" id="simpan" value="Simpan" class="btn" onclick="return validateForm()"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>