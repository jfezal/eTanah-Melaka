<%-- 
    Document   : charting_daftar_hakmilik_sambungan
    Created on : Dec 31, 2009, 3:11:30 PM
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
        <title>Charting Selepas Pendaftaran Hakmilik Sambungan</title>
    </head>
    <body>
      <stripes:form beanclass="etanah.view.stripes.pembangunan.PecahSempadanActionBean">
          <%-- <jsp:include page="/WEB-INF/jsp/pembangunan/common/incMaklumat_permohonan.jsp"/>
           <jsp:include page="/WEB-INF/jsp/pembangunan/common/incMaklumat_tanah.jsp"/>--%>
           <div class="subtitle">
               <fieldset class="aras1">
                   <legend>Resit Bayaran</legend>
                   <div class="content" align="center">
                       <br>
                       <p>Klik butang 'Resit Bayaran' untuk memaparkan resit bayaran</p>
                       <br>
                       <table>
                           <tr>
                               <td>Bayaran Upah Ukur :</td>
                               <td><s:text name="upahUkur" size="40"/></td>
                           </tr>
                           <tr>
                               <td>Bayaran Pelan :</td>
                               <td><s:text name="bayaranPelan" size="40"/></td>
                           </tr>
                           <tr>
                               <td>Bayaran :</td>
                               <td><s:text name="bayaran" size="40"/></td>
                           </tr>
                           <tr>
                               <td> &nbsp;</td>
                               <td><s:button name="resitByran" class="longbtn" value="Resit Bayaran"/></td>
                           </tr>
                       </table>
                   </div>
               </fieldset>
           </div>
           <%--display bila user klik button charting kelulusan--%>
           <div class="subtitle">
               <fieldset class="aras1">
                   <legend>Keputusan Charting Kelulusan/Penolakan</legend>
                   <div class="content" align="center">
                       <table>
                           <tr>
                               <td> &nbsp;</td>
                               <td><s:radio name="rad1" value="lulus"/>Lulus &nbsp;
                                   <s:radio name="rad1" value="tolak"/>Tolak &nbsp;
                               </td>
                           </tr>
                           <tr>
                               <td>Ulasan :</td>
                               <td><s:textarea cols="87" rows="8" name="ulasan"/></td>
                           </tr>
                       </table>
                   </div>
               </fieldset>
           </div>
           <%--end--%>
           <p align="center">
                <s:button name="charting" value="Charting" class="btn"/>&nbsp;
                <s:button name="simpan" value="Simpan" class="btn"/>&nbsp;
           </p>
        </stripes:form>
    </body>
</html>
