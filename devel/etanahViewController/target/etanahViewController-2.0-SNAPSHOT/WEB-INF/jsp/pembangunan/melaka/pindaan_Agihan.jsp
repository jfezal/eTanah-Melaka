<%-- 
    Document   : pindaan_Agihan
    Created on : Jul 15, 2010, 3:59:20 PM
    Author     : NageswaraRao
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<%--<s:useActionBean beanclass="etanah.view.stripes.pembangunan.melaka.PindaanAgihanActionBean" var="penggunaperanan"/>--%>
<s:form beanclass="etanah.view.stripes.pembangunan.PindaanAgihanActionBean">
    <s:messages/>
    <s:errors/>


    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Pindaan  </legend>
            <div class="content" align="center">
                <table width="80%" align="center"  cellspacing="10" border="0">
                    <tr>
                        <td> <b>Status</b> </td>
                        <td>: &nbsp; Kemaskini/Pindaan </td>
                    </tr>
                    <tr>
                        <td><b> Arahan </b> </td>
                        <td><s:textarea rows="5" cols="130" name="arahan" /></td>
                    </tr>
                    <tr><td align="center" colspan="2">
                            <s:button name="simpan" id="save" value=" Simpan " class="btn"/>
                        </td></tr>       
                </table>
            </div>
        </fieldset>
    </div><br><br>
    <hr><br><br>

    <div class="subtitle" >
        <fieldset class="aras1" >
            <legend>
                Agihan Tugas
            </legend>
            <table width="85%" align="right">
                <tr><td>
                        <s:select name="pengguna.idPengguna" style="width:300px;">
                            <s:option value="">-- Sila Pilih --</s:option>
                            <s:option value="test1"> Test1 </s:option>
                            <s:option value="test2"> Test2 </s:option>
                            <s:option value="test3"> Test3 </s:option>
                            <s:option value="test4"> Test4 </s:option>
                            <s:option value="test5"> Test5 </s:option>

                            <%-- <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama"/>--%>
                        </s:select> &nbsp;&nbsp;
                        <s:submit name="agihPT" value="Agih" class="btn"/>
                    </td></tr>
            </table>
        </fieldset>
    </div>
</s:form>