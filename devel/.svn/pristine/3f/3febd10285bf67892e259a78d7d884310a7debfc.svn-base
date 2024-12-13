<%-- 
    Document   : sedia_cetak_notis_borang2A
    Created on : Dec 31, 2009, 2:41:17 PM
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

<s:form beanclass="etanah.view.stripes.pembangunan.Notis2AActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Siasatan
            </legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr>
                        <td><p><label><font color="#003194">Tempat Siasatan :</font></label>
                            <s:text name="tmptSiasatan" size="40"/></p></td>
                    </tr>                    
                    <tr>
                        <td><p><label><font color="#003194">Tarikh :</font></label>
                            <s:text name="tarikh" size="40" class="datepicker"/></p></td>
                    </tr>
                    <tr>
                        <td><p><label><font color="#003194">Masa :</font></label>
                        <s:select name="jam" style="width:61px" id="jam">
                            <s:option value="00">Jam</s:option>
                            <s:option value="01">01</s:option>
                            <s:option value="02">02</s:option>
                            <s:option value="03">03</s:option>
                            <s:option value="04">04</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="06">06</s:option>
                            <s:option value="07">07</s:option>
                            <s:option value="08">08</s:option>
                            <s:option value="09">09</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="11">11</s:option>
                            <s:option value="12">12</s:option>
                        </s:select>
                        <s:select name="minit" style="width:72px" id="minit">
                            <s:option value="00">Minit</s:option>
                            <s:option value="00">00</s:option>
                            <s:option value="05">05</s:option>
                            <s:option value="10">10</s:option>
                            <s:option value="15">15</s:option>
                            <s:option value="20">20</s:option>
                            <s:option value="25">25</s:option>
                            <s:option value="30">30</s:option>
                            <s:option value="35">35</s:option>
                            <s:option value="40">40</s:option>
                            <s:option value="45">45</s:option>
                            <s:option value="50">50</s:option>
                            <s:option value="55">55</s:option>
                        </s:select>
                        <s:select name="pagiPetang" style="width:80px" id="pagiPetang">
                            <s:option value="00">Pilih</s:option>
                            <s:option value="AM">Pagi</s:option>
                            <s:option value="PM">Petang</s:option>
                        </s:select></p></td>
                    </tr>                    
                    <tr>
                        <td><p><label><font color="#003194">Perkara :</font></label>
                            <s:textarea name="perkara" rows="8" cols="50"/></p></td>
                    </tr>
                </table>
            </div>
        </fieldset>
   </div>
   <p align="center">        
        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
    </p>
   <%--<div class="subtitle">
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
                        <td><p><label><font color="black">Ditandatangani oleh : </font></label>
                            <s:select name="jawatan">
                                        <s:option>Pilih..</s:option>
                                        <s:option>Pengarah Tanah dan Galian</s:option>
                                        <s:option>Pendaftar</s:option>
                                        <s:option>Pentadbir Tanah dan Daerah</s:option>
                                        <s:options-collection collection="" value="" label=""/>
                                        </s:select></p></td>
                    </tr>
                    <tr>
                        <td><p><label><font color="black">Nama : </font></label>
                            <s:text name="ttNotis"/></p></td>
                    </tr>
                </table>
            </div>
        </fieldset>
   </div>--%>
   <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tambahan
            </legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr>
                        <td><p><label><font color="black">Nama Penerima Notis :</font></label>
                            <s:text name="penerimaNotis" size="40"/></p></td>
                    </tr>
                    <tr>
                        <td><p><label><font color="black">Alamat :</font></label>
                            <s:textarea name="alamat" cols="50" rows="4"/></p></td>
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
                        <td><p><label><font color="black">Ditandatangani oleh : </font></label>
                            <s:select name="jawatan">
                                        <s:option>Pilih..</s:option>
                                        <s:option>Pengarah Tanah dan Galian</s:option>
                                        <s:option>Pendaftar</s:option>
                                        <s:option>Pentadbir Tanah dan Daerah</s:option>
                                        <s:options-collection collection="" value="" label=""/>
                                        </s:select></p></td>
                    </tr>
                    <tr>
                        <td><p><label><font color="black">Nama : </font></label>
                            <s:text name="ttNotis"/></p></td>
                    </tr>
                </table>
            </div>
        </fieldset>
   </div>--%>
    
</s:form>
