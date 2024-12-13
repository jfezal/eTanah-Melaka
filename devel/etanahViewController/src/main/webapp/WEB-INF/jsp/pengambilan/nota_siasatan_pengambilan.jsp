<%-- 
    Document   : nota_siasatan_pengambilan
    Created on : 25-Mar-2010, 09:29:29
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.NotaSiasatanActionBean">
     <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan
            </legend>
            <p align="center">

                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="No. Hakmilik" >${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <%--<display:column title="Luas Diambil"><s:text name="luasTerlibat[${line_rowNum - 1}]"/>
                    <s:select name="kodUnitLuas[${line_rowNum-1}]"><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                    </s:select>
                    </display:column>--%>
                </display:table>
                     </fieldset>
    </div>

             <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Nota Siasatan
                    </legend>
                    <div class="content" align="left">
                        <table align="left" border="0" width="100%">
                            <tr>
                                <td align="left" width="30%"><b>Tarikh penyempurnaan notis dibawah Sek 52 </b>:</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%"><b>Kehadiran</b> :</td>
                                <td align="left" width="70%">
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="pilih" title="Pilih"/>
                        <display:column property="No" title="No"/>
                        <display:column property="Nama" title="Nama" href="#"/>
                        <display:column property="No KP" title="No KP"/>
                        <display:column property="alamat" title="Alamat"/>
                        <display:column property="poskod" title="Poskod"/>
                        <display:column property="Negeri" title="Negeri"/>
                        <display:column property="tel" title="Telefon"/>
                        <display:column property="jawatan" title="Jawatan"/>
                        </display:table>
                                <s:button name="savePengambilanInfo" value="Tambah" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                                <s:button name="savePengambilanInfo" value="Hapus" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/></td>
                              
                            </tr>
                            <tr>
                                <td align="left" width="30%">1. Tarikh pemilikan Tanah :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <br>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Cara pemilikan Tanah :</td>
                                <td align="left" class="input1" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <br>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Harga pembelian Tanah :</td>
                                <td align="left" class="input1" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">2. Lokasi Tanah :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Jauh dengan jalan dan pekan/bandar :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">3. Keadaan Tanah :</td>
                                <td align="left" class="input1" width="70%"><s:text name="fromDate" id="datepicker" class="datepicker"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Komen :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/> </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">3. Keadaan Tanah :</td>
                                <td align="left" class="input1" width="70%">
                                    <input type="radio" name="keadaantanah" value="Ada" />Rata&nbsp;
                                    <input type="radio" name="keadaantanah" value="Tiada" />Paya&nbsp;
                                    <input type="radio" name="keadaantanah" value="Tiada" />berbukit&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Komen :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">4. Jenis tanaman :</td>
                                <td align="left" class="input1" width="70%">
                                    <input type="radio" name="keadaantanah" value="Ada" />Tiada&nbsp;
                                    <input type="radio" name="keadaantanah" value="Tiada" />Ada&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Komen :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">   Bangunan :</td>
                                <td align="left" class="input1" width="70%">
                                    <input type="radio" name="keadaanbangunan" value="Ada" />Tiada&nbsp;
                                    <input type="radio" name="keadaanbangunan" value="Tiada" />Ada&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Komen :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">5. Permohonan tukar syarat/pecah sempadan.Jika ada,bila dan bagaimana kedudukan sekarang :</td>
                                <td align="left" class="input1" width="70%">
                                    <input type="radio" name="keadaantanah" value="Ada" />Tiada&nbsp;
                                    <input type="radio" name="keadaantanah" value="Tiada" />Ada&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Tarikh Permohonan Tukar Syarat/Pecah Sempadan :</td>
                                <td align="left" class="input1" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Kedudukan Terkini :</td>
                                <td align="left" class="input1" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">6. Gadaian/Pajakan/Perjanjian Jualbeli :</td>
                                <td align="left" class="input1" width="70%">
                                    <input type="radio" name="keadaantanah" value="Ada" />Tiada&nbsp;
                                    <input type="radio" name="keadaantanah" value="Tiada" />Ada&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">7. Tuntutan jumlah pampasan :</td>
                                <td align="left" class="input1" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Alasan :</td>
                                <td align="left" class="input1" width="70%"><s:textarea name="tujuan1" cols="87" rows="8"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Keterangan Agensi Pemohon :</td>
                                <td align="left" class="input1" width="70%"><s:textarea name="tujuan2" cols="87" rows="8"/></td>
                            </tr>
                             <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Keterangan Pegawai Penilaian(Jika Ada Perubahan) :</td>
                                <td align="left" class="input1" width="70%"><s:textarea name="tujuan2" cols="87" rows="8"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Lain-lain Hal :</td>
                                <td align="left" class="input1" width="70%"><s:textarea name="tujuan3" cols="87" rows="8"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">&nbsp;&nbsp;&nbsp;&nbsp;Perintah :</td>
                                <td align="left" class="input1" width="70%"><s:textarea name="tujuan1" cols="87" rows="8"/></td>
                            </tr>
                        </table>
                            <br>
                            <br>
                            <br>
                                <p align="center"> <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  /></p>
                    </div>
                </fieldset>
            </div>
</s:form>
             

