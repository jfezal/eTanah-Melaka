<%-- 
    Document   : bahan_batuan_ns
    Created on : Jul 30, 2010, 7:05:46 PM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranBahanBatuanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tujuan Permohonan:-</legend><br/>
            <p> (a) Mengeluarkan bahan batuan<br/>
                (b) Memproses bahan batuan<br/>
                (c) Memindahkan bahan batuan
            </p>
           <%--  <p><label>Tujuan Permohonan :</label>
                <s:select name="tujuan">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="">Mengeluarkan bahan batuan</s:option>
                    <s:option value="">Memproses bahan batuan</s:option>
                    <s:option value="">Memindahkan bahan batuan</s:option>
                </s:select>
            </p>--%>
        </fieldset>
    </div><br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bahan Batuan Dipohon</legend>
            <s:hidden name="permohonan.cawangan.daerah.nama" value="${actionBean.permohonan.cawangan.daerah.nama}"/>
            <s:hidden name="hakmilikPermohonan.bandarPekanMukimBaru.nama" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}"/>
           <%-- <p>
                <label>Daerah :</label>
                ${actionBean.permohonan.cawangan.daerah.nama}
            </p>
            <p>
                    <label>Mukim/Pekan/Bandar :</label>
                    ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
             </p>--%>
             <%--<p>
                    <label><font color="red">*</font>Mukim/Pekan/Bandar :</label>
                    <s:select name="bandarPekanMukimBaru.kod" value="${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.kod}" id="mpb">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodBPM}" label="nama" value="kod" />
                    </s:select>
             </p>--%>
             <p>
                <label>Jenis :</label>
                <%--<s:text name="permohonanBahanBatuan.jenisBahanBatu" size="50"/>--%>
                <s:select name="jenisBahanBatu.kod" value="${actionBean.permohonanBahanBatuan.jenisBahanBatu.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBahanBatu}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>Kawasan pengambilan :</label>
                <s:text name="permohonanBahanBatuan.kawasanAmbilBatuan" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No. Lot (Jika ada))
            </p>
             <p>
                <label>Kawasan pemindahan :</label>
                <s:text name="permohonanBahanBatuan.kawasanPindahBatuan" size="20" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;(No.Lot (Jika ada))
            </p>
            <p>
                <label>Jalan yang dilalui :</label>
                <s:text name="permohonanBahanBatuan.jalanDilalui" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <%--<p>--Melaka--
                <label>Bina Bangunan Sementara:</label>
                <s:select name="permohonanBahanBatuan.bangunanSementara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="">Akan membina</s:option>
                    <s:option value="">Tidak akan menbina</s:option>
                </s:select>&nbsp;(Jika ada sila nyatakan ukuran bangunan tersebut)
            </p>
            <p>
                <label>Lebar:</label>
                <s:text name="permohonanBahanBatuan.lebarBangunanSementara" id="lebar" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                <s:select name="lebarBangunanSementara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="KK">Kaki</s:option>
                    <s:option value="MT">Meter</s:option>
                </s:select>
            </p>
            <p>
                <label>Panjang:</label>
                <s:text name="permohonanBahanBatuan.panjangBangunanSementara" id="panjang" size="20" maxlength="20" onkeyup="validateNumber(this,this.value);"/>
                <s:select name="panjangBangunanSementara">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="KK">Kaki</s:option>
                    <s:option value="MT">Meter</s:option>
                </s:select>
            </p>--%>
            
            <p>
                <label>Tempoh pengeluaran :</label>
                <s:text name="permohonanBahanBatuan.tarikhMula"  id= "datepicker" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
                <%--&nbsp;hingga&nbsp;--%>
                &nbsp;<font color="#003194"><b>hingga</b></font>&nbsp;
                <s:text name="permohonanBahanBatuan.tarikhTamat" id= "datepicker2" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label>Jangka Masa :</label>
                <s:text name="permohonanBahanBatuan.tempohKeluar" size="20" maxlength="3" onkeyup="validateNumber(this,this.value);" value="${actionBean.permohonanBahanBatuan.tempohKeluar}"/>
                <s:select name="unitTempohKeluarUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar}">
                    <s:option value="">Sila Pilih</s:option>
                     <s:option value="H">Hari</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="T">Tahun</s:option>
                </s:select>
            </p>
            <p>
                <label>Jumlah isipadu :</label>
                <s:text name="permohonanBahanBatuan.jumlahIsipadu" size="20" maxlength="6" onkeyup="validateNumber(this,this.value);"/>
                &nbsp;meterpadu
                <%--&nbsp;<font color="#003194"><b>meterpadu</b></font>&nbsp;--%>
            </p>
            <p>
                <label>Bilangan Pekerja :</label>
                <s:text name="permohonanBahanBatuan.bilanganPekerja" size="20" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                &nbsp;orang
                 <%--&nbsp;<font color="#003194"><b>orang</b></font>&nbsp;--%>
            </p>
           <br/>
            <p>
                <label>&nbsp;</label>
                <%--<s:reset name="reset" value="Isi Semula" class="btn"/>--%>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>

        </fieldset>
    </div>
</s:form>



