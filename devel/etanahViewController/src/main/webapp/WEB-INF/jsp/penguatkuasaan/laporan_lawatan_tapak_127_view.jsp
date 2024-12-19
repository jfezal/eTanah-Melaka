<%-- 
    Document   : lawatan_tapak
    Created on : Jan 19, 2010, 2:32:39 PM
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
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Lokasi Tanah :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Jarak Dari Bandar/Pekan :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Mercu Tanda Terdekat :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Jalan Keluar Masuk :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kemudahan Asas :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Zon Pembangunan kawasan sekeliling (lingkungan 1.0km) :</td>
                        <td class="input1"></td>
                    </tr>
                </table>
            </div>
           <div class="subtitle">
        <fieldset class="aras2">
            <legend>
                Maklumat Lot-lot Sempadan
            </legend>
                  <div class="content" align="center">
                        <display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil"></display:column>
                            <display:column title="Arah"></display:column>
                            <display:column title="No.Lot/Plot/PT"></display:column>
                            <display:column title="Bandar/Pekan/Mukim"></display:column>
                            <display:column title="Status"></display:column>
                            <display:column title="Kategori Kegunaan Tanah"></display:column>
                            <display:column title="Guna Tanah Semasa"></display:column>
                        </display:table>
                  </div>
                     
        </fieldset>
           </div>
                    
                    <table>
                    <tr>
                        <td class="rowlabel1">Laporan Siasatan / :</td>
                       <td class="input1"></td>
                    </tr>
                </table>

            
        </fieldset>
    </div>
    <div class="content" align="right">
        <table>
            <tr>
                <td>
                   <s:submit name="dokumenlaporan" value="Simpan" class="btn"/>
                    <s:submit name="senaraitugasan" value="Keluar" class="btn"/>
                </td>
            </tr>
        </table>

    </div>
</s:form>