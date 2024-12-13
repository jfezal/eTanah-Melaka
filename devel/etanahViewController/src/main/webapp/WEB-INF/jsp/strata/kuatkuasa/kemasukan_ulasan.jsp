<%-- 
    Document   : kemasukan_ulasan
    Created on : Nov 3, 2010, 4:08:35 PM
    Author     : Srinu 
    edited : July 7, 2011 by zadhirul.farihim
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript">
    function test(f) {
        $(f).clearForm();
    }
</script>
<s:form name="form1" id="form1" beanclass="etanah.view.strata.LaporanSiasatKuatkuasaStrataActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Kemasukan Ulasan</legend>
            <div class="content" align="left">
                <table>
                    <tr>
                        <td valign="top">
                            <b>Ulasan :</b>
                        </td>
                        <td>
                            <s:textarea  rows="5" cols="100" name="ulasan" id="ulasan" class="normal_text"></s:textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><center><s:button name="simpanUlasan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/>
                        <s:button  name="isiSemulaUlasan" value="Isi Semula" class="btn" onclick="doSubmit(this.form,this.name,'page_div')"/></center></td>
                    </tr>
                    <br/>
                </table>
            </div>
        </fieldset>
    </div>
</s:form>