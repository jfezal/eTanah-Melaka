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

if (document.form1.keputusan[0].checked==true) {

document.form1.tindakan[0].disabled = true;
document.form1.tindakan[1].disabled = true;
document.form1.ulasan.disabled = true;
document.form1.arahan.disabled = true;

} else if (document.form1.keputusan[1].checked==true) {

document.form1.tindakan[0].disabled = false;
document.form1.tindakan[1].disabled = false;
document.form1.ulasan.disabled = true;
document.form1.arahan.disabled = true;

}
else if (document.form1.keputusan[2].checked==true) {

document.form1.tindakan[0].disabled = true;
document.form1.tindakan[1].disabled = true;
document.form1.ulasan.disabled = false;
document.form1.arahan.disabled = false;
document.form1.ulasan.focus();
}
}
</script>
<s:form name ="form1" beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Keputusan PPTD
            </legend>
            <br>
            <table>
                <tr>
                    <td class="rowlabel1">Keputusan :</td>
                    <td class="input1">
                        <s:radio name="keputusan" id="keputusan" onclick="updateradio();" value="Tidak Setuju"/> Tidak Setuju
                        <s:radio name="keputusan" id="keputusan" onclick="updateradio()" value="Rujuk Mahkamah"/> Rujuk Mahkamah
                         <s:radio name="keputusan" id="keputusan" onclick="updateradio()" value="Setuju"/> Setuju
                    </td>
                </tr>
                 <tr>
                    <td class="rowlabel1">Tindakan :</td>
                    <td class="input1">
                        <s:radio name="tindakan" id="tindakan" value="Dakwa" disabled="true" /> Dakwa
                        <s:radio name="tindakan" id="tindakan" value="Kompaun" disabled="true" /> Kompoun
                        
                    </td>
                </tr>
                <tr>
                    <td class="rowlabel1">Ulasan :</td>
                    <td class="input1"><s:textarea name="ulasan" id="ulasan" rows="5" cols="50" disabled="true" /></td>
                </tr>
                <tr>
                    <td class="rowlabel1">Arahan :</td>
                    <td class="input1"><s:textarea name="arahan" id="arahan" rows="5" cols="50" disabled="true" /></td>
                </tr>
            </table>
        </fieldset>
  </div>
</s:form>