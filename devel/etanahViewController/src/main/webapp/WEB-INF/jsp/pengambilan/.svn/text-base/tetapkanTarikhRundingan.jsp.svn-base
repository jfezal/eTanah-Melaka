<%-- 
    Document   : tetapkanTarikhRundingan
    Created on : Jun 1, 2010, 11:57:36 AM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

 <s:form beanclass="etanah.view.stripes.pengambilan.pendudukanSementaraActionBean">
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Perbicaraan/Perundingan
        </legend>
            <div class="content" align="center">
                <table align="left">
                     <p>
                        <label> Tarikh :</label>
                     <div align="left"><s:text name="tarikh" class="datepicker" id="tarikh"/></div>

                     <p>
                         <label>Lokasi :</label>
                     <div align="left"><s:textarea name="lokasi" rows="3" cols="50" /></div>
                    
                    <tr>
                    <td class="rowlabel1"><label>Waktu :</label></td>
                        <td class="input1">
                        <table>
                            <tr>
                                <div align="left"><td><s:text name="jam" size="2" />: </td>
                                    <td><s:text name="minit" size="2" />: </td>
                                    <td>
                                        <s:select name="masa" >
                                            <s:option>AM</s:option>
                                            <s:option>PM</s:option>
                                        </s:select>
                                    </td>
                                </div>
                            </tr>
                        </table>
                        </td>
                    </tr>
                     <%--<p>
                        <label> Waktu :</label>
                        <s:text name="t" size="20"/>
                     </p>--%>
                </table><br /><br />
            <div align="center">
            <table align="center">
                    <p>
                        <s:reset name="" value="Isi Semula" class="btn"/>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
            </table>
            </div>
            </div>
        </fieldset>
    </div>
</s:form>