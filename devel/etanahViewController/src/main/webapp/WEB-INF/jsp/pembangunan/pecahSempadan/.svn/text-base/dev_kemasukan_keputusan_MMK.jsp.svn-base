<%-- 
    Document   : kemasukan_keputusan_MMKN
    Created on : Nov 18, 2009, 10:58:51 AM
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


<stripes:form beanclass="etanah.view.stripes.pembangunan.KeputusanMMKActionBean">
    <s:messages/>
    <s:errors/>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Majlis Mesyuarat Negeri Sembilan
            </legend>
            <div class="content" align="center">
                <table>
                     <tr>
                        <td>Keputusan MMK: </td>
                        <td><s:radio name="fasaMohon.keputusan.kod" value="L"/>Lulus &nbsp;
                            <s:radio name="fasaMohon.keputusan.kod" value="T"/>Tolak &nbsp;
                            <s:radio name="fasaMohon.keputusan.kod" value="Tangguh"/>Tangguh &nbsp;</td>
                    </tr>
                    <tr>
                        <td>Ulasan :</td>
                        <td><s:textarea cols="87" rows="8" name="fasaMohon.ulasan"/></td>
                    </tr>
                </table>
            </div>
        </fieldset>
   </div>
  <p align="center">
       <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
  </p>
</stripes:form>
    
