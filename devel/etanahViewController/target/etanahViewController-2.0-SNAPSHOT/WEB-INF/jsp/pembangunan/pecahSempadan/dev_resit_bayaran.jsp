<%-- 
    Document   : dev_resit_bayaran
    Created on : Feb 22, 2010, 3:08:18 PM
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

<s:form beanclass="etanah.view.stripes.pembangunan.PecahSempadanActionBean">
    <div class="subtitle">
           <fieldset class="aras1">
               <legend>Notis 5A/Notis 7G</legend>
               <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr><td><p><label><font color="black">Cukai Tahun Pertama :</font></label>
                               <s:textarea name="cukaiPertama" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Premium :</font></label>
                               <s:textarea name="premium" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Upah Ukur/Tanda Sempadan :</font></label>
                               <s:textarea name="upahUkur" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Kos Sumbangan Infrastruktur :</font></label>
                               <s:textarea name="kosInfrastruktur" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Penyediaan Pelan Suratan Hakmilik Tetap :</font></label>
                               <s:textarea name="pelanSuratanTetap" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Pendaftaran Suratan Hakmilik Tetap :</font></label>
                               <s:textarea name="suratanTetap" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Penyediaan Pelan Suratan Hakmilik Sementara :</font></label>
                               <s:textarea name="pelanSuratanSementara" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Pendaftaran Suratan Hakmilik Sementara :</font></label>
                               <s:textarea name="suratanSementara" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Notis :</font></label>
                               <s:textarea name="notis" rows="1" cols="50"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Jumlah :</font></label>
                               <s:textarea name="jumlah" rows="1" cols="50"/></p></td></tr><br>
                        </table>
                    </div>
           </fieldset>
       </div>
</s:form>

