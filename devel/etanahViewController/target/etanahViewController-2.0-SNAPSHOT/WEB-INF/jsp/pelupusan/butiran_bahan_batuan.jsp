<%-- 
    Document   : butiran_bahan_batuan
    Created on : Apr 22, 2010, 6:41:19 PM
    Author     : sitifariza.hanim
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranBahanBatuanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bahan Batuan Dipohon</legend>
           
             <p>
                <label>Jenis Bahan Batuan :</label>
                <s:select name="jenisBahanBatu.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBahanBatu}" label="nama" value="nama" />
                </s:select>
            </p>
            <p>
                <label>Kawasan/Tempat diambil bahan batuan (Nombor Lot (Jika ada)) :</label>
                <s:text name="permohonanBahanBatuan.kawasanAmbilBatuan" size="50"/>
            </p>
             <p>
                <label>Kawasan/Tempat dipindah bahan batuan (Nombor Lot (Jika ada)) :</label>
                <s:text name="permohonanBahanBatuan.kawasanPindahBatuan" size="50"/>
            </p>
            <p>
                <label>Jalan yang dilalui :</label>
                <s:text name="permohonanBahanBatuan.jalanDilalui" size="50"/>
            </p>
            <p>
                <label>Tempoh pengeluaran bahan batuan :</label>
                <s:text name="tarikhMula"  id= "datepicker" class="datepicker" />
                <label>&nbsp;</label>hingga<label>&nbsp;</label>
                <s:text name="tarikhTamat" id= "datepicker2" class="datepicker"/>
            </p>
            <p>
                <label>Tempoh :</label>
                <s:text name="permohonanBahanBatuan.tempohKeluar" size="20"/>
                <s:select name="unitTempohKeluarUOM" value="${actionBean.permohonanBahanBatuan.unitTempohKeluar.kod}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="HR">Hari</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="T">Tahun</s:option>
                </s:select>
            </p>
            <p>
                <label>Jumlah isipadu bahan batuan yang dipohon (METER PADU):</label>
                <s:text name="permohonanBahanBatuan.jumlahIsipadu" size="50"/>
            </p>
      
            <%--<p>
                <label>Jumlah isipadu bahan batuan yang dikeluarkan :</label>
                <s:text name="isipaduBahan" size="50"/>
            </p>
            <label>&nbsp;</label>
            <p>
                <label>Anggaran muatan 1 lori :</label>
                <s:text name="muatan" size="30"/><label>&nbsp;</label>meter padu
            </p>--%>
         <%--   <p>
                <label>Bilangan mesin yang digunakan :</label>
                <s:text name="bilMesin" size="50"/><br>&nbsp;<br/>
            </p>--%>

          <%--  <legend>Butir-butir Mesin Yang Digunakan</legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_pemohon">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                    </display:column>
                    <display:column property="pihak.jenisLori" title="Jenis Lori yang digunakan"/>
                    <display:column property="pihak.noPendaftaran" title="Nombor Pendaftaran" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="remove('${line.idPemohon}')">
                        </div>
                    </display:column>
                </display:table>
            </div><label>&nbsp;</label>--%>

            <%--<p>
                <label>Kapasiti pengeluaran mesin: pemecah utama <br> (Batu Kuari)</label>
                <s:text name="kapasitiBahan" size="30"/>metrik ton
            </p>--%>
            <p>
                <s:reset name="reset" value="Isi Semula" class="btn"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
             
        </fieldset>
    </div>
</s:form>
