<%-- 
    Document   : akuan_terima
    Created on : 08-Apr-2010, 10:55:03
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanSementaraActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Akuan Terima Bayaran/Cek/Bank Draf/Resit
                    </legend>
                    <div class="content" align="left">

                        <table align="left" width="100%">
                             <tr>
                                <td align="left" width="30%" rowspan="1">Urusan/Tujuan Permohonan :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1">Amaun(RM) :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1">No. Cek/Bank Draf/Resit :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Tarikh Cek/Bank Draf/Resit :</td>
                                <td align="left" class="input1" width="70%"><s:text name="fromDate" id="datepicker" class="datepicker"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Bank Cek/Bank Draf/Resit :</td>
                                <td align="left" class="input1" width="70%"><s:text name="fromDate" id="datepicker" class="datepicker"/></td>
                            </tr>

                        </table>
                            <br>
                            <br>
                            <p>
                            <label>&nbsp;</label>
                            <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick=""/>
                            </p>


                    </div>
                </fieldset>
            </div>

        </s:form>