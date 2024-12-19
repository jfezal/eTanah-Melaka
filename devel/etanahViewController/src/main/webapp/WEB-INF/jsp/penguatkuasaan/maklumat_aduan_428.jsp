<%-- 
    Document   : borang_aduan
    Created on : Jan 18, 2010, 4:02:47 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
 <div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Aduan
        </legend>
        <div class="content" align="center">
           <table>
                    <tr>
                        <td class="rowlabel1">ID Aduan :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Tarikh :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Daerah :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Cara Aduan :</td>
                        <td class="input1">
                            <s:select name="">
                                <s:option>Email</s:option>
                                <s:option>Surat</s:option>
                                <s:option>Telefon</s:option>
                                <s:option>Faks</s:option>
                                <s:option>Pemantauan</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Peruntukan Undang-undang :</td>
                        <td class="input1"></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Ringkasan Aduan :</td>
                        <td class="input1"><s:textarea name="" rows="5" cols="50"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Bentuk Halangan :</td>
                        <td class="input1"><s:textarea name="" rows="5" cols="50"/></td>
                    </tr>
                </table>

                <br>
            </div>
        </fieldset>
    </div>
    </s:form>