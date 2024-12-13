<%-- 
    Document   : bukti_penyampaian_notis
    Created on : Feb 22, 2010, 9:36:05 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function test(f) {
        $(f).clearForm();
    }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatNotisActionBean">
  <s:messages /><div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Bukti Penyampaian Notis
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">No.Fail :</td>
                        <td class="input1">${actionBean.permohonan.idPermohonan}
                       <s:hidden name="noRujukan" value="${actionBean.permohonan.idPermohonan}"/></td>&nbsp;
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh Penyampaian :</td>
                          <s:text name="tarikhDihantar" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhDihantar"/>&nbsp;

                    </tr>
                    </table>
            </div>
        </fieldset>
                               <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <p align="right">
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanBukti" value="Simpan"/>
        </p>
    </div>
</s:form>