<%-- 
    Document   : BorangO
    Created on : 08-Apr-2010, 13:56:55
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanBorang">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Perakuan Segera
                    </legend>
                    <div class="content" align="left">

                        <table align="left" width="100%">
                            <tr>
                                <td align="left" width="30%" rowspan="1">ID Permohonan Terdahulu :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                        </table>
                            <p>
                            <label>&nbsp;</label>
                            <s:button name="savePengambilanInfo" value="Hantar" class="btn" onclick=""/>
                            </p>
                        <br>
                    </div>
                </fieldset>
            </div>
                <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Deposit (RM)
                    </legend>
                    <div class="content" align="left">
                        <table align="left" width="100%">
                            <tr>
                                <td align="left" width="30%" rowspan="1">Deposit (RM) :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                        </table>
                            <p>
                            <label>&nbsp;</label>
                            <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick=""/>
                            </p>
                        <br>
                    </div>
                </fieldset>
            </div>
</s:form>

