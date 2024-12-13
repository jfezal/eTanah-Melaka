<%-- 
    Document   : resultPPTD
    Created on : Feb 11, 2010, 10:34:58 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function updateradio() {

        if (document.form1.laporan[0].checked==true) {

           document.form1.tindakan[0].disabled = false;
            document.form1.tindakan[1].disabled = false;

           if (document.form1.tindakan[0].checked==true){
                document.form1.ulasan.disabled = false;
                document.form1.kompaun.disabled = true;
                document.form1.ulasan.focus();
            }

            else if (document.form1.tindakan[1].checked==true){
                document.form1.ulasan.disabled = true;
                document.form1.kompaun.disabled = false;
                document.form1.kompaun.focus();
            }
        } else if (document.form1.laporan[1].checked==true) {

            document.form1.tindakan[0].disabled = true;
            document.form1.tindakan[1].disabled = true;
            document.form1.ulasan.disabled = true;
            document.form1.kompaun.disabled = true;

        }

    }
</script>
<s:form name="form1" beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Perakuan UPP PTG
            </legend>
            <br>
            <table>
                <tr>
                    <td class="rowlabel1">Laporan Lengkap :</td>
                    <td class="input1">
                        <s:radio name="laporan" id="laporan" value="" onclick="updateradio();" /> Ya
                        <s:radio name="laporan" id="laporan" value="" onclick="updateradio();" /> Tidak
                    </td>
                </tr>
                <tr>
                    <td class="rowlabel1">Tindakan :</td>
                    <td class="input1"><s:radio name="tindakan" id="tindakan" value="" disabled="true" onclick="updateradio();"/> Dakwa
                        <s:radio name="tindakan" id="tindakan" value="" disabled="true" onclick="updateradio();"/> Kompoun

                    </td>
                </tr>
                <tr>
                    <td class="rowlabel1">Ulasan :</td>
                    <td class="input1"><s:textarea name="ulasan" id="ulasan" rows="5" cols="50" disabled="true"/></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Nilai Kompaun Dicadangkan:</td>
                    <td class="input1"><s:text name="kompaun" id="kompaun" disabled="true"/></td>
                </tr>
            </table>
        </fieldset>
    </div>
</s:form>