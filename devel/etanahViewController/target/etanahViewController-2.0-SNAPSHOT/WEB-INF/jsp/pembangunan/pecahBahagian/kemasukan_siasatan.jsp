<%-- 
    Document   : kemasukan_siasatan
    Created on : Jan 4, 2010, 12:09:19 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Kemasukan Bantahan</title>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pembangunan.Notis2AActionBean">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Siasatan
                    </legend>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr>
                                <td><p><label><font color="black">Tempat Siasatan :</font></label>
                                    <s:text name="tmptSiasatan" size="40"/></p></td>
                            </tr>
                            <tr>
                                <td><p><label><font color="black">Hari :</font></label>
                                    <s:text name="hari" size="40"/></p></td>
                            </tr>
                            <tr>
                                <td><p><label><font color="black">Tarikh :</font></label>
                                    <s:text name="tarikh" size="40"/></p></td>
                            </tr>
                            <tr>
                                <td><p><label><font color="black">Masa :</font></label>
                                    <s:text name="masa" size="40"/></p></td>
                            </tr>
                            <tr>
                                <td><p><label><font color="black">Perkara :</font></label>
                                    <s:textarea name="perkara" rows="8" cols="50"/></p></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Perakuan
                    </legend>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr>
                                <td><p><label><font color="black">Tarikh Notis :</font></label>
                                    <s:text name="tarikhNotis" size="30"/></p></td>
                            </tr>
                            <tr>
                                <td><p><label><font color="black">Ditandatangani oleh :</font></label>
                                    <s:select name="jawatan">
                                                <s:option>Pilih..</s:option>
                                                <s:option>Pengarah Tanah dan Galian</s:option>
                                                <s:option>Pendaftar</s:option>
                                                <s:option>Pentadbir Tanah dan Daerah</s:option>
                                                <%--<s:options-collection collection="" value="" label=""/>--%>
                                                </s:select></p></td>
                            </tr>
                            <tr>
                                <td><p><label><font color="black">Nama :</font></label>
                                    <s:text name="ttNotis"/></p></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Siasatan
                    </legend>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr>
                                <td><p><label><font color="black">Butiran Siasatan :</font></label>
                                    <s:textarea name="butiran" rows="8" cols="50"/></p></td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>

            <p align="center">
                <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
            </p>
        </s:form>
    </body>
</html>
