<%-- 
    Document   : maklumat_agensi
    Created on : Feb 22, 2010, 10:57:14 AM
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
                Maklumat Agensi Yang Terlibat
            </legend>
            <div class="content" align="left">
                Klik butang tambah untuk masukkan maklumat agensi yang terlibat
            </div>
            <div class="content" >
                <p>
                        <label>Tarikh Operasi :</label>
                        <s:text name="tarikhOperasi" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhOp"/>&nbsp;
                    </p>
                     <p>
                        <label>Lokasi Operasi :</label>
                         <s:textarea name="operasiPenguatkuasaan.lokasi" rows="5" cols="50" />&nbsp;
                    </p>
                    <p>
                        <label>Laporan Operasi :</label>
                         <s:textarea name="operasiPenguatkuasaan.catatan" rows="5" cols="50" />&nbsp;
                    </p>
                
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
        </fieldset>
    </div>

</s:form>