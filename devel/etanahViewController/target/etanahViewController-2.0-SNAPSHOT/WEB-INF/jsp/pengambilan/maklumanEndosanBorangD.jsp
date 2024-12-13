<%--
    Document   : endosanBorangD
    Created on : Mac 1, 2011, 9:45:56 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumanEndosanBorangDActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
          <legend>Warta Borang D</legend><br />
          <font color="black">Maklumat Tanah</font>
            <div class="content" align="left">
                <table border="0" width="80%">
                    <tr><td align="left" width="30%"><label>No perserahan :</label></td>
                        <td><font style="text-transform: uppercase"><s:text name="noPerserahan" size="20" /></font></td>
                    </tr>

                    <tr><td align="left" width="30%"><label>Tarikh perserahan :</label></td>
                        <td><font style="text-transform: uppercase"><s:text name="tarikhPerserahan" size="20" /></font></td>
                    </tr>
                    <tr><td align="left" width="30%"><label>No WRNS :</label></td>
                       <td><font style="text-transform: uppercase"><s:text name="noWRNS" size="20" /></font></td>
                    </tr>
                    <tr><td align="left" width="30%"><label>No Fail :</label></td>
                        <td><font style="text-transform: uppercase"><s:text name="noFail" size="20" /></font></td>
                    </tr>
                </table><br />
            </div>
        </fieldset>
            <p align="center">
             <s:button class="btn" value="Simpan" name="simpan" />
            </p>
    </div>
</s:form>

