<%-- 
    Document   : kodSekatanDetails
    Created on : Oct 31, 2011, 10:47:40 AM
    Author     : NageswaraRao
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type = "text/javascript">
    function searchKodSekatan(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KodSekatanDetailsActionBean">
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="5">
                    <tr>
                        <td>Membatalkan Sekatan Kepentingan Daripada <b> ${actionBean.hakmilik.sekatanKepentingan.sekatan}</b> </td>
                    </tr>
                    <tr>
                        <td>Kepada</td>
                    </tr>
                    <tr>
                        <td> <s:textarea name="kodSekatanKepentinganBaru12" readonly="readonly" id="sekatan1" rows="5" cols="120" value="${actionBean.hp.sekatanKepentinganBaru.kod} - ${actionBean.hp.sekatanKepentinganBaru.sekatan}" /> </td>
                    </tr>
                    <tr>
                        <td><s:hidden name="sekatanBaru1"  id="kodSktn1"  value="${actionBean.hp.sekatanKepentinganBaru.kod}" />
                                <s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan(1)" /> </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td>
                    </tr>
                </table>
                </div>
        </fieldset>
    </div>
    </s:form>
