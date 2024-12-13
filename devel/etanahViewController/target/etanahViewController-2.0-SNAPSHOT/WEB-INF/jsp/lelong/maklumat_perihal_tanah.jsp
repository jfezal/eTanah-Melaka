<%-- 
    Document   : maklumat_perihal_tanah
    Created on : Mar 3, 2010, 12:01:22 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:form action="">
 <div class="subtitle">
        <fieldset class="aras1">
            <legend>
               Maklumat Lokasi dan Perihal Tanah
            </legend>
                     <div class="content">
                <p>
                   <label>Lokasi: </label>
                   <s:text name=""/>&nbsp;
                </p>

                <p>
                   <label>Alamat: </label>
                   <s:text name=""/>&nbsp;
                </p>
                <p>
                   <label>&nbsp;</label>
                   <s:text name=""/>&nbsp;
                </p>

                <p>
                   <label>Poskod: </label>
                   <s:text name=""/>&nbsp;
                </p>

                <p>
                     <label>Bandar:</label>
                     <s:select name="">
                         <s:option value="">--Sila Pilih--</s:option>
                     </s:select>
                 </p>

                 <p>
                     <label>Negeri:</label>
                     <s:select name="">
                         <s:option value="">--Sila Pilih--</s:option>
                     </s:select>
                 </p>
                <p>
                   <label>Luas Kawasan: </label>
                   <s:text name=""/>&nbsp;
                </p>
                  <p>
                      <label>Perihal Tanah :</label>
                      <s:textarea name="" rows="5" cols="50" /> &nbsp;
                  </p>
            </div>
     </fieldset>
    </div>
 </s:form>