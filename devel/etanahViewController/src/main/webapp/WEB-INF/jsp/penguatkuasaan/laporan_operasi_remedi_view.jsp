<%-- 
    Document   : laporan_operasi_remedi_2
    Created on : 25-Jan-2010, 10:43:58
    Author     : nurshahida.radzi
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
                Laporan Operasi
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Tarikh Operasi :</td>
                        <td class="input1"></td>
                    </tr>
                </table>
                <fieldset class="aras2">
                    <legend>
                        Maklumat Agensi Yang Terlibat
                    </legend>
                    <div class="content" align="center">

                        <display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil"></display:column>
                            <display:column title="Agensi Yang Terlibat"></display:column>
                            <display:column title="Alamat"></display:column>
                            <display:column title="Catatan"></display:column>
                        </display:table>
                    </div>
                </fieldset>
                 <br>
                   <fieldset class="aras2">
                    <legend>
                        Maklumat Kos Operasi
                    </legend>
                      <div class="content" align="center">

                        <display:table class="tablecloth" name="" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil"></display:column>
                            <display:column title="Perkara"></display:column>
                            <display:column title="Kuantiti"></display:column>
                            <display:column title="Jumlah Bayaran"></display:column>
                            <display:column title="Catatan"></display:column>
                        </display:table>
                    </div>
                </fieldset>                      
                <table>
                    <tr>
                        <td class="rowlabel1">Jumlah Kos Operasi (RM) :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Laporan Operasi :</td>
                        <td class="input1"></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>
