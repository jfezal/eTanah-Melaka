<%-- 
    Document   : laporan_lawatan_tapak
    Created on : Jan 18, 2010, 2:43:10 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Lawatan Tapak Dan Siasatan
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Tarikh Lawatan :</td>
                        <td class="input1">
                            <s:text name="tkhMula" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhMula"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Lokasi Tanah :</td>
                        <td class="input1"><s:textarea name="lokasi" rows="5" cols="50" /></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tanah Pekan / Bandar / Desa :</td>
                        <td class="input1"><s:textarea name="jarak_bandar" rows="5" cols="50" /></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Jalan Keluar Masuk :</td>
                        <td class="input1"><s:text name="jalan_masuk" /></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kemudahan Asas :</td>
                        <td class="input1"><s:textarea name="kemudahan_asas" rows="5" cols="50" /></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Pembangunan kawasan sekeliling (lingkungan 1.0km) :</td>
                        <td class="input1"><s:radio name="radio_" id="radio_" value="Ada"/> Ada
                            <s:radio name="radio_" id="radio_" value="Tiada"/> Tiada
                        </td>
                    </tr>
                </table>
                <div class="content"  align="left">
                    Klik butang tambah untuk masukkan maklumat lot-lot sempadan
                </div>
                
                         <div class="content"  align="center">
                          <a href="">Pilih Semua</a> | <a href="">Kosongkan Pilihan</a><br>

                        <display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Pilih">
                                <s:checkbox name="" id="" class="checkbox" />
                            </display:column>
                            <display:column title="Bil"></display:column>
                            <display:column title="Arah"></display:column>
                            <display:column title="No.Lot/Plot/PT"></display:column>
                            <display:column title="Bandar/Pekan/Mukim"></display:column>
                            <display:column title="Status"></display:column>
                            <display:column title="Kategori Kegunaan Tanah"></display:column>
                            <display:column title="Guna Tanah Semasa"></display:column>
                        </display:table>
</div>
                        <div class="content" align="right">
                    <table>
                        <tr>
                            <td>
                                <s:submit name="" value="Tambah" class="btn"/>
                                <s:submit name="" value="Hapus" class="btn"/>
                            </td>
                        </tr>
                    </table>
                        </div>
                    
                    <table>
                    <tr>
                        <td class="rowlabel1">Laporan Siasatan / Pelanggaran Syarat yang berlaku:</td>
                       <td class="input1"><s:textarea name="laporan" rows="5" cols="50" /></td>
                    </tr>
                </table>

            </div>
        </fieldset>
    </div>
    <div class="content" align="right">
        <table>
            <tr>
                <td>
                    <s:reset  name="reset" value="Isi Semula" class="btn"/>
                    <s:submit name="dokumenlaporan" value="Simpan" class="btn"/>
                    <s:submit name="senaraitugasan" value="Keluar" class="btn"/>
                </td>
            </tr>
        </table>

    </div>                   
</s:form>