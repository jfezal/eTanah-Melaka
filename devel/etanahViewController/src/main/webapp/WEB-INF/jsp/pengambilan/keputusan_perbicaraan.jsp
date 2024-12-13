<%-- 
    Document   : keputusan_perbicaraan
    Created on : 08-Apr-2010, 11:12:13
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
                        Keputusan
                    </legend>
                    <div class="content" align="left">
                        <table align="left"  width="100%">
                            <tr>
                                <td align="left" width="30%" rowspan="1">Keputusan Perbicaraan :</td>
                                <td align="left" width="70%"><input type="radio" name="notis" value="hari" id="notis"/>Award
                                                             <input type="radio" name="notis" value="hari" id="notis" />Tangguh</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1">Tawaran Wang Pampasan(RM) :</td>
                                <td align="left" width="70%"><s:text name="idInsert"/></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1">Ulasan :</td>
                                <td align="left" width="70%"><s:textarea name="idInsert" rows="4" cols="35"/></td>
                            </tr>
                        </table>
                            <br>
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
