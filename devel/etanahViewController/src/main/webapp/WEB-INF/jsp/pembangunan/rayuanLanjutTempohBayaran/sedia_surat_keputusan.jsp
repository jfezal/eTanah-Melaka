<%-- 
    Document   : sedia_surat_keputusan
    Created on : Mar 4, 2010, 4:13:45 PM
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

<s:form beanclass="etanah.view.stripes.pembangunan.KertasRingkasanActionBean">
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Surat Makluman Keputusan
                    </legend>
                    <div class="content" align="center">
                        <table border="0" width="80%">
                            <tr><td><p><label><font color="black">Keputusan : </font></label>
                               <s:textarea name="keputusan" rows="1" cols="20"/></p></td></tr><br>
                            <tr><td><p><label><font color="black">Dokumen Yang Perlu Dilampirkan : </font></label>
                               <s:textarea name="dokumen" rows="5" cols="30"/></p></td></tr><br>
                        </table>
                    </div>
                </fieldset>
           </div>
                               <p align="center">
                                <s:button name="suratKeputusan" value="Jana Surat" class="longbtn"/>&nbsp;
                            </p>
</s:form>
